package memotest;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import memotest.screens.AbstractScreen;
import memotest.screens.GameScreen;
import memotest.screens.TitleScreen;
import memotest.utils.assets.AssetLoader;

public class MemoTest extends Game {
	private AssetLoader loader;
	// TODO: remove public
	public AbstractScreen titleScreen, gameScreen;
	
	@Override
	public void create() {
		loader = new AssetLoader(this);
		gameScreen = new GameScreen(this);
		titleScreen = new TitleScreen(this);
		loader.queue("Filter", "cells/cellFilter.png", Texture.class)
				.queue("CellRegion", "cells/cells.png", Texture.class)
				.queue("Shapes", "cells/cellImages.png", Texture.class)
				.queue("ProgressBar", "objects/progressBar.png", Texture.class)
				.queue("Buttons", "GUI/buttons.png", Texture.class).load(titleScreen);
	}
	
	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
		// TODO: change this
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE))
			Gdx.app.exit();
	}
	
	@Override
	public void dispose() {
		loader.dispose();
	}
	
	public AssetLoader getLoader() {
		return loader;
	}
}
