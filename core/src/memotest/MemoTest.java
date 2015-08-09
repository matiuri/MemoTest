package memotest;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;

import memotest.screens.AbstractScreen;
import memotest.screens.GameScreen;
import memotest.utils.assets.AssetLoader;

public class MemoTest extends Game {
	private AssetLoader loader;
	private AbstractScreen gameScreen;
	
	@Override
	public void create() {
		loader = new AssetLoader();
		loader.queue("Cell", "TempCell.png", Texture.class).load();
		gameScreen = new GameScreen(this);
		setScreen(gameScreen);
	}
	
	@Override
	public void render() {
		super.render();
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
