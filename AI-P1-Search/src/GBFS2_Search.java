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
public class GBFS2_Search{

	/**
	 * 
	 */
	private boolean goalFound;
	private int VerticesVisited;
	private int MaxFrontierSize;
	private int TotalIterations;
	private int NumberofTestedNodetofindGoal;
	private int PathLength;
	private List<Node> GBFSpath = new ArrayList<Node>();
	
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
	public List<Node> getGBFSpath(){
		return GBFSpath;
	}
	public GBFS2_Search() {
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
		GBFSpath.add(StartNode);
		
		StartNode.setHeur(StartNode.HeurCalculator(GoalNode));
		GBFSqueue.add(StartNode);
		MaxFrontierSize = GBFSqueue.size();
		
		NumberofTestedNodetofindGoal++;
		if(StartNode.TestGoal(GoalNode)){
			goalFound = true;
			System.out.println("Goal Node is found");
			return;	
		}
		
		Node CurrentNode = new Node();
		SearchToFindGaol : while(!GBFSqueue.isEmpty()){
			CurrentNode = (Node) GBFSqueue.poll();	
			
			if(!CurrentNode.getDuplicateNode()){ //If the popped node has not been visited before, node will be expand and the successors will be pushed 
				TotalIterations++;
				for(Vertex NeighborVertex : CurrentNode.Successors(G)){ //If the popped node has not been visited before, node will be expand and the successors will be pushed 
					Node NeighborNode = new Node(NeighborVertex);
					GBFSqueue.add(NeighborNode);
					if(NeighborVertex.getIsVisited()){
						NeighborVertex.setDuplicateVertex(true);
						NeighborNode.setDuplicateNode(true);
					}
					//MaxFrontierSize++;
					if(!NeighborVertex.getIsVisited()){
						NeighborVertex.setIsVisited(true);
						VerticesVisited++;
						//Node NeighborNode = new Node(NeighborVertex);
						NeighborNode.setParent(CurrentNode);
						int depthParent = CurrentNode.getDepth();
						NeighborNode.setDepth(depthParent+1);
						NeighborNode.setHeur(NeighborNode.HeurCalculator(GoalNode));
						GBFSpath.add(NeighborNode);
						//GBFSqueue.add(NeighborNode);
						
						NumberofTestedNodetofindGoal++;
						if ( NeighborNode.TestGoal(GoalNode)){
							goalFound = true;
							System.out.println("Goal Node is found");
							
							GoalNode = NeighborNode;
							List<Node> Path =  GoalNode.traceBack();
							System.out.println("Track-Back path from goal to start is:");
							for(Node NP:Path){
								System.out.println(" Vertex "+NP.getID()+ "(" + NP.getXcoordinate() + "," + NP.getYcoordinate() + ")");
							}
							
							break SearchToFindGaol;
						}
					}
				}
			}
			MaxFrontierSize = Math.max(MaxFrontierSize, GBFSqueue.size()); //After pushing all successor nodes to the Frontier, I calculate its's size
		}
		if(goalFound == false)
			System.out.println("There is no path from (" + StartNode.getXcoordinate() + "," + StartNode.getYcoordinate()+")" + " to (" + GoalNode.getXcoordinate() + "," + GoalNode.getYcoordinate()+")" );
		else{
			PathLength = GBFSpath.get(GBFSpath.size()-1).getDepth();
			/*int i=0;
			for (Node N : GBFSpath) {
				i++;
				System.out.println(i+ " Vertex " + N.getID() + "	(" + N.getXcoordinate() + "," + N.getYcoordinate() + ")");
			}*/
		}
	}
}
