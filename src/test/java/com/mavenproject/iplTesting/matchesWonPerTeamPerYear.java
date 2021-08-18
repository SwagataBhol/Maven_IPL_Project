package com.mavenproject.iplTesting;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;


import com.model.mavenipl.Match;

class matchesWonPerTeamPerYear {

	@Test
	void test() {
		
		List<Match> matches=App.readMatchesData();

		HashMap<String,HashMap<String , Integer>> subListResult2017=new HashMap<>();
		
		List<Match> sublist=matches.subList(0, 11);
		
//		{Gujarat Lions=4, Mumbai Indians=12, Rising Pune Supergiant=10, Sunrisers Hyderabad=8, 
//		Kings XI Punjab=7, Delhi Daredevils=6, Kolkata Knight Riders=9, Royal Challengers Bangalore=3}
		
		HashMap<String,Integer> subHashMap=new HashMap<>();
		subHashMap.put("Sunrisers Hyderabad", 2);
		subHashMap.put("Rising Pune Supergiant",1);
		subHashMap.put("Kolkata Knight Riders",2);
		subHashMap.put("Kings XI Punjab",2);
		subHashMap.put("Royal Challengers Bangalore",1);
		subHashMap.put("Mumbai Indians",2);
		subHashMap.put("Delhi Daredevils",1);
		
		
		subListResult2017.put("2017", subHashMap);
	
		
		HashMap<String,HashMap<String , Integer>> wrongData = null;
		
		
//		comparing outputs (5)
		assertEquals(App.MatchesWonPerTeamPerYear(sublist),subListResult2017);
		
		assertNotEquals(App.MatchesWonPerTeamPerYear(matches),wrongData);
		
		assertEquals(matches.size(),636);
		
		assertEquals(App.MatchesWonPerTeamPerYear(matches).getClass().getSimpleName(),subListResult2017.getClass().getSimpleName());
		
		assertThrows(NullPointerException.class,()->App.MatchesWonPerTeamPerYear(null));
		
		
		
	
		
		
	}

	}
