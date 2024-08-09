package com.example.EcommerceShop.exception;

import com.example.EcommerceShop.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<String>> handleAppException(AppException ex) {
        ApiResponse<String> errorResponse = new ApiResponse<>(
                HttpStatus.BAD_REQUEST.value(), // Mã lỗi HTTP
                ex.getMessage(), // Thông báo lỗi
                null // Không có kết quả bổ sung
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    // Xử lý các loại lỗi khác nếu cần
}