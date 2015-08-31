package memotest.board;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;

import memotest.utils.assets.AssetLoader;
import memotest.utils.time.Timer;

public class Table extends Group {
	private final int width, height, pairs;
	private int pairsRem = 0;
	private Cell[][] cells;
	private Cell[] selected;
	private final float time = 0.75f;
	private float timer;
	private Timer timerActor;
	
	public Table(int width, int height, AssetLoader loader) {
		if ((width * height) % 2 != 0)
			throw new IllegalArgumentException("You can't use this width and height");
		Shape.init(loader);
		setBounds(0, 0, width * 64, height * 64);
		cells = new Cell[width][height];
		selected = new Cell[2];
		this.width = width;
		this.height = height;
		pairs = width * height / 2;
		Shape[] pairs = setPairs();
		int i = 0;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				cells[x][y] = new Cell(x, y, loader);
				cells[x][y].setShape(pairs[i++]);
				addActor(cells[x][y]);
			}
		}
	}
	
	@Override
	public void act(float delta) {
		if (timerActor != null && timerActor.isTimeOver()) {
			// TODO: Game Over
			System.out.println("Game Over");
		}
		if (selected[0] == null || selected[1] == null)
			timer = time;
		else if (timer >= 0)
			timer -= delta;
		else if (timer <= 0) {
			if (selected[0].getShape().equals(selected[1].getShape())) {
				selected[0].setRemoved(true);
				selected[1].setRemoved(true);
				pairsRem++;
				if (timerActor != null)
					timerActor.addTime(10);
				if (pairsRem == pairs) {
					// TODO: WIN
					if (timerActor != null)
						timerActor.setPerform(false);
					System.out.println("Win");
				}
			} else {
				selected[0].setSelected(false);
				selected[1].setSelected(false);
			}
			selected[0] = null;
			selected[1] = null;
		}
	}
	
	private Shape[] setPairs() {
		Shape[] shapes = new Shape[pairs * 2];
		Cluster[] clusters = new Cluster[pairs];
		for (int i = 0; i < pairs; i++) {
			// int color = i / Shape.Shapes.values().length;
			// if (color >= Shape.Colors.values().length)
			// throw new IllegalArgumentException("The amount of pairs is too
			// big");
			// int shape = i % Shape.Shapes.values().length;
			// clusters[i] = new Cluster(
			// new Shape(Shape.Colors.values()[color],
			// Shape.Shapes.values()[shape]));
			boolean repeted;
			do {
				repeted = false;
				int color = MathUtils.random(Shape.Colors.values().length - 1);
				int shape = MathUtils.random(Shape.Shapes.values().length - 1);
				Shape shapeTemp = new Shape(Shape.Colors.values()[color],
						Shape.Shapes.values()[shape]);
				for (int j = 0; j < i; j++) {
					if (shapeTemp.equals(clusters[j].shape)) {
						repeted = true;
						break;
					}
				}
				if (!repeted) {
					clusters[i] = new Cluster(shapeTemp);
				}
			} while (repeted);
		}
		for (int i = 0; i < pairs * 2; i++) {
			int index;
			do {
				index = MathUtils.random(pairs - 1);
			} while (clusters[index].num == 0);
			shapes[i] = clusters[index].shape;
			clusters[index].num--;
		}
		return shapes;
	}
	
	public void reset() {
		Shape[] pairs = setPairs();
		int i = 0;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				cells[x][y].setShape(pairs[i++]);
			}
		}
	}
	
	public int getBoardWidth() {
		return width;
	}
	
	public int getBoardHeight() {
		return height;
	}
	
	public void setTimerActor(Timer timerActor) {
		this.timerActor = timerActor;
	}
	
	public void select(float xPos, float yPos) {
		int x = (int) (xPos / 64), y = (int) (yPos / 64);
		if (selected[0] == null && !cells[x][y].isRemoved()) {
			selected[0] = cells[x][y];
			selected[0].setSelected(true);
		} else if (selected[1] == null) {
			if (!cells[x][y].equals(selected[0]) && !cells[x][y].isRemoved()) {
				selected[1] = cells[x][y];
				selected[1].setSelected(true);
			}
		}
	}
	
	private class Cluster {
		private Shape shape;
		private int num;
		
		private Cluster(Shape shape) {
			this.shape = shape;
			num = 2;
		}
	}
}
