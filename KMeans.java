import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*************************************
 * Runs k-means cluster analysis on two-dimensional data points.
 * 
 * Groups data points in a text file into a given number clusters
 * by running a k-means cluster analysis algorithm for
 * a given number of iterations. Prints each cluster's centroid and
 * data points.
 * 
 * Data points are held in LinkedList dataLL and centroids are
 * held in LinkedList centroidLL. In each iteration of kMeans,
 * data points are added to the LinkedList clusterDataLL
 * of the closest centroid. 
*************************************/

public class KMeans {
	
	//constants used if do not ask for user input
	private int DEFAULT_ITER = 2,
			DEFAULT_K = 2;
	
	private int iter = -1,
			k = -1;
	private String filePath = "";
	private List<DataPoint> dataLL;
	private List<Centroid> centroidLL;
	

	public static void main(String[] args){
		KMeans kMeansRunner = new KMeans();
		kMeansRunner.run();
	}
		
	//Calls the methods used in the program
	public void run(){
		//ask for inputs if not given to constructor
		if (iter == -1) {	
			getInputs();	
		}
		
		readInputDataFromFile();	
		createClusters();
		System.out.println(resultsToString());		
	}
	
	//Constructor without inputs
	public KMeans(){
	}
	
	//Constructor with inputs
	public KMeans(int iter, int k, String filePath){
		this.iter = iter;
		this.k = k;
		this.filePath = filePath;
	}
		
	public void getInputs(){
		//Constants used if don't want to use user prompts
		int iter = DEFAULT_ITER; //# of iterations 
		int k = DEFAULT_K; //# of clusters
		
		
		//Read in user input
		try{
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);	
			
			System.out.println("Enter the desired number of clusters: ");
			String nextStr = br.readLine();
			k = Integer.valueOf(nextStr).intValue();	
			System.out.println("Enter the number of iterations to run: ");
			nextStr = br.readLine();
			iter = Integer.valueOf(nextStr).intValue();
			System.out.println("Enter the file path: ");
			nextStr=br.readLine();
			filePath = nextStr;
			
			br.close();		
		}
		catch (IOException e){
			System.out.println('\n');
			System.out.println(e.getMessage());			
		}		
		
		
		
	}
	
	//Reads the data points from the file into a linked list
	public void readInputDataFromFile(){
		try{
			//Get the data points from the text file
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);
			
			String fileText = ""; //holds full text of the file
			String currLine = ""; //holds a line of the file
			
			//for each line in the file, add to the full text
			while ((currLine = br.readLine()) != null)
				fileText += currLine + "\n";
			
			br.close();		
		
			Scanner dScanner = new Scanner(fileText);
			dataLL = new LinkedList<DataPoint>(); //holds all the data points
		
			//initialize dataLL with all data points
			while (dScanner.hasNextDouble()){
				DataPoint d = new DataPoint();
				d.setX(dScanner.nextDouble());
				d.setY(dScanner.nextDouble());
				dataLL.add(d);		
			}
			dScanner.close();
		}
		catch (IOException e){
			System.out.println('\n');
			System.out.println(e.getMessage());			
		}	
	}
	
	//Runs k-means algorithm on data points to create clusters of data
	@SuppressWarnings("unchecked")
	public void createClusters(){
	
		centroidLL = new LinkedList<Centroid>(); //holds each centroid
		
		//initialize k centroids to the locations of the first k data points
		int d = 0;
		for (int i = 0; i < k; i++){
			Centroid c = new Centroid(dataLL.get(d).getX(), dataLL.get(d).getY());
			centroidLL.add(c);
			d++; 				
		}		
		
		//Runs each iteration of the k-means algorithm
		for (int i = 0; i < iter; i++){
			
			//Remove all data points from the cluster to prepare for the next iteration
			//Trivial in first iteration
			for (int j = 0; j < centroidLL.size(); j++){
				centroidLL.get(j).resetCluster();
			}
			
			//Add each data point to the closest centroid's cluster
			Centroid closestCent = new Centroid();
			for (int j = 0; j < dataLL.size(); j++){ 
				closestCent = dataLL.get(j).findClosestCentroid((LinkedList<Centroid>) centroidLL);			
				closestCent.addToCluster(dataLL.get(j));
			}
			
			//Update centroids' locations to the "center" of the cluster
			for (int j = 0; j < centroidLL.size(); j++){
				centroidLL.get(j).updateCentroid();
			}
			
		}
	}
	
	//Return string describing the centroid and data points of each cluster
	public String resultsToString(){
		String clustersString = "";
		for (int i = 0; i < centroidLL.size(); i ++){
			clustersString += "Cluster " + i + '\n';
			clustersString += " Centroid: (" + centroidLL.get(i).getX() + ", " + centroidLL.get(i).getY() + ")" + '\n';
			
			String dataStr = " Data: ";
			for (int j = 0; j < centroidLL.get(i).getClusterDataLL().size(); j++){
				dataStr += "(" + centroidLL.get(i).getClusterDataLL().get(j).getX() + ", " + centroidLL.get(i).getClusterDataLL().get(j).getY() + ")";
				if (j < centroidLL.get(i).getClusterDataLL().size() - 1)	//if not the last element
					dataStr += ", "; 
			}	
			clustersString += dataStr;
			
			if (i < centroidLL.size() - 1)
				clustersString += '\n';			
		}
		
		return clustersString;
	}
}