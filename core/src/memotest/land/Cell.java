package memotest.land;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import memotest.utils.assets.AssetLoader;

public class Cell extends Actor {
	private Texture tex;
	
	public Cell(int x, int y, AssetLoader loader) {
		tex = loader.get("Cell", Texture.class);
		setBounds(x * 64, y * 64, 64, 64);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.setColor(getColor());
		batch.draw(tex, getX(), getY(), getWidth(), getHeight());
		batch.setColor(Color.WHITE);
	}
}
