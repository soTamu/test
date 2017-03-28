import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 
 */

/**
 * @author Somaieh
 *
 */
public class DFS2_Search {

	/**
	 * 
	 */
	private boolean goalFound;
	private int VerticesVisited;
	private int MaxFrontierSize;
	private int TotalIterations;
	private int NumberofTestedNodetofindGoal;
	private int PathLength;
	private List<Node> DFSpath = new ArrayList<Node>();
	
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
	public List<Node> getDFSpath(){
		return DFSpath;
	}
	public DFS2_Search() {
		// TODO Auto-generated constructor stub
		goalFound = false;
	}

	public void DFS(Graph G, Vertex Start, Vertex Goal){
		Start.setIsVisited(true);
		VerticesVisited++;

		Node StartNode = new Node(Start);
		Node GoalNode = new Node(Goal);
		Stack<Node> DFSstack = new Stack<Node>();
		StartNode.setDepth(0);
		DFSpath.add(StartNode);
		
		DFSstack.push(StartNode);
		MaxFrontierSize = DFSstack.size();
		
		NumberofTestedNodetofindGoal++;
		if(StartNode.TestGoal(GoalNode)){
			goalFound = true;
			System.out.println("Goal Node is found");
			return;	
		}
		
		Node CurrentNode = new Node();
		SearchToFindGaol : while(!DFSstack.isEmpty()){
			CurrentNode = (Node) DFSstack.pop();
			
			if(!CurrentNode.getDuplicateNode()){ //If the popped node has not been visited before, node will be expand and the successors will be pushed 
				TotalIterations++;
				for(Vertex NeighborVertex : CurrentNode.Successors(G)){	
					Node NeighborNode = new Node(NeighborVertex);
					DFSstack.push(NeighborNode);
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
						//DFSstack.push(NeighborNode);
					}
				}
				MaxFrontierSize = Math.max(MaxFrontierSize, DFSstack.size()); //After pushing all successor nodes to the Frontier, I calculate its's size
	
				if( CurrentNode.getID() == StartNode.getID())
					CurrentNode.setDepth(0);
				else{
						Node parent = CurrentNode.getParent();
						int depthParent = parent.getDepth();
						CurrentNode.setDepth(depthParent+1);
				}
				DFSpath.add(CurrentNode);
				
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
			}
		}
		if(goalFound == false)
			System.out.println("There is no path from (" + StartNode.getXcoordinate() + "," + StartNode.getYcoordinate()+")" + " to (" + GoalNode.getXcoordinate() + "," + GoalNode.getYcoordinate()+")" );
		else{
			PathLength = DFSpath.get(DFSpath.size()-1).getDepth();
			/*int i=0;
			for (Node N : DFSpath) {
				i++;
				System.out.println(i+" Vertex " + N.getID() + "	(" + N.getXcoordinate() + "," + N.getYcoordinate() + ")");
			}*/
		}
	}
}
