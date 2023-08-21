package com.example.stopwatch.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Form */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {
    /** ユーザ名 */
    @NotBlank(message = "ユーザ名:空白です。")
    private String name;
    /** パスワード */
    @NotBlank(message = "パスワード:空白です。")
    private String password;
}
