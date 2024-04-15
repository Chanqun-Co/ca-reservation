package io.sharing.server.core.support.exception;

public class TisException extends RuntimeException{

    public TisException(TisError tisError) {
        super(tisError.message());
    }
}
