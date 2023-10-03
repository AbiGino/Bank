package com.example.bankApplication.Repository;

import com.example.bankApplication.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    @Query(value = "select * from emp.customer where account_number =:account_number", nativeQuery = true)
    Customer findByAccountNumber(Integer account_number);

    @Query(value = "select * from emp.customer where name =:name and password =:password", nativeQuery = true)
    Customer findByNameAndPassword(String name,String password);
}
