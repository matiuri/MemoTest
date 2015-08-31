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

public abstract class AbstractScreen implements Screen {
	protected MemoTest game;
	private NinePatchDrawable buttonUp, buttonDown;
	private BitmapFont font;
	private boolean initedGUI = false;
	
	public AbstractScreen(MemoTest game) {
		this.game = game;
	}
	
	@Override
	public void resize(int width, int height) {
	}
	
	@Override
	public void pause() {
	}
	
	@Override
	public void resume() {
	
	}
	
	@Override
	public void dispose() {
	}
	
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
		par.borderWidth = 1.5f;
		font = gen.generateFont(par);
		gen.dispose();
		initedGUI = true;
	}
	
	protected final TextButton createButton(String text) {
		if (!initedGUI)
			throw new IllegalStateException("You haven't called initGUI method!");
		TextButtonStyle tbs = new TextButtonStyle(buttonUp, buttonDown, buttonDown, font);
		TextButton button = new TextButton(text, tbs);
		return button;
	}
	
	protected final Label createLabel(String text) {
		if (!initedGUI)
			throw new IllegalStateException("You haven't called initGUI method!");
		LabelStyle ls = new LabelStyle(font, Color.WHITE);
		Label label = new Label(text, ls);
		return label;
	}
}
