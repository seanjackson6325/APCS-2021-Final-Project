package dev.seanjackson.flappygame.entities;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import dev.seanjackson.flappygame.Game;
import dev.seanjackson.flappygame.utils.ImageLoader;
import dev.seanjackson.flappygame.utils.SoundLoader;
import dev.seanjackson.flappygame.utils.StateManager;
import dev.seanjackson.flappygame.utils.Timer;

/**====================}-\ PressSpace Class /-{====================*/
/** @author Sean Jackson                                           */
/** Purpose: Keep track of the game's current state.               */
/**          This class does nothing but change a value that       */
/**          classes refer to.                                     */
/**================================================================*/

public class PressSpace extends Entity
{
   // HIDES/SHOW SPRITE
   public boolean shouldRender;
   
   // SPRITE UPDATES AFTER SOME # OF SECONDS
   Timer startDelay;
   
   // MAIN AND ONLY CONSTRUCTOR
   public PressSpace()
   {
      super(Game.WIDTH/2,
            Game.HEIGHT + ImageLoader.PRESS_SPACE.getHeight() / 4,
            ImageLoader.PRESS_SPACE.getWidth() / 4,
            ImageLoader.PRESS_SPACE.getHeight() / 4,
            ImageLoader.PRESS_SPACE);
      
      bounds.x -= image.getWidth() / 8;
      startDelay = new Timer();
   }

   
   @Override
   public void tick()
   {
	  // FLASHING EFFECT
      shouldRender = Game.GAME_UPS >= 30;
      
      // DRAW SPRITE UNTIL IT ARRIVES CLOSE TO IT'S FINAL POSITION
      if(bounds.y > 450)
    	  shouldRender = true;
      
      // SETS TO THE GAMEPLAY STATE WHEN SPACE IS HIT
      if(Game.GAME_INPUT_MANAGER.getKey(KeyEvent.VK_SPACE) && startDelay.getNumSeconds() >= 1l)
      {
         Game.GAME_STATE_MANAGER.setState(StateManager.GAME_STATE);
         Game.MENU_MUSIC.stop();
         Game.GAME_MUSIC = SoundLoader.PlaySound(SoundLoader.DEFAULT_FOLDER_DIR, SoundLoader.GAME_MUSIC, 0.9f);
      }
      
      // MAKE SPRITE GLIDE
      glideToPos(bounds.x, 400, 16);
   }

   @Override
   public void render(Graphics g)
   {
      if(shouldRender)
    	  drawImage(g);
      
      drawDebug(g);
   }
}
