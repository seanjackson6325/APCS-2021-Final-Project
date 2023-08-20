package dev.seanjackson.flappygame.entities;

import java.awt.Graphics;

import dev.seanjackson.flappygame.Game;
import dev.seanjackson.flappygame.utils.ImageLoader;
import dev.seanjackson.flappygame.utils.Timer;

/**================}-\ GameOver Class /-{================*/
/** @author Sean Jackson                                 */
/** Purpose: Game Over sprite that appears when you die  */
/**======================================================*/

public class GameOver extends Entity
{
	int alpha;	// ALPHA VALUE (COLOR)
	private static final int WIDTH = 256, HEIGHT = 128;
	
	private Timer delay;	// WAIT BEFORE MOVING
	
	// MAIN AND ONLY CONSTRUCTOR
	public GameOver()
	{
		super(Game.WIDTH/2 - WIDTH/2,
			  Game.HEIGHT/2 - HEIGHT/2,
			  WIDTH, HEIGHT, ImageLoader.GAME_OVER);
		
		alpha = 0;
		delay = new Timer();
		
		// SET LOCATION OF SCORE COUNTER TO OFF OF SCREEN
		Game.GAME_SCORE_COUNTER.bounds.setLocation(Game.WIDTH << 2, 0);
	}

	@Override
	public void tick()
	{
		// UPDATE WHEN TIME IS UP:
		if(delay.getNumSeconds() >= 2)
		{
			glideToPos(32, 32, 32);
			
			// GLIDE THE SCORE COUNTER
			Game.GAME_SCORE_COUNTER.glideToPos(Game.WIDTH - Game.GAME_SCORE_COUNTER.bounds.width - 100, 32, 16);
		}
	}

	@Override
	public void render(Graphics g)
	{
		drawImage(g);
		drawDebug(g);
	}
}
