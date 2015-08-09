package memotest.screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import memotest.MemoTest;
import memotest.land.Cell;
import memotest.land.Table;

public class GameScreen extends AbstractScreen {
	private Stage stage;
	
	public GameScreen(MemoTest game) {
		super(game);
	}
	
	@Override
	public void show() {
		stage = new Stage(new FitViewport(800, 480));
		Table t = new Table(2, 3, game.getLoader());
		Cell[][] cells = t.getCells();
		for (int i = 0; i < cells.length; i++)
			for (int j = 0; j < cells[i].length; j++)
				stage.addActor(cells[i][j]);
	}
	
	@Override
	public void render(float delta) {
		stage.act(delta);
		stage.draw();
	}
	
	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height);
	}
	
	@Override
	public void hide() {
		// TODO Auto-generated method stub
	}
}
