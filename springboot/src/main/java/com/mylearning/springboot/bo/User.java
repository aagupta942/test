package com.mylearning.springboot.bo;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Request Validation")
@Entity
public class User {

	@Size(min = 2, message = "Name must more than 2 characters")
	@ApiModelProperty(notes = "Name must more than 2 characters")
	private String name;
	
	@Past
	@ApiModelProperty(notes = "birthDate can't be future date")
	private Date birthDate;
	
	@Id
	@GeneratedValue
	private Integer id;

	@OneToMany(mappedBy = "user")
	private List<Posts> posts;
	
	@OneToOne(mappedBy = "user")
	private UserPrivacyInformation userPrivacyInformation;
	
	@Override
	public String toString() {
		return "User [name=" + name + ", birthDate=" + birthDate + ", id=" + id + "]";
	}

}
