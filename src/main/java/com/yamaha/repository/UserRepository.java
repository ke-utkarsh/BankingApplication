package com.yamaha.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yamaha.entities.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByusername(String username);
	
	
	  @Transactional
	  
	  @Modifying
	  
	  @Query("update User u set u.amount=?1 where u.username=?2"
	  ) int updateAmountByUsername(double amount, String username);
	 
}
