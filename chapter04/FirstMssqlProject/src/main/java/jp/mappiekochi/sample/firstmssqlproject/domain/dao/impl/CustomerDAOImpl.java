package jp.mappiekochi.sample.firstmssqlproject.domain.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jp.mappiekochi.sample.firstmssqlproject.domain.dao.CustomerDAO;
import jp.mappiekochi.sample.firstmssqlproject.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public CustomerDAOImpl() {
        super();
    }

    @Override
    public List<Customer> getAll() {
        Query query = entityManager.createQuery("from Customer"); // JPQL における select * from Customer
        @SuppressWarnings("unchecked")
        List<Customer> list = query.getResultList();
        return list;
    }

    @Override
    public Customer findByCustomerId(int customerId) {
        return (Customer)entityManager.createQuery("from Customer where customerId = " + customerId)
                .getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Customer> findByFirstName(String firstName) {
        return (List<Customer>)entityManager.createQuery("from Customer where firstName = '" + firstName + "'")
                .getResultList();
    }

    @Override
    public List<Customer> find(String param) {
//        return (List<Customer>)entityManager.createQuery("from Customer where firstName = :param")
//                .setParameter("param", param)
//                .getResultList();
        int cId = 0;
        try {
            cId = Integer.parseInt(param);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return (List<Customer>)entityManager.createQuery("from Customer where customerId = :cId or firstName like :fName or lastName like :lName")
//                .setParameter("cId", cId)
//                .setParameter("fName", "%" + param + "%")
//                .setParameter("lName", "%" + param + "%")
//                .getResultList();
//        return (List<Customer>)entityManager.createQuery("from Customer where customerId = ?1 or firstName like ?2 or lastName like ?3")
//                .setParameter(1, cId)
//                .setParameter(2, "%" + param + "%")
//                .setParameter(3, "%" + param + "%")
//                .getResultList();
        return (List<Customer>)entityManager.createNamedQuery("findByCustomerIdOrName")
                .setParameter("cId", cId)
                .setParameter("fName", "%" + param + "%")
                .setParameter("lName", "%" + param + "%")
                .getResultList();
    }
}
