package theater.common.infrastructure.persistence.hibernate;

import java.sql.SQLException;

import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Transactional(rollbackOn=Exception.class)
public class BaseHibernateRepository<T> {
    
        protected SessionFactory sessionFactory;
    
	@Autowired
	public void setSessionFactory(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
        
        public void save(T entity) {
		getSession().save(entity);
	}
}
