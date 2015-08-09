package memotest.utils.assets;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.Disposable;

public class AssetLoader implements Disposable {
	private AssetManager manager;
	private Map<String, String> map;
	
	public AssetLoader() {
		manager = new AssetManager();
		map = new HashMap<>();
	}
	
	public <T> AssetLoader queue(String key, String path, Class<T> CLASS) {
		if (!map.containsKey(key)) {
			manager.load(path, CLASS);
			map.put(key, path);
		} else {
			throw new IllegalArgumentException("The key " + key + " couldn't be used");
		}
		return this;
	}
	
	public void load() {
		// TODO: improve this
		manager.finishLoading();
	}
	
	public <T> T get(String key, Class<T> CLASS) {
		if (map.containsKey(key)) {
			T obj = manager.get(map.get(key), CLASS);
			return obj;
		}
		throw new IllegalArgumentException("The key " + key + " couldn't be found");
	}
	
	public void unload(String key) {
		if (!map.containsKey(key))
			throw new IllegalArgumentException("The key " + key + " couldn't be found");
		manager.unload(map.get(key));
	}
	
	public void clear() {
		manager.clear();
	}
	
	@Override
	public void dispose() {
		manager.dispose();
	}
}
