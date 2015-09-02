package memotest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import memotest.MemoTest;
import memotest.board.Table;
import memotest.input.TableInputListener;
import memotest.utils.time.Timer;

/**
 * This class represents the screen of the game itself.
 * <p>
 * Extends {@link AbstractScreen}
 * 
 * @author Matías
 */
public class GameScreen extends AbstractScreen {
	/**
	 * The {@link Stage}
	 */
	private Stage stage;
	/**
	 * The {@link Table}
	 */
	private Table table;
	/**
	 * The {@link Timer}
	 */
	private Timer timer;
	
	/**
	 * Constructs a new {@link GameScreen}
	 * 
	 * @param game
	 *            -> A reference to {@link MemoTest}
	 */
	public GameScreen(MemoTest game) {
		super(game);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
		stage = new Stage(new ExtendViewport(800, 480));
		table = new Table(6, 6, game.getLoader());
		table.addListener(new TableInputListener(table));
		stage.addActor(table);
		Timer.init(game.getLoader());
		float width = stage.getWidth() - table.getRight() - 50;
		float x = table.getRight() + (stage.getWidth() - table.getRight()) / 2 - width / 2;
		timer = new Timer(30, x, stage.getHeight() / 2 - 25, width, 50);
		timer.setPerform(true);
		stage.addActor(timer);
		table.setTimerActor(timer);
		Gdx.input.setInputProcessor(stage);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#render(float)
	 */
	@Override
	public void render(float delta) {
		// TODO: Change this
		if (Gdx.input.isKeyJustPressed(Keys.R))
			table.reset();
		stage.act(delta);
		stage.draw();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see memotest.screens.AbstractScreen#resize(int, int)
	 */
	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#hide()
	 */
	@Override
	public void hide() {
		stage.dispose();
	}
}
