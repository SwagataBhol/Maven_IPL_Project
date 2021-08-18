package com.mavenproject.iplTesting;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.mavenproject.iplTesting.*;
import com.model.mavenipl.Deliveries;
import com.model.mavenipl.Match;

class extraRunsPerTeam {

	@Test
	void test() {
		
		
		List<Deliveries> delivery=App.readDeliveriesData();
		List<Match> matches=App.readMatchesData();
		
	
		HashMap<String,Integer> wrongData=null;
		
		int dataForGujaratLions=98;
		
		HashMap<String,Integer> sublistResult=new HashMap<>();
		
		List<Match> sublistMatch=matches.subList(577,590);
		List<Deliveries> sublistDelivery=delivery.subList(139076,140000);
		
//		{Mumbai Indians=17, Kings XI Punjab=8, Kolkata Knight Riders=7, Royal Challengers Bangalore=3}

		sublistResult.put("Mumbai Indians",17);
		sublistResult.put("Kings XI Punjab",8);
		sublistResult.put("Kolkata Knight Riders",7);
		sublistResult.put("Royal Challengers Bangalore",3);
		
	
		
//		checking outputs of ExtraRunsPerTeam2016 (4)
		
		assertEquals(App.ExtraRunsPerTeam2016(sublistDelivery, sublistMatch),sublistResult);
		
		assertEquals(App.ExtraRunsPerTeam2016(delivery, matches).get("Gujarat Lions"),98);
		
		assertNotEquals(App.ExtraRunsPerTeam2016(delivery, matches),wrongData);
		
		assertEquals(delivery.size(),150460);
		
		assertEquals(matches.size(),636);
		
		assertEquals(App.ExtraRunsPerTeam2016(sublistDelivery, sublistMatch).getClass().getSimpleName(),sublistResult.getClass().getSimpleName());
		
		assertThrows(NullPointerException.class,()->App.ExtraRunsPerTeam2016(null,sublistMatch));

			
	}

}
