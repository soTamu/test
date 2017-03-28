/**
 * 
 */
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.MediaSize.Other;

/**
 * @author Somaieh
 *
 */

public class Vertex { //implements Comparable<Vertex>{
	/**
	 * 
	 */

	private int ID;
	private int X;
	private int Y;
	private boolean IsVisited;
	private boolean DuplicateVertex;
	private List<Vertex> Neighbors = new ArrayList<Vertex>();
	
	public Vertex() {
		// TODO Auto-generated constructor stub
	}

	public Vertex(int id, int x, int y){
		this.ID= id;
		this.X=x;
		this.Y=y;
		this.IsVisited = false;
		this.DuplicateVertex = false;

	}

	public int getID(){
		return ID;
	}
	
	public int getXcoordinate(){
		return X;
	}
	
	public int getYcoordinate(){
		return Y;
	}
	
	public boolean getIsVisited(){
		return IsVisited;
	}
	
	public boolean getDuplicateVertex(){
		return DuplicateVertex;
	}
	
	public void setIsVisited(boolean visited){
		this.IsVisited=visited;
	}
	
	public void setDuplicateVertex(boolean visited){
		this.DuplicateVertex=visited;
	}
	
	public List<Vertex> getNeighbor(){
		return Neighbors;
	}
	
	public void AddNeighbor(Vertex vertex) {
		this.Neighbors.add(vertex);
		vertex.Neighbors.add(this); 
	}

}
