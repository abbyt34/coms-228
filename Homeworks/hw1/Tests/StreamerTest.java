package edu.iastate.cs2280.hw1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

/**
 * @author Abby Taylor
 * @date 9/20/2024
 * 
 * The StreamerTest class tests each method given in the Streamer class
 * This class tests the who() function, the next() function, and the Streamer constructor
 */
class StreamerTest {

	/**
	 * identifyNextTest() tests the who() and next() functions in the Streamer class
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void identifyNextTest() throws FileNotFoundException {
		//create a new Town object using the file reader
		Town t = new Town("ISP4x4.txt");
		
		//find a cell in the grid
		TownCell cell = t.grid[2][1];
		//test to see if the cell matches the correct Streamer State type
		assertEquals(State.STREAMER, cell.who(), "The cell should be a Streamer cell");
		
		//progress the given cell to the next type
		TownCell nextCell = cell.next(t);
		//test to see if the cell matches the right nextCell based on specification rules
		assertEquals(State.OUTAGE, nextCell.who());
	}
	
	/**
	 * constructorTest() tests the constructor function for the Streamer class
	 */
	@Test
	public void contructorTest() {
		//create a new empty 3x3 Town object
		Town t = new Town(3,3);
		
		//populate a Streamer into a cell in the grid
		t.grid[0][2] = new Streamer(t, 0, 2);
		//find the State of the given cell
		State actualState = t.grid[0][2].who();
		//test to see if the cell matches the right expected State
		assertEquals(State.STREAMER, actualState, "The state of the cell should be Streamer.");
	}

}
