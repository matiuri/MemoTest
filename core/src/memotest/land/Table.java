package memotest.land;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;

import memotest.utils.assets.AssetLoader;

public class Table extends Actor {
	private final int width, height, pairs;
	private Cell[][] cells;
	private Cell[] selected;
	private float time = 1, timer;
	
	public Table(int width, int height, AssetLoader loader) {
		if ((width * height) % 2 != 0)
			throw new IllegalArgumentException("You can't use this width and height");
		setBounds(0, 0, width * 64, height * 64);
		cells = new Cell[width][height];
		selected = new Cell[2];
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
	
	@Override
	public void act(float delta) {
		if (selected[0] == null || selected[1] == null)
			timer = time;
		else if (timer >= 0)
			timer -= delta;
		else if (timer <= 0) {
			if (selected[0].getColor().equals(selected[1].getColor())) {
				// TODO: change this; not remove
				selected[0].remove();
				selected[1].remove();
			}
			selected[0].setSelected(false);
			selected[1].setSelected(false);
			selected[0] = null;
			selected[1] = null;
		}
	}
	
	private Color[] setPairs() {
		// TODO: change this
		Color[] colors = new Color[pairs * 2];
		Cluster[] clusters = new Cluster[pairs];
		for (int i = 0; i < pairs; i++) {
			clusters[i] = new Cluster(
					new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1));
		}
		for (int i = 0; i < pairs * 2; i++) {
			int index;
			do {
				index = MathUtils.random(pairs - 1);
			} while (clusters[index].num == 0);
			colors[i] = clusters[index].color;
			clusters[index].num--;
		}
		return colors;
	}
	
	public void reset() {
		Color[] pairs = setPairs();
		int i = 0;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				cells[x][y].setColor(pairs[i++]);
			}
		}
	}
	
	public Cell[][] getCells() {
		return cells;
	}
	
	public void select(float xPos, float yPos) {
		int x = (int) (xPos / 64), y = (int) (yPos / 64);
		if (selected[0] == null && cells[x][y].getParent() != null) {
			selected[0] = cells[x][y];
			selected[0].setSelected(true);
		} else if (selected[1] == null) {
			if (!cells[x][y].equals(selected[0]) && cells[x][y].getParent() != null) {
				selected[1] = cells[x][y];
				selected[1].setSelected(true);
			}
		}
	}
	
	private class Cluster {
		private Color color;
		private int num;
		
		private Cluster(Color color) {
			this.color = color;
			num = 2;
		}
	}
	
	public int getBoardWidth() {
		return width;
	}
	
	public int getBoardHeight() {
		return height;
	}
}
