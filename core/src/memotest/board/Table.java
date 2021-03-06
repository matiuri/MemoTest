package memotest.board;

import static memotest.board.Generator.*;

import com.badlogic.gdx.scenes.scene2d.Group;

import memotest.MemoTest;
import memotest.screens.GameFinishedScreen;
import memotest.utils.assets.AssetLoader;
import memotest.utils.time.Timer;

/**
 * This class represents the table.
 * <p>
 * Extends {@link Group}.
 * 
 * @author Matías
 */
public class Table extends Group {
	private MemoTest game;
	/**
	 * The width and height without counting the {@link Cell} size, and the
	 * amount of pairs
	 */
	private final int width, height, pairs;
	/**
	 * The amount of remaining pairs
	 */
	private int pairsRem = 0;
	/**
	 * The reference to all the {@link Cell}s
	 */
	private Cell[][] cells;
	/**
	 * THe reference to the selected {@link Cell}s
	 */
	private Cell[] selected;
	/**
	 * The amount of time of the cooldown
	 */
	private final float time = 0.75f;
	/**
	 * The current amount of time
	 */
	private float timer;
	/**
	 * The reference to the {@link Timer}. May be null.
	 */
	private Timer timerActor;
	private int movesLeft;
	private boolean movesMode;
	
	/**
	 * Constructs a new {@link Table}
	 * 
	 * @param width
	 *            -> The amount of cols
	 * @param height
	 *            -> The amount of rows
	 * @param loader
	 *            -> An {@link AssetLoader}
	 */
	public Table(MemoTest game, int width, int height, AssetLoader loader) {
		if ((width * height) % 2 != 0)
			throw new IllegalArgumentException("You can't use this width and height");
		this.game = game;
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
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.scenes.scene2d.Group#act(float)
	 */
	@Override
	public void act(float delta) {
		if ((!movesMode && timerActor.isTimeOver()) || (movesMode && movesLeft == 0)) {
			game.setScreen(new GameFinishedScreen(game, false, !movesMode, 0));
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
				if (!movesMode)
					timerActor.addTime(7);
				else {
					if (movesLeft + 7 < 25)
						movesLeft += 7;
					else
						movesLeft = 25;
				}
				if (pairsRem == pairs) {
					if (!movesMode)
						timerActor.setPerform(false);
					float temp;
					if (movesMode)
						temp = movesLeft;
					else
						temp = timerActor.getTime();
					game.setScreen(new GameFinishedScreen(game, true, !movesMode, temp));
				}
			} else {
				selected[0].setSelected(false);
				selected[1].setSelected(false);
				if (movesMode)
					movesLeft -= 1;
			}
			selected[0] = null;
			selected[1] = null;
		}
	}
	
	private Shape[] setPairs() {
		if (pairs > Shape.Colors.values().length * Shape.Shapes.values().length)
			throw new IllegalArgumentException("There are too many pairs: " + pairs);
		return sortPairs(generateRandomPairs(pairs));
	}
	
	/**
	 * Resets all the pairs
	 */
	public void reset() {
		Shape[] pairs = setPairs();
		int i = 0;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				cells[x][y].setShape(pairs[i++]);
			}
		}
	}
	
	/**
	 * @return The amount of cols
	 */
	public int getBoardWidth() {
		return width;
	}
	
	/**
	 * @return The amount of rows
	 */
	public int getBoardHeight() {
		return height;
	}
	
	/**
	 * Sets the {@link #timerActor}
	 * 
	 * @param timerActor
	 *            -> An instance of {@link Timer}
	 */
	public void setTimerActor(Timer timerActor) {
		this.timerActor = timerActor;
		movesMode = false;
	}
	
	public void setMoovesLeft(int movesLeft) {
		this.movesLeft = movesLeft;
		movesMode = true;
	}
	
	public int getMovesLeft() {
		return movesLeft;
	}
	
	/**
	 * Selects a cell
	 * 
	 * @param xPos
	 * @param yPos
	 */
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
}
