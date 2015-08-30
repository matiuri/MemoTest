package memotest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import memotest.MemoTest;
import memotest.board.Table;
import memotest.input.TableInputListener;
import memotest.utils.time.Timer;

public class GameScreen extends AbstractScreen {
	private Stage stage;
	private Table table;
	private Timer timer;
	
	public GameScreen(MemoTest game) {
		super(game);
	}
	
	@Override
	public void show() {
		stage = new Stage(new ExtendViewport(800, 480));
		table = new Table(6, 6, game.getLoader());
		table.addListener(new TableInputListener(table));
		stage.addActor(table);
		Timer.init(game.getLoader());
		float width = stage.getWidth() - table.getRight() - 50;
		float x = table.getRight() + (stage.getWidth() - table.getRight()) / 2 - width / 2;
		timer = new Timer(60, x, stage.getHeight() / 2 - 25, width, 50);
		timer.setPerform(true);
		stage.addActor(timer);
		table.setTimerActor(timer);
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void render(float delta) {
		// TODO: Change this
		if (Gdx.input.isKeyJustPressed(Keys.R))
			table.reset();
		stage.act(delta);
		stage.draw();
	}
	
	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height);
	}
	
	@Override
	public void hide() {
		stage.dispose();
	}
}
