import java.util.LinkedList;

//Represents the data points that are put into clusters
public class DataPoint extends Point {
	
	public DataPoint(){
		super();
	}
	
	//Initialize with x and y values
	public DataPoint(double x, double y){
		super(x,y);
	}

	//Finds the closest centroid to the current data point
	public Centroid findClosestCentroid(LinkedList<Centroid> centroidLL){
		double minDist = Double.MAX_VALUE;
		Centroid closestCent = new Centroid();
		double currDist;
		
		//gets the distance between the data point and each centroid
		//and returns the centroid with the minimum distance
		int i = 0;
		while  (i < centroidLL.size()){
			currDist = Math.sqrt(Math.pow((this.getX() - centroidLL.get(i).getX()),2) + Math.pow((this.getY() - centroidLL.get(i).getY()),2)); //the "this" references are just to clarify
			if (currDist < minDist) {
				minDist = currDist;
				closestCent = centroidLL.get(i);
			}		
			i++;
		}

		return closestCent;		
	}
}