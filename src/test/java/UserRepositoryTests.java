import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import web.config.HibernateConfig;
import web.config.WebConfig;
import web.dao.UserDao;
import web.models.Role;
import web.repository.RoleRepository;
import web.repository.UserRepository;
import web.security.SecurityConfig;

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
        System.out.println(roleRepository.findById("ROLE_USER"));
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
    public void createUserWithOneRole() {}

    @Test
    public void createUserWithTwoRoles() {}

    @Test
    public void assignRoleToExistingUser() {}

    @Test
    public void removeRoleFromExistingUser() {}

    @Test
    public void createUserWithNewRole() {}

    @Test
    public void getUser() {}

    @Test
    public void removeUser() {}

}
