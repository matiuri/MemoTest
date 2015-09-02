package memotest.screens;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import memotest.MemoTest;

/**
 * This class represents the loading screen.
 * <p>
 * Extends {@link AbstractScreen}
 * 
 * @author Matías
 */
public class LoadingScreen extends AbstractScreen {
	private final AssetManager manager;
	private final AbstractScreen nextScreen;
	private SpriteBatch sb;
	private BitmapFont font;
	private float timer = 0.5f;
	
	/**
	 * Constructs a new {@link LoadingScreen}
	 * 
	 * @param game
	 *            -> A reference to {@link MemoTest}
	 * @param manager
	 *            -> A reference to the {@link AssetManager} that have to load
	 *            the queued assets
	 * @param nextScreen
	 *            -> The next screen
	 */
	public LoadingScreen(MemoTest game, final AssetManager manager,
			final AbstractScreen nextScreen) {
		super(game);
		this.manager = manager;
		this.nextScreen = nextScreen;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
		sb = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.WHITE);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#render(float)
	 */
	@Override
	public void render(float delta) {
		// TODO: improve this
		if (!manager.update()) {
			sb.begin();
			font.draw(sb, "Loading...", 50, 50);
			font.draw(sb, (int) (manager.getProgress() * 100) + "%", 120, 50);
			sb.end();
		} else {
			timer -= delta;
			sb.begin();
			font.draw(sb, "Loading...", 50, 50);
			font.draw(sb, "Loaded!", 120, 50);
			sb.end();
			if (timer >= 0)
				game.setScreen(nextScreen);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#hide()
	 */
	@Override
	public void hide() {
		sb.dispose();
		font.dispose();
	}
}
