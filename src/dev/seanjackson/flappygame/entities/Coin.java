package dev.seanjackson.flappygame.entities;

import java.awt.Graphics;

import dev.seanjackson.flappygame.Game;
import dev.seanjackson.flappygame.utils.ImageLoader;
import dev.seanjackson.flappygame.utils.SoundLoader;
import dev.seanjackson.flappygame.utils.StateManager;

/**==========}-\ Coin Class /-{==========*/
/** @author Sean Jackson                 */
/** Purpose: The coin the bird collects  */
/**======================================*/
public class Coin extends Entity
{
   float floatAngle;   // ANGLE USED TO CALCULATE FLOATING MOTION
   int startY;         // STARTING Y POS
   
   // SIZE (WIDTH AND HEIGHT) OF COIN
   public static final int SIZE = 74;
   
   // MAIN AND ONLY CONSTRUCTOR
   public Coin(int x, int y)
   {
      super(x, y, SIZE, SIZE, ImageLoader.COIN);
      startY = y;
   }

   @Override
   public void tick()
   {
	  // CHECK IF COIN INTERSECTS BIRD OR SCREEN EDGE AND ACT ACCORDINGLY
      if((bounds.intersects(Game.GAME_BIRD.bounds) || (bounds.x + bounds.width < 0)))
      {
    	 if(Game.GAME_SCORE_COUNTER != null)
    		 Game.GAME_SCORE_COUNTER.increment();
         SoundLoader.PlaySound(SoundLoader.DEFAULT_FOLDER_DIR, SoundLoader.COIN, 1f);
         remove();
      }
      else if(Game.GAME_STATE_MANAGER.isInState(StateManager.DEATH_STATE)) remove();
      
      // INCREMENT ANGLE FOR FLOATING EFFECT
      floatAngle += 0.1f;
      
      // UPDATE Y POS FOR FLOATING EFFECT
      bounds.y = startY + (int)(Math.sin(floatAngle) * 32);
   }

   @Override
   public void render(Graphics g)
   {    
      g.drawImage(image, bounds.x, bounds.y, bounds.width, bounds.height, null);
      drawDebug(g);
   }

}
