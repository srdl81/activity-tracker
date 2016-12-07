package ams.labs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Format Invalid")
public class InvalidFormatException extends RuntimeException {
    public InvalidFormatException(String s) {
        super(s);
    }
}
