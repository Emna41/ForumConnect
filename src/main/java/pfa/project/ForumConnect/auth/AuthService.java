package pfa.project.ForumConnect.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pfa.project.ForumConnect.config.JwtService;
import pfa.project.ForumConnect.model.Role;
import pfa.project.ForumConnect.repo.UserRepo;
import pfa.project.ForumConnect.model.user;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepo repo;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager manager;

    public AuthenticationResponse register(RegisterRequest request) {
        System.out.println(request.getName());
        System.out.println(request.getEmail());


        var u = user.builder()
                .name(request.getName())
                .mail(request.getEmail())
                .pwd(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
            repo.save(u);
            var jwtToken= jwtService.generateToken(u);
            var res=AuthenticationResponse.builder().token(jwtToken).build();
        System.out.println("Generated token: " + jwtToken);
        return res;

    }

    public AuthenticationResponse authenticate(AuthenticateRequest request) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()

                )
        );
        var u = repo.findByMail(request.getEmail())
                .orElseThrow();

        var jwtToken= jwtService.generateToken(u);
        var res=AuthenticationResponse.builder().token(jwtToken).build();
        System.out.println("Generated token: " + jwtToken);

        return res;
    }

}
