import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * 
 */

/**
 * @author Somaieh
 *
 */
public class Graph {

	/**
	 * 
	 */
	private Vertex [] Vertices;
	
	public Vertex[] getVertices() {
		return Vertices;
	}
	
	public Graph() {
		// TODO Auto-generated constructor stub
	}
	
	public Graph(String FileName){
		

		try {
			 FileReader ReadeFile = new FileReader(FileName);
			 BufferedReader ReadLine = new BufferedReader(ReadeFile);
			 @SuppressWarnings("resource")
			 Scanner ScanLine = new Scanner(ReadLine);
			 String Line = ScanLine.nextLine();
			 String [] LineContent = Line.split(" ");
			 int NumVertex = Integer.parseInt(LineContent[1]);
			 Vertices = new Vertex[NumVertex];
			 for(int i=0; i<NumVertex; i++){
				 Line=ScanLine.nextLine();
				 LineContent = Line.split(" ");
				 Vertices[i]=new Vertex(Integer.parseInt(LineContent[0]),Integer.parseInt(LineContent[1]),Integer.parseInt(LineContent[2]));
			 }
			 Line = ScanLine.nextLine();
			 LineContent = Line.split(" ");
			 int NumEdge= Integer.parseInt(LineContent[1]);
			 for (int i = 0; i < NumEdge; i++) {
				 Line=ScanLine.nextLine();
				 LineContent = Line.split(" ");
				 Vertices[Integer.parseInt(LineContent[1])].AddNeighbor(Vertices[Integer.parseInt(LineContent[2])]);
			 }
			 ReadeFile.close(); 

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("File not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("File can not be closed");

		}
	}
}
