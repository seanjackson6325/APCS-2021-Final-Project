/* Name: Sean Jackson
*  Date May 12, 2021
*  Filename: Pipe.java
*/

package dev.seanjackson.flappygame.entities;

import java.awt.Graphics;

import dev.seanjackson.flappygame.Game;
import dev.seanjackson.flappygame.utils.ImageLoader;
import dev.seanjackson.flappygame.utils.PipeGenerator;

public class Pipe extends Entity
{
   public static final byte TOP    = 0;
   public static final byte BOTTOM = 1;
   
   byte type;
   public boolean active;
   public boolean canMove;
   
   public Pipe(int h, byte t)
   {
      super(Game.WIDTH + 10, 0, ImageLoader.PIPE_TUBE.getWidth() >> 2, h, ImageLoader.PIPE_TUBE);
      type = t;
      if(type == BOTTOM) bounds.y = Game.HEIGHT - h;
      active = true;
      canMove = true;
   }

   @Override
   public void tick()
   {
      if(canMove)
      {
         bounds.x += PipeGenerator.PIPE_SPEED;
         if(bounds.x + bounds.width < 0)
         {
            remove();
            active = false;
         }
      }
   }
   
   @Override
   public void render(Graphics g)
   {
      drawImage(g);
      
      int width = ImageLoader.PIPE_TOP.getWidth() >> 2;
      int height = ImageLoader.PIPE_TOP.getHeight() >> 2;
      
      int y = (type == BOTTOM) ? bounds.y : bounds.y + bounds.height - height;
      
      g.drawImage(ImageLoader.PIPE_TOP, bounds.x - (int)((width - bounds.getWidth()) / 2), y, width, ImageLoader.PIPE_TOP.getHeight() >> 2, null);
      
      drawDebug(g);
   }

}
