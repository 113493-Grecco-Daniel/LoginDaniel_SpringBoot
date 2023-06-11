package ar.edu.utn.frc.tup.lciii.models;

import ar.edu.utn.frc.tup.lciii.utils.validations.password.ValidPassword;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    private Long id;
    @NotNull(message= "userName cant´t be null")
    private String userName;

    @NotNull(message= "password cant´t be null")
    @ValidPassword
    private  String password;

    @NotNull(message= "email cant´t be null")
    @Email(message= "the e-mail need to be a valid email")
    private  String email;

    private String avatar;
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime lastLogin;



}
