package br.com.viniciusgomes.todolist.task;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

// Esta é uma interface que estende JpaRepository para realizar operações no banco de dados relacionadas à entidade TaskModel.

public interface ITaskRepository extends JpaRepository<TaskModel, UUID> {
    // A interface ITaskRepository é parametrizada com o tipo de entidade (TaskModel) e o tipo da chave primária (UUID).

    List<TaskModel> findByIdUser(UUID idUser);
    // Este método de consulta personalizado permite buscar uma lista de tarefas associadas a um determinado ID de usuário.

    TaskModel findByIdAndIdUser(UUID id, UUID idUser);
    // Este método de consulta personalizado permite buscar uma tarefa com base em seu ID e no ID do usuário associado a ela.
}
