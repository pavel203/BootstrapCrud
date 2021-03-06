package web.dao;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import web.models.User;
import java.util.List;

@Component
public interface UserDao {
    List<User> getAllUsers();
    void addUser(User user);
    void deleteUser(int id);
    void updateUser(User user);
    User getUserById(int id);
    UserDetails getUserByUsername(String userName);
}
