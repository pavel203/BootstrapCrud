package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Transactional
public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        return entityManagerFactory.createEntityManager().createQuery("from User ", User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(user);

        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteUser(User user) {
        entityManagerFactory.createEntityManager().remove(user);
    }

    @Override
    public void updateUser(User user) {
        User entity = entityManagerFactory.createEntityManager().find(User.class, user.getId());
        entityManagerFactory.createEntityManager().refresh
                (entityManagerFactory.createEntityManager().merge(entity));
    }

    @Override
    public User getUserById(long id) {
        return entityManagerFactory.createEntityManager().find(User.class, id);
    }
}
