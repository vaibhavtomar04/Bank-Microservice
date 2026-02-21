package com.example.accounts.Repo;

import com.example.accounts.entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {


 Optional<Account> findByCustomerId(Long CustomerId);

 @Transactional @Modifying
 void deleteByCustomerId(Long customerId);
}
