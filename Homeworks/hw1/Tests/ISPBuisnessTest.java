package edu.iastate.cs2280.hw1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * @author Abby Taylor
 * @date 9/24/2024
 * 
 * The ISPBusinessTest class is made to test each function in the ISPBusiness class. 
 * Tests the getProfit() method and the updatePlain() method
 */
class ISPBusinessTest {
	
	/**
	 * test() tests the functions given in the ISPBusiness class (getProfit() and updatePlain())
	 */
	@Test
	void test() {
		//create a 3x3 town for testing
        Town t = new Town(3, 3);

        //initialize the grid with all Casual cells
        for (int i = 0; i < t.getLength(); i++) {
        	for (int j = 0; j < t.getWidth(); j++) {
        		t.grid[i][j] = new Casual(t, i , j);
        	}
        }
        //test the actual vs. the calculated profit (both should be 9)
        assertEquals(9, ISPBusiness.getProfit(t), "The profit should be 9 dollars since each cell is a Casual in a 3x3 grid");
        
        //re-intialize the grid with the updatePlain() function to update all cells
        t = ISPBusiness.updatePlain(t);
        //test the first grid to see if it changed to a Reseller; it should using the rules of the neighborhood
        assertEquals(State.RESELLER, t.grid[0][0].who(), "The next cell in the top corner of the grid should be a Reseller based on the homework specifications");
	}


}
