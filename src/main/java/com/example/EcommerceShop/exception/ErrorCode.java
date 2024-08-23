    package com.example.EcommerceShop.exception;

    import org.springframework.http.HttpStatus;
    import org.springframework.http.HttpStatusCode;

    public enum ErrorCode {

        USER_EXISTED(1002, "User existed", HttpStatus.BAD_REQUEST),
        INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
        ;

        ErrorCode(int code, String message, HttpStatusCode statusCode) {
            this.code = code;
            this.message = message;
            this.statusCode = statusCode;
        }

        private final int code;
        private final String message;
        private final HttpStatusCode statusCode;

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public HttpStatusCode getStatusCode() {
            return statusCode;
        }
    }
