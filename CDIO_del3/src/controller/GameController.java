
package controller;

import java.awt.Color;
import desktop_codebehind.Car;
//import desktop_fields.*;
import desktop_fields.Street;
import desktop_resources.GUI;
import entity.*;

public class GameController {
	private Dicebox box = new Dicebox();
	private Player[] players = new Player[6];
	private int playerCount;
	private Field[] fields;
	private int playerTurn;

	public void run() {
		Player tmp = players[playerTurn];
		switch(tmp.getInformation()){
		case -1: 
			GUI.showMessage(String.format("Din balance er nu: %d fordi du ikke kan styre din økonomi", tmp.getBalance()));  
			break;
		case 0:
			String answer = GUI.getUserSelection("Vil du koebe feltet?", "Ja","Nej");
			switch(answer){
			case "Ja": 
				if(tmp.buyField((Ownable)fields[tmp.getPosition()])) {
					GUI.setBalance(tmp.getName(), tmp.getBalance());
					GUI.setOwner(tmp.getPosition(), tmp.getName());
				} else {
					GUI.showMessage("Du har ikke penge nok!");
				}
				break;
			}
			break;
		case 1: 
			GUI.showMessage("Du ejer feltet");
			break;
		case 2:
			if(fields[tmp.getPosition()] instanceof Tax) {
				GUI.showMessage("You have payed!!!");
			} else {
				GUI.showMessage("SHOW ME DA MONEYYYYY!!!");
			}
		}
		this.switchTurn();
		
	}

	private void playerTurn(Player player) {
		int roll = box.roll();
		int points = fields[roll].getPoints();
		String fieldname = fields[roll].getName();
		boolean check_account = player.addToBalance(points);

		if (check_account == true) {
			GUI.setBalance(player.getName(), player.getBalance());
		} else {
			player.setBankrupt();
		}

		System.out.println("spiller" + player.getName() + "  har slaaet: " + roll + " han fik: " + points
				+ " og han har landet paa felt: " + fields + ", saldo:" + player.getBalance());
		GUI.removeAllCars(player.getName());
		GUI.setCar(roll - 1, player.getName());
		// Suspend excecution for 200 ms
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	// creates fields in array
	public void setupGame() {
		fields = new Field[] { new Territory("Tribe encampment", 100, 1000), new Territory("Crater", 300, 1500),
				new Territory("Mountain", 500, 2000), new Territory("Cold desert", 700, 3000),
				new Territory("Black cave", 1000, 4000), new Territory("The werewall", 1300, 4300),
				new Territory("Mountain village", 1600, 4750), new Territory("South Citadel", 2000, 5000),
				new Territory("Palads gates", 2600, 5500), new Territory("Tower", 3200, 6000),
				new Territory("Castle", 4000, 8000), new Refuge("Walled city", 5000), new Refuge("Monastery", 500),
				new LaborCamp("Huts in the mountain", 100, 2500), new LaborCamp("The pit", 100, 2500),
				new Tax("Goldmine", 0), new Tax("Caravan", 0), new Fleet("Second Sail", 4000),
				new Fleet("Sea Grover", 4000), new Fleet("The Buccanees", 4000), new Fleet("Privateer armade", 4000) };
	}

	// adds fields to GUI
	public void setupGUI() {

		desktop_fields.Field[] fields = new desktop_fields.Field[21];
		Street s = null;

		s = new Street.Builder().setBgColor(Color.gray).setTitle("Tribe encampment").build();
		s.setDescription("");
		s.setSubText("");
		fields[0] = s;

		s = new Street.Builder().setBgColor(Color.gray).setTitle("Crater").build();
		s.setDescription("");
		s.setSubText("");
		fields[1] = s;

		s = new Street.Builder().setBgColor(Color.white).setTitle("Mountain").build();
		s.setDescription("");
		s.setSubText("");
		fields[2] = s;

		s = new Street.Builder().setBgColor(Color.gray).setTitle("Cold Desert").build();
		s.setDescription("");
		s.setSubText("");
		fields[3] = s;

		s = new Street.Builder().setBgColor(Color.green).setTitle("Black cave").build();
		s.setDescription("");
		s.setSubText("");
		fields[4] = s;

		s = new Street.Builder().setBgColor(Color.orange).setTitle("The werewall").build();
		s.setDescription("");
		s.setSubText("");
		fields[5] = s;

		s = new Street.Builder().setBgColor(Color.DARK_GRAY).setTitle("Mountain village").build();
		s.setDescription("");
		s.setSubText("");
		fields[6] = s;

		s = new Street.Builder().setBgColor(Color.magenta).setTitle("South Citadel").build();
		s.setDescription("");
		s.setSubText("");
		fields[7] = s;

		s = new Street.Builder().setBgColor(Color.CYAN).setTitle("Palace gates").build();
		s.setDescription("");
		s.setSubText("");
		fields[8] = s;

		s = new Street.Builder().setBgColor(Color.gray).setTitle("Tower").build();
		s.setDescription("");
		s.setSubText("");
		fields[9] = s;

		s = new Street.Builder().setBgColor(Color.blue).setTitle("Castle").build();
		s.setDescription("");
		s.setSubText("");
		fields[10] = s;

		s = new Street.Builder().setBgColor(Color.white).setTitle("Walled city").build();
		s.setDescription("");
		s.setSubText("");
		fields[11] = s;

		s = new Street.Builder().setBgColor(Color.green).setTitle("Monastery").build();
		s.setDescription("");
		s.setSubText("");
		fields[12] = s;

		desktop_fields.Refuge r = null;
		r = new desktop_fields.Refuge.Builder().setBgColor(Color.green).setTitle("Huts in the mountain").build();
		r.setDescription("");
		r.setSubText("");
		fields[13] = r;

		r = new desktop_fields.Refuge.Builder().setBgColor(Color.DARK_GRAY).setTitle("The pit").build();
		r.setDescription("");
		r.setSubText("");
		fields[14] = r;

		desktop_fields.Tax t = null;
		t = new desktop_fields.Tax.Builder().setBgColor(Color.magenta).setTitle("Goldmine").build();
		t.setDescription("Fuck");
		t.setSubText("");
		fields[15] = t;

		t = new desktop_fields.Tax.Builder().setBgColor(Color.CYAN).setTitle("Caravan").build();
		t.setDescription("");
		t.setSubText("");
		fields[16] = t;

		desktop_fields.Shipping f = null;
		f = new desktop_fields.Shipping.Builder().setBgColor(Color.gray).setTitle("Second sail").build();
		f.setDescription("");
		f.setSubText("");
		fields[17] = f;

		f = new desktop_fields.Shipping.Builder().setBgColor(Color.blue).setTitle("Sea Grover").build();
		f.setDescription("");
		f.setSubText("");
		fields[18] = f;
		f = new desktop_fields.Shipping.Builder().setBgColor(Color.CYAN).setTitle("The Buccanees").build();
		f.setDescription("");
		f.setSubText("");
		fields[19] = f;

		f = new desktop_fields.Shipping.Builder().setBgColor(Color.green).setTitle("Privateer armade").build();
		f.setDescription("");
		f.setSubText("");
		fields[20] = f;

		GUI.create(fields);
	}

	private Car getCar(int i) {
		Car car = null;
		switch (i) {
		case 0:
			car = new Car.Builder().typeCar().patternHorizontalDualColor().primaryColor(Color.RED).build();
			break;
		case 1:
			car = new Car.Builder().typeCar().patternHorizontalDualColor().primaryColor(Color.BLACK).build();
			break;
		case 2:
			car = new Car.Builder().typeCar().patternHorizontalDualColor().primaryColor(Color.BLUE).build();
			break;
		case 3:
			car = new Car.Builder().typeCar().patternHorizontalDualColor().primaryColor(Color.GREEN).build();
			break;
		case 4:
			car = new Car.Builder().typeCar().patternHorizontalDualColor().primaryColor(Color.GRAY).build();
			break;
		case 5:
			car = new Car.Builder().typeCar().patternHorizontalDualColor().primaryColor(Color.CYAN).build();
			break;
		}
		return car;
	}

	//
	public void addPlayer() {
		String playerName = GUI.getUserString("Write user name, minum 2-6 names, after x - names/player, leave empty space press OK);

		if (playerName.length() != 0) {
			GUI.addPlayer(playerName, 30000, getCar(playerCount));
			playerCount++;
			for (int i = 0; i < players.length; i++) {
				if (players[i] == null) {
					players[i] = new Player(playerName, i);
					break;
				}
			}
		}
	}

	public void roll() {
		GUI.getUserButtonPressed("Kast terning", "Dice");
		int r = box.roll();
		GUI.setDice(box.getDice()[0].getValue(), box.getDice()[1].getValue());
		players[playerTurn].setLastRoll(r);
		players[playerTurn].movePlayer(r);
		GUI.setCar(players[playerTurn].getPosition(), players[playerTurn].getName());
	}

	public void switchTurn() {
		int leif = 0;
		for (int i = 0; i < players.length; i++) {
			if (players[i] != null) {
				leif++;
			}
		}
		switch (playerTurn) {
		case 0:
			playerTurn = 1;
			break;
		case 1:
			if(leif == 2) {
				playerTurn = 0;
			} else {
				playerTurn = 2;
			}
			break;
		case 2:
			if (leif == 3){
				playerTurn = 0;
			} else {
				playerTurn = 3;
			}
			break;
		case 3:
			if (leif == 4) {
				playerTurn = 0;
			} else {
				playerTurn = 4;
			}
			break;
		case 4:
			if (leif == 5) {
				playerTurn = 0;
			} else {
				playerTurn = 5;
			}
			break;
		case 5:
			playerTurn = 0;
			break;
		}
	}
	
	public int landOnField(){
		Player unicorn = players[playerTurn];
		int position = unicorn.getPosition();
		fields[position].landOnField(unicorn);
		return unicorn.getInformation();
	}
	
}
