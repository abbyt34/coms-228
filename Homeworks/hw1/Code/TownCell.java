package edu.iastate.cs2280.hw1;
/**
*
* @author Abby Taylor
*	Also provide appropriate comments for this class
*
*/
public abstract class TownCell{
	protected Town plain;
	protected int row;
	protected int col;
	
	
	// constants to be used as indices.
	protected static final int RESELLER = 0;
	protected static final int EMPTY = 1;
	protected static final int CASUAL = 2;
	protected static final int OUTAGE = 3;
	protected static final int STREAMER = 4;
	
	public static final int NUM_CELL_TYPE = 5;
	
	//Use this static array to take census.
	public static final int[] nCensus = new int[NUM_CELL_TYPE];
	public TownCell(Town p, int r, int c) {
		plain = p;
		row = r;
		col = c;
	}
	
	/**
	 * Checks all neigborhood cell types in the neighborhood.
	 * Refer to homework pdf for neighbor definitions (all adjacent
	 * neighbors excluding the center cell).
	 * Use who() method to get who is present in the neighborhood
	 * 
	 * @param counts of all customers
	 */
	public void census(int nCensus[]) {
		// zero the counts of all customers
		nCensus[RESELLER] = 0;
		nCensus[EMPTY] = 0;
		nCensus[CASUAL] = 0;
		nCensus[OUTAGE] = 0;
		nCensus[STREAMER] = 0;
		//iterates through each cell in the town
		for (int i = row - 1; i < row + 1; i++) {
			for (int j = col -1; j < col + 1; j++) {
				if (row == i && col == j) {
					continue;
				}
				if (i >= 0 && i < plain.getWidth() && j >= 0 && j < plain.getLength()) {
					State neighborState = plain.grid[i][j].who();
					
					if (neighborState == State.RESELLER) {
						nCensus[RESELLER] += 1;
					}
					else if (neighborState == State.EMPTY) {
						nCensus[EMPTY] += 1;
					}
					else if (neighborState == State.CASUAL) {
						nCensus[CASUAL] += 1;
					}
					else if (neighborState == State.OUTAGE) {
						nCensus[OUTAGE] += 1;
					}
					else if (neighborState == State.STREAMER) {
						nCensus[STREAMER] += 1;
					}
				}
			}
		}
	}
	/**
	 * Gets the identity of the cell.
	 *
	 * @return State
	 */
	public abstract State who();
	/**
	 * Determines the cell type in the next cycle.
	 *
	 * @param tNew: town of the next cycle
	 * @return TownCell
	 */
	public abstract TownCell next(Town tNew);
}
