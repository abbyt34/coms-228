package edu.iastate.cs2280.hw1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

/**
 * @author Abby Taylor
 * @date 9/20/2024
 * 
 * The EmptyTest method tests each method given in the Empty class.
 * This class tests the who() function, the next() function, and the Empty constructor.
 */
class EmptyTest {

	/**
	 * identifyNextTest() creates a new Town object using the file reader, finds a certain grid space, and wuses who() to valigate it's State.
	 * Then it uses next() to update said cell and valigates it's State with the who() function.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void idenitfyNextTest() throws FileNotFoundException {
		//creates new Town object t using the file reader function
		Town t = new Town("ISP4x4.txt");
		
		//create a new TownCell object cell and assign a grid space to it
		TownCell cell = t.grid[1][0];
		//use who() to validate that said grid space's state is State.EMPTY
		assertEquals(State.EMPTY, cell.who(), "The cell should be an Empty cell type");
		
		//creates another TownCell and uses the next() function to update the last cell into the next progression
		TownCell nextCell = cell.next(t);
		//use who() to validate that said grid space's state is State.CASUAL
		assertEquals(State.CASUAL, nextCell.who(), "The cell should be an Casual cell type");
	}
	
	/**
	 * constructorTest() creates an empty Town grid and assigned a Empty cell to it and proves it exists using the who() function
	 * 
	 */
	@Test
	public void contructorTest() {
		//create a new empty Town grid
		Town t = new Town(2,2);
		
		//assigns a cell to be an Empty State by using the constructor
		t.grid[1][0] = new Empty(t, 1, 0);
		
		//assign the grid a State using who()
		State actualState = t.grid[1][0].who();
		//compare the state and actualState of the cell to make sure they match and were correctly applied in the constructor
		assertEquals(State.EMPTY, actualState, "The state of the cell should be empty.");
	}

}
