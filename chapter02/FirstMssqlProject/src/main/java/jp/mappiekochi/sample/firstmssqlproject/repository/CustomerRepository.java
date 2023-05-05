package jp.mappiekochi.sample.firstmssqlproject.repository;

import jp.mappiekochi.sample.firstmssqlproject.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    public Optional<Customer> findByCustomerId(Integer customerId); // Optional: null かもしれないオブジェクトをラップするためのクラス (Java 8 ~)
}
