package memotest.board;

import com.badlogic.gdx.math.MathUtils;

/**
 * This auxiliary class has been created in order to reduce the code amount in
 * {@link Table}
 * 
 * @author Matías
 */
class Generator {
	private static int pairs;
	
	/**
	 * Creates the random pairs
	 * 
	 * @param pairs
	 *            -> The amount of pairs
	 * @return A {@link Cluster} array. Each cluster is coumpouned by a
	 *         {@link Shape} and the aviable repetitions left (2)
	 */
	static Cluster[] generateRandomPairs(int pairs) {
		Generator.pairs = pairs;
		Cluster[] clusters = new Cluster[pairs];
		for (int i = 0; i < pairs; i++) {
			boolean repeted;
			do {
				repeted = false;
				int color = MathUtils.random(Shape.Colors.values().length - 1);
				int shape = MathUtils.random(Shape.Shapes.values().length - 1);
				Shape shapeTemp = new Shape(Shape.Colors.values()[color],
						Shape.Shapes.values()[shape]);
				for (int j = 0; j < i; j++) {
					if (shapeTemp.equals(clusters[j].shape)) {
						repeted = true;
						break;
					}
				}
				if (!repeted) {
					clusters[i] = new Cluster(shapeTemp);
				}
			} while (repeted);
		}
		return clusters;
	}
	
	/**
	 * Randomize the positions of a {@link Cluster}
	 * 
	 * @param clusters
	 *            -> A {@link Cluster}
	 * @return A randomized {@link Shape} array
	 */
	static Shape[] sortPairs(Cluster[] clusters) {
		Shape[] shapes = new Shape[pairs * 2];
		for (int i = 0; i < pairs * 2; i++) {
			int index;
			do {
				index = MathUtils.random(pairs - 1);
			} while (clusters[index].num == 0);
			shapes[i] = clusters[index].shape;
			clusters[index].num--;
		}
		return shapes;
	}
	
	private static class Cluster {
		private Shape shape;
		private int num;
		
		private Cluster(Shape shape) {
			this.shape = shape;
			num = 2;
		}
	}
}
