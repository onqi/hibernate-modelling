package name.onqi;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author onqi
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-persistence.xml")
@Transactional
public class ModelTest {
    private String[] names = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
    @Autowired
    private SessionFactory sessionFactory;

    @Before
    public void createStructure(){
        Company c1 = createCompany(1);
        Company c2 = createCompany(2);
        Company c3 = createCompany(3);
        Company c4 = createCompany(4);
        Company c5 = createCompany(5);

        Unit u1 = createUnit(1, c1);
        Unit u2 = createUnit(2, c2);
        Unit u3 = createUnit(3, c3);
        Unit u4 = createUnit(4, c4);
        Unit u5 = createUnit(5, c1);

        User r1 = createUser(1, u1);
        User r2 = createUser(2, u2);
        User r3 = createUser(3, u3);
        User r4 = createUser(4, u4);
        User r5 = createUser(5, u1);
        User r6 = createUser(6, u5);

        Device d1 = createDevice(1, r1);
        Device d2 = createDevice(2, r2);
        Device d3 = createDevice(3, r3);
        Device d4 = createDevice(4, r4);
        Device d5 = createDevice(5, r1);
        Device d6 = createDevice(6, r1);
        Device d7 = createDevice(7, r6);
        Device d8 = createDevice(8, r6);
    }

    @Test
    public void createParent() {

    }


    @Transactional
    public Company createCompany(int nameIndex) {
        Company c = new Company();
        c.setName(names[nameIndex - 1]);
        sessionFactory.getCurrentSession().save(c);
        return c;
    }

    @Transactional
    public Unit createUnit(int nameIndex, Company c) {
        Unit u = new Unit();
        u.setCompany(c);
        u.setName(names[nameIndex - 1]);
        sessionFactory.getCurrentSession().save(u);
        return u;
    }

    public User createUser(int nameIndex, Unit u){
        User r = new User();
        r.setUnit(u);
        r.setName(names[nameIndex - 1]);
        sessionFactory.getCurrentSession().save(r);
        return r;
    }

    public Device createDevice(int nameIndex, User r){
        Device d = new Device();
        d.setUser(r);
        d.setName(names[nameIndex - 1]);
        sessionFactory.getCurrentSession().save(d);
        return d;
    }
}
