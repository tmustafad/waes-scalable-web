package com.rsouza01.waesscalableweb.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for the Panel Upload
 * @author Rodrigo Souza (rsouza01)
 *
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class DifferenceRequest {

	/**
	 * Json enconded in a base-64 format
	 */
	@NotNull
	@Size(min=1, message="The content must be provided")
    private String base64Content;

}
