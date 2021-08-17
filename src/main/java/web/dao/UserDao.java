package web.dao;

import org.springframework.stereotype.Component;
import web.models.User;
import java.util.List;

@Component
public interface UserDao {
    List<User> getAllUsers();
    void addUser(User user);
    void deleteUser(long id);
    void updateUser(User user);
    User getUserById(long id);
}
