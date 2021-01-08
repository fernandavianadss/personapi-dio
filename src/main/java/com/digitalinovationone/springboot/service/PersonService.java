package com.digitalinovationone.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.digitalinovationone.springboot.dto.request.PersonDTO;
import com.digitalinovationone.springboot.dto.response.MessageResponseDTO;
import com.digitalinovationone.springboot.entity.Person;
import com.digitalinovationone.springboot.mapper.PersonMapper;
import com.digitalinovationone.springboot.repository.PersonRepository;

@Service
public class PersonService {

	private PersonRepository personRepository;
	
	private final PersonMapper personMapper = PersonMapper.INSTANCE;

	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MessageResponseDTO createPerson(PersonDTO personDTO) {
		
		Person personToSave = personMapper.toModel(personDTO);
		
		Person savedPerson = personRepository.save(personToSave);
		
		return MessageResponseDTO.builder().message("Created person with ID " + savedPerson.getId()).build();
	}
}
