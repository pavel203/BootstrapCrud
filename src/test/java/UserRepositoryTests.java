import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import web.config.*;
import web.repository.RoleRepository;
import web.dao.UserDao;
import web.repository.UserRepository;
import web.models.Role;
import web.models.User;
import web.security.SecurityConfig;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootTest(classes = {HibernateConfig.class, SecurityConfig.class, WebConfig.class})
@RunWith(SpringRunner.class)
@AutoConfigureTestEntityManager
@Transactional
@Rollback(value = false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void anyTest() {
        userDao.getUserById(1).getRoles().forEach(System.out::println);
    }


    @Test
    public void insertRolesIntoDataBase() {

        Role roleUser = new Role("ROLE_USER");
        Role roleAdmin = new Role("ROLE_ADMIN");
        testEntityManager.persist(roleUser);
        testEntityManager.persist(roleAdmin);

        roleRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void createUserWithOneRole() {
/*
        Optional<Role> roleUser = roleRepository.findById(1);
        User user = new User("USER", "USER");
        Set<Role> set1 = new HashSet<>();
        set1.add(roleUser.get());
        user.setRoles(set1);

 */

        Optional<Role> roleAdmin = roleRepository.findById(2);
        User admin = new User("ADMIN", "ADMIN");
        Set<Role> set2 = new HashSet<>();
        set2.add(roleAdmin.get());
        admin.setRoles(set2);

        userRepository.save(admin);

    }



    @Test
    public void createUserWithTwoRoles() {
        Role roleUser = testEntityManager.find(Role.class, 1);
        Role roleAdmin = testEntityManager.find(Role.class, 2);
        User user = new User("User-Admin", "User-Admin");
        //user.setRole(roleUser);
        //user.setRole(roleAdmin);
        userRepository.save(user);
    }

    @Test
    public void assignRoleToExistingUser() {
        User user = userDao.getUserById(5);
        Role roleAdmin = testEntityManager.find(Role.class, 1);

        //user.setRole(roleAdmin);

        System.out.println(user);
    }

    @Test
    public void removeRoleFromExistingUser() {
        User user = userDao.getUserById(5);
        //Role roleAdmin = new Role(2);
        //user.removeRole(roleAdmin);

    }

    @Test
    public void createUserWithNewRole() {
        //Role role = new Role("ROLE_USER");
        //User user = new User("EDITOR", "EDITOR");
        //System.out.println(user.getRoles());
        //user.addRole(role);

        //userDao.addUser(user);
    }

    @Test
    public void getUser() {
        User user = userRepository.getById(1);
        System.out.println(user.getUserName());
        //System.out.println(user.getRoles());
    }

    @Test
    public void removeUser() {
        userDao.deleteUser(1);
    }

}
