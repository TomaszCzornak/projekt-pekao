package com.pekao.projektpekao.domain.book;

public class CreateBookFactoryParams {
	
	private final Long xyz;
	
	public CreateBookFactoryParams(Long xyz) {
		if (xyz == null) {
			throw new IllegalStateException("XYZ cannot be null");
		}
		
		this.xyz = xyz;
	}
}
