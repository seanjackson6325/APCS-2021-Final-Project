package dev.seanjackson.flappygame.entities;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import dev.seanjackson.flappygame.Game;
import dev.seanjackson.flappygame.utils.ImageLoader;

/* Name: Sean Jackson
*  Date May 12, 2021
*  Filename: Smoke.java
*/

public class Smoke extends Entity
{  
   public Smoke(int x, int y)
   {
      super(x, y, 50, 50, ImageLoader.SMOKE);
      bounds.x -= bounds.width / 2;
      bounds.y -= bounds.height / 2;
   }

   @Override
   public void tick()
   {
      bounds.x-=5;
      if(bounds.x + bounds.width < 0) remove();
   }
   
   @Override
   public void render(Graphics g)
   {
      Graphics2D g2 = (Graphics2D)g;
      AffineTransform old = g2.getTransform();
      g2.rotate(bounds.x / 20.0f, bounds.getCenterX(), bounds.getCenterY());
      //super.render(g);
      
      int size = ((Game.WIDTH >> 1) - bounds.x) >> 2;
      
      g.drawImage(image, bounds.x, bounds.y, size, size, null);
      
      g2.setTransform(old);
      
      drawDebug(g);
   }
}
