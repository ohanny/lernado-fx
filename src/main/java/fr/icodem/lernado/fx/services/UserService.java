package fr.icodem.lernado.fx.services;

import fr.icodem.lernado.fx.domain.User;

import java.util.concurrent.CompletableFuture;

public class UserService {
    public CompletableFuture<User> authenticate(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setFirstName("John");
        user.setLastName("Doe");

        return CompletableFuture.completedFuture(user);
    }
}
