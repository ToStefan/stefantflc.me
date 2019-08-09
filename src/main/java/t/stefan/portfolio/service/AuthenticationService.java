package t.stefan.portfolio.service;

import t.stefan.portfolio.security.JwtAuthenticationResponse;
import t.stefan.portfolio.security.UserPrincipal;
import t.stefan.portfolio.web.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;

public interface AuthenticationService {

    JwtAuthenticationResponse login(UserDTO userDto);
    UserDTO register(UserDTO userDto);
    UserPrincipal me(HttpServletRequest request);
}
