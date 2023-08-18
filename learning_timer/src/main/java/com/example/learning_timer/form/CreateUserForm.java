package com.example.learning_timer.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Form */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserForm {
    /** ユーザ名 */
    @NotBlank
    private String name;
    /** パスワード */
    @NotBlank
    private String password;
}
