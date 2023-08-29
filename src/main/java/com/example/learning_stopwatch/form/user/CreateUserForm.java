package com.example.learning_stopwatch.form.user;

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
    @NotBlank(message = "ユーザ名:空白です。")
    private String name;
    /** パスワード */
    @NotBlank(message = "パスワード:空白です。")
    private String password;
    /** 確認用パスワード */
    @NotBlank(message = "確認用パスワード:空白です。")
    private String checkPassword;
}
