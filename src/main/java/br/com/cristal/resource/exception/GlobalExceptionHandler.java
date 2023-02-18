package br.com.cristal.resource.exception;

import br.com.cristal.domain.dto.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GenericError.class)
    public ResponseEntity<ErrorDTO> genericError(GenericError genericError){
        ErrorDTO errorDTO = ErrorDTO
                .builder()
                .message(genericError.getMessage())
                .build();
        return ResponseEntity.status(genericError.getStatus()).body(errorDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> genericError(MethodArgumentNotValidException methodArgumentNotValidException){
        ErrorDTO errorDTO = ErrorDTO
                .builder()
                .message(methodArgumentNotValidException.getMessage())
                .build();
        return ResponseEntity.status(400).body(errorDTO);
    }
}
