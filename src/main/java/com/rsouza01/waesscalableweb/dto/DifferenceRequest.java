package com.rsouza01.waesscalableweb.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class DifferenceRequest {

	@NotNull
	@Size(min=1, message="The content must be provided")
    private String base64Content;

}
