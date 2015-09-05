package memotest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
	private Label movesLeft;
	/**
	 * Whether the game is timed or not
	 */
	private boolean timed;
	
	/**
	 * Constructs a new {@link GameScreen}
	 * 
	 * @param game
	 *            -> A reference to {@link MemoTest}
	 * @param timed
	 *            -> Whether the game is timed or not
	 */
	public GameScreen(MemoTest game, boolean timed) {
		super(game);
		this.timed = timed;
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
		table.setY(stage.getHeight() / 2 - table.getHeight() / 2);
		stage.addActor(table);
		if (timed) {
			// TODO: center table and put timer on bottom
			Timer.init(game.getLoader());
			float width = stage.getWidth() - table.getRight() - 50;
			float x = table.getRight() + (stage.getWidth() - table.getRight()) / 2 - width / 2;
			Timer timer = new Timer(30, x, stage.getHeight() / 2 - 25, width, 50);
			timer.setPerform(true);
			stage.addActor(timer);
			table.setTimerActor(timer);
		} else {
			table.setX(stage.getWidth() / 2 - table.getWidth() / 2);
			table.setMoovesLeft(10);
			initGUI(24);
			movesLeft = createLabel("Moves Left: " + table.getMovesLeft());
			movesLeft.setX(stage.getWidth() / 2 - movesLeft.getWidth() / 2);
			stage.addActor(movesLeft);
		}
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
		if (!timed) {
			movesLeft.setText("Moves Left: " + table.getMovesLeft());
			movesLeft.setX(stage.getWidth() / 2 - movesLeft.getWidth() / 2);
		}
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
