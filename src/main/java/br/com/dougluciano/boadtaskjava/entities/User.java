package br.com.dougluciano.boadtaskjava.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
public class User extends AbstractFullEntity{

    @NotBlank (message = "not_blank")
    @Size(min = 1, max = 20, message = "Username must be between {min} and max {max}")
    @Column(name = "user_name", nullable = false, unique = true)
    private String username;

    @NotBlank (message = "Password cannot be blank or null")
    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

}
