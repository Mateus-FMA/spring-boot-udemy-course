package br.com.mateusfma.springboot.app.ws.ui.controller;

import br.com.mateusfma.springboot.app.ws.exceptions.UserServiceException;
import br.com.mateusfma.springboot.app.ws.services.UserService;
import br.com.mateusfma.springboot.app.ws.ui.model.User;
import br.com.mateusfma.springboot.app.ws.ui.model.request.CreateUserRequest;
import br.com.mateusfma.springboot.app.ws.ui.model.request.UpdateUserRequest;
import br.com.mateusfma.springboot.app.ws.ui.model.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("users")
public class UserController {

    private Map<String, User> users;

    @Autowired
    private UserService userService;

    @PostMapping(consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE
    }, produces = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        User user = userService.createUser(request);

        UserResponse response = new UserResponse();
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setUserId(user.getId());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path="/{userId}")
    public ResponseEntity<UserResponse> retrieveUser(
            @PathVariable String userId,
            @RequestParam(value="page", defaultValue="1") int page,
            @RequestParam(value="limit", defaultValue="50") int limit,
            @RequestParam(value="sort", required=false) String sort) throws UserServiceException {
        if (users == null)
            throw new UserServiceException("Container not initialized.");

        if (users.containsKey(userId)) {
            User user = users.get(userId);

            UserResponse response = new UserResponse();
            response.setFirstName(user.getFirstName());
            response.setLastName(user.getLastName());
            response.setEmail(user.getEmail());
            response.setUserId(userId);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path="/{userId}", produces = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity<UserResponse> retrieveUser(@PathVariable String userId) throws UserServiceException {
        if (userService.hasUser(userId)) {
            User user = userService.getUser(userId);

            UserResponse response = new UserResponse();
            response.setFirstName(user.getFirstName());
            response.setLastName(user.getLastName());
            response.setEmail(user.getEmail());
            response.setUserId(userId);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    }, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    }, path="/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable String userId,
                                                   @RequestBody @Valid UpdateUserRequest request) {
        if (userService.hasUser(userId)) {
            User user = userService.updateUser(userId, request);

            UserResponse response = new UserResponse();
            response.setFirstName(user.getFirstName());
            response.setLastName(user.getLastName());
            response.setEmail(user.getEmail());
            response.setUserId(userId);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping(path="/{userId}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable String userId) {
        if (users != null && users.containsKey(userId)) {
            User user = users.get(userId);

            users.remove(userId);

            UserResponse response = new UserResponse();
            response.setFirstName(user.getFirstName());
            response.setLastName(user.getLastName());
            response.setEmail(user.getEmail());
            response.setUserId(userId);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
