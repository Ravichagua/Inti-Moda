package com.intimoda.app.DTO;

import com.intimoda.app.jpa.model.DocumentType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min = 4, max = 20, message = "El nombre de usuario debe tener entre 4 y 20 caracteres")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico no tiene un formato válido")
    private String email;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre no debe tener más de 50 caracteres")
    private String name;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 50, message = "El apellido no debe tener más de 50 caracteres")
    private String lastname;

    @NotNull(message = "El tipo de documento es obligatorio")
    private DocumentType documentType;

    @Min(value = 1000000, message = "El número de documento debe tener al menos 7 dígitos")
    private int documentNumber;

    @Min(value = 100000000, message = "El número de teléfono debe tener al menos 9 dígitos")
    private int phone;

    // Getters y setters
}
