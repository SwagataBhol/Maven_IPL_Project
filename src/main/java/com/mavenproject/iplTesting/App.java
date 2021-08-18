package com.mavenproject.iplTesting;

import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.model.mavenipl.*;
//import org.json.*;

public class App {
	
//	matches index
	static int id=0;
	static int season=1;
	static int city=2;
	static int date=3;
	static int team1=4;
	static int team2=5;
	static int tossWinner=6;
	static int tossDecision=7;
	static int result=8;
	static int dlApplied=9;
	static int winner=10;
	static int winByRuns=11;
	static int winByWicket=12;
	static int playerOfMatch=13;
	static int venue=14;
	static int umpire1=15;
	static int umpire2=16;
	static int umpire3=17;
	
// deliveries index
	static int matchId=0;
	static int inning=1;
	static int battingTeam=2;
	static int bowlingTeam=3;
	static int over=4;
	static int ball=5;
	static int batsman=6;
	static int nonStriker=7;
	static int bowler=8;
	static int isSuperOver=9;
	static int wideRuns=10;
	static int byeRuns=11;
	static int legbyeRuns=12;
	static int noballRuns=13;
	static int penaltyRuns=14;
	static int batsmanRuns=15;
	static int extraRuns=16;
	static int totalRuns=17;
	static int playerDismissed=18;
	static int dismissalKind=19;
	static int fielder=20;
	
	
//	read matches data 
public static List<Match> readMatchesData() {
		List<Match> matches = new ArrayList<Match>();

			
	String line="";
	int ignore=0;
	
		
		try {
			BufferedReader bf=new BufferedReader(new FileReader("./data/matches.csv"));
			while((line=bf.readLine())!= null) 
			{
				Match match=new Match();
				
				  if(ignore == 0){
					  ignore++;
	                  continue;
	              }
				  String[] data=line.split(",");
				  match.setSeason(data[season]);
				  match.setWinner(data[winner]);
				  match.setId(data[id]);
				  match.setTossWinner(data[tossWinner]);
				 matches.add(match);
				 
			}
			
	}
		catch(IOException e) {
			e.printStackTrace();
		}
		return matches;
	}
// read deliveries data 
public static List<Deliveries> readDeliveriesData() {
	List<Deliveries> deliveries = new ArrayList<Deliveries>();

		
String line="";
int ignore=0;

	
	try {
		BufferedReader bf=new BufferedReader(new FileReader("./data/deliveries.csv"));
		while((line=bf.readLine())!= null) 
		{
			Deliveries delivery=new Deliveries();
			
			  if(ignore == 0){
				  ignore++;
                  continue;
              }
			  String[] data=line.split(",");
			  delivery.setBowlingTeam(data[bowlingTeam]);
			  delivery.setMatchId(data[matchId]);
			  delivery.setExtraRuns(data[extraRuns]);
			  delivery.setBowler(data[bowler]);
			  delivery.setBall(data[ball]);
			  delivery.setTotalRuns(data[totalRuns]);
			  
			  deliveries.add(delivery);
//			  System.out.println(deliveries.size());
			 
		}
		 
}
	catch(IOException e) {
		e.printStackTrace();
	}
	return deliveries;
}

// 1st problem
public static HashMap<String,Integer> MatchesPlayedPerYear(List<Match> matchData){
	if(matchData.equals(null)) {
		return null;
	}
	
	int count=0;
	HashMap<String, Integer> season = new HashMap<String,Integer>();
	for(int i=0;i<matchData.size();i++) {
		
		String year=matchData.get(i).getSeason();
		if(season.containsKey(year)) {
			int c=season.get(year);
			c+=1;
			season.put(year, c);
		}
		else {
			count=1;
			season.put(year, count);
		}
	}
	
	
	return season;
	
	
}

// 2nd problem
public static HashMap<String,HashMap<String,Integer>> MatchesWonPerTeamPerYear(List<Match> matchData){
	
	HashMap<String, HashMap<String, Integer>> matchesWon = new HashMap<String, HashMap<String, Integer>>();
	
	for(int i=0;i<matchData.size();i++) {
		
		String year=matchData.get(i).getSeason();
		String teamName=matchData.get(i).getWinner();
		
		if(matchesWon.containsKey(year)) {
			
			if(matchesWon.get(year).containsKey(teamName)) {
				int c=matchesWon.get(year).get(teamName);
				c+=1;
				matchesWon.get(year).put(teamName,c);
			}
			else {
				
				matchesWon.get(year).put(teamName,1);
			}
		}
		else{
			
			int count=1;
			HashMap<String, Integer> winnerr = new HashMap<String,Integer>();
			 winnerr.put(teamName,count);
			 matchesWon.put(year,winnerr );
		}
	}
	return matchesWon;
}

// 3rd problem
public static HashMap<String,Integer> ExtraRunsPerTeam2016(List<Deliveries> deliveryyData,List<Match> matchData){
	
	HashMap<String,Integer> extraRunsPerTeam=new HashMap<String,Integer>();
	List<String> ids2016=new ArrayList<String>();
	
	for(int i=0;i<matchData.size();i++) {
		
		String years=matchData.get(i).getSeason();

		String id=matchData.get(i).getId();
		if(years.equals("2016")) {
			
			
			ids2016.add(id);

		}	
	}

	
	

	for(int i=0;i<deliveryyData.size();i++) {
		
		String checkid=deliveryyData.get(i).getMatchId();
		
		
		if(ids2016.contains(checkid)) {
			
//			System.out.println(i);
			String bowlingTeam=deliveryyData.get(i).getBowlingTeam();
			String extraRuns=deliveryyData.get(i).getExtraRuns();
			int extraRunsValue=Integer.parseInt(extraRuns);
			
			if(extraRunsPerTeam.containsKey(bowlingTeam)) {
				
				int cnt=extraRunsPerTeam.get(bowlingTeam);
				cnt+=extraRunsValue;
				extraRunsPerTeam.put(bowlingTeam,cnt);
			}
			else {
				
				extraRunsPerTeam.put(bowlingTeam,extraRunsValue );
			}	
		}	
	}
//	System.out.println("index"+index);
	
	return extraRunsPerTeam;
}

//4th problem
public static HashMap<String,Double> TopTenEconomicalBowlers2015(List<Deliveries> deliveryyData,List<Match> matchData){
	
	List<String> ids2015=new ArrayList<String>();
	HashMap<Double,String> topTenEcoBowler=new HashMap<Double,String>();
	HashMap<String,Double> topTenEcoBowler2015=new HashMap<String,Double>();
	HashMap<String,Integer> bowlerBall=new HashMap<String,Integer>();
	HashMap<String,Integer> bowlerRun=new HashMap<String,Integer>();
	List<Double> sortedlist=new ArrayList<Double>();
	
//	filtering 2015 ids
	for(int i=0; i<matchData.size();i++) {
		
		String id=matchData.get(i).getId();
		String year2015=matchData.get(i).getSeason();
		if(year2015.equals("2015")) {
			
			
			ids2015.add(id);
		}
	}
	
	for(int i=0; i<deliveryyData.size(); i++) {
		
		String deliveryid2015=deliveryyData.get(i).getMatchId();
		if(ids2015.contains(deliveryid2015)) {
			
			String bowler=deliveryyData.get(i).getBowler();
			int ball=Integer.parseInt(deliveryyData.get(i).getBall());
			int runs=Integer.parseInt(deliveryyData.get(i).getTotalRuns());
			if(bowlerBall.containsKey(bowler)){
				
				int ballCount=bowlerBall.get(bowler);
				ballCount+=ball;
				bowlerBall.put(bowler, ballCount);
				
				int runCount=bowlerRun.get(bowler);
				runCount+=runs;
				bowlerRun.put(bowler, runCount);
				
				
			}
			else {
				
				bowlerBall.put(bowler,ball);
				bowlerRun.put(bowler, runs);
				
			}
		}
		
	}
	
	for(String key : bowlerBall.keySet()) {
		
		double totalRuns=bowlerRun.get(key);
		double totalBalls=bowlerBall.get(key);
		double calculation=totalRuns/totalBalls;
		
		topTenEcoBowler.put(calculation, key);
		sortedlist.add(calculation);
	
	
	}
	Collections.sort(sortedlist,Collections.reverseOrder());
	List<Double> sublist = sortedlist.subList(0, 10);

	
	for(int i=0; i<sublist.size();i++) {
		
		if(topTenEcoBowler.containsKey(sublist.get(i))) {
			
			String BowlerName=topTenEcoBowler.get(sublist.get(i));
			
			topTenEcoBowler2015.put(BowlerName,sublist.get(i));

		}
	}
	System.out.println(topTenEcoBowler2015);
	return topTenEcoBowler2015;
	
}

public static HashMap<String,Integer> TossAndMatchesWonPerTeam(List<Match> matchData){
	
	HashMap<String,Integer> wonTossAndMatch=new HashMap<String,Integer>();
	for(int i=0;i<matchData.size();i++) {
		
		String wonToss=matchData.get(i).getTossWinner();
		String winner=matchData.get(i).getWinner();
		
		if(wonToss.equals(winner)) {
			
			if(wonTossAndMatch.containsKey(winner)) {
				
				int c=wonTossAndMatch.get(winner);
				c+=1;
				wonTossAndMatch.put(winner, c);
			}
			else {
				
				wonTossAndMatch.put(winner, 1);
			}
		}
	}
	return wonTossAndMatch;
}


	public static void main(String args[]) {
		
	
	
	
	List<Match> matchData=readMatchesData(); //returning matches data
	List<Deliveries> deliveryyData=readDeliveriesData(); //returning deliveries data
	
	

	HashMap<String,Integer> problem1=MatchesPlayedPerYear(matchData); //function call for matches played per year
	System.out.println(problem1);

//	problem1.forEach((k,v) -> System.out.println("Key = "
//			+ k + ", Value = " + v));
//	
//	
	HashMap<String, HashMap<String, Integer>> problem2 =MatchesWonPerTeamPerYear(matchData); //function call for matches won per team per year
	System.out.println(problem2);
//	problem2.forEach((k,v) -> System.out.println("Key = "
//			+ k + ", Value = " + v));
//
//	
	HashMap<String,Integer> problem3=ExtraRunsPerTeam2016(deliveryyData,matchData); //function call for extra runs played per year
	System.out.println(problem3);
//	problem3.forEach((k,v) -> System.out.println("Key = "
//			+ k + ", Value = " + v));
//	
	HashMap<String,Double> problem4=TopTenEconomicalBowlers2015(deliveryyData,matchData);//top 10 economical bowler in 2015
	System.out.println(problem4);
//	problem4.forEach((k,v) -> System.out.printf("Key = "
//			+ k + ", Value = " +"%.02f", v));
//	
	HashMap<String,Integer> problem5=TossAndMatchesWonPerTeam(matchData);// Find the number of times each team won the toss and also won the match
	System.out.println(problem5);
//	problem5.forEach((k,v) -> System.out.printf(" Key = "
//			+ k + ", Value = " + v));
	}
}
	
//######################################################################################################################
//####################################################################################################################	

