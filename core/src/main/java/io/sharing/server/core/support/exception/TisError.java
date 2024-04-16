package io.sharing.server.core.support.exception;

public enum TisError {
    DUPLICATE_RESERVATION("이미 예약된 상품입니다.");

    private final String message;

    TisError(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
