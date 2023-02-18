package br.com.cristal.resource.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class GenericError extends RuntimeException{
    private final int status;
    private final String message;

    public static GenericError badRequest(String message) {
        return new GenericError(400 , message);
    }

    public static GenericError notFound(String message) {
        return new GenericError(404 , message);
    }
}
