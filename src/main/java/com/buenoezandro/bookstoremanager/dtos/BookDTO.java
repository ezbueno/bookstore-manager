package com.buenoezandro.bookstoremanager.dtos;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

	private Long id;

	@NotBlank(message = "{name.not.blank}")
	@Size(max = 200)
	private String name;

	@NotNull(message = "{pages.not.null}")
	private Integer pages;

	@NotNull(message = "{chapters.not.null}")
	private Integer chapters;

	@NotBlank(message = "{isbn.not.blank}")
	@Size(max = 100)
	@Pattern(regexp = "(?:ISBN(?:-10)?:?\\ )?(?=[0-9X]{10}$|(?=(?:[0-9]+[-\\ ]){3})[-\\ 0-9X]{13}$)[0-9]{1,5}[-\\ ]?[0-9]+[-\\ ]?[0-9]+[-\\ ]?[0-9X]$", message = "{isbn.not.valid}")					 
	private String isbn;

	@NotBlank(message = "{publisherName.not.blank}")
	@Size(max = 200)
	private String publisherName;

	@Valid
	@NotNull
	private AuthorDTO author;

}
