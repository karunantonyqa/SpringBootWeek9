package com.qa.mySpringBootDatabase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.mySpringBootDatabase.model.*;

@Repository
public interface MySpringBootRepository extends JpaRepository<MySpringBootDataModel,Long> {
	
	public MySpringBootDataModel findByName(String name);

}

