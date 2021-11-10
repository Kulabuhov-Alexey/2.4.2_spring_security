package web.dao;

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
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public User show (int id) {
        TypedQuery<User> tp = entityManager.createQuery("select u from User u where u.id = :id", User.class);
        tp.setParameter("id", id);
        return tp.getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public void update(int id, User updatedUser){
        User userToBeUpdated = show(id);
        userToBeUpdated.setName(updatedUser.getName());
        userToBeUpdated.setLastName(updatedUser.getLastName());
        userToBeUpdated.setEmail(updatedUser.getEmail());
        entityManager.merge(userToBeUpdated);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(show(id));
    }

}
