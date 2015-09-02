package memotest.board;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import memotest.utils.assets.AssetLoader;

/**
 * This class represents the shape of the {@link Cell}s
 * 
 * @author Matías
 */
public class Shape {
	/**
	 * All the shapes' forms
	 */
	private static Texture shapesTex;
	/**
	 * The color of this {@link Shape}
	 */
	private Colors color;
	/**
	 * The shape (itself) of this {@link Shape}
	 */
	private Shapes shape;
	
	/**
	 * This mehtod set the value of {@link #shapesTex}
	 * 
	 * @param loader
	 *            -> An {@link AssetLoader} which contains the shapes' forms'
	 *            textures
	 */
	public static void init(AssetLoader loader) {
		shapesTex = loader.get("Shapes", Texture.class);
	}
	
	/**
	 * Constructs a new {@link Shape}
	 * 
	 * @param color
	 *            -> The color of the shape
	 * @param shape
	 *            -> The shape (itself) of the shape
	 */
	public Shape(Colors color, Shapes shape) {
		this.color = color;
		this.shape = shape;
	}
	
	/**
	 * Draws this {@link Shape}
	 * 
	 * @param x
	 *            -> The x-pos
	 * @param y
	 *            -> The y-pos
	 * @param batch
	 *            -> An instance of {@link SpriteBatch}
	 */
	public void draw(float x, float y, Batch batch) {
		batch.setColor(color.color);
		batch.draw(shape.region, x, y, 64, 64);
		batch.setColor(Color.WHITE);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Shape) {
			Shape sh = (Shape) obj;
			if (color == sh.color && shape == sh.shape)
				return true;
		}
		return false;
	}
	
	//@off
	/**
	 * The enumerated type of the colors
	 * 
	 * @author Matías
	 */
	public enum Colors {
		RED(Color.RED), BLUE(Color.BLUE), GREEN(Color.GREEN), YELLOW(Color.YELLOW), PINK(Color.PINK);
				
		private Color color;
		
		Colors(Color color) {
			this.color = color;
		}
	}
	
	/**
	 * The enumerated type of the shapes
	 * 
	 * @author Matías
	 */
	public enum Shapes {
		PENTAGON(0, 0), SQUARE(1, 0), SPIRAL(2, 0), TRIANGLE(3, 0), CIRCLE(0, 1), STAR(1, 1),
		DIAMOND(2, 1), EXPLOSION(3, 1);
				
		private TextureRegion region;
		
		Shapes(int x, int y) {
			region = new TextureRegion(shapesTex, x * 64, y * 64, 64, 64);
		}
	}
	//@on
}
