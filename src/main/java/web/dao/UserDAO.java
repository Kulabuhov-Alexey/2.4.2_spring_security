package web.dao;

import org.springframework.stereotype.Component;
import web.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {


    private List<User> users;
    private static int USER_COUNT;

    {
        users = new ArrayList<>();

        users.add(new User(++USER_COUNT, "Name1", "LastName1", "email1@mail.net"));
        users.add(new User(++USER_COUNT, "Name2", "LastName2", "email2@mail.net"));
        users.add(new User(++USER_COUNT, "Name3", "LastName3", "email3@mail.net"));
        users.add(new User(++USER_COUNT, "Name4", "LastName4", "email4@mail.net"));
        users.add(new User(++USER_COUNT, "Name5", "LastName5", "email5@mail.net"));
    }

        public List<User> getUsers(){
        return users;
    }
//    @Transactional(readOnly = true)
//    public List<User> getUsers() {
//        return entityManager.createQuery("select u from User u", User.class).getResultList();
//    }


//    public List<User> getUsersCount(int count){
//        return users.stream().limit(count).collect(Collectors.toList());
//    }

//    @Transactional
//    public void save(User user) {
//        entityManager.persist(user);
//    }
    public void save(User user) {
        user.setId(++USER_COUNT);
        users.add(user);
    }

//    @Transactional(readOnly = true)
//    public User show (int id) {
//        TypedQuery<User> tp = entityManager.createQuery("select u from User u where u.id = :id", User.class);
//        tp.setParameter("id", id);
//        return tp.getResultList().stream().findFirst().orElse(null);
//    }
    public User show (int id){
        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }


    public void update(int id, User updatedUser){
        User userToBeUpdated = show(id);
        userToBeUpdated.setName(updatedUser.getName());
        userToBeUpdated.setLastName(updatedUser.getLastName());
        userToBeUpdated.setEmail(updatedUser.getEmail());

    }


//    public void delete(int id) {
//        entityManager.remove(show(id));
//    }
    public void delete(int id) {
        users.removeIf(p -> p.getId() == id);
    }
}