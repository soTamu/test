import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

/**
 * @author Somaieh
 *
 */
public class MainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Graph Map = new Graph(args[1]);
		Vertex Start = new Vertex();
		Vertex Goal = new Vertex();
		for (Vertex v: Map.getVertices()){
			if(Integer.parseInt(args[2]) == v.getXcoordinate() && Integer.parseInt(args[3]) == v.getYcoordinate())
			{	
				System.out.println("Start Node is: "+ v.getID()+ " ("+v.getXcoordinate() + "," + v.getYcoordinate() + ")");
				Start = Map.getVertices()[v.getID()];
				break;
			}
		}
		for (Vertex v: Map.getVertices()){
			if(Integer.parseInt(args[4]) == v.getXcoordinate() && Integer.parseInt(args[5]) == v.getYcoordinate())
			{	
				System.out.println("Goal Node is: "+ v.getID()+ " ("+v.getXcoordinate() + "," + v.getYcoordinate() + ")");
				Goal = Map.getVertices()[v.getID()];
				break;
			}
		}
		List<Node> Path= new ArrayList<Node>();
		switch (args[0]) {
		case "BFS_nav" : 
			{
				System.out.println("Search Algorithm: BFS");
				BFS_Search SearchAlgo = new BFS_Search();
				SearchAlgo.BFS(Map, Start, Goal);
				System.out.println("Total Iterations: " + SearchAlgo.getTotalIterations());
				System.out.println("Maximum Frontier Size = " + SearchAlgo.getMaxFrontierSize());
				System.out.println("Number of Goal Tests = " + SearchAlgo.getNumberofTestedNodetofindGoal());
				System.out.println("Number of Vertices Visited = "  + SearchAlgo.getVericesVisited()+ "/275");
				System.out.println("Path Length = " + SearchAlgo.getPathLength());

			}
		break;

		case "DFS_nav" : 
			{
				System.out.println("Search Algorithm: DFS");
				DFS_Search SearchAlgo = new DFS_Search();
				SearchAlgo.DFS(Map,Start,Goal);
				System.out.println("Total Iterations: " + SearchAlgo.getTotalIterations());
				System.out.println("Maximum Frontier Size = "+SearchAlgo.getMaxFrontierSize());
				System.out.println("Number of Goal Tests = " + SearchAlgo.getNumberofTestedNodetofindGoal());
				System.out.println("Number of Vertices Visited = " + SearchAlgo.getVericesVisited()+ "/275");
				System.out.println("Path Length = "	+ SearchAlgo.getPathLength());

			}
		break;

		case "GBFS_nav" : 
			{
				System.out.println("Search Algorithm: GBFS");
				GBFS_Search SearchAlgo = new GBFS_Search();
				SearchAlgo.GBFS(Map,Start,Goal);
				System.out.println("Total Iterations: " + SearchAlgo.getTotalIterations());
				System.out.println("Maximum Frontier Size = "+SearchAlgo.getMaxFrontierSize());
				System.out.println("Number of Goal Tests = " + SearchAlgo.getNumberofTestedNodetofindGoal());
				System.out.println("Number of Vertices Visited = " + SearchAlgo.getVericesVisited()+ "/275");
				System.out.println("Path Length = "	+ SearchAlgo.getPathLength());

			}
		break;	

		default:
			break;
		}
	}
}
