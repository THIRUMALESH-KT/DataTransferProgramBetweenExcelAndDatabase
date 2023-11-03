package com.security.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class customer {

	@Id
	private Long id;
	private String firstName;
	private String lastName;
	private String country;
	private Long mobile;
}
