package edu.iastate.cs2280.hw1;
/**
* @author Abby Taylor
* @date 9/20/2024
*
* This Reseller class extends TownCell in order to properly construct, label and update the current Reseller
* cells in the Town grid.
*/
public class Reseller extends TownCell{
	/**
	 * This constructor calls on the TownCell super constructor to properly construct a Reseller cell
	 *
	 * @param p
	 * @param r
	 * @param c
	 */
	public Reseller(Town p, int r, int c) {
		//calls the TownCell super constructor
		super(p, r, c);
	}
	
	/**
	 * who() returns the current state in the cell
	 * Reseller returns the State RESELLER
	 *
	 * @return State.RESELLER
	 */
	@Override
	public State who() {
		//returns the Reseller State
		return State.RESELLER;
	}
	/**
	 * This function properly updates the current Reseller cell based on the given rules in the specifications.
	 *
	 * @param Town tNew
	 * @return new Empty(tNew, row, col)
	 * @return new Steamer(tNew, row, col)
	 * @return this
	 */
	@Override
	public TownCell next(Town tNew) {
		//gather neighborhood data
		census(nCensus);
		
		//if there are three or fewer Causal users OR three or more Empty users, the cell is Empty
		if ((nCensus[CASUAL] <= 3) || (nCensus[EMPTY] >= 3)) {
			//returns a new Empty cell
			return new Empty(tNew, row, col);
		}
		//if there are five or more Casual users, the cell is a Streamer
		else if (nCensus[CASUAL] >= 5) {
			//returns a new Streamer cell
			return new Streamer(tNew, row, col);
		}
		//if no conditions are met, the cell remains the same
		else {
			//returns the same Reseller cell
			return new Reseller(tNew, row, col);
		}
	}
}
