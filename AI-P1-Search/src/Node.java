import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

/**
 * @author Somaieh
 *
 */

public class Node implements Comparable<Node>  {
	
	/**
	 * 
	 */
	
	private int X;
	private int Y;
	private int ID;
	private boolean DuplicateNode;
	private int Depth; 
	private Node Parent;
	private double Heur; // estimated distance to goal, for GBFS
	
	public Node() {
		// TODO Auto-generated constructor stub
	}
	
	public Node(Vertex vertex) {
		this.X = vertex.getXcoordinate();
		this.Y = vertex.getYcoordinate();
		this.ID = vertex.getID();
		this.DuplicateNode = false;
	}
	
	public int getXcoordinate(){ 
		return X;
	}
	
	public int getYcoordinate(){ 
		return Y;
	}
	
	public boolean getDuplicateNode(){
		return DuplicateNode;
	}
	public void setDuplicateNode(boolean visited){
		this.DuplicateNode=visited;
	}
	
	public int getID(){
		return ID;
	}
	
	public int getDepth(){
		return Depth;
	}
	
	public void setDepth(int depth){
		this.Depth=depth;
	}
	
	public double getHeur() { 
		return Heur; 
	}
	
	public Node getParent(){
		return Parent;
	}
	
	public void setParent(Node parent){
		this.Parent = parent;
	}
	
	public void setHeur(double heur) {
		this.Heur = heur;
	}

	public List<Vertex> Successors(Graph G) {
		int index= this.ID;
		return G.getVertices()[index].getNeighbor();
	}

	public List<Node> traceBack() {
		List<Node> pathToStart = new ArrayList<Node>();
		Node temp = this;
		if (temp.Parent == null) {
			return null;
		}
		pathToStart.add(temp);
		while (temp.Parent != null) {
			temp = temp.Parent;
			pathToStart.add(temp);
		}

		return pathToStart;
	}
	
	public double HeurCalculator(Node goal){
		return Math.sqrt(Math.pow((this.X - goal.getXcoordinate()) , 2.0) + Math.pow((this.Y - goal.getYcoordinate()) , 2.0));
	}

	@Override
	public int compareTo(Node OtherNode) {
		// TODO Auto-generated method stub
		if (this.Heur == OtherNode.Heur)
			return 0;
		else if (this.Heur > OtherNode.Heur)
			return 1;
		else 
			return -1;
	}
	
	public boolean TestGoal(Node goal){
		
		if (this.X == goal.X && this.Y == goal.Y) 
			return true;
		return false;
	}

}
