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
   // BOUNDS OF ENTITY
   protected Rectangle bounds;
   
   // IMAGE RENDERED BY ENTITY
   protected BufferedImage image;
   
   // ALL ENTITIES IN GAME
   public static ArrayList<Entity> ENTITIES = new ArrayList<Entity>();
   
   // MAIN AND ONLY CONSTRUCTOR
   public Entity(int x, int y, int w, int h, BufferedImage b)
   {
      image = b;
      bounds = new Rectangle(x, y, w, h);
      add();
   }
   
   // UPDATE ENTITY
   public abstract void tick();
   
   // RENDER ENTITY
   public abstract void render(Graphics g);
   
   // DRAW IMAGE AT THE ENTITY POSITION
   protected void drawImage(Graphics g)
   {
      g.drawImage(image, bounds.x, bounds.y, bounds.width, bounds.height, null);
   }
   
   // DRAW THE BOUNDS OF AN ENTITY TO HELP RENDERING ISSUES
   protected void drawDebug(Graphics g)
   {
      if(Game.GAME_INPUT_MANAGER.getKey(KeyEvent.VK_D))
      {
         g.setColor(Color.RED);
         g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
      }
   }
   
   // HAVE ENTITY GLIDE TO POSITION
   public void glideToPos(int x, int y, int div)
   {
	   bounds.x += (x - bounds.x) / (float)div;
	   bounds.y += (y - bounds.y) / (float)div;
   }
   
//========================================================================================================================
   
   // GET POSITION
   public int getX() { return bounds.x; }
   public int getY() { return bounds.y; }
   
   // GET DIMENSIONS
   public int  getWidth() { return bounds.width;  }
   public int getHeight() { return bounds.height; }
   
   // SET POSITION
   public void setX(int x) { this.bounds.x = x; }
   public void setY(int y) { this.bounds.y = y; }
   
//========================================================================================================================
   
   // ADD ENTITY TO LIST
   public void    add() { ENTITIES.add    (this); }
   
   // REMOVE ENTITY FROM LIST
   public void remove() { ENTITIES.remove (this); }
   
   // UPDATE ALL ENTITIES IN LIST
   public static void TICK_ENTITIES()
   {
      for(int i = 0; i < ENTITIES.size(); i++) ENTITIES.get(i).tick();
   }
   
   // RENDER ALL ENTITIES IN LIST
   public static void RENDER_ENTITIES(Graphics g)
   {
      for(Entity e : ENTITIES) e.render(g);
   }
}
