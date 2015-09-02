package memotest.input;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import memotest.board.Table;

/**
 * This class handles the input events related to the {@link Table}
 * 
 * @author Matías
 */
public class TableInputListener extends InputListener {
	/**
	 * A reference to {@link Table}
	 */
	private Table table;
	
	/**
	 * Constructs a new {@link TableInputListener}
	 * 
	 * @param table
	 *            -> A reference to {@link Table}
	 */
	public TableInputListener(Table table) {
		this.table = table;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.badlogic.gdx.scenes.scene2d.InputListener#touchDown(com.badlogic.gdx.
	 * scenes.scene2d.InputEvent, float, float, int, int)
	 */
	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		if (x < 0 || y < 0 || x > table.getBoardWidth() * 64 || y > table.getBoardHeight() * 64)
			return false;
		table.select(x, y);
		return true;
	}
}
