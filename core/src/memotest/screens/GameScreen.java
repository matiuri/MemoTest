package memotest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import memotest.MemoTest;
import memotest.board.Table;
import memotest.input.TableInputListener;
import memotest.utils.time.Timer;

public class GameScreen extends AbstractScreen {
	private Stage stage;
	private Table t;
	
	public GameScreen(MemoTest game) {
		super(game);
	}
	
	@Override
	public void show() {
		stage = new Stage(new FitViewport(800, 480));
		t = new Table(6, 6, game.getLoader());
		t.addListener(new TableInputListener(t));
		stage.addActor(t);
		Timer.init(game.getLoader());
		Timer timer = new Timer(5f, 100, 100, 200, 50);
		timer.setPerform(true);
		stage.addActor(timer);
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void render(float delta) {
		if (Gdx.input.isKeyJustPressed(Keys.R))
			t.reset();
		stage.act(delta);
		stage.draw();
	}
	
	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height);
	}
	
	@Override
	public void hide() {
	}
}
