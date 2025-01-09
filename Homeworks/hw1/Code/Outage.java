package edu.iastate.cs2280.hw1;
/**
* @author Abby Taylor
* @date 9/20/2024
*
* This Outage class extends TownCell in order to properly construct, label and update the Outage cells present
* in the Town grid.
*/
public class Outage extends TownCell{
	/**
	 * This function calls the TownCell constructor to populate a new Outage cell
	 *
	 * @param p
	 * @param r
	 * @param c
	 */
	public Outage(Town p, int r, int c) {
		//calls the TownCell constructor
		super(p, r, c);
	}
	
	/**
	 * This function returns the OUTAGE State to signify which cell is present
	 *
	 * @return State.OUTAGE
	 */
	@Override
	public State who() {
		//returns the OUTAGE state
		return State.OUTAGE;
	}
	/**
	 * This function creates a new Town cell and searches the neighborhood of the one provided.
	 * The function switches the cell according to the rules provided in the specification.
	 *
	 * @return Empty(tNew, row, col)
	 */
	@Override
	public TownCell next(Town tNew) {
		//returns new Empty cell
		return new Empty (tNew, row, col);
	}
}
