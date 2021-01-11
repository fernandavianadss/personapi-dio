package com.digitalinovationone.springboot.mocks;

import com.digitalinovationone.springboot.dto.request.PhoneDTO;
import com.digitalinovationone.springboot.entity.Phone;
import com.digitalinovationone.springboot.enums.PhoneType;

public class PhoneMock {
	
	private static final String PHONE_NUMBER = "2199247-9696";
	private static final PhoneType PHONE_TYPE = PhoneType.MOBILE;
	private static final long PHONE_ID = 1L;
	
	public static PhoneDTO createMockDTO() {
		return PhoneDTO.builder()
				.number(PHONE_NUMBER)
				.type(PHONE_TYPE)
				.build();
		
	}

	public static Phone createFakeEntity() {
		return Phone.builder()
				.id(PHONE_ID)
				.number(PHONE_NUMBER)
				.type(PHONE_TYPE)
				.build();
	}
}
