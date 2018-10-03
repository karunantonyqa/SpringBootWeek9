package com.qa.mySpringBootDatabase.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.mySpringBootDatabase.exception.ResourceNotFoundException;
import com.qa.mySpringBootDatabase.model.*;
import com.qa.mySpringBootDatabase.repository.MySpringBootRepository;

@RestController
@RequestMapping("api")
public class MySpringBootDataController {
	
	@Autowired
	MySpringBootRepository myRepo;
	
	//method create person
	@PostMapping("/person")
	public MySpringBootDataModel createPerson(@Valid @RequestBody MySpringBootDataModel mSDM) {
		return myRepo.save(mSDM);
	}
	
	//method get person
	@GetMapping("person/{id}")
	public MySpringBootDataModel getPersonbyID(@PathVariable(value = "id")Long personID) {
		return myRepo.findById(personID).orElseThrow(()-> new ResourceNotFoundException("MySpringBootDataModel","id",personID));
	}
	
	
	//method get all people
	@GetMapping("/person")
	public List<MySpringBootDataModel> getAllPeople(){
		return myRepo.findAll();
	}
	
	//method update person
	@PutMapping("/person/{id}")
	public MySpringBootDataModel updatePerson(@PathVariable(value = "id") Long personID, @Valid @RequestBody MySpringBootDataModel personDetails) {
		MySpringBootDataModel mSDM = myRepo.findById(personID).orElseThrow(()-> new ResourceNotFoundException("Person","id",personID));
		
		mSDM.setName(personDetails.getName());
		mSDM.setAddress(personDetails.getAddress());
		mSDM.setAge(personDetails.getAge());
		
		MySpringBootDataModel updateData = myRepo.save(mSDM);
		return updateData;
	}
	

	//method remove person
	@DeleteMapping("/person/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable(value = "id") Long personID){
		MySpringBootDataModel mSDM = myRepo.findById(personID).orElseThrow(()-> new ResourceNotFoundException("Person","id",personID));
		
		myRepo.delete(mSDM);
		return ResponseEntity.ok().build();
	}
	
}