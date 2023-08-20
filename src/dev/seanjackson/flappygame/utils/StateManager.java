package dev.seanjackson.flappygame.utils;

/**================}-\ StateManager Class /-{================*/
/** @author Sean Jackson                                     */
/** Purpose: Keep track of the game's current state.         */
/**          This class does nothing but change a value that */
/**          classes refer to.                               */
/**==========================================================*/

public final class StateManager
{
	// POSSIBLE STATES IN GAME
	public static final byte GAME_STATE = 0;
	public static final byte MENU_STATE = 1;
	public static final byte DEATH_STATE = 2;
	
	// CURRENT STATE GAME IS IN
	private byte state;
	
	// MAIN AND ONLY CONSTRUCTOR
	public StateManager(byte state)
	{
		this.state = state;
	}
	
	// SET THE STATE OF THE GAME
	public void setState(byte state)
	{
		this.state = state;
	}

	/** @return CURRENT STATE OF GAME */
	public boolean isInState(byte state)
	{
		return this.state == state;
	}
}