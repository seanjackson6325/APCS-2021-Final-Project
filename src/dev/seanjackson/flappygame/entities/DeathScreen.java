package dev.seanjackson.flappygame.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import dev.seanjackson.flappygame.Game;
import dev.seanjackson.flappygame.utils.SoundLoader;
import dev.seanjackson.flappygame.utils.StateManager;
import dev.seanjackson.flappygame.utils.Timer;

/**===============}-\ DeathScreen Class /-{===============*/
/** @author Sean Jackson                                  */
/** Purpose: Death Screen that appears when player dies   */
/**=======================================================*/

public class DeathScreen extends Entity
{
	// THE COLOR THAT TAKES OVER THE SCREEN WHEN BIRD DIES
	private static final Color bColor = new Color(0, 0, 0, 127);
	
	// GAME OVER INSTANCE
	private GameOver gameOver;
	
	// THE DELAY BEFORE THE PLAYER CAN HIT SPACE TO EXIT
	// FORCES PLAYER TO SEE SCORE AND SUCH
	Timer exitDelay;
	
	// MAIN AND ONLY CONSTRUCTOR
	public DeathScreen()
	{
		super(Game.WIDTH << 1, 0, Game.WIDTH, Game.HEIGHT, null);
		gameOver = new GameOver();
		exitDelay = new Timer();
		
		new HighScore(Game.WIDTH * 3, -Game.HEIGHT);
	}

	@Override
	public void tick()
	{
		glideToPos(0, 0, 24);
		
		gameOver.tick();
		
		if(exitDelay.getNumSeconds() >= 4)
		{
			// NOW THAT TIME IS UP THE GAME CAN RESET:
			if(Game.GAME_INPUT_MANAGER.getKey(KeyEvent.VK_SPACE))
	        {
				Entity.ENTITIES.remove(Game.GAME_DEATH_SCREEN);
				Game.GAME_STATE_MANAGER.setState(StateManager.MENU_STATE);
				Game.DEATH_MUSIC.stop();
				Game.RESET();
	        }
		}
	}
	
	@Override
	public void render(Graphics g)
	{
		if(Game.GAME_STATE_MANAGER.isInState(StateManager.DEATH_STATE))
		{
			g.setColor(bColor);
			g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
		}
	}
	
}
