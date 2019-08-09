package t.stefan.portfolio.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Long id){
        super("Entity with identifier: " + id + " not found!");
    }
}
