package com.example.rent2gojavaproject.services.dtos.requests.userRequest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    String firstName;
    String lastName;
    String phoneNumber;
    @Email()
    String email;
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=.*])(?=\\S+$).{8,}"
            ,message = "En az 8 karakter\n" +
            "\n" +
            "En az bir rakam içerir\n" +
            "\n" +
            "En az bir alt alfa karakteri ve bir üst alfa karakteri içerir\n" +
            "\n" +
            "Bir dizi özel karakter ( @#%$^.*vb.) içinde en az bir karakter içerir.\n" +
            "\n" +
            "Boşluk, sekme vb. içermez.")
    String password;
}