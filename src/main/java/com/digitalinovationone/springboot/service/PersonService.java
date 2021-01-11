package com.digitalinovationone.springboot.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.digitalinovationone.springboot.dto.request.PersonDTO;
import com.digitalinovationone.springboot.dto.response.MessageResponseDTO;
import com.digitalinovationone.springboot.entity.Person;
import com.digitalinovationone.springboot.exception.PersonNotFoundException;
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

	public List<PersonDTO> listAll() {
		List<Person> allPerson = personRepository.findAll();
		return allPerson.stream().map(personMapper::toDTO).collect(Collectors.toList());
	}

	public PersonDTO findById(Long id) throws PersonNotFoundException {
		
		Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
		
		return personMapper.toDTO(person);
	}
}
