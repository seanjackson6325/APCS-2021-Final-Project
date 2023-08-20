package dev.seanjackson.flappygame.entities;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

import dev.seanjackson.flappygame.Game;
import dev.seanjackson.flappygame.utils.ImageLoader;
import dev.seanjackson.flappygame.utils.NumUtils;
import dev.seanjackson.flappygame.utils.PipeGenerator;
import dev.seanjackson.flappygame.utils.SoundLoader;

/**=====}-\ Bird Class /-{=====*/
/** @author Sean Jackson       */
/** Purpose: Player class      */
/**============================*/
public class Bird extends Entity
{
   float velocity;
   public boolean isDead;
   public boolean inDeathAnimation;
   
   public Bird(int x, int y)
   {
      super(x, y, 80, 60, ImageLoader.BIRD);
      bounds.x -= bounds.width / 2;
      bounds.y -= bounds.height / 2;
      isDead = false;
      inDeathAnimation = false;
   }

   @Override
   public void tick()
   {
      if(!isDead)
      {
         boolean onFloor   = bounds.y + bounds.height >= Game.HEIGHT;
         boolean onCeiling = bounds.y < 0;
         boolean onPipe    = bounds.intersects(PipeGenerator.p0.bounds) || 
                             bounds.intersects(PipeGenerator.p1.bounds);
         
         if(onFloor || onCeiling || onPipe) { isDead = true; velocity = -20.0f; }
         
         if(Game.inputManager.getKey(KeyEvent.VK_SPACE) && (velocity > -1 || onFloor))
         {
            velocity = -10.0f;
            
            for(int i = 0; i < 20; i++)
               new Smoke((int)bounds.x + NumUtils.ranInt(20), 
                         (int)bounds.y + NumUtils.ranInt(20));
            
            //SoundLoader.PlaySound(SoundLoader.DEFAULT_FOLDER_DIR, SoundLoader.JUMP, .9f);
         }
         
         velocity += 1.0f;
         bounds.y += velocity;
      }
      else
      {
         inDeathAnimation = true;
         velocity += 1.0f;
         bounds.y += velocity;
         
         if(bounds.y > Game.HEIGHT << 2) Game.THE_GAME.reset();
      }
   }
   
   @Override
   public void render(Graphics g)
   {
      Graphics2D g2 = (Graphics2D)g;
      AffineTransform old = g2.getTransform();
      g2.rotate(velocity / 30.0, bounds.getCenterX(), bounds.getCenterY());
      
      drawImage(g);
      g2.setTransform(old);
      
      drawDebug(g);
   }
}
