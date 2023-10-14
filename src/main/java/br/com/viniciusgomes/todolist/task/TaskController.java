package br.com.viniciusgomes.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.viniciusgomes.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;

    // Este controlador lida com operações relacionadas a tarefas.

    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
        // Este método lida com a criação de uma nova tarefa.

        // Obtém o ID do usuário da solicitação.
        var idUser = request.getAttribute("idUser");
        taskModel.setIdUser((UUID) idUser);

        // Obtém a data e hora atual.
        var currentDate = LocalDateTime.now();

        // Valida se a data de início ou término da tarefa é anterior à data atual.
        if (currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("A data de início / data de término deve ser maior que a data atual");
        }

        // Valida se a data de início da tarefa é posterior à data de término.
        if (taskModel.getStartAt().isAfter(taskModel.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("A data de início deve ser menor que a data de término");
        }

        // Salva a nova tarefa no repositório e retorna uma resposta de sucesso.
        var task = this.taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request) {
        // Este método lida com a obtenção de uma lista de tarefas para um usuário.

        // Obtém o ID do usuário da solicitação.
        var idUser = request.getAttribute("idUser");

        // Obtém uma lista de tarefas associadas ao ID do usuário.
        var tasks = this.taskRepository.findByIdUser((UUID) idUser);
        return tasks;
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id) {
        // Este método lida com a atualização de uma tarefa existente.

        // Obtém a tarefa existente com base no ID.
        var task = this.taskRepository.findById(id).orElse(null);

        // Verifica se a tarefa não foi encontrada.
        if (task == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Tarefa não encontrada");
        }

        // Obtém o ID do usuário da solicitação.
        var idUser = request.getAttribute("idUser");

        // Verifica se o usuário tem permissão para alterar esta tarefa.
        if (!task.getIdUser().equals(idUser)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Usuário não tem permissão para alterar essa tarefa");
        }

        // Copia as propriedades não nulas do modelo da tarefa atual para a tarefa existente e salva a tarefa atualizada.
        Utils.copyNonNullProperties(taskModel, task);
        var taskUpdated = this.taskRepository.save(task);

        // Retorna uma resposta de sucesso.
        return ResponseEntity.ok().body(taskUpdated);
    }
}

