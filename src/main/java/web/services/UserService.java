package web.services;

import org.springframework.stereotype.Service;
import web.models.User;
import java.util.List;

@Service
public interface UserService {
    List<User> getAllUsers();
    void addUser(User user);
    void deleteUser(int id);
    void updateUser(User user);
    User getUserById(long id);
}
