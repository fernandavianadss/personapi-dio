package com.digitalinovationone.springboot.service;


import static com.digitalinovationone.springboot.mocks.PersonMock.createFakeEntity;
import static com.digitalinovationone.springboot.mocks.PersonMock.createMockDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.digitalinovationone.springboot.dto.request.PersonDTO;
import com.digitalinovationone.springboot.dto.response.MessageResponseDTO;
import com.digitalinovationone.springboot.entity.Person;
import com.digitalinovationone.springboot.mapper.PersonMapper;
import com.digitalinovationone.springboot.repository.PersonRepository;

@ExtendWith(MockitoExtension.class)
public class PersonTest {
	
	@Mock
	private PersonRepository personRepository;
	
	@Mock
	private PersonMapper personMapper;
	
	@InjectMocks
	private PersonService personService;
	
	@Test
	void testGivenPersonDTOThenReturnSavedMessage() {
		
		PersonDTO  personDTO = createMockDTO();
		
		Person expectedSavedPerson = createFakeEntity();
		
		when(personMapper.toModel(personDTO)).thenReturn(expectedSavedPerson);
		
		when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);
				
		MessageResponseDTO sucessMessage = personService.createPerson(personDTO);
	
        assertEquals("Created person with ID 1", sucessMessage.getMessage());
	}
}
