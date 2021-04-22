package fr.unice.polytech.si3.qgl.sunny1000;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.polytech.si3.qgl.regatta.cockpit.ICockpit;

import fr.unice.polytech.si3.qgl.sunny1000.game.InitGame;
import fr.unice.polytech.si3.qgl.sunny1000.game.NextRound;
import fr.unice.polytech.si3.qgl.sunny1000.game.goal.regattaGoal.RegattaGoal;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Util;

import static fr.unice.polytech.si3.qgl.sunny1000.game.properties.Util.*;


public class Cockpit implements ICockpit {
	private Captain captain;
	private List<String> log;


	public void initGame(String game) {
		System.out.println("Init game input: " + game);
		ObjectMapper objectMapper = new ObjectMapper();
		InitGame iGame = null;
		try {
			iGame = objectMapper.readValue(game, InitGame.class);
			captain=new Captain(iGame);
		} catch (IOException e) {
			e.printStackTrace();
		}
		log=new ArrayList<String>();
	}

	/**
	 * The nextRound methode take a JsonString and parse it
	 * to Objects the generate the Actions that we want to execute
	 * @see ObjectMapper
	 * we used Object mapper to parse the Json
	 * @param round
	 * String that contains the Json String
	 * @return actionsJson
	 * This methode return a string that contains the actions
	 */
	public String nextRound(String round) {

		//Object that contains the Json Data
		NextRound r=null;
		try {
			r = getObjectMapper().readValue(round, NextRound.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		captain.nextRound(r);
		String json="";
		try {
			json=Util.getObjectMapper().writeValueAsString(captain.choseActions());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}


	public Captain getCaptain() {
		return captain;
	}
	@Override
	public List<String> getLogs() {
		return log;
	}
}
