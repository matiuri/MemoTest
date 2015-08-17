package memotest.land;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import memotest.utils.assets.AssetLoader;

public class Cell extends Actor {
	private Texture cell, back, filter;
	private boolean selected, removed;
	
	public Cell(int x, int y, AssetLoader loader) {
		cell = loader.get("Cell", Texture.class);
		back = loader.get("BackCell", Texture.class);
		filter = loader.get("Filter", Texture.class);
		setBounds(x * 64, y * 64, 64, 64);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		if (selected) {
			batch.setColor(getColor());
			batch.draw(cell, getX(), getY(), getWidth(), getHeight());
			if (removed) {
				batch.setColor(Color.BLACK);
				batch.draw(filter, getX(), getY(), getWidth(), getHeight());
			}
			batch.setColor(Color.WHITE);
		} else
			batch.draw(back, getX(), getY(), getWidth(), getHeight());
		// TODO: apply filter if removed
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public void setRemoved(boolean removed) {
		this.removed = removed;
	}
	
	public boolean isRemoved() {
		return removed;
	}
}
