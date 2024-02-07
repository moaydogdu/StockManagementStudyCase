package com.study.stockmanagementstudycase.common.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CustomResponse<T> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T response;

    private boolean isSuccess;

    private HttpStatus httpStatus;

    @Builder.Default
    private LocalDateTime dateTime = LocalDateTime.now();

    public CustomResponse(
            final @Nullable T response,
            final @NonNull HttpStatus httpStatus
    ) {
        this.response = response;
        this.httpStatus = httpStatus;
        this.isSuccess = httpStatus.is2xxSuccessful();
    }

    public static final CustomResponse<Void> SUCCESS = CustomResponse.<Void>builder()
            .isSuccess(true)
            .httpStatus(HttpStatus.OK)
            .build();

    public static <E> CustomResponse<E> ok(
            final E response
    ) {
        return CustomResponse.<E>builder()
                .response(response)
                .isSuccess(true)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    public static <E> CustomResponse<E> created(
            final E response
    ) {
        return CustomResponse.<E>builder()
                .response(response)
                .isSuccess(true)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }
}
