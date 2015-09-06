package memotest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import memotest.MemoTest;

public class GameFinishedScreen extends AbstractScreen {
	private final boolean won, timed;
	private final float value;
	
	private Stage stage;
	
	public GameFinishedScreen(MemoTest game, boolean won, boolean timed, float value) {
		super(game);
		this.won = won;
		this.timed = timed;
		this.value = value;
	}
	
	@Override
	public void show() {
		initGUI();
		stage = new Stage(new ScreenViewport());
		Table table = new Table();
		stage.addActor(table);
		table.setFillParent(true);
		String text = (won) ? "Gongratulations! " : "Game Over";
		if (won) {
			if (timed)
				text += value + " seconds left";
			else
				text += (int) value + " moves left";
		}
		TextButton replay, menu;
		replay = createButton("Replay");
		menu = createButton("Menu");
		table.add(createLabel(text)).colspan(2).pad(25).expandX().row();
		table.add(replay).pad(25).width(400).align(Align.right);
		table.add(menu).pad(25).width(400).align(Align.left);
		
		replay.addCaptureListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.gameScreen = new GameScreen(game, timed);
				game.setScreen(game.gameScreen);
			}
		});
		menu.addCaptureListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(game.titleScreen);
			}
		});
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void render(float delta) {
		if (won)
			Gdx.gl.glClearColor(0, 0.3f, 0, 1);
		else
			Gdx.gl.glClearColor(0.5f, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
	}
	
	@Override
	public void hide() {
		stage.dispose();
	}
}
