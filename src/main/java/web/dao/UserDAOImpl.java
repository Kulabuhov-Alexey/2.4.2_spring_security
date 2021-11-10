package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    @PersistenceContext
    private EntityManager entityManager;

//    private List<User> users;
//    private static int USER_COUNT;
//
//    {
//        users = new ArrayList<>();
//
//        users.add(new User(++USER_COUNT, "Name1", "LastName1", "email1@mail.net"));
//        users.add(new User(++USER_COUNT, "Name2", "LastName2", "email2@mail.net"));
//        users.add(new User(++USER_COUNT, "Name3", "LastName3", "email3@mail.net"));
//        users.add(new User(++USER_COUNT, "Name4", "LastName4", "email4@mail.net"));
//        users.add(new User(++USER_COUNT, "Name5", "LastName5", "email5@mail.net"));
//    }

//    @Override
//    public List<User> getUsers(){
//        return users;
//    }

    public List<User> getUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }


//    public List<User> getUsersCount(int count){
//        return users.stream().limit(count).collect(Collectors.toList());
//    }


    public void save(User user) {
        entityManager.persist(user);
    }
//    @Override
//    public void save(User user) {
//        user.setId(++USER_COUNT);
//        users.add(user);
//    }


    public User show (int id) {
        TypedQuery<User> tp = entityManager.createQuery("select u from User u where u.id = :id", User.class);
        tp.setParameter("id", id);
        return tp.getResultList().stream().findFirst().orElse(null);
    }
//    @Override
//    public User show (int id){
//        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
//    }

//    @Override
//    public void update(int id, User updatedUser){
//        User userToBeUpdated = show(id);
//        userToBeUpdated.setName(updatedUser.getName());
//        userToBeUpdated.setLastName(updatedUser.getLastName());
//        userToBeUpdated.setEmail(updatedUser.getEmail());
//
//    }


    public void update(int id, User updatedUser){
        User userToBeUpdated = show(id);
        userToBeUpdated.setName(updatedUser.getName());
        userToBeUpdated.setLastName(updatedUser.getLastName());
        userToBeUpdated.setEmail(updatedUser.getEmail());
        entityManager.merge(userToBeUpdated);
    }




    public void delete(int id) {
        entityManager.remove(show(id));
    }
//    @Override
//    public void delete(int id) {
//        users.removeIf(p -> p.getId() == id);
//    }
}
