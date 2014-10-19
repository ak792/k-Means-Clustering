import java.util.LinkedList;

//Represents the centroid of a cluster
public class Centroid extends Point{
	private LinkedList<DataPoint> clusterDataLL;
	
	public Centroid(){
		super();
		clusterDataLL = new LinkedList<DataPoint>();
	}
	
	//Initialize with x and y values
	public Centroid(double x, double y){
		super(x,y);
		clusterDataLL = new LinkedList<DataPoint>();
	}
	
	/* Recalculates new centroid location by
	 * taking the average x and y values of the 
	 * data points the cluster contains. */
	
	public void updateCentroid(){
		double sumX = 0;
		double sumY = 0;
		int count = clusterDataLL.size();
		
 		for (int i = 0; i < count; i++){
			sumX += clusterDataLL.get(i).getX();
			sumY += clusterDataLL.get(i).getY();
		}	
 		
 		this.setX(sumX / count);
		this.setY(sumY / count);
	}
	
	//Adds a data point to the cluster
	public void addToCluster(DataPoint d){
		clusterDataLL.add(d);		
	}
	
	//Clears all data points from the cluster
	public void resetCluster(){
		clusterDataLL.clear();	
	}
	
	public LinkedList<DataPoint> getClusterDataLL(){
		return clusterDataLL;		
	}
}