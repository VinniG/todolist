package br.com.viniciusgomes.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.viniciusgomes.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    // Este é um filtro que lida com a autenticação de solicitações relacionadas a tarefas.

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var servletPath = request.getServletPath();

        if (servletPath.startsWith("/tasks/")) {
            // Verifica se a solicitação está relacionada a recursos /tasks.

            // Obter a autenticação (nome de usuário e senha) do cabeçalho "Authorization".
            var authorization = request.getHeader("Authorization");
            var authEncoded = authorization.substring("Basic".length()).trim();
            byte[] authDecode = Base64.getDecoder().decode(authEncoded);
            var authString = new String(authDecode);
            String[] credentials = authString.split(":");
            String username = credentials[0];
            String password = credentials[1];

            // Validar o usuário com base no nome de usuário.
            var user = this.userRepository.findByUsername(username);

            if (user == null) {
                // Se o usuário não for encontrado, retorna um erro de autenticação (401).
                response.sendError(401);
            } else {
                // Valida a senha usando BCrypt.
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());

                if (passwordVerify.verified) {
                    // Se a senha estiver correta, permite que a solicitação prossiga
                    // e define o ID do usuário na solicitação para uso posterior.
                    request.setAttribute("idUser", user.getId());
                    filterChain.doFilter(request, response);
                } else {
                    // Se a senha estiver incorreta, retorna um erro de autenticação (401).
                    response.sendError(401);
                }
            }
        } else {
            // Se a solicitação não está relacionada a recursos /tasks, permite que a solicitação prossiga.
            filterChain.doFilter(request, response);
        }
    }
}
