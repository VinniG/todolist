package br.com.viniciusgomes.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

// Com a anotação @Data, todos os métodos getter, setter, construtor, toString, equals e hashCode serão gerados automaticamente para a classe.
@Data

// É usada para mapear uma classe Java para uma entidade no banco de dados relacional.
@Entity(name = "tb_users") 

public class UserModel {

    // Indica a chave primária da entidade.
    @Id
    // Indica que a geração do valor da chave primária id deve ser feita usando um gerador chamado "UUID".
    @GeneratedValue(generator = "UUID")
    private UUID id;

    // É usada para especificar que uma coluna em um banco de dados relacional deve ser única.
    @Column(unique = true)
    private String username;
    private String name;
    private String password;

    // Define automaticamente o valor desse campo para a data e hora de criação da entidade.
    @CreationTimestamp 
    private LocalDateTime createdAt;

}
