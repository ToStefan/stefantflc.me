package t.stefan.portfolio.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import t.stefan.portfolio.dto.UserDTO;
import t.stefan.portfolio.security.JwtAuthenticationException;
import t.stefan.portfolio.security.JwtAuthenticationResponse;
import t.stefan.portfolio.security.JwtTokenUtil;
import t.stefan.portfolio.security.JwtUser;
import t.stefan.portfolio.service.UserAuthenticationService;

@RestController
@RequestMapping(value = "api/auth")
@CrossOrigin
public class AuthenticationController {

	@Value("${jwt.header}")
	private String tokenHeader;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserAuthenticationService userAuthenticationService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@PostMapping(value = "/sign-in")
	public ResponseEntity<?> authenticateUser(@RequestBody UserDTO userDTO) throws JwtAuthenticationException {

		authenticate(userDTO.getUsername(), userDTO.getPassword());

		final UserDetails userDetails = userAuthenticationService.loadUserByUsername(userDTO.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtAuthenticationResponse(token));
	}

	@RequestMapping(value = "me")
	public JwtUser getAuthenticatedUser(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader).substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		JwtUser user = (JwtUser) userAuthenticationService.loadUserByUsername(username);
		return user;
	}

	private void authenticate(String username, String password) {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (BadCredentialsException e) {
			throw new JwtAuthenticationException("Bad credentials!", e);
		}
	}
}
