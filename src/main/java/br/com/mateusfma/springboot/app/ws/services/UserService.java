package br.com.mateusfma.springboot.app.ws.services;

import br.com.mateusfma.springboot.app.ws.ui.model.User;
import br.com.mateusfma.springboot.app.ws.ui.model.request.CreateUserRequest;
import br.com.mateusfma.springboot.app.ws.ui.model.request.UpdateUserRequest;

public interface UserService {

    boolean hasUser(String userId);

    User createUser(CreateUserRequest request);

    User getUser(String userId);

    User updateUser(String userId, UpdateUserRequest request);

}
