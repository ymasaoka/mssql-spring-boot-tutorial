package jp.mappiekochi.sample.firstmssqlproject.domain.dao;

import jp.mappiekochi.sample.firstmssqlproject.entity.Customer;

import java.io.Serializable;
import java.util.List;

public interface CustomerDAO <T> extends Serializable {
    List<Customer> getAll();
    T findByCustomerId(int customerId);
    List<T> findByFirstName(String firstName);

    List<T> find(String param);
}
