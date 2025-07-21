package br.com.dougluciano.boadtaskjava.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User extends AbstractFullEntity{

    @NotBlank (message = "not_blank")
    @Size(min = 1, max = 20, message = "Username must be between {min} and max {max}")
    @Column(name = "user_name", nullable = false, unique = true)
    private String username;

    @NotBlank (message = "not_blank")
    @Column(name = "password", nullable = false)
    private String password;

}
