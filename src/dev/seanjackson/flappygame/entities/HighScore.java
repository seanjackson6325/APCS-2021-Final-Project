package dev.seanjackson.flappygame.entities;

import java.awt.Graphics;

import dev.seanjackson.flappygame.Game;
import dev.seanjackson.flappygame.utils.ImageLoader;
import dev.seanjackson.flappygame.utils.Timer;

public class HighScore extends Entity
{
	// WIDTH AND HEIGHT OF SPRITE
	public static final int WIDTH = ImageLoader.HIGH_SCORE[0].getWidth() >> 1;
	public static final int HEIGHT = ImageLoader.HIGH_SCORE[0].getHeight() >> 1;
	
	// THE HIGHEST SCORE
	private static int HIGHEST = 0;
	
	// DELAY BEFORE HIGHSCORE GLIDES INTO FRAME
	private Timer delay;
	
	// NEW SCORECOUNTER TO DISPLAY HIGHSCORE
	private ScoreCounter highScore;
	
	public HighScore(int x, int y)
	{
		super(x, y, WIDTH, HEIGHT, ImageLoader.HIGH_SCORE[0]);
		delay = new Timer();
		int score = Game.GAME_SCORE_COUNTER.getScore();
		if(score > HIGHEST)
			HIGHEST = score;
		highScore = new ScoreCounter(HIGHEST);
		highScore.bounds.setLocation(bounds.x + bounds.width + 20, bounds.y);
	}

	@Override
	public void tick()
	{
		// UPDATE EVERYTHING WHEN TIME IS UP:
		if(delay.getNumSeconds() >= 2)
		{
			image = ImageLoader.HIGH_SCORE[(int) (Game.GET_GAME_TIME() % 2)];
			glideToPos(Game.WIDTH / 3 - WIDTH / 2, Game.HEIGHT / 2 - HEIGHT / 2, 16);
			highScore.tick();
			highScore.bounds.setLocation(bounds.x + bounds.width + 20, bounds.y);
		}
	}

	@Override
	public void render(Graphics g)
	{
		drawImage(g);
		drawDebug(g);
	}
}
