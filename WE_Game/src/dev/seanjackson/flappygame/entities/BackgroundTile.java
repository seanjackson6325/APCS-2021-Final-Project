package dev.seanjackson.flappygame.entities;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.seanjackson.flappygame.Game;
import dev.seanjackson.flappygame.utils.PipeGenerator;

/**=====}-\ BackgroundTile /-{=====*/
/**                                */
/** @author Sean Jackson           */
/** Purpose: Scrolling background  */
/**================================*/
public class BackgroundTile extends Entity
{
   private int startX;
   
   public BackgroundTile(int startX, BufferedImage b)
   {
      super(startX, 0, Game.WIDTH, Game.HEIGHT, b);
      this.startX = startX;
   }

   @Override
   public void tick()
   {
      bounds.y = 0;
      if(bounds.x + bounds.width <= startX + 1) bounds.x = startX;
      else bounds.x += PipeGenerator.PIPE_SPEED >> 2;
   }
   
   @Override
   public void render(Graphics g)
   {
      drawImage(g);
      
      drawDebug(g);
   }
}