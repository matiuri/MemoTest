package memotest.desktop;

import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import memotest.MemoTest;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		DisplayMode dm = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDisplayMode();
		cfg.width = dm.getWidth();
		cfg.height = dm.getHeight();
		cfg.resizable = true;
		cfg.fullscreen = true;
		cfg.vSyncEnabled = false;
		cfg.backgroundFPS = 30;
		cfg.foregroundFPS = 0;
		new LwjglApplication(new MemoTest(), cfg);
	}
}
