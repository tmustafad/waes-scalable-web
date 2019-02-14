package com.rsouza01.waesscalableweb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class DifferenceResponse {

    private String status;

    private String message;
    
	private String[] differences;
}
