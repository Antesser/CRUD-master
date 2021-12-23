package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public User saveAndFlush(User user) {
        return entityManager.merge(user);
    }

    @Override
    public void deleteById(int id) {
        entityManager.remove(findById(id));
    }

    @Override
    public User findById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List findAll() {
        return entityManager.createQuery("from User").getResultList();
    }
}
