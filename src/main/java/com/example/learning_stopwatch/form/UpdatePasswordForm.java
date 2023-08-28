package com.example.learning_stopwatch.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Form */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswordForm {
    /** 現在のパスワード */
    @NotBlank(message = "現在のパスワード:空白です。")
    private String oldPassword;
    /** 新しいパスワード */
    @NotBlank(message = "新しいパスワード:空白です。")
    private String newPassword;
    /** 新しいパスワード（再確認用） */
    @NotBlank(message = "新しいパスワード（再入力）:空白です。")
    private String checkPassword;
}
