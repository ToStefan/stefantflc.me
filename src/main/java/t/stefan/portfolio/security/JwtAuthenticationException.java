package t.stefan.portfolio.security;

public class JwtAuthenticationException extends RuntimeException {

	public JwtAuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}
}
