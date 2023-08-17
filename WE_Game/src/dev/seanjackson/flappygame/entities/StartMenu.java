package dev.seanjackson.flappygame.entities;
import java.awt.Graphics;

import dev.seanjackson.flappygame.Game;
import dev.seanjackson.flappygame.utils.ImageLoader;

/* Name: Sean Jackson
*  Date May 12, 2021
*  Filename: StartMenu.java
*/

public class StartMenu extends Entity
{

   public StartMenu()
   {
      super(Game.WIDTH/2,
           -ImageLoader.TITLE.getHeight() / 4,
            ImageLoader.TITLE.getWidth() / 4,
            ImageLoader.TITLE.getHeight() / 4,
            ImageLoader.TITLE);
      bounds.x -= image.getWidth() / 8;
   }

   @Override
   public void tick()
   {
      bounds.y += (40 - bounds.y) / 16;
   }
   
   @Override
   public void render(Graphics g)
   {
      drawImage(g);
      
      drawDebug(g);
   }
}
