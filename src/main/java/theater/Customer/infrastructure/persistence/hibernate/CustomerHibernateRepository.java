package theater.Customer.infrastructure.persistence.hibernate;

import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import theater.Customer.domain.entity.Customer;
import theater.Customer.domain.repository.CustomerRepository;
import theater.common.infrastructure.persistence.hibernate.BaseHibernateRepository;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

@Transactional(rollbackOn=Exception.class)
@Repository
public class CustomerHibernateRepository extends BaseHibernateRepository<Customer> 
        implements CustomerRepository{

        @Override
        public List<Customer> getAll() {
            Criteria criteria = getSession().createCriteria(Customer.class);
            return criteria.list();
        }

        @Override
        public Customer find(String name) {
            Criteria criteria = getSession().createCriteria(Customer.class);
            criteria.add(Restrictions.eq("name", name));
            return (Customer) criteria.uniqueResult();
        }
        
        public void save(Customer customer) {
		super.save(customer);
	}
}
