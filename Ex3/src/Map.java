package assignments.Ex3;

/**
 * This class represents a 2D map as a "screen" or a raster matrix or maze over integers.
 * @author boaz.benmoshe
 *
 */
public class Map implements Map2D {
	private int[][] _map;
	private boolean _cyclicFlag = true;
	
	/**
	 * Constructs a w*h 2D raster map with an init value v.
	 * @param w
	 * @param h
	 * @param v
	 */
	public Map(int w, int h, int v) {
		init(w,h, v);
		}
	/**
	 * Constructs a square map (size*size).
	 * @param size
	 */
	public Map(int size) {this(size,size, 0);}
	
	/**
	 * Constructs a map from a given 2D array.
	 * @param data
	 */
	public Map(int[][] data) {
		init(data);
	}
	@Override
	public void init(int w, int h, int v) {
		/////// add your code below ///////

		///////////////////////////////////
	}
	@Override
	public void init(int[][] arr) {
		/////// add your code below ///////

		///////////////////////////////////
	}
	@Override
	public int[][] getMap() {
		int[][] ans = null;
		/////// add your code below ///////

		///////////////////////////////////
		return ans;
	}
	@Override
	// return amount of the columns in the first row.
	public int getWidth() {
		return this._map[0].length;		
		}
	@Override
	// return amount of rows 
	public int getHeight() {
		return this._map.length;
	}
	@Override
	// return value of pixel in row y column x [][], "if" because x || y can't be negative.
	public int getPixel(int x, int y) { 
		if (x < 0 || y < 0) {
			return -1;
		}
		return this._map[y][x];
	}
	@Override
	/* return value of point p in the matrix, overload from last function, doesn't need "if" because
	it calls the geters. */
	public int getPixel(Pixel2D p) {	
		return this.getPixel(p.getX(),p.getY());
	}
	@Override
	// set v (value) in row y column x
	public void setPixel(int x, int y, int v) {
		if (x < 0 || y < 0) {
			return;
		}	
		this._map[y][x] = v;
	}
	@Override

	// return value of point p in the matrix, "if" because getY, getX can't be negative.
	public void setPixel(Pixel2D p, int v) {
		if (x < 0 || y < 0) {
			return ;
		}	
		this._map[p.getY()][p.getX()] = v;
	}
	@Override
	/** 
	 * Fills this map with the new color (new_v) starting from p.
	 * https://en.wikipedia.org/wiki/Flood_fill
	 */
	public int fill(Pixel2D xy, int new_v) {
		int ans=0;
		/////// add your code below ///////

		///////////////////////////////////
		return ans;
	}

	@Override
	/**
	 * BFS like shortest the computation based on iterative raster implementation of BFS, see:
	 * https://en.wikipedia.org/wiki/Breadth-first_search
	 */
	public Pixel2D[] shortestPath(Pixel2D p1, Pixel2D p2, int obsColor) {
		Pixel2D[] ans = null;  // the result.
		/////// add your code below ///////

		///////////////////////////////////
		return ans;
	}
	@Override
	/////// add your code below ///////
	public boolean isInside(Pixel2D p) {
		return false;
	}

	@Override
	/////// add your code below ///////
	public boolean isCyclic() {
		return false;
	}
	@Override
	/////// add your code below ///////
	public void setCyclic(boolean cy) {;}
	@Override
	/////// add your code below ///////
	public Map2D allDistance(Pixel2D start, int obsColor) {
		Map2D ans = null;  // the result.
		/////// add your code below ///////

		///////////////////////////////////
		return ans;
	}
}
