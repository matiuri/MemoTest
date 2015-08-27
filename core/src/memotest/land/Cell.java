package memotest.land;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import memotest.utils.assets.AssetLoader;

public class Cell extends Actor {
	private TextureRegion cell, back;
	private Texture filter;
	private boolean selected, removed;
	private Shape shape;
	
	public Cell(int x, int y, AssetLoader loader) {
		Texture cellRegion = loader.get("CellRegion", Texture.class);
		cell = new TextureRegion(cellRegion, 64, 0, 64, 64);
		back = new TextureRegion(cellRegion, 0, 0, 64, 64);
		filter = loader.get("Filter", Texture.class);
		setBounds(x * 64, y * 64, 64, 64);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		if (selected) {
			batch.draw(cell, getX(), getY(), getWidth(), getHeight());
			if (shape != null)
				shape.draw(getX(), getY(), batch);
			if (removed)
				batch.draw(filter, getX(), getY(), getWidth(), getHeight());
		} else
			batch.draw(back, getX(), getY(), getWidth(), getHeight());
	}
	
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	
	public Shape getShape() {
		return shape;
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
