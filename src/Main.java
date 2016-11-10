import java.awt.Color;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Polymino poly = new Polymino("[(0,0), (0,7)]");
		LinkedList<Polymino> liste = Polymino.Create("test.txt");
		
//		poly = liste.pop();
//		poly.rotate(true);
//		poly=liste.pop();
//		poly=liste.pop();
//		poly=liste.pop();
//		Image2D test= new Image2D(1000,1000);
//		Image2dViewer test2 = new Image2dViewer(test);
//		Polymino poly2 = poly.expand(50);
//
//		test.addPixel(poly2.xCoords, poly2.yCoords, Color.green);
		
		Polymino.affiche("test.txt");
		
	}
	
	

}
