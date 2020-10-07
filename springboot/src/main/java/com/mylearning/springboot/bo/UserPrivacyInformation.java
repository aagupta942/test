package com.mylearning.springboot.bo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Privacy")
public class UserPrivacyInformation {

	@Id
	@GeneratedValue
	private Integer id;
	private Integer number;
	private String aadharNumber;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;
	
	@Override
	public String toString() {
		return "UserPrivacyInformation [number=" + number + ", aadharNumber=" + aadharNumber + "]";
	}
	
	
}
