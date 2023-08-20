package dev.seanjackson.flappygame.entities;

import java.awt.Graphics;

import dev.seanjackson.flappygame.Game;
import dev.seanjackson.flappygame.utils.ImageLoader;
import dev.seanjackson.flappygame.utils.PipeGenerator;

/**=====}-\ Game Class /-{=====*/
/** @author Sean Jackson       */
/** Purpose: Spawn pipes       */
/** Last modified: 5/31/21     */
/**============================*/

public class Pipe extends Entity
{
   // PIPE ON TOP OR BOTTOM OF 
   public static final byte TOP    = 0;
   public static final byte BOTTOM = 1;
   
   byte type;                  // TYPE
   public boolean active;      // ACTIVE? (OFF SCREEN)
   public boolean canMove;     // CAN IT MOVE?
   
   public Pipe(int h, byte t)
   {
      super(Game.WIDTH + 64, 0, ImageLoader.PIPE_TUBE.getWidth() >> 2, h, ImageLoader.PIPE_TUBE);
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
