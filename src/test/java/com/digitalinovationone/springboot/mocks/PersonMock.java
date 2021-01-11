package com.digitalinovationone.springboot.mocks;

import java.time.LocalDate;
import java.util.Collections;

import com.digitalinovationone.springboot.dto.request.PersonDTO;
import com.digitalinovationone.springboot.entity.Person;

public class PersonMock {
	
	private static final String FIRST_NAME = "Ana";
	private static final String LAST_NAME = "Souza";
	private static final String CPF_NUMBER = "805.620.540-30";
	private static final long PERSON_ID = 1L;
	public static final LocalDate BIRTH_DATE = LocalDate.of(2021, 03, 24);
	
	public static PersonDTO createMockDTO() {
		return PersonDTO.builder()
				.firstName(FIRST_NAME)
				.lastName(LAST_NAME)
				.cpf(CPF_NUMBER)
				.birthDate("04/05/2021")
				.phones(Collections.singletonList(PhoneMock.createMockDTO()))
				.build();
		
	}
	
	public static Person createFakeEntity() {
		return Person.builder()
				.id(PERSON_ID)
				.firstName(FIRST_NAME)
				.lastName(LAST_NAME)
				.cpf(CPF_NUMBER)
				.birthDate(BIRTH_DATE)
				.phones(Collections.singletonList(PhoneMock.createFakeEntity()))
				.build();
	}
}
