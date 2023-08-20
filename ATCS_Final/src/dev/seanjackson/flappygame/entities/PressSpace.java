package dev.seanjackson.flappygame.entities;
import java.awt.Graphics;

import dev.seanjackson.flappygame.Game;
import dev.seanjackson.flappygame.utils.ImageLoader;

/* Name: Sean Jackson
*  Date May 12, 2021
*  Filename: PressSpace.java
*/

public class PressSpace extends Entity
{
   boolean shouldRender;
   
   public PressSpace()
   {
      super(Game.WIDTH/2,
            Game.HEIGHT + ImageLoader.PRESS_SPACE.getHeight() / 4,
            ImageLoader.PRESS_SPACE.getWidth() / 4,
            ImageLoader.PRESS_SPACE.getHeight() / 4,
            ImageLoader.PRESS_SPACE);
      bounds.x -= image.getWidth() / 8;
   }

   @Override public void tick()
   {
      shouldRender = Game.GAME_UPS >= 30;
      
      if(bounds.y > 450) shouldRender = true;
      
      bounds.y -= (bounds.y - 400) / 16;
   }

   @Override
   public void render(Graphics g)
   {
      if(shouldRender) drawImage(g);
      
      drawDebug(g);
   }
}
