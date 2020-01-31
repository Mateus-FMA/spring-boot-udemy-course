package br.com.mateusfma.springboot.app.ws.services;

import br.com.mateusfma.springboot.app.ws.components.IdGenerator;
import br.com.mateusfma.springboot.app.ws.ui.model.User;
import br.com.mateusfma.springboot.app.ws.ui.model.request.CreateUserRequest;
import br.com.mateusfma.springboot.app.ws.ui.model.request.UpdateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private Map<String, User> users;
    private IdGenerator idGenerator;

    @Autowired
    public UserServiceImpl(IdGenerator idGenerator) {
        this.users = new HashMap<>();
        this.idGenerator = idGenerator;
    }

    @Override
    public boolean hasUser(String userId) {
        return users.containsKey(userId);
    }

    @Override
    public User createUser(CreateUserRequest request) {
        User user = new User();
        user.setId(idGenerator.generateUserId());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        users.put(user.getId(), user);

        return user;
    }

    @Override
    public User getUser(String userId) {
        return users.get(userId);
    }

    @Override
    public User updateUser(String userId, UpdateUserRequest request) {
        User user = users.get(userId);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        users.put(userId, user);

        return user;
    }
}
