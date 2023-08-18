package com.example.learning_timer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
/** Userコントローラ */

import com.example.learning_timer.form.CreateUserForm;
import com.example.learning_timer.service.UsersService;
@Controller
@RequestMapping("user")
public class UsersController{
    /** DI対象 */
    @Autowired
    UsersService service;
    @ModelAttribute
    public CreateUserForm setUpForm(){
        CreateUserForm form = new CreateUserForm();
        return form;
    }
    @GetMapping("create")
    public String showCreateUser(CreateUserForm form,Model model){

        return "create";
    }


}
