import java.util.LinkedList;

import junit.framework.TestCase;


public class KMeansTestCase extends TestCase {
	 
	public KMeansTestCase(final String name){
		super(name);
	}
	
	//Tests output of kMeans method
	public void testKMeans(){
		//run program on given inputs
		int iter = 1; //# of iterations 
		int k = 3; //# of clusters
		String filePath = "C:\\Users\\Alex\\Documents\\kMeans_File\\kMeansInput.txt";
		KMeans testKMeans = new KMeans(iter, k, filePath);
		testKMeans.readInputDataFromFile();		
		testKMeans.createClusters();
		String testClustersString = testKMeans.resultsToString();
		
		//Create correct results
		LinkedList<Centroid> corrCentroidLL = new LinkedList<Centroid>();
		corrCentroidLL.add(new Centroid(1,4));
		corrCentroidLL.get(0).getClusterDataLL().add(new DataPoint(1,4));
		corrCentroidLL.add(new Centroid(2.75,2));
		corrCentroidLL.get(1).getClusterDataLL().add(new DataPoint(2,3));
		corrCentroidLL.get(1).getClusterDataLL().add(new DataPoint(4,2));
		corrCentroidLL.get(1).getClusterDataLL().add(new DataPoint(4,1));
		corrCentroidLL.get(1).getClusterDataLL().add(new DataPoint(1,2));
		corrCentroidLL.add(new Centroid(3,5));
		corrCentroidLL.get(2).getClusterDataLL().add(new DataPoint(3,5));
		
		String corrClustersString = "";
		for (int i = 0; i < corrCentroidLL.size(); i ++){
			corrClustersString += "Cluster " + i + '\n';
			corrClustersString += " Centroid: (" + corrCentroidLL.get(i).getX() + ", " + corrCentroidLL.get(i).getY() + ")" + '\n';
			
			String dataStr = " Data: ";
			for (int j = 0; j < corrCentroidLL.get(i).getClusterDataLL().size(); j++){
				dataStr += "(" + corrCentroidLL.get(i).getClusterDataLL().get(j).getX() + ", " + corrCentroidLL.get(i).getClusterDataLL().get(j).getY() + ")";
				if (j < corrCentroidLL.get(i).getClusterDataLL().size() - 1)	//if not the last element
					dataStr += ", "; 
			}	
			corrClustersString += dataStr;
			
			if (i < corrCentroidLL.size() - 1)
				corrClustersString += '\n';			
		} 

		//Verify test results match correct results
		assertEquals(testClustersString, corrClustersString);
	}
	
	
}