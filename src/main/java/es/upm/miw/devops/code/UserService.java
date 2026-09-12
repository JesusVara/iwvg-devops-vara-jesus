package es.upm.miw.devops.code;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UsersDatabase usersDatabase;

    public UserService() {
        this.usersDatabase = new UsersDatabase();
    }

    public Optional<User> findById(String id) {
        return usersDatabase.findAll()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }
}
