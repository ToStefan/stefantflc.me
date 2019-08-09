package t.stefan.portfolio.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import t.stefan.portfolio.security.JwtAuthenticationResponse;
import t.stefan.portfolio.security.UserPrincipal;
import t.stefan.portfolio.service.impl.AuthenticationServiceImpl;
import t.stefan.portfolio.web.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/auth")
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserDTO userDTO) {
        JwtAuthenticationResponse jwt = authenticationService.login(userDTO);
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        UserDTO user = authenticationService.register(userDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/me")
    public UserPrincipal getAuthenticatedUser(HttpServletRequest request) {
        return authenticationService.me(request);
    }
}
