package ams.labs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Model not found")
public class ModelNotFoundException extends RuntimeException {
    public ModelNotFoundException(String s) {
        super(s);
    }
}
