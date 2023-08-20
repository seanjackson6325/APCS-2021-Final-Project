/* Name: Sean Jackson
*  Date May 13, 2021
*  Filename: DeathFlash.java
*/

package dev.seanjackson.flappygame.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.seanjackson.flappygame.Game;

public class DeathFlash extends Entity
{
   int alpha;
   Color c;
   boolean done;
   
   public DeathFlash()
   {
      super(0, 0, 0, 0, null);
      alpha = 255;
   }

   @Override
   public void tick()
   {
      if(!done)
      {
         alpha-=8;
         if(alpha < 0) { remove(); done = true; }  
      }
   }
   
   @Override
   public void render(Graphics g)
   {
      g.setColor(new Color(255, 255, 255, alpha));
      g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
   }
}
