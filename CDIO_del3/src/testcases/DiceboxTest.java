package testcases;

import static org.junit.Assert.*;

import org.junit.Test;

import entity.Dice;
import entity.Dicebox;

public class DiceboxTest {


	@Test
	public void diceBoxTest() {
		

		Dicebox db = new Dicebox();

		for(int i = 0;i < 10000;i++){
			db.roll();
			assertTrue(db.d1.roll() >= 1 && db.d1.roll() <= 6);
			assertTrue(db.d2.roll() >= 1 && db.d2.roll() <= 6);
			assertTrue(db.getDiceSum()>=2 && db.getDiceSum() <=12);
		}
	}

	
	


}


