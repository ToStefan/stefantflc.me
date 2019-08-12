package t.stefan.portfolio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleEntityNotFound(final EntityNotFoundException exception,
                                                  final HttpServletRequest request) {
        return new ExceptionResponse(exception.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(value = UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse handleUserAlreadyExist(final UserAlreadyExistException exception,
                                                    final HttpServletRequest request) {
        return new ExceptionResponse(exception.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(value = UserVerificationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleUserVerification(final UserVerificationException exception,
                                                    final HttpServletRequest request) {
        return new ExceptionResponse(exception.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleException(final Exception exception,
                                             final HttpServletRequest request) {
        return new ExceptionResponse(exception.getMessage(), request.getRequestURI());
    }
}
