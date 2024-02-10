package com.study.stockmanagementstudycase.common.exception;

import com.study.stockmanagementstudycase.common.model.CustomSubError;
import com.study.stockmanagementstudycase.common.model.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException ex
    ) {

        log.error(ex.getMessage(), ex);

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(
                error -> {
                    String fieldName = ((FieldError) error).getField();
                    String message = error.getDefaultMessage();
                    errors.put(fieldName, message);
                }
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handlePathVariableErrors(
            final ConstraintViolationException ex
    ) {

        log.error(ex.getMessage(), ex);

        final List<CustomSubError> subErrors = new ArrayList<>();
        ex.getConstraintViolations()
                .forEach(constraintViolation ->
                        subErrors.add(
                                CustomSubError.builder()
                                        .message(constraintViolation.getMessage())
                                        .field(StringUtils.substringAfterLast(constraintViolation.getPropertyPath().toString(), "."))
                                        .value(constraintViolation.getInvalidValue() != null ? constraintViolation.getInvalidValue().toString() : null)
                                        .type(constraintViolation.getInvalidValue().getClass().getSimpleName())
                                        .build()
                        )
                );

        return new ResponseEntity<>(
                subErrors,
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    protected ResponseEntity<ErrorResponse> handleAlreadyExistsException(
            final AlreadyExistsException ex,
            final HttpServletRequest request
    ) {

        log.error(ex.getMessage(), ex);

        List<String> details = new ArrayList<>();

        details.add(ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorName(ErrorResponse.Header.ALREADY_EXISTS.value())
                .message(ex.getMessage())
                .status(AlreadyExistsException.STATUS.value())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<ErrorResponse>(errorResponse, AlreadyExistsException.STATUS);
    }

    @ExceptionHandler(InvalidException.class)
    protected ResponseEntity<ErrorResponse> handleInvalidException(
            final InvalidException ex,
            final HttpServletRequest request
    ) {

        log.error(ex.getMessage(), ex);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorName(ErrorResponse.Header.INVALID.value())
                .message(ex.getMessage())
                .status(InvalidException.STATUS.value())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<ErrorResponse>(errorResponse, InvalidException.STATUS);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleNotFoundException(
            final NotFoundException ex,
            final HttpServletRequest request
    ) {

        log.error(ex.getMessage(), ex);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorName(ErrorResponse.Header.NOT_FOUND.value())
                .message(ex.getMessage())
                .status(NotFoundException.STATUS.value())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<ErrorResponse>(errorResponse, NotFoundException.STATUS);
    }

    @ExceptionHandler(UnableException.class)
    protected ResponseEntity<ErrorResponse> handleUnableException(
            final UnableException ex,
            final HttpServletRequest request
    ) {

        log.error(ex.getMessage(), ex);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorName(ErrorResponse.Header.UNABLE.value())
                .message(ex.getMessage())
                .status(UnableException.STATUS.value())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<ErrorResponse>(errorResponse, UnableException.STATUS);
    }

}
