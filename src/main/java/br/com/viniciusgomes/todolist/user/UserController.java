package br.com.viniciusgomes.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    // O controlador é uma classe que lida com as solicitações relacionadas a usuários.

    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel) {
        // Este método é mapeado para tratamento de solicitações POST na rota "/users".

        var user = this.userRepository.findByUsername(userModel.getUsername());
        // Procura um usuário no repositório com base no nome de usuário fornecido no modelo.

        if (user != null) {
            // Se um usuário com o mesmo nome de usuário já existe, retorna uma resposta de erro.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }

        var passwordHashred = BCrypt.withDefaults()
            .hashToString(12, userModel.getPassword().toCharArray());
        // Usa o algoritmo BCrypt para criar um hash da senha fornecida no modelo.

        userModel.setPassword(passwordHashred);
        // Define a senha no modelo como o hash recém-gerado.

        var userCreated = this.userRepository.save(userModel);
        // Salva o novo usuário no repositório.

        return ResponseEntity.status(HttpStatus.OK).body(userCreated);
        // Retorna uma resposta de sucesso com o usuário criado.
    }
}

