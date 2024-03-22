package com.study.stockmanagementstudycase.auth.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "First name can't be blank.")
    private String firstName;

    @NotBlank(message = "Last name can't be blank.")
    private String lastName;

    @Size(
            min = 7,
            message = "Minimum e-mail length is 7 characters."
    )
    private String email;

    @NotBlank(message = "Phone number can't be blank.")
    @Size(
            min = 11,
            max = 20
    )
    private String phoneNumber;

    @Size(min = 8)
    private String password;

}
