package br.com.viniciusgomes.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Esta classe representa uma entidade "TaskModel" que será mapeada em uma tabela no banco de dados.
 * Ela contém propriedades relacionadas a tarefas.
 */
@Data
@Entity(name = "tb_tasks")
public class TaskModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id; // Chave primária da tarefa.

    private String description; // Descrição da tarefa.

    @Column(length = 50)
    private String title; // Título da tarefa, com limite de 50 caracteres.

    private LocalDateTime startAt; // Data de início da tarefa.

    private LocalDateTime endAt; // Data de término da tarefa.

    private String priority; // Prioridade da tarefa.

    private UUID idUser; // ID do usuário associado a esta tarefa.

    @CreationTimestamp
    private LocalDateTime createdAt; // Data e hora de criação da tarefa.

    /**
     * Este método setter é personalizado para o campo 'title'.
     * Ele valida se o título não excede 50 caracteres e, caso contrário, lança uma exceção.
     */
    public void setTitle(String title) throws Exception {
        if (title.length() > 50) {
            throw new Exception("O campo title deve conter no máximo 50 caracteres");
        }
        this.title = title;
    }
}

