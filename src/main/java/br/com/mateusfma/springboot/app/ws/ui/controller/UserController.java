package br.com.mateusfma.springboot.app.ws.ui.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @PostMapping
    public String createUser() {
        return "createUser was called";
    }

    @GetMapping
    public String retrieveUser() {
        return "retrieveUser was called";
    }

    @PutMapping
    public String updateUser() {
        return "updateUser was called";
    }

    @DeleteMapping
    public String deleteUser() {
        return "deleteUser was called";
    }
}
