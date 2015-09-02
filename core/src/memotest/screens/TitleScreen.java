package memotest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import memotest.MemoTest;

/**
 * This class represents the title screen of {@link MemoTest}
 * 
 * @author Matías
 */
public class TitleScreen extends AbstractScreen {
	/**
	 * The {@link Stage}
	 */
	private Stage stage;
	
	/**
	 * Constructs a new {@link TitleScreen}
	 * 
	 * @param game
	 *            -> A reference to {@link MemoTest}
	 */
	public TitleScreen(MemoTest game) {
		super(game);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
		stage = new Stage(new ScreenViewport());
		Table table = new Table();
		table.setDebug(true);
		stage.addActor(table);
		table.setFillParent(true);
		initGUI();
		Label title = createLabel("MemoTest");
		TextButton newGame = createButton("New Game");
		TextButton exit = createButton("Exit");
		
		// FIXME: fix position and size
		table.add(title).colspan(2).pad(25).expandX().row();
		table.add(newGame).pad(25).expandX().fill().row();
		table.add(exit).pad(25).expandX().fill();
		
		newGame.addCaptureListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(game.gameScreen);
			}
		});
		
		exit.addCaptureListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Gdx.app.exit();
			}
		});
		Gdx.input.setInputProcessor(stage);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#render(float)
	 */
	@Override
	public void render(float delta) {
		stage.act(delta);
		stage.draw();
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
