package dev.seanjackson.flappygame.entities;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import dev.seanjackson.flappygame.Game;
import dev.seanjackson.flappygame.utils.ImageLoader;

/**=====}-\ Smoke Class /-{=====*/
/** @author Sean Jackson        */
/** Purpose: Smoke particle     */
/**=============================*/

public class Smoke extends Entity
{
   public static final byte SIZE = 30;  // MINIMUM SIZE OF PARTICLE

   // MAIN AND ONLY CONSTRUCTOR
   public Smoke(int x, int y)
   {
      super(x, y, SIZE, SIZE, ImageLoader.SMOKE);
      
      // CENTER SPRITE TO INITIAL POSITION
      bounds.x -= bounds.width / 2;
      bounds.y -= bounds.height / 2;
   }

   @Override
   public void tick()
   {
      bounds.x -= 5;
      if(bounds.x + bounds.width < -SIZE)
    	  remove();
   }
   
   @Override
   public void render(Graphics g)
   {
      Graphics2D g2 = (Graphics2D)g;
      AffineTransform old = g2.getTransform();
      g2.rotate(bounds.x / 40.0f, bounds.getCenterX(), bounds.getCenterY());
      
      int size = (((Game.WIDTH >> 1) - bounds.x) >> 2) + SIZE;
      bounds.width = bounds.height = size;
      
      g.drawImage(image, bounds.x, bounds.y, bounds.width, bounds.height, null);      
      g2.setTransform(old);
      
      drawDebug(g);
   }
}
