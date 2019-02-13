package com.rsouza01.waesscalableweb.service;

import com.rsouza01.waesscalableweb.enums.PanelSide;
import com.rsouza01.waesscalableweb.model.DataDiffEntry;

public interface DataDiffService {

	void inputData(String id, String input, PanelSide panelSide);

	DataDiffEntry difference(String id);
}
