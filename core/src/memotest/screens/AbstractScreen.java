package memotest.screens;

import com.badlogic.gdx.Screen;

import memotest.MemoTest;

public abstract class AbstractScreen implements Screen {
	protected MemoTest game;
	
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
}
