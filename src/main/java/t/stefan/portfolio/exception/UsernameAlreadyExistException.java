package t.stefan.portfolio.exception;

public class UsernameAlreadyExistException extends RuntimeException {
    public UsernameAlreadyExistException(String username){
        super("User with username: " + username + " already exist.");
    }
}
