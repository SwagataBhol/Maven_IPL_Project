package com.mavenproject.iplTesting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.model.mavenipl.Match;

class matchesPlayedPerYear {

	@Test
	void test() {
		
		
		
		HashMap<String , Integer> wrongData = null;
		
		List<Match> matches = App.readMatchesData();

		List<Match> sublistMatch=matches.subList(0,5);
		
		HashMap<String, Integer> subListResult = new HashMap<>(); 
		subListResult.put("2017", 5);
		
		
		
		
		
		//matches.getClass().getName();
		
		//Checking for Correct OutPut (5)
		assertEquals(App.MatchesPlayedPerYear(sublistMatch), subListResult);
		
		assertEquals(App.MatchesPlayedPerYear(sublistMatch).getClass().getSimpleName(),subListResult.getClass().getSimpleName());
		
		assertNotEquals(App.MatchesPlayedPerYear(matches), wrongData);
		
		assertEquals(App.MatchesPlayedPerYear(matches).get("2010"),60);
		
		assertEquals(matches.size(),636);
		
		assertThrows(NullPointerException.class,()->App.MatchesPlayedPerYear(null));
		
		
	}

}
