package memotest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

import memotest.MemoTest;

/**
 * This class represents a {@link MemoTest} screen.
 * <p>
 * Implements {@link Screen}
 * 
 * @author Matías
 */
public abstract class AbstractScreen implements Screen {
	/**
	 * A reference to {@link MemoTest}
	 */
	protected MemoTest game;
	private NinePatchDrawable buttonUp, buttonDown;
	private BitmapFont font;
	private boolean initedGUI = false;
	
	/**
	 * Constructs a new {@link AbstractScreen}
	 * 
	 * @param game
	 *            -> A reference to {@link MemoTest}
	 */
	public AbstractScreen(MemoTest game) {
		this.game = game;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#resize(int, int)
	 */
	@Override
	public void resize(int width, int height) {
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#pause()
	 */
	@Override
	public void pause() {
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#resume()
	 */
	@Override
	public void resume() {
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#dispose()
	 */
	@Override
	public void dispose() {
	}
	
	/**
	 * This method should be called by all the screens that extend
	 * {@link AbstractScreen} and want to use scene2dui
	 */
	protected final void initGUI() {
		Texture tex = game.getLoader().get("Buttons", Texture.class);
		NinePatch up, down;
		up = new NinePatch(new TextureRegion(tex, 0, 32, 64, 32), 12, 12, 12, 12);
		down = new NinePatch(new TextureRegion(tex, 0, 0, 64, 32), 12, 12, 12, 12);
		buttonUp = new NinePatchDrawable(up);
		buttonDown = new NinePatchDrawable(down);
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(
				Gdx.files.internal("fonts/Ubuntu-Regular.ttf"));
		FreeTypeFontParameter par = new FreeTypeFontParameter();
		par.size = 64;
		par.color = Color.WHITE;
		par.borderColor = Color.BLACK;
		par.borderWidth = 3;
		font = gen.generateFont(par);
		gen.dispose();
		initedGUI = true;
	}
	
	/**
	 * Creates a {@link TextButton}
	 * 
	 * @param text
	 *            -> The text of the button
	 * @return The button
	 * @throws IllegalStateException
	 *             if the method {@link #initGUI()} hasn't been called
	 */
	protected final TextButton createButton(String text) throws IllegalStateException {
		if (!initedGUI)
			throw new IllegalStateException("You haven't called initGUI method!");
		TextButtonStyle tbs = new TextButtonStyle(buttonUp, buttonDown, buttonDown, font);
		TextButton button = new TextButton(text, tbs);
		return button;
	}
	
	/**
	 * Creates a {@link Label}
	 * 
	 * @param text
	 *            -> The text of the label
	 * @return The label
	 * @throws IllegalStateException
	 *             if the method {@link #initGUI()} hasn't been called
	 */
	protected final Label createLabel(String text) throws IllegalStateException {
		if (!initedGUI)
			throw new IllegalStateException("You haven't called initGUI method!");
		LabelStyle ls = new LabelStyle(font, Color.WHITE);
		Label label = new Label(text, ls);
		return label;
	}
}
