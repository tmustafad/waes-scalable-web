package com.rsouza01.waesscalableweb.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public enum PanelSide {
	LEFT("left"), 
	RIGHT("right");

	private String panelSide;
}
