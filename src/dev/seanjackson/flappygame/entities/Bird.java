package dev.seanjackson.flappygame.entities;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

import dev.seanjackson.flappygame.Game;
import dev.seanjackson.flappygame.utils.ImageLoader;
import dev.seanjackson.flappygame.utils.Utils;
import dev.seanjackson.flappygame.utils.PipeGenerator;
import dev.seanjackson.flappygame.utils.SoundLoader;
import dev.seanjackson.flappygame.utils.StateManager;

/**=====}-\ Bird Class /-{=====*/
/** @author Sean Jackson       */
/** Purpose: Player class      */
/**============================*/
public class Bird extends Entity
{
   float velocity;           // VERTICAL VELOCITY COMPONENT
   float rotScale;           // VALUE MULTIPLIED BY VELOCITY TO GET ANGLE
   public boolean isDead;    // DEAD?
   
   // MAIN AND ONLY CONSTRUCTOR
   public Bird(int x, int y)
   {
      super(x, y, 80, 60, ImageLoader.BIRD);
      
      // CENTER SPRITE TO INITIAL POSITION
      bounds.x -= bounds.width / 2;
      bounds.y -= bounds.height / 2;
      
      // OBVIOUSLY IT SHOULDN'T SPAWN DEAD
      isDead = false;
      
      // THIS IS FOR JUMPING.
      // WHEN THE BIRD DIES IT ROTATES MORE DRAMATICALLY
      rotScale = 1/30.0f;
   }

   @Override
   public void tick()
   {
      if(!isDead)
      {
    	 // GET COLLISION INFORMATION
         boolean onFloor   = bounds.y + bounds.height >= Game.HEIGHT;
         boolean onCeiling = bounds.y < 0;
         boolean onPipe    = bounds.intersects(PipeGenerator.p0.bounds) || 
                             bounds.intersects(PipeGenerator.p1.bounds);
         
         // IF THERE IS A COLLISION:
         if(onFloor || onCeiling || onPipe)
         {
        	 isDead = true;        // DOGE = DEAD :(
        	 velocity = -7.0f;     // JUMP
        	 rotScale = 0.1f;      // ROTATE MORE DRAMATICALLY
        	 
        	 // PROCESS BIRD DEATH AND SET GAME TO DEATH STATE
        	 Game.THE_GAME.processBirdDeath();
        	 Game.GAME_STATE_MANAGER.setState(StateManager.DEATH_STATE);
        	 Game.GAME_MUSIC.stop();
        	 Game.DEATH_MUSIC = SoundLoader.PlaySound(SoundLoader.DEFAULT_FOLDER_DIR, SoundLoader.DEATH_MUSIC, .9f);
         }
         
         // 
         if(Game.GAME_INPUT_MANAGER.getKey(KeyEvent.VK_SPACE) && velocity > -1)
         {
        	// JUMP
            velocity = -10.0f;
            
            // GENERATE A NEW SMOKE PARTICLE
            new Smoke((int)bounds.x + Utils.ranInt(Smoke.SIZE), 
                      (int)bounds.y + Utils.ranInt(Smoke.SIZE));
         }
         
         // UPDATE VELOCITY (ACCELERATE DOWN)
         velocity += 1.0f;
         
         // ADD VELOCITY TO Y POS
         bounds.y += velocity;
      }
      else // (IF DEAD)
      {
    	 // DO SLOW MOTION FALL THINGY
         velocity += 0.1f;
         bounds.y += velocity;
      }
   }
   
   @Override
   public void render(Graphics g)
   {
	  // SET UP GRAPHICS2D FOR ROTATION
      Graphics2D g2 = (Graphics2D)g;
      AffineTransform old = g2.getTransform();
      g2.rotate(velocity * rotScale, bounds.getCenterX(), bounds.getCenterY());
      
      drawImage(g);
      
      // RESET GRAPHICS AFFINETRANSFORM
      g2.setTransform(old);
      
      drawDebug(g);
   }
}
