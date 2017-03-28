import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 
 */

/**
 * @author Somaieh
 *
 */
public class GBFS_Search{

	/**
	 * 
	 */
	private boolean goalFound;
	private int VerticesVisited;
	private int MaxFrontierSize;
	private int TotalIterations;
	private int NumberofTestedNodetofindGoal;
	private int PathLength;
	
	public int getVericesVisited(){
		return VerticesVisited;
	}
	public int getMaxFrontierSize(){
		return MaxFrontierSize;
	}
	public int getTotalIterations() {
		return TotalIterations;
	}
	public int getNumberofTestedNodetofindGoal(){
		return NumberofTestedNodetofindGoal;
	}
	public int getPathLength() {
		return PathLength;
	}

	public GBFS_Search() {
		// TODO Auto-generated constructor stub
		goalFound = false;
	}

	public void GBFS(Graph G, Vertex Start, Vertex Goal){
		Start.setIsVisited(true);
		VerticesVisited++;
		
		Node StartNode = new Node(Start);
		Node GoalNode = new Node(Goal);	
		PriorityQueue<Node> GBFSqueue = new PriorityQueue<Node>();
		StartNode.setDepth(0);
		StartNode.setHeur(StartNode.HeurCalculator(GoalNode));
		GBFSqueue.add(StartNode);
		MaxFrontierSize = GBFSqueue.size();
		
		Node CurrentNode = new Node();
		SearchToFindGaol : while(!GBFSqueue.isEmpty()){
			TotalIterations++;
			CurrentNode = (Node) GBFSqueue.poll();	
            System.out.println("iter = " + TotalIterations + ", " + "frontier = "+MaxFrontierSize + ", " + "popped = "+ CurrentNode.getID() + " (" + CurrentNode.getXcoordinate()+ "," + CurrentNode.getYcoordinate() + "), " + "depth = " + CurrentNode.getDepth() + ", " + "dist2goal = " + CurrentNode.HeurCalculator(GoalNode));			
            NumberofTestedNodetofindGoal++;
			if ( CurrentNode.TestGoal(GoalNode)){
				goalFound = true;
				System.out.println("Goal Node is found");
				
				GoalNode = CurrentNode;
				List<Node> Path =  GoalNode.traceBack();
				System.out.println("Track-Back path from goal to start is:");
				for(Node NP:Path){
					System.out.println("Vertex "+NP.getID()+ "(" + NP.getXcoordinate() + "," + NP.getYcoordinate() + ")");
				}
				
				break SearchToFindGaol;
			}
            
			//if(!CurrentNode.getDuplicateNode()){ //If the popped node has not been visited before, node will be expand and the successors will be pushed 
				for(Vertex NeighborVertex : CurrentNode.Successors(G)){  
					Node NeighborNode = new Node(NeighborVertex);
					//GBFSqueue.add(NeighborNode);
					/*if(NeighborVertex.getIsVisited()){
						NeighborVertex.setDuplicateVertex(true);
						NeighborNode.setDuplicateNode(true);
					}*/
					if(!NeighborVertex.getIsVisited()){
						NeighborVertex.setIsVisited(true);
						VerticesVisited++;
						NeighborNode.setParent(CurrentNode);
						int depthParent = CurrentNode.getDepth();
						NeighborNode.setDepth(depthParent+1);
						NeighborNode.setHeur(NeighborNode.HeurCalculator(GoalNode));
						GBFSqueue.add(NeighborNode);
						System.out.println("pushed "+NeighborNode.getID() + " (" + NeighborNode.getXcoordinate()+ "," + NeighborNode.getYcoordinate() + ")");
					}
				//}
			}
			MaxFrontierSize = Math.max(MaxFrontierSize, GBFSqueue.size()); //After pushing all successor nodes to the Frontier, I calculate its's size
		}
		if(goalFound == false)
			System.out.println("There is no path from (" + StartNode.getXcoordinate() + "," + StartNode.getYcoordinate()+")" + " to (" + GoalNode.getXcoordinate() + "," + GoalNode.getYcoordinate()+")" );
		else{
			PathLength = GoalNode.getDepth(); 
		}
	}
}
