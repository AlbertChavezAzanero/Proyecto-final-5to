package pe.leboulevard.demo.domain.common.exceptions;

public class DuplicateDataException extends RuntimeException {
    public DuplicateDataException(String message) {
        super(message);
    }
}