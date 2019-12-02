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
    public String retrieveUser(@RequestParam(value="page", defaultValue="1") int page,
                               @RequestParam(value="limit", defaultValue="50") int limit,
                               @RequestParam(value="sort", required=false) String sort) {
        return "retrieveUser was called with page " + page + " and limit " + limit + " with sort " + sort;
    }

    @GetMapping(path="/{userId}")
    public String retrieveUser(@PathVariable String userId) {
        return "retrieveUser was called with userId " + userId;
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
