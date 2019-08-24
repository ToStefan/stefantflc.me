package t.stefan.portfolio.exception;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException(){
        super("Invalid username or password");
    }
}
