package web.dao;

import org.springframework.security.core.userdetails.UserDetails;
import web.model.User;
import java.util.List;


public interface UserDAO {
    List<User> getUsers();
    void save(User user);
 //   User show (int id);
    void update(String username, User updatedUser);
    void delete(String username);
    User findUserByUsername(String username);
}
