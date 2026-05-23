package assignments.Ex3;
import java.util.ArrayList;
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
	// Constructs a map from scratch with a given width, height and a starting value.
	@Override
	public void init(int w, int h, int v) {
		if (w <= 0 || h <= 0) {
			this._map = new int [0][0];
			return;
		}
		this._map = new int [h][w];
		for(int[] row : this._map){
			for ( int col = 0; col < w; col++){
			row[col] = v;
			}
		}
	}
	// Takes an existing 2D array and copies it to my map.
	@Override
	public void init(int[][] arr) {
		if (arr == null || arr.length == 0 || arr[0] == null){
			this._map = new int [0][0];
			return;
		}
		int totalRows = arr.length;
		this._map = new int [totalRows][];
		for (int r = 0; r < totalRows; r++){
			int totalCols = arr[r].length;
			this._map[r] = new int[totalCols];
			for (int c = 0; c < totalCols; c++){
				this._map [r][c] = arr [r][c];
			}
	   	}
	}
	// Returns a copy of my current map for the user.
	@Override
	public int[][] getMap() {
		int[][] ans = null;
		if (this._map == null){
			return null;
		}
		int h = this.getHeight();
		int w = this.getWidth();
		ans = new int [h][w];
		for (int r = h - 1; r >= 0; r--){
			for (int c = w - 1; c >= 0; c--){
				ans[r][c] = this._map[r][c];
			}
		}
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
		if (p.getX() < 0 || p.getY() < 0) {
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
		if (xy == null){
			return ans;
		}
		int startX = xy.getX();
		int startY = xy.getY();
		int old_v = this.getPixel(startX, startY);
		if (old_v == -1 || old_v == new_v){
			return ans;
		}
		ArrayList<Pixel2D> targets = new ArrayList<Pixel2D>();
		targets.add(xy);
		setPixel(startX, startY, new_v);
		ans++;
		int [] dx = {-1, 0, 1, 0};
		int [] dy = {0, 1, 0, -1};
		for (int i = 0; i < 4; i++){
			int nextX = startX + dx[i];
			int nextY = startY + dy[i];
			if (getPixel (nextX, nextY) == old_v){
				setPixel(nextX, nextY, new_v);
				ans++;
				targets.add(new Index2D(nextX, nextY));
			}
		}
		return ans;	
	}

	@Override
	/**
	 * BFS like shortest the computation based on iterative raster implementation of BFS, see:
	 * https://en.wikipedia.org/wiki/Breadth-first_search
	 */
	public Pixel2D[] shortestPath(Pixel2D p1, Pixel2D p2, int obsColor) {
		Pixel2D[] ans = null;  // the result.
		if (p1 == null || p2 == null){
			return ans;
		}
		startCol = getPixel(p1);
		endCol = getPixel(p2);
		if (startCol == obsColor || endCol == obsColor || startCol == -1 || endCol == -1){
			return null;
		}
		if (p1.getX() == p2.getX() && p1.getY() == p2.getY()){
			return new Pixel2D[]{p1};
		}
		int h = getHeight();
		int w = getWidth();
		int [][] stepsGrid = new int [h][w];
		for (int i = 0; i < h; i++){
			for (int j = 0; j < w; j++){
				stepsGrid[i][j] = -1;
			}
		}
		ArrayList<Pixel2d> queue = new ArrayList<>();
		queue.add(p1);
		stepsGrid[p1.getY()][p1.getX()] = 0;
		return ans;
		int [] dx = {-1, 0, 1, 0};
		int [] dy = {0, 1, 0, -1};
		boolean reached = false;
		while (queue.size() > 0 ){
			Pixel2D curr = queue.remove(0);
			if (curr.getX() ==p2.getX() && curr.getY() == p2.getY()){
				reached = true;
				break;
			}
			int currentDistance = stepsGrid[curr.getY()][curr.getX()];
			for (int i = 0; i < 4; i++){
				int nextX = curr.getX() + dx[i];
				int nextY = curr.getY() + dy[i];
				if (getPixel(nextX, nextY) != -1 && getPixel(nextX, nextY) != obsColor && stepsGrid[nextY][nextX] == -1){
					stepsGrid[nextY][nextX] = currentDistance + 1;
					queue.add (new Index2D(nextX, nextY));
				}
			}
		}
		if (!reached){
			return null;
		}
		int totalSteps = stepsGrid[p2.getY()][p2.getX()];
		ans = new Pixel2D[totalSteps + 1];
		Pixel2D check = p1;
		ans[0] = check;
		for(int k = 1; k <= totalSteps; k++){
			for (int i = 0; i < 4; i++){
				int nextX = check.getX() + dx[i];
				int nextY = check.getY() + dy[i];
				if (nextX >= 0 && nextY >= 0 && nextX < w && nextY < h){
					if (stepsGrid[nextY][nextX] == k){
						check = new Index2D(nextX, nextY);
						ans[k] = check;
						break;
					}
				}
			}
		}
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
