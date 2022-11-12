package web.dao;



import web.model.User;

import java.util.List;

public interface UserDao {
    void create(User user);

    List<User> findAllUsers();

    User findById(Long id);

    void update(User user);

    void delete(Long id);


}
