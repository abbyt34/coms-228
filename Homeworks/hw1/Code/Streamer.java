package edu.iastate.cs2280.hw1;
/**
* @author Abby Taylor
* @date 9/20/2024
*
* This Streamer class extends TownCell in order to properly construct, label and update the current Streamer
* cells in the Town grid.
*/
public class Streamer extends TownCell{
	/**
	 * This function calls the TownCell constructor to populate a new Streamer cell
	 *
	 * @param p
	 * @param r
	 * @param c
	 */
	public Streamer(Town p, int r, int c) {
		//calls the TownCell constuctor
		super(p, r, c);
	}
	
	/**
	 * This function returns the STREAMER State to signify which cell is present
	 *
	 * @return State.STREAMER
	 */
	@Override
	public State who() {
		//returns the STREAMER state
		return State.STREAMER;
	}
	/**
	 * This function takes in the rules provided by the specification and assigns new, or leaves the same
	 * cell in the current Streamer cell's place.
	 *
	 * @param Town tNew
	 * @return Reseller(tNew, row, col)
	 * @return Outage(tNew, row, col)
	 * @return Empty(tNew, row, col)
	 * @return this
	 */
	@Override
	public TownCell next(Town tNew) {
		//reads the surrounding neighborhood
		census(nCensus);
		
		//if there is less than or equal to 1 Outage and Empty cell, then it populates a Reseller
		if ((nCensus[OUTAGE] + nCensus[EMPTY]) <= 1) {
			//the cell state remains the same
		       return new Reseller(tNew, row, col);
		}
		//if there is 1 or more Resellers in the neighborhood, it causes an Outage to the Streamer
		if (nCensus[RESELLER] != 0) {
			//returns a new Outage cell in it's place
			return new Outage(tNew, row, col);
		}
		//if there is 1 or more Outages in the neighborhood, it causes the Streamer to leave
		else if (nCensus[OUTAGE] != 0) {
			//returns a new Empty cell in it's place
			return new Empty(tNew, row, col);
		}
		//if no conditions are met, the cell remains the same
		else {
			//the cell state remains the same
			return new Streamer(tNew, row, col);
		}
	}
}
