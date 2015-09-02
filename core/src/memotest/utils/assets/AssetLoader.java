package memotest.utils.assets;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.Disposable;

import memotest.MemoTest;
import memotest.screens.AbstractScreen;
import memotest.screens.LoadingScreen;

/**
 * This class manages the assets
 * 
 * @author Matías
 */
public class AssetLoader implements Disposable {
	/**
	 * The {@link AssetManager}
	 */
	private AssetManager manager;
	/**
	 * The assets' key-path {@link Map}
	 */
	private Map<String, String> map;
	/**
	 * The reference to {@link MemoTest}
	 */
	private MemoTest game;
	
	/**
	 * Constructs a new {@link AssetLoader}
	 * 
	 * @param game
	 *            -> The reference to {@link MemoTest}
	 */
	public AssetLoader(MemoTest game) {
		manager = new AssetManager();
		map = new HashMap<>();
		this.game = game;
	}
	
	/**
	 * Queues an asset which has to be loaded
	 * 
	 * @param <T>
	 *            -> The type of the asset's class
	 * @param key
	 *            -> The alias of the asset
	 * @param path
	 *            -> The path of the asset
	 * @param CLASS
	 *            -> The class of the asset
	 * @return This -> That allows you to chain this method, and
	 *         {@link AssetLoader#load(AbstractScreen)}
	 * @throws IllegalArgumentException
	 *             if the alias is being used by another asset
	 */
	public <T> AssetLoader queue(String key, String path, Class<T> CLASS)
			throws IllegalArgumentException {
		if (!map.containsKey(key)) {
			manager.load(path, CLASS);
			map.put(key, path);
		} else {
			throw new IllegalArgumentException("The key " + key + " couldn't be used");
		}
		return this;
	}
	
	/**
	 * Loads the queued assets
	 * 
	 * @param nextScreen
	 *            -> The {@link AbstractScreen} that has to be displayed after
	 *            loading the assets
	 */
	public void load(final AbstractScreen nextScreen) {
		game.setScreen(new LoadingScreen(game, manager, nextScreen));
	}
	
	/**
	 * @param <T>
	 *            -> The type of the asset's class
	 * @param key
	 *            -> The asset's alias
	 * @param CLASS
	 *            -> The asset's class
	 * @return The asset
	 * 		
	 * @throws IllegalArgumentException
	 *             if the alias of the asset doesn't exist
	 */
	public <T> T get(String key, Class<T> CLASS) throws IllegalArgumentException {
		if (map.containsKey(key)) {
			T obj = manager.get(map.get(key), CLASS);
			return obj;
		}
		throw new IllegalArgumentException("The key " + key + " couldn't be found");
	}
	
	/**
	 * Unloads an asset
	 * 
	 * @param key
	 *            -> The asset's alias
	 * @throws IllegalArgumentException
	 *             if the alias of the asset doesn't exist
	 */
	public void unload(String key) throws IllegalArgumentException {
		if (!map.containsKey(key))
			throw new IllegalArgumentException("The key " + key + " couldn't be found");
		manager.unload(map.get(key));
	}
	
	/**
	 * Unload all the assets of the {@link AssetLoader#manager}, and also clears
	 * the {@link AssetLoader#map}
	 */
	public void clear() {
		manager.clear();
		map.clear();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.utils.Disposable#dispose()
	 */
	@Override
	public void dispose() {
		manager.dispose();
		map.clear();
	}
}
