package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDAO;
import web.model.User;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserDAO userDao;

    @Autowired
    public UserServiceImpl(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

//    @Override
//    public User show(int id) {
//        return userDao.show(id);
//    }

    @Override
    public void update(String username, User updatedUser) {
        userDao.update(username, updatedUser);
    }

    @Override
    public void delete(String username) {
        userDao.delete(username);
    }

    @Override
    public User findUserByUsername (String username) {
        return userDao.findUserByUsername(username);
    }







//    public UserDetails loadUserByUsername(String username){
//        return userDao.findUserByUsername(username);
//    }
}
