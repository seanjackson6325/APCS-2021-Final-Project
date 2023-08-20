package dev.seanjackson.flappygame.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.seanjackson.flappygame.Game;

/**================}-\ DeathFlash Class /-{================*/
/** @author Sean Jackson                                   */
/** Purpose: The flash on screen when the player dies      */
/**========================================================*/

public class DeathFlash extends Entity
{
   int alpha;   // ALPHA (TRANSPARENCY) VALUE FOR RGBA RENDERING
   Color c;		// COLOR OBJECTS USED FOR RENDERING
   
   public DeathFlash()
   {
      super(0, 0, 0, 0, null);
      alpha = 255;
   }

   @Override
   public void tick()
   {
	  // CHANGE ALPHA VALUE
	  alpha-=8;
	  
	  // CLEANUP WHEN DONE
	  if(alpha < 0) remove();
   }
   
   @Override
   public void render(Graphics g)
   {
      g.setColor(new Color(255, 255, 255, alpha));
      g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
      drawDebug(g);
   }
}
