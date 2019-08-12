package t.stefan.portfolio.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String user){
        super("User with username/e-mail: " + user + " already exist");
    }
}
