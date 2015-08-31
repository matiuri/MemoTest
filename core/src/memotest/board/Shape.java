package memotest.board;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import memotest.utils.assets.AssetLoader;

public class Shape {
	private static Texture shapesTex;
	private Colors color;
	private Shapes shape;
	
	public static void init(AssetLoader loader) {
		shapesTex = loader.get("Shapes", Texture.class);
	}
	
	public Shape(Colors color, Shapes shape) {
		this.color = color;
		this.shape = shape;
	}
	
	public void draw(float x, float y, Batch batch) {
		batch.setColor(color.color);
		batch.draw(shape.region, x, y, 64, 64);
		batch.setColor(Color.WHITE);
	}
	
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
	public enum Colors {
		RED(Color.RED), BLUE(Color.BLUE), GREEN(Color.GREEN), YELLOW(Color.YELLOW), PINK(Color.PINK);
				
		private Color color;
		
		Colors(Color color) {
			this.color = color;
		}
	}
	
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
