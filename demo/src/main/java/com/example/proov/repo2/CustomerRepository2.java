package com.example.proov.repo2;

import com.example.proov.repo2.Customer2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository2 extends JpaRepository<Customer2, Long> { //siia sisse lähevad soovitud meetodid
//    Customer2 findByName(String name);
//
//    List<Customer2> getOne(int i);
    //List<Customer> findAllBy
}
