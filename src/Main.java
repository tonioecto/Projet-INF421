import java.awt.Color;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Polyomino poly = new Polyomino("[(0,0), (0,2),(1,1),(1,0),(1,2),(2,1)]");
	LinkedList<Polyomino> liste = Polyomino.Create("test.txt");
//		
//		Polyomino poly = liste.pop();
//		
//		poly=liste.pop();
//		poly=liste.pop();
//		poly=liste.pop();
//		
//		poly.rotate(!true);
//		poly = poly.reflection(false);
//		poly.translate(new int[] {10,10});
//		poly.reframe();
//		
		
	//	poly.addCase(new Case(3,3));
	//	poly=poly.reflection(true);
//		
//		
		Image2D frame= new Image2D(1000,1000);
		Image2dViewer test2 = new Image2dViewer(frame);
		//Polyomino.displayPolyomino(poly, 100, frame, Color.black, new int[] {0,0});
		Polyomino.displayPolyominosSym(liste,  frame, 30, Color.black);
//		Polymino poly2 = poly.expand(30);
//
//
//		test.addPixel(poly2.xCoords, poly2.yCoords, Color.green);
		
		//Polymino.affiche("test.txt");
		
	}
	
	

}
