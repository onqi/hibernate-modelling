package name.onqi;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;


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
    public void test(){
        Long[] cIds = new Long[] {null, 1L, 2L, 3L, 4L, 5L};
        Long[] uIds = new Long[] {null, 1L, 2L, 3L, 4L, 5L};
        Long[] rIds = new Long[] {null, 1L, 2L, 3L, 4L, 5L, 6L};
        Long[] dIds = new Long[] {null, 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L};
        for (Long cId : cIds) {
            for (Long uId : uIds) {
                for (Long rId : rIds) {
                    for (Long dId : dIds) {
                        testFetch(new Long[]{cId, uId, rId, dId});
                    }
                }
            }
        }
    }

    private void testFetch(Long[] indices) {
        Long companyId = indices[0];
        Long unitId = indices[1];
        Long userId = indices[2];
        Long deviceId = indices[3];
        List<Branch> sqlBranches = fetchSql(companyId, unitId, userId, deviceId);
        List<Branch> hibernateBranches = fetch(companyId, unitId, userId, deviceId);
        Assert.assertArrayEquals(String.format("Failed on %s", Arrays.toString(indices)), sqlBranches.toArray(), hibernateBranches.toArray());
    }


    public List<Branch> fetch(Long companyId, Long unitId, Long userId, Long deviceId){
        StringBuilder queryBuilder = new StringBuilder("SELECT c.id AS companyId, u.id AS unitId, r.id AS userId, d.id AS deviceId")
                .append(" FROM Device d RIGHT OUTER JOIN d.user as r RIGHT OUTER JOIN r.unit as u RIGHT OUTER JOIN u.company as c")
                .append(" WHERE 1=1");
        if (companyId != null){
            queryBuilder.append(" AND c.id =:cId");
        }
        if (unitId != null){
            queryBuilder.append(" AND u.id =:uId");
        }
        if (userId != null){
            queryBuilder.append(" AND r.id =:rId");
        }
        if (deviceId != null){
            queryBuilder.append(" AND d.id =:dId");
        }
        Query query = sessionFactory.getCurrentSession().createQuery(
                queryBuilder.toString());
        if (companyId != null){
            query.setParameter("cId", companyId);
        }
        if (unitId != null){
            query.setParameter("uId", unitId);
        }
        if (userId != null){
            query.setParameter("rId", userId);
        }
        if (deviceId != null){
            query.setParameter("dId", deviceId);
        }
        List<Branch> branches = (List<Branch>) query
                .setResultTransformer(Transformers.aliasToBean(Branch.class))
                .list();
        return branches;

    }

    public List<Branch> fetchSql(Long companyId, Long unitId, Long userId, Long deviceId){
        Query q = sessionFactory.getCurrentSession().createSQLQuery("SELECT c.id AS companyId, u.id AS unitId, r.id AS userId, d.id AS deviceId" +
                " FROM Company c" +
                " LEFT OUTER JOIN Unit u on (u.company_id = c.id)" +
                " LEFT OUTER JOIN User r ON (r.unit_id = u.id)" +
                " LEFT OUTER JOIN Device d ON (d.user_id = r.id)" +
                " WHERE (:cId IS NULL OR c.id =:cId)" +
                " AND (:uId IS NULL OR u.id =:uId)" +
                " AND (:rId IS NULL OR r.id =:rId)" +
                " AND (:dId IS NULL OR d.id =:dId)")
                .addScalar("companyId", LongType.INSTANCE)
                .addScalar("unitId", LongType.INSTANCE)
                .addScalar("userId", LongType.INSTANCE)
                .addScalar("deviceId", LongType.INSTANCE);
        List<Branch> branches = q
                .setParameter("cId", companyId).setParameter("uId", unitId).setParameter("rId", userId).setParameter("dId", deviceId)
                .setResultTransformer(Transformers.aliasToBean(Branch.class))
                .list();

        return branches;
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
