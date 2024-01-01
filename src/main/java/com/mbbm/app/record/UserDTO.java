package com.mbbm.app.record;

import com.mbbm.app.enums.EGender;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record UserDTO(

        @NotBlank(message = "First Name is mandatory")
        @Size(max = 20)
        String firstName,

        @Size(max = 20)
        String lastName,

        @NotBlank(message = "Username is mandatory")
        String username,

        @NotBlank(message = "email is mandatory")
        @Size(max = 50)
        @Email
        String email,

        String birthdate,

        String nationality,

        EGender gender,

        boolean isCompany,

        boolean isDeleted
) { }
