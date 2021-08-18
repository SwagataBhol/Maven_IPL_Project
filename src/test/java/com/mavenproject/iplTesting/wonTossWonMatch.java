package com.mavenproject.iplTesting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.model.mavenipl.Match;

class wonTossWonMatch {

	@Test
	void test() {
		
		HashMap<String,Integer> wonTossWonMatch=new HashMap<>();
		List<Match> matches=App.readMatchesData();
		
		HashMap<String,Integer> wrongData=null;
		
		wonTossWonMatch=App.TossAndMatchesWonPerTeam(matches);
		int sizeOfData=wonTossWonMatch.size();
		
		assertEquals(App.TossAndMatchesWonPerTeam(matches),wonTossWonMatch);
		assertEquals(App.TossAndMatchesWonPerTeam(matches).size(),sizeOfData);
		assertNotEquals(App.TossAndMatchesWonPerTeam(matches),wrongData);
		
	}

}
