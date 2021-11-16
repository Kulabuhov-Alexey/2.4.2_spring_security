package web.dao;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import web.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
        //return entityManager.createQuery("select u from User u join fetch u.roles", User.class).getResultList();
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

//    @Override
//    public User show (int id) {
//        TypedQuery<User> tp = entityManager.createQuery("select u from User u where u.id = :id", User.class);
//        tp.setParameter("id", id);
//        return tp.getResultList().stream().findFirst().orElse(null);
//    }

    @Override
    public void update(String username, User updatedUser){
        User userToBeUpdated = findUserByUsername(username);
        userToBeUpdated.setName(updatedUser.getName());
        userToBeUpdated.setLastName(updatedUser.getLastName());
        userToBeUpdated.setEmail(updatedUser.getEmail());
//        userToBeUpdated.setRoles(updatedUser.getRoles());
        entityManager.merge(userToBeUpdated);
    }

//    @Override
//    public void delete(int id) {
//        entityManager.remove(show(id));
//    }

    @Override
    public void delete(String username) {
        entityManager.remove(findUserByUsername(username));
    }



    public User findUserByUsername(String username) {
        TypedQuery<User> tp = entityManager.createQuery("select u from User u where u.username = :username", User.class);
        tp.setParameter("username", username);
        return tp.getResultList().stream().findFirst().orElse(null);

    }

//    @Override
//    public User findUserByUsername(String username) {
//        if (!userMap.containsKey(username)) {
//            return null;
//        }
//
//        return userMap.get(username);
//    }

}
