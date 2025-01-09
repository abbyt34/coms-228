package edu.iastate.cs2280.hw1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * @author Abby Taylor
 * @date 9/23/2024
 * 
 * The TownCellTest class tests each method given in the TownCell class.
 * This class tests the next() method.
 */
class TownCellTest{
	
	/**
	 * test() tests the next() function and the who() functions in the TownCell class
	 */
	@Test
	void test() {
		//create new 3x3 Town for testing
		Town t = new Town(3, 3);
		
		//iterate through the entire town and intialize each cell with a Casual cell
		for (int i = 0; i < t.getLength(); i++) {
			for (int j = 0; j < t.getWidth(); j++) {
				t.grid[i][j] = new Casual(t, i, j);
			}
		}
		
		//find the first grid and use the next() function to get the next cell type
		TownCell cell = t.grid[0][0].next(t);
		//the next cell should be equal to the Reseller State type
		assertEquals(State.RESELLER, cell.who(), "The cell should be of the Reseller State type based on the specifications");
		
	}

}
