package dev.seanjackson.flappygame.entities;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.seanjackson.flappygame.Game;
import dev.seanjackson.flappygame.utils.PipeGenerator;

/**========}-\ BackgroundTile /-{========*/
/**                                      */
/** @author Sean Jackson                 */
/** Purpose: Scrolling background tile   */
/**======================================*/
public class BackgroundTile extends Entity
{
   // THE START X POSITION
   private int startX;
   
   // THE CURRENT SPEED OF THE TILE
   private int speed;
   
   // MAIN AND ONLY CONSTRUCTOR
   public BackgroundTile(int startX, BufferedImage b)
   {
      super(startX, 0, Game.WIDTH, Game.HEIGHT, b);
      this.startX = startX;
      speed = 0;
   }

   @Override
   public void tick()
   {
      bounds.y = 0;
      if(bounds.x + bounds.width <= startX + 1) bounds.x = startX;
      else bounds.x += speed; //PipeGenerator.PIPE_SPEED >> 2;
   }
   
   // SET PIPE SPEED
   public void setSpeed(int s)
   {
	   speed = s;
   }
   
   @Override
   public void render(Graphics g)
   {
      drawImage(g);
      drawDebug(g);
   }
}