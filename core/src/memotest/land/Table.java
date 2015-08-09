package memotest.land;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;

import memotest.utils.assets.AssetLoader;

public class Table {
	private final int width, height, pairs;
	private Cell[][] cells;
	
	public Table(int width, int height, AssetLoader loader) {
		if ((width * height) % 2 != 0)
			throw new IllegalArgumentException("You can't use this width and height");
		cells = new Cell[width][height];
		this.width = width;
		this.height = height;
		pairs = width * height / 2;
		Color[] pairs = setPairs();
		int i = 0;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				cells[x][y] = new Cell(x, y, loader);
				cells[x][y].setColor(pairs[i++]);
			}
		}
	}
	
	private Color[] setPairs() {
		// TODO: change this
		Color[] colors = new Color[pairs * 2];
		for (int i = 0; i < pairs * 2; i += 2) {
			colors[i] = new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1);
			colors[i + 1] = colors[i];
		}
		return colors;
	}
	
	public Cell[][] getCells() {
		return cells;
	}
}
