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

public class TitleScreen extends AbstractScreen {
	private Stage stage;
	
	public TitleScreen(MemoTest game) {
		super(game);
	}
	
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
	
	@Override
	public void render(float delta) {
		stage.act(delta);
		stage.draw();
	}
	
	@Override
	public void hide() {
		stage.dispose();
	}
}
