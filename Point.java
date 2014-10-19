//Abstract class to handle storing and retrieving
//x and y values for DataPoints and Centroids
public abstract class Point {
	private double x;
	private double y;
	
	//default constructor
	public Point(){
	}
	
	//intiailize x and y values
	public Point(double x, double y){
		setX(x);
		setY(y);
	}
	
	public double getX() { return x; }
	public void setX(double x) { this.x = x; }
	public double getY() {	return y; }
	public void setY(double y) { this.y = y; }
}