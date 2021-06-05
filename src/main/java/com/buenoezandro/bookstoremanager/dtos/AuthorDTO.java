package com.buenoezandro.bookstoremanager.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {

	private Long id;
	
	@NotBlank(message = "{name.not.blank}")
	@Size(max = 200)
	private String name;
	
	@NotNull(message = "{age.not.null}")
	@Max(value = 100)
	private Integer age;

}
