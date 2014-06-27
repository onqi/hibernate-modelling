package name.onqi;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author onqi
 */
@Component
public class BranchDao {
    @Autowired
    private SessionFactory sessionFactory;

    public int countBranches(Long companyId, Long unitId, Long userId, Long deviceId) {
        return (Integer) sessionFactory.getCurrentSession().createQuery("SELECT count(*) from Company c").uniqueResult();
    }
}
