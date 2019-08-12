package t.stefan.portfolio.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import t.stefan.portfolio.entity.Role;
import t.stefan.portfolio.entity.RoleName;
import t.stefan.portfolio.exception.UserAlreadyExistException;
import t.stefan.portfolio.repository.RoleRepository;
import t.stefan.portfolio.repository.UserRepository;
import t.stefan.portfolio.security.JwtAuthenticationResponse;
import t.stefan.portfolio.security.JwtTokenUtil;
import t.stefan.portfolio.security.UserAuthenticationService;
import t.stefan.portfolio.security.UserPrincipal;
import t.stefan.portfolio.service.AuthenticationService;
import t.stefan.portfolio.util.Constants;
import t.stefan.portfolio.web.dto.UserDTO;
import t.stefan.portfolio.web.mapper.UserMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserAuthenticationService userAuthService;
    private final JwtTokenUtil jwtTokenUtil;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final MailServiceImpl mailService;
    private final UserMapper userMapper;

    @Override
    public JwtAuthenticationResponse login(UserDTO userDto) {
        authenticate(userDto.getUsername(), userDto.getPassword());
        UserDetails userDetails = userAuthService.loadUserByUsername(userDto.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return new JwtAuthenticationResponse(token);
    }

    @Override
    public UserDTO register(UserDTO userDto) {
        if ((userRepository.existsByUsername(userDto.getUsername())) == true)
            throw new UserAlreadyExistException(userDto.getUsername());
        if((userRepository.existsByEmail(userDto.getEmail())) == true)
            throw new UserAlreadyExistException(userDto.getEmail());

        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName(RoleName.ROLE_GUEST));
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDto.setRoles(roles);
        userDto.setIsEnabled(false);

        UserDTO user = userMapper.toDTO(userRepository.save(userMapper.toEntity(userDto)));
        mailService.userConfirmation(user);
        return user;
    }

    @Override
    public UserPrincipal me(HttpServletRequest request) {
        String token = request.getHeader(Constants.tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        UserPrincipal user = (UserPrincipal) userAuthService.loadUserByUsername(username);
        return user;
    }

    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch(AuthenticationException e){}
    }
}
