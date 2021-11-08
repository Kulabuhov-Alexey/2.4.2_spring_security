package web.dao;

import web.model.User;
import java.util.List;


public interface UserDAO {
    List<User> getUsers();
    void save(User user);
    User show (int id);
    void update(int id, User updatedUser);
    void delete(int id);
}
