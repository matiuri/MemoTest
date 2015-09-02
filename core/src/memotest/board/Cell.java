package memotest.board;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import memotest.utils.assets.AssetLoader;

/**
 * This class represents each cell of the board.
 * <p>
 * Extends {@link Actor}
 * 
 * @author Matías
 */
public class Cell extends Actor {
	/**
	 * The {@link TextureRegion}s of the {@link Cell}
	 */
	private TextureRegion front, back;
	/**
	 * This {@link Texture} is used when the cell has been removed
	 */
	private Texture filter;
	/**
	 * Whether the cell is selected or has been removed
	 */
	private boolean selected, removed;
	/**
	 * The {@link Shape} of the {@link Cell}
	 */
	private Shape shape;
	
	/**
	 * Constructs a new {@link Cell}
	 * 
	 * @param x
	 *            -> The x-pos, in the {@link Table#cells} array's scale
	 * @param y
	 *            -> The y-pos, in the {@link Table#cells} array's scale
	 * @param loader
	 *            -> An {@link AssetLoader}, which contains the {@link Texture}s
	 *            that this {@link Cell} needs
	 */
	public Cell(int x, int y, AssetLoader loader) {
		Texture cellRegion = loader.get("CellRegion", Texture.class);
		front = new TextureRegion(cellRegion, 64, 0, 64, 64);
		back = new TextureRegion(cellRegion, 0, 0, 64, 64);
		filter = loader.get("Filter", Texture.class);
		setBounds(x * 64, y * 64, 64, 64);
	}
	
	/** (non-Javadoc)
	 * @off
	 * @see
	 * com.badlogic.gdx.scenes.scene2d.Actor#draw(com.badlogic.gdx.graphics.g2d.Batch, float)
	 * @on
	 */
	@Override
	public void draw(Batch batch, float parentAlpha) {
		if (selected) {
			batch.draw(front, getX(), getY(), getWidth(), getHeight());
			if (shape != null)
				shape.draw(getX(), getY(), batch);
			if (removed)
				batch.draw(filter, getX(), getY(), getWidth(), getHeight());
		} else
			batch.draw(back, getX(), getY(), getWidth(), getHeight());
	}
	
	/**
	 * Sets the {@link #shape}
	 * 
	 * @param shape
	 *            -> An instance of {@link Shape} class
	 */
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	
	/**
	 * @return The {@link #shape}
	 */
	public Shape getShape() {
		return shape;
	}
	
	/**
	 * Sets the value of {@link #selected}
	 * 
	 * @param selected
	 *            -> The new value of {@link #selected}
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	/**
	 * Sets the value of {@link #removed}
	 * 
	 * @param removed
	 *            -> The new value of {@link #removed}
	 */
	public void setRemoved(boolean removed) {
		this.removed = removed;
	}
	
	/**
	 * @return Whether or not this {@link Cell} has been removed
	 */
	public boolean isRemoved() {
		return removed;
	}
}
