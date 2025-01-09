package edu.iastate.cs2280.hw1;
/**
* @author Abby Taylor
*
* @date 9/18/2024
*/
public class Empty extends TownCell{
	/**
	 * Constructor for the Empty Cell. Calls super to TownCell
	 *
	 * @param p
	 * @param r
	 * @param c
	 */
	public Empty(Town p, int r, int c) {
		//calls the TownCell constructor
		super(p, r, c);
	}
	
	/**
	 * This function returns the current state of the cell
	 *
	 * @return State.EMPTY : the empty state cell
	 */
	@Override
	public State who() {
		//returns the EMPTY State
		return State.EMPTY;
	}
	
	/**
	 * This function takes in a new Town and searches the neighborhood for the surrounding neighbors of the cell.
	 * According to the rules stated in the homework, certain numbers of certain cells make different
	 * moves.
	 *
	 * @param Town tNew
	 * @return Reseller(tNew, row, col)
	 * @return Streamer(tNew, row, col)
	 * @return Casual(tNew, row, col)
	 */
	@Override
	public TownCell next (Town tNew) {
		//reads the surrounding neighborhood
		census(nCensus);
		
		//if there are less than or equal to one Outage plus Empty cell, the cell turns into a Reseller
		if ((nCensus[OUTAGE] + nCensus[EMPTY]) <= 1) {
			//returns a new Reseller cell
       	return new Reseller(tNew, row, col);
       }
		//if there is five or more Casual cells, the cell turns into a Streamer
		else if (nCensus[CASUAL] >= 5) {
			//returns a new Streamer cell
			return new Streamer(tNew, row, col);
		}
		//if no condition is met, a Casual cell populates in an Empty space
		else {
			//returns a new Casual cell
			return new Casual(tNew, row, col);
		}
	}
}
