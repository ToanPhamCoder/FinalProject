package com.example.EcommerceShop.exception;

import com.example.EcommerceShop.response.ApiResponse;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Map;
import java.util.Objects;

@Slf4j

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String MIN_ATTRIBUTE = "min";

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ApiResponse<String>> handleAppException(AppException ex) {
        ApiResponse<String> errorResponse = new ApiResponse<>(
                HttpStatus.BAD_REQUEST.value(), // Mã lỗi HTTP
                ex.getMessage(), // Thông báo lỗi
                null // Không có kết quả bổ sung
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    // Xử lý các loại lỗi từ @Valid @NotBlank.. từ thư viện validate
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException exception){
        ErrorCode errorCode = ErrorCode.INVALID_KEY;
        String errorMessage = "Uncategorized error";

        FieldError fieldError = exception.getBindingResult().getFieldError(); //Lấy được message mình đã tạo khi validate
        if (fieldError != null) {
            String defaultMessage = fieldError.getDefaultMessage();
            if (defaultMessage != null) {
                try {
                    errorCode = ErrorCode.valueOf(defaultMessage);
                    errorMessage = errorCode.getMessage();
                } catch (IllegalArgumentException e) {
                    // Nếu không tìm thấy ErrorCode tương ứng, sử dụng defaultMessage
                    errorMessage = defaultMessage;
                }
            }
        }

        ApiResponse apiResponse = new ApiResponse();
        log.error("Validation error: {}", errorMessage);
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorMessage);

        return ResponseEntity.badRequest().body(apiResponse);
    }

}