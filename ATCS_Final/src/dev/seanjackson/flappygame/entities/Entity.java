package dev.seanjackson.flappygame.entities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import dev.seanjackson.flappygame.Game;

/**===============}-\ Entity Class /-{===============*/
/** @author Sean Jackson                             */
/** Purpose: Superclass that all game objects extend */
/**==================================================*/

public abstract class Entity
{
   protected Rectangle bounds;
   protected BufferedImage image;
   
   public static ArrayList<Entity> ENTITIES = new ArrayList<Entity>();
   
   public Entity(int x, int y, int w, int h, BufferedImage b)
   {
      image = b;
      bounds = new Rectangle(x, y, w, h);
      add();
   }
   
   public abstract void tick();
   public abstract void render(Graphics g);
   
   protected void drawImage(Graphics g)
   {
      g.drawImage(image, bounds.x, bounds.y, bounds.width, bounds.height, null);
   }
   
   protected void drawDebug(Graphics g)
   {
      if(Game.THE_GAME.inputManager.getKey(KeyEvent.VK_D))
      {
         g.setColor(Color.RED);
         g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
      }
   }
   
   public int getX() { return bounds.x; }
   public int getY() { return bounds.y; }
   
   public int  getWidth() { return bounds.width;  }
   public int getHeight() { return bounds.height; }
   
   public void setX(int x) { this.bounds.x = x; }
   public void setY(int y) { this.bounds.y = y; }
   
   public void    add() { ENTITIES.add    (this); }
   public void remove() { ENTITIES.remove (this); }
   
   public static void TICK_ENTITIES()
   {
      for(int i = 0; i < ENTITIES.size(); i++) ENTITIES.get(i).tick();
   }
   
   public static void RENDER_ENTITIES(Graphics g)
   {
      for(Entity e : ENTITIES) e.render(g);
   }
}
