/* Name: Sean Jackson
*  Date May 17, 2021
*  Filename: Coin.java
*/

package dev.seanjackson.flappygame.entities;

import java.awt.Graphics;

import dev.seanjackson.flappygame.Game;
import dev.seanjackson.flappygame.utils.ImageLoader;
import dev.seanjackson.flappygame.utils.SoundLoader;

public class Coin extends Entity
{
   float floatAngle;
   int startY;
   
   public Coin(int x, int y)
   {
      super(x, y, 74, 74, ImageLoader.COIN);
      startY = y;
   }

   @Override
   public void tick()
   {
      if((bounds.intersects(Game.THE_GAME.GAME_BIRD.bounds) || (bounds.x + bounds.width < 0)) 
          && !Game.THE_GAME.GAME_BIRD.inDeathAnimation)
      {
         Game.THE_GAME.GAME_SCORE_COUNTER.increment();
         SoundLoader.PlaySound(SoundLoader.DEFAULT_FOLDER_DIR, SoundLoader.COIN, 1f);
         remove();
      }
      floatAngle += 0.1f;
      bounds.y = startY + (int)(Math.sin(floatAngle) * 32);
   }

   @Override
   public void render(Graphics g)
   {
      int halfWidth = (int)(bounds.getWidth() / 2);
      int halfHeight = (int)(bounds.getHeight() / 2);
      
      g.drawImage(image, bounds.x, bounds.y, bounds.width, bounds.height, null);
      
      drawDebug(g);
   }

}
