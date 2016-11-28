import java.awt.Color;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Polyomino poly = new Polyomino("[(0,0), (0,2),(1,1),(1,0),(1,2),(2,1)]");
	//LinkedList<Polyomino> liste = Polyomino.Create("test.txt");
//		
//		Polyomino poly = liste.pop();
//		
//		poly=liste.pop();
//		poly=liste.pop();
//		poly=liste.pop();
//		
		//poly.rotate(!true);
//		poly = poly.reflection(false);
//		poly.translate(new int[] {10,10});
//		poly.reframe();
//		
		
	//	poly.addCase(new Case(3,3));
	//	poly=poly.reflection(true);
//		
//		
		//Image2D frame= new Image2D(1000,1000);
		//Image2dViewer test2 = new Image2dViewer(frame);
		//Polyomino.displayPolyomino(poly, 100, frame, Color.black, new int[] {0,0});
		//Polyomino.displayPolyominosSym(liste,  frame, 30, Color.black);
//		Polymino poly2 = poly.expand(30);
//
//
//		test.addPixel(poly2.xCoords, poly2.yCoords, Color.green);
		
		//Polymino.affiche("test.txt");
		
		//int[] test = Polyomino.initPrimes("Primes.txt", 1000);
		//System.out.println(test[0]);
		
		
		//Image2D frame= new Image2D(1000,1000);
		//Image2dViewer test2 = new Image2dViewer(frame);                             //Ne peut pas �tre s�par� ?
		
		//Polyomino a = new Polyomino("[(0,0), (0,2),(1,1),(1,0),(1,2),(2,1)]");
		//Polyomino b = new Polyomino("[(0,0), (0,2),(1,1),(1,0),(1,2),(2,1)]");
		//Polyomino c = new Polyomino("[(0,0), (0,3),(1,1),(1,0),(1,2),(2,1)]");
		//Polyomino d = new Polyomino("[(0,0), (0,2),(1,1),(1,0),(1,2)]");
		//d.addCase(new Case(2,1));
		//System.out.println(Polyomino.equals(a, b)); //true
		//System.out.println(Polyomino.equals(a, c)); //false
		//System.out.println(Polyomino.equals(a, d)); //true
		//a.rotate(true);
		//a.rotate(false);
		

		//Polyomino.displayPolyomino(a, 100, frame, Color.black, new int[] {0,0});
		
		//System.out.println(Polyomino.equals(a, d)); //true
		
		//Polyomino.enumFixed(4);
		
	//	Polyomino a = new Polyomino("[(0,0), (0,1), (0,2)]");
	//	a.addCase(new Case(-1,1));
		LinkedList<Polyomino> a = Polyomino.genFixed(5);
		Image2D frame= new Image2D(1000,1000);
		Image2dViewer test2 = new Image2dViewer(frame); 
		Polyomino.displayPolyominos(a, frame,20, Color.black);


		

		
	}
	
	

}
