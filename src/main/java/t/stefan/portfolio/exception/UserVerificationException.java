package t.stefan.portfolio.exception;

public class UserVerificationException extends RuntimeException {
    public UserVerificationException(){
        super("Bad credentials or user account not confirmed!");
    }
}
