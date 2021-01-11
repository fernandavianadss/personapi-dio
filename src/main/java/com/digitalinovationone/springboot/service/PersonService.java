package com.digitalinovationone.springboot.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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
		
		return createdResponseMessage(savedPerson, "Updated person with ID ");
	}

	public List<PersonDTO> listAll() {
		List<Person> allPerson = personRepository.findAll();
		return allPerson.stream().map(personMapper::toDTO).collect(Collectors.toList());
	}

	public PersonDTO findById(Long id) throws PersonNotFoundException {
		
		Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
		
		return personMapper.toDTO(person);
	}

	public void deleteById(Long id) throws PersonNotFoundException {
		
		verifyIfExists(id);
		
		personRepository.deleteById(id);
	}

	public MessageResponseDTO updateById(Long id, @Valid PersonDTO personDTO) throws PersonNotFoundException {
		
		verifyIfExists(id);
		
		Person personToUpdate = personMapper.toModel(personDTO);
		
		Person updatePerson = personRepository.save(personToUpdate);
		
		return createdResponseMessage(updatePerson, "Updated person with ID ");
		
	}
	
	private Person verifyIfExists(Long id) throws PersonNotFoundException {
		
		return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
		
	}

	private MessageResponseDTO createdResponseMessage(Person savedPerson, String message) {
		return MessageResponseDTO.builder().message(message + savedPerson.getId()).build();
	}
}
