package edu.iastate.cs2280.hw1;
/**
* @author Abby Taylor
*/
public class Casual extends TownCell{
	public Casual(Town p, int r, int c) {
		super(p, r, c);
	}
	
	@Override
	public State who() {
		return State.CASUAL;
	}
	
	@Override
	public TownCell next(Town tNew) {
       census(nCensus); // Gather neighbor data
      
       if (nCensus[RESELLER] >= 1) { //if there is a reseller, they cause an outage to the casual user
           return new Outage(tNew, row, col);
       }
       else if (nCensus[STREAMER] >= 1) { //if there is a streamer, the casual user becomes a streamer
       	return new Streamer(tNew, row, col);
       }
       else if ((nCensus[OUTAGE] + nCensus[EMPTY]) <= 1) {
       	return new Streamer(tNew, row, col);
       }
       else if (nCensus[CASUAL] >= 5) {
       	return new Streamer(tNew, row, col);
       }
       else {
           return this; //if no condition is present, then the cell remains casual
       }
   }
}
