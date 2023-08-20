package dev.seanjackson.flappygame.entities;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import dev.seanjackson.flappygame.Game;
import dev.seanjackson.flappygame.utils.ImageLoader;

/**============}-\ TitleScreen Class /-{============*/
/** @author Sean Jackson                            */
/** Purpose: The logo in the menu of the game       */
/**=================================================*/

public class TitleScreen extends Entity
{
   private float angle;
	
   public TitleScreen()
   {
      super(Game.WIDTH/2,
           -ImageLoader.TITLE.getHeight()  / 2,
            ImageLoader.TITLE.getWidth() / 2,
            ImageLoader.TITLE.getHeight() / 2,
            ImageLoader.TITLE);
      bounds.x -= image.getWidth() / 4;
      angle = 0f;
   }

   @Override
   public void tick()
   {
      bounds.y += (40 - bounds.y) >> 4;
      angle += 0.05f;
   }
   
   @Override
   public void render(Graphics g)
   {
	  Graphics2D g2 = (Graphics2D)g;
	  AffineTransform original = g2.getTransform();
	  g2.rotate(Math.sin(angle) * .25f, bounds.getCenterX(), bounds.getCenterY());
	  g2.drawImage(image, bounds.x, bounds.y, bounds.width, bounds.height, null);
	  g2.setTransform(original);
      drawDebug(g);
   }
}
