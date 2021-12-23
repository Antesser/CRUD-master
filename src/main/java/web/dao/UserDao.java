package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    User saveAndFlush(User user);
    void deleteById(int id);
    User findById(int id);
    List<User> findAll();
}
