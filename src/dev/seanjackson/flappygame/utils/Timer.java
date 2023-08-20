package dev.seanjackson.flappygame.utils;

import dev.seanjackson.flappygame.Game;

/**============}-\ Timer Class /-{============*/
/** @author Sean Jackson                      */
/** Purpose: Timer that counts in seconds     */
/**===========================================*/

public class Timer
{
	long start;	// THE CURRENT GAME TIME WHEN TIMER IS INSTANTIATED
	
	// MAIN AND ONLY CONSTRUCTOR
	public Timer()
	{
		// SET START TO CURRENT GAME TIME
		start = Game.GET_GAME_TIME();
	}
	
	/** @return # SECONDS THAT HAVE PASSED SINCE INSTANTIATION */
	public long getNumSeconds()
	{
		return Game.GET_GAME_TIME() - start;
	}
}
