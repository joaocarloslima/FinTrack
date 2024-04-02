package br.com.fiap.fintrack.validation;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.List;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationErrorHandler {

    record ValidationError(String campo, String mensagem){
        public ValidationError(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public List<ValidationError> handle(MethodArgumentNotValidException exception){
        return exception
                .getFieldErrors()
                .stream()
                .map(ValidationError::new)
                .toList();
    }
    
}
