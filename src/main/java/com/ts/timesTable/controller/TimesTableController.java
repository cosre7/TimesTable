package com.ts.timesTable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.ts.timesTable.domain.TimesTable;
import com.ts.timesTable.service.TimesTableService;

@Controller
public class TimesTableController {
	
	@Autowired
	TimesTableService timesTableService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@ResponseBody
	@RequestMapping(value= "/", method = RequestMethod.POST) 
	public String create(TimesTable timesTable) {
		
		int firstMultiplier = 1;
		
		String[][] table = timesTableService.generateTimesTable(
				timesTable.getFirstTimes(), 
				timesTable.getLastTimes(), 
				firstMultiplier, 
				timesTable.getLastMultiplier());
		
		Gson gson = new Gson();
		String gsonString = gson.toJson(table);
		
		return gsonString;
	}
}
