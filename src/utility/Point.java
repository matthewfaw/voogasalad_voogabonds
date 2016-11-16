package utility;

/**
 * A class that represents 2 and 3D points, as well as supporting some math between them. (Distance calculations, vector math, etc.)
 * @author Weston
 *
 */
public class Point {
	private double myX;
	private double myY;
	private double myZ;
	
	/**
	 * Constructs a 3D point with the given coordinates
	 * @param x
	 * @param y
	 * @param z
	 */
	public Point(double x, double y, double z) {
		myX = x;
		myY = y;
		myZ = z;
	}
	
	/**
	 * Constructs a 2D point with the given coordinates
	 * @param x
	 * @param y
	 */
	public Point(double x, double y) {
		this(x, y, 0);
	}
	
	/**
	 * Constructs a 3D point from the given coordinates
	 * @param x
	 * @param y
	 * @param z
	 */
	public Point(Number x, Number y, Number z) {
		this(x.doubleValue(), y.doubleValue(), z.doubleValue());
	}
	
	/**
	 * Constructs a 2D point with the given coordinates
	 * @param x
	 * @param y
	 */
	public Point(Number x, Number y) {
		this(x.doubleValue(), y.doubleValue(), 0);
	}
	
	/**
	 * Constructs a point at the origin
	 */
	public Point(){
		this(0, 0, 0);
	}
	
	/**
	 * Adds the two given points together
	 * @param p
	 * @return a new point, whose coordinates equal the two given points added together coordinate-wise
	 */
	public Point add(Point p) {
		return new Point(
				myX + p.myX,
				myY + p.myY,
				myZ + p.myZ
				);
	}
	
	/**
	 * Subtracts Point p from this
	 * @param p
	 * @return a new point, whose coordinates equal the difference between the two given points coordinate-wise
	 */
	public Point subtract(Point p) {
		return new Point(
				myX - p.myX,
				myY - p.myY,
				myZ - p.myZ
				);
	}
	
	/**
	 * Computes the dot product of p and this
	 * @param p
	 * @return the dot product
	 */
	public double dot(Point p) {
		return 	myX * p.myX +
				myY * p.myY +
				myZ * p.myZ;
	}
	
	/**
	 * Computes the cross product of p and this
	 * @param p
	 * @return a new Point perpendicular to this and p, with magnitude |this|*|p| 
	 */
	public Point cross(Point p) {
		return new Point(
				myY * p.myZ - myZ * p.myY,
				myZ * p.myX - myX * p.myZ,
				myX * p.myY - myY * p.myX
				);
				
	}
	
	/**
	 * Computes the euclidean distance between this and p
	 * @param p
	 * @return the distance
	 */
	public double euclideanDistance(Point p) {
		return Math.sqrt(
				Math.pow(myX - p.myX, 2) +
				Math.pow(myY - p.myY, 2) +
				Math.pow(myZ - p.myZ, 2)
				);
	}
	
	/**
	 * Computes the rectilinear distance between this and p
	 * @param p
	 * @return the distance
	 */
	public double rectilinearDistance(Point p){
		return
				Math.abs(myX - p.myX) +
				Math.abs(myY - p.myY) +
				Math.abs(myZ - p.myZ);
	}
	
	/**
	 * Computes the magnitude of this
	 * @return the magnitude
	 */
	public double magnitude(){
		return Math.sqrt(this.dot(this));
	}
	
	public double getX(){
		return myX;
	}
	public double getY(){
		return myY;
	}
	public double getZ(){
		return myZ;
	}
	
	public void setX(Number n){
		myX = n.doubleValue();
	}
	public void setY(Number n){
		myY= n.doubleValue();
	}
	public void setZ(Number n){
		myZ= n.doubleValue();
	}
}
