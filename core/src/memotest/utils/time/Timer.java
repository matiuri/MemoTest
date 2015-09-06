package memotest.utils.time;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import memotest.utils.assets.AssetLoader;

/**
 * This class represents the timer.
 * <p>
 * Extends {@link Actor}
 * 
 * @author Matías
 */
public class Timer extends Actor {
	/**
	 * The complete progress bar's texture
	 */
	private static Texture tex;
	/**
	 * The total (and max) time of this {@link Timer}
	 */
	private final float totalTime;
	/**
	 * THe amount of time, the x-pos and the width of this {@link Timer}
	 */
	private float timer, x, width;
	/**
	 * The progress bar's texture of this {@link Timer}
	 */
	private Bars bar = Bars.GRAY;
	/**
	 * Whether, or not, this {@link Timer} has to count time
	 */
	private boolean perform = false;
	
	/**
	 * Sets the {@link #tex} {@link Texture}
	 * 
	 * @param loader
	 *            -> An instance of {@link AssetLoader}
	 */
	public static void init(AssetLoader loader) {
		tex = loader.get("ProgressBar", Texture.class);
	}
	
	/**
	 * Constructs a new {@link Timer}
	 * 
	 * @param totalTime
	 *            -> The max amount of time
	 * @param x
	 *            -> The x-pos
	 * @param y
	 *            -> The y-pos
	 * @param width
	 *            -> The width
	 * @param height
	 *            -> The height
	 */
	public Timer(final float totalTime, float x, float y, float width, float height) {
		this.totalTime = totalTime;
		timer = totalTime;
		this.x = x;
		this.width = width;
		setBounds(x, y, width, height);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.badlogic.gdx.scenes.scene2d.Actor#draw(com.badlogic.gdx.graphics.g2d.
	 * Batch, float)
	 */
	@Override
	public void draw(Batch batch, float parentAlpha) {
		bar.patch.draw(batch, getX(), getY(), getWidth(), getHeight());
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.scenes.scene2d.Actor#act(float)
	 */
	@Override
	public void act(float delta) {
		if (perform) {
			timer -= delta;
			if (timer >= 0) {
				float percent = timer / totalTime;
				if (percent > 0.50f) {
					if (bar != Bars.GREEN)
						bar = Bars.GREEN;
				} else if (percent > 0.25f) {
					if (bar != Bars.YELLOW)
						bar = Bars.YELLOW;
				} else {
					if (bar != Bars.RED)
						bar = Bars.RED;
				}
				setWidth(width * (percent + 0.12f));
				setX(x + (width - getWidth()) / 2);
			} else {
				remove();
			}
		}
	}
	
	/**
	 * @return If the timer doesn't have time left
	 */
	public boolean isTimeOver() {
		return timer <= 0;
	}
	
	/**
	 * Sets the value of {@link #perform}
	 * 
	 * @param perform
	 *            -> The new value of {@link #perform}
	 */
	public void setPerform(boolean perform) {
		this.perform = perform;
	}
	
	/**
	 * Adds time to this {@link Timer}
	 * 
	 * @param time
	 *            -> The amount of time
	 */
	public void addTime(float time) {
		if (timer + time <= totalTime)
			timer += time;
		else
			timer = totalTime;
	}
	
	public float getTime() {
		return timer;
	}
	
	private enum Bars {
		RED(0), YELLOW(1), GREEN(2), GRAY(3);
		
		static final int RAD = 12;
		
		NinePatch patch;
		
		Bars(int y) {
			patch = new NinePatch(new TextureRegion(tex, 0, y * 32, 64, 32), RAD, RAD, RAD, RAD);
		}
	}
}
