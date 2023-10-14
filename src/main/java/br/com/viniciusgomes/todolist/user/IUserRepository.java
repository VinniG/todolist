package br.com.viniciusgomes.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

// Esta é uma interface que estende JpaRepository para realizar operações no banco de dados relacionadas à entidade UserModel.

public interface IUserRepository extends JpaRepository<UserModel, UUID> {
    // A interface IUserRepository é parametrizada com o tipo de entidade (UserModel) e o tipo da chave primária (UUID).

    UserModel findByUsername(String username);
    // Este é um método de consulta personalizado que permite procurar um usuário com base no nome de usuário.

    // Os métodos de consulta são definidos automaticamente pelo Spring Data JPA com base no nome do método.
    // O método findByUsername irá retornar um usuário com o nome de usuário especificado.
}