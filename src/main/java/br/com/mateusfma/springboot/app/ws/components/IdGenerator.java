package br.com.mateusfma.springboot.app.ws.components;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IdGenerator {

    public String generateUserId() {
        return UUID.randomUUID().toString();
    }

}
