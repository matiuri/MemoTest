package memotest.input;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import memotest.board.Table;

public class TableInputListener extends InputListener {
	private Table table;
	
	public TableInputListener(Table table) {
		this.table = table;
	}
	
	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		if (x < 0 || y < 0 || x > table.getBoardWidth() * 64 || y > table.getBoardHeight() * 64)
			return false;
		table.select(x, y);
		return true;
	}
}
