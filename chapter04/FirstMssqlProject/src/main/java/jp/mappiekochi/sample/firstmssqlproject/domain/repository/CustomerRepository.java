package jp.mappiekochi.sample.firstmssqlproject.domain.repository;

import jp.mappiekochi.sample.firstmssqlproject.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByCustomerId(Integer customerId); // Optional: null かもしれないオブジェクトをラップするためのクラス (Java 8 ~)

    @Query("SELECT c FROM Customer c ORDER BY c.customerId")
    List<Customer> findAllOrderByCustomerId();

    @Query("SELECT c FROM Customer c WHERE c.emailAddress = :email")
    List<Customer> findByEmail(@Param("email") String email);
}
