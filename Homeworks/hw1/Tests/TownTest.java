package edu.iastate.cs2280.hw1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

/**
 * @author Abby Taylor
 * @date 9/21/2024
 * 
 * TownTest class tests the given functions in the Town class
 */
class TownTest {

	/**
	 * populateAndMeasureTest() constructs both types of Town objects (one random and one via file) and uses their getWidth() and getLength() functions to
	 * verify they were populated right.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void populateAndMeasureTest() throws FileNotFoundException {
		//contruct a new Town method using the random method
		Town t = new Town(5, 5);
		t.randomInit(4);
		//test the length and width of the randomly made Town
		assertEquals(5, t.getLength(), "Length should be 5");
		assertEquals(5, t.getWidth(), "Width should be 5");
		
		//construct a new Town method using the file reader
		Town t2 = new Town("ISP4x4.txt");
		//test the length and width of the randomly made Town
		assertEquals(4, t2.getLength(), "Length should be 4");
		assertEquals(4, t2.getWidth(), "Width should be 4");	
	}

	/**
	 * toStringTest() tests that a "hand made" grid matches the toString() printed version of the same grid
	 */
	@Test
	public void toStringTest() {
		//create a new empty 3x3 Town object for testing
		Town t = new Town(3,3);
		
		//populate random cells in each cell
		t.grid[0][0] = new Casual(t, 0, 0);
		t.grid[0][1] = new Streamer(t, 0, 1);
		t.grid[0][2] = new Casual(t, 0, 2);
		t.grid[1][0] = new Empty(t, 1, 0);
		t.grid[1][1] = new Reseller(t, 1, 1);
		t.grid[1][2] = new Casual(t, 1, 2);
		t.grid[2][0] = new Outage(t, 2, 0);
		t.grid[2][1] = new Outage(t, 2, 1);
		t.grid[2][2] = new Empty(t, 2, 2);
		
		//find the expected grid based on the population above
		String expectedGrid = 
				"C S C \n" +
				"E R C \n" +
				"O O E \n";
		
		//use toString() to find the actual grid of the Town object
		String actualGrid = t.toString();
		
		//test to see if the grids match
		assertEquals(expectedGrid, actualGrid, "The grids should match.");
	}
}
