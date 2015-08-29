package memotest.utils.time;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import memotest.utils.assets.AssetLoader;

public class Timer extends Actor {
	private static Texture tex;
	private final float totalTime;
	private float timer, x, width;
	private Bars bar = Bars.GRAY;
	private boolean perform = false;
	
	public static void init(AssetLoader loader) {
		tex = loader.get("ProgressBar", Texture.class);
	}
	
	public Timer(final float totalTime, float x, float y, float width, float height) {
		this.totalTime = totalTime;
		timer = totalTime;
		this.x = x;
		this.width = width;
		setBounds(x, y, width, height);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		bar.patch.draw(batch, getX(), getY(), getWidth(), getHeight());
	}
	
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
				// TODO: Dessapear when percent == 0
				setWidth(width * (percent + 0.12f));
				setX(x + width - getWidth() / 2);
			}
		}
	}
	
	public boolean isTimeOver() {
		return timer <= 0;
	}
	
	public void setPerform(boolean perform) {
		this.perform = perform;
	}
	
	private enum Bars {
		RED(0), YELLOW(1), GREEN(2), GRAY(3);
		
		static final int rad = 12;
		
		NinePatch patch;
		
		Bars(int y) {
			patch = new NinePatch(new TextureRegion(tex, 0, y * 32, 64, 32), rad, rad, rad, rad);
		}
	}
}
