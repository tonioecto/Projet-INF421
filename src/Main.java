import java.awt.Color;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int[] primes = Polyomino.initPrimes("Primes.txt",3000);
//		Polyomino poly = new Polyomino("[(0,0), (0,2),(1,1),(1,0),(1,2),(2,1)]",primes);
//		System.out.println(poly.width);
//		System.out.println(poly.height);
//		
//		int[][] M = Cover.convert(poly);
//		System.out.println(M[0][1]);
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
	//	a.addCase(new Case(-1,1))
//		int[] primes=Polyomino.initPrimes("Primes.txt",3000);
//		LinkedList<Polyomino> a = Polyomino.fixed(8,primes);
//		System.out.println(a.size());
//		Image2D frame= new Image2D(2000,200);
//		Image2dViewer test2 = new Image2dViewer(frame); 
//		Polyomino.displayPolyominos(a, frame,20, Color.black,primes);
		LinkedList<Integer> a = new LinkedList<Integer>();
		a.add(1);a.add(2);a.add(3);
		LinkedList<Integer> b = new LinkedList<Integer>();
		b.add(1);b.add(2);
		LinkedList<Integer> c = new LinkedList<Integer>();
		c.add(2);c.add(3);
		LinkedList<Integer> d = new LinkedList<Integer>();
		d.add(1);d.add(3);
		LinkedList<Integer> e = new LinkedList<Integer>();
		e.add(1);
		LinkedList<Integer> f = new LinkedList<Integer>();
		f.add(2);
		LinkedList<Integer> g = new LinkedList<Integer>();
		g.add(3);
		LinkedList<LinkedList<Integer>> h=new LinkedList<LinkedList<Integer>>();
		h.add(b);h.add(c);h.add(d);h.add(e);h.add(f);h.add(g);
		int[][] m=new int[][] {{1,1,0},{0,1,1},{1,0,1},{1,0,0},{0,1,0},{0,0,1}};
		LinkedList<LinkedList<LinkedList<Integer>>> k=Polyomino.exactCover(a, m, h);
		LinkedList<LinkedList<LinkedList<Integer>>> k1=DancingLinks.exactCover(DancingLinks.init(m));
//		for (LinkedList<LinkedList<Integer>> l:k1){
//			System.out.println(l);
//		}
		
		//System.out.print((char) 65);
		

		//Data H= DancingLinks.init(M);
//		H=H.L.D.D.L.D;
//		System.out.println(H.N);
//		H=H.D;
//		for(int i=0; i<10; i++){
//			System.out.println(H);
//			H=H.L;
//		}
//		

		
//		H=H.R.R;
//		DancingLinks.coverColumn(H);
//		H=H.D;
//		DancingLinks.coverColumn(H);
//		H=H.R;
//		DancingLinks.coverColumn(H);

		test5();

		
	}
	
	public static void test81(){
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);

		Polyomino poly = new Polyomino("[(0,0), (0,2),(1,1),(1,0),(1,2),(2,1)]",primes);
		
		Polyomino p = new Polyomino("[(0,0)]",primes);
		LinkedList<Polyomino> L = new LinkedList<Polyomino>();
		L.add(p);
		
		int[][] origin = new int[poly.cases.size()][2];
		
		int[][] M =Cover.toExactCoverNoRepet(L, poly,origin);
		
		System.out.println(Arrays.deepToString(M));
		
		System.out.println("ok");
		LinkedList<LinkedList<LinkedList<Integer>>> k=DancingLinks.exactCover(DancingLinks.init(M));
		for (LinkedList<LinkedList<Integer>> l:k){
			System.out.println(l);
		}


	}
	
	public static void test999(){
		//int[][] M = new int[][] {{0,0,1,0,1,1,0},{1,0,0,1,0,0,1},{0,1,1,0,0,1,0},{1,0,0,1,0,0,0},{0,1,0,0,0,0,1},{0,0,0,1,1,0,1},{1,1,1,1,1,1,1}};
		int[][] M = new int[][] {{0,0,1},{0,1,0},{1,0,1}};
		System.out.println(Arrays.deepToString(M));
		
		Data H = DancingLinks.init(M);
		
		//System.out.println(H.R.D);
		//System.out.println(H.R.D.L.L);


		
		LinkedList<LinkedList<LinkedList<Integer>>> k=DancingLinks.exactCover(DancingLinks.init(M));
		for (LinkedList<LinkedList<Integer>> l:k){
			System.out.println(l);
		}
	}
	public static void test1(){
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);

		Polyomino poly = new Polyomino("[(0,0), (0,2),(1,1),(1,0),(1,2),(2,1),(2,0),(2,2),(0,1)]",primes);
		
		Polyomino p = new Polyomino("[(0,0),(0,1),(0,2)]",primes);
		LinkedList<Polyomino> L = new LinkedList<Polyomino>();
		L.add(p);
		
		int[][] origin = new int[poly.cases.size()][2];
		
		int[][] M =Cover.toExactCoverNoRepet(L, poly,origin);
		
		System.out.println(Arrays.deepToString(M));
		
		System.out.println("ok");
		LinkedList<LinkedList<LinkedList<Integer>>> k=DancingLinks.exactCover(DancingLinks.init(M));
		for (LinkedList<LinkedList<Integer>> l:k){
			System.out.println(l);
		}
	}
	public static void test2(){
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);

		Polyomino poly = new Polyomino("[(0,0), (0,2),(1,1),(1,0),(1,2),(2,1),(2,0),(2,2),(0,1)]",primes);
		Polyomino p2 = new Polyomino("[(0,0),(0,1),(0,2)]",primes);
		Polyomino p3 = new Polyomino("[(0,0),(1,0),(2,0)]",primes);
		Polyomino p = new Polyomino("[(0,0),(0,1)]",primes);
		Polyomino p1 = new Polyomino("[(0,0),(1,0)]",primes);
		LinkedList<Polyomino> L = new LinkedList<Polyomino>();
		L.add(p);
		L.add(p1);
		L.add(p2);
		L.add(p3);
		int[][] origin = new int[poly.cases.size()][2];
		
		int[][] M =Cover.toExactCoverNoRepet(L, poly,origin);
		System.out.println(Arrays.deepToString(M));
		LinkedList<LinkedList<LinkedList<Integer>>> k=DancingLinks.exactCover(DancingLinks.init(M));
		System.out.println(k.size());
		for (LinkedList<LinkedList<Integer>> l:k){
			System.out.println(l);
		}
	}
	public static void test3(){
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);

		Polyomino poly = new Polyomino("[(0,0),(1,1),(1,0),(0,1)]",primes);
		Polyomino p2 = new Polyomino("[(0,0),(0,1),(0,2)]",primes);
		Polyomino p3 = new Polyomino("[(0,0),(1,0),(2,0)]",primes);
		Polyomino p = new Polyomino("[(0,0),(0,1)]",primes);
		Polyomino p1 = new Polyomino("[(0,0),(1,0)]",primes);
		LinkedList<Polyomino> L = new LinkedList<Polyomino>();
		L.add(p);
		L.add(p1);
		L.add(p2);
		L.add(p3);
		int[][] origin = new int[poly.cases.size()][2];
		
		int[][] M =Cover.toExactCoverNoRepet(L, poly,origin);
		System.out.println(Arrays.deepToString(M));
		LinkedList<LinkedList<LinkedList<Integer>>> k=DancingLinks.exactCover(DancingLinks.init(M));
		System.out.println(k.size());
		for (LinkedList<LinkedList<Integer>> l:k){
			System.out.println(l);
		}
	}
	public static void test4(){
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);

		Polyomino poly = new Polyomino("[(0,0), (0,2),(1,1),(1,0),(1,2),(0,1)]",primes);
		Polyomino p2 = new Polyomino("[(0,0),(0,1),(0,2)]",primes);
		Polyomino p3 = new Polyomino("[(0,0),(1,0),(2,0)]",primes);
		Polyomino p = new Polyomino("[(0,0),(0,1)]",primes);
		Polyomino p1 = new Polyomino("[(0,0),(1,0)]",primes);
		LinkedList<Polyomino> L = new LinkedList<Polyomino>();
		L.add(p);
		L.add(p1);
		L.add(p2);
		L.add(p3);
		System.out.println(poly.height+"  "+poly.width);
		int[][] origin = new int[poly.cases.size()][2];
		
		int[][] M =Cover.toExactCoverNoRepet(L, poly,origin);
		System.out.println(Arrays.deepToString(M));
		
		LinkedList<LinkedList<LinkedList<Integer>>> k=DancingLinks.exactCover(DancingLinks.init(M));
		System.out.println(k.size());
		for (LinkedList<LinkedList<Integer>> l:k){
			System.out.println(l);
		}
	}
	
	public static void test5(){
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);

		Polyomino poly = new Polyomino("[(0,0), (0,1),(0,2),(1,0),(1,2),(1,1)]",primes);
		
		Polyomino p = new Polyomino("[(0,0),(0,1),(1,0),(1,1)]",primes);
		Polyomino p2 = new Polyomino("[(0,0),(1,0)]",primes);
		LinkedList<Polyomino> L = new LinkedList<Polyomino>();
		L.add(p);
		L.add(p2);
		
		int[][] origin = new int[poly.cases.size()][2];
		
		int[][] M =Cover.toExactCoverNoRepet(L, poly,origin);
		System.out.println(Arrays.deepToString(origin));

		
		System.out.println(Arrays.deepToString(M));
		
		System.out.println("ok");
		LinkedList<LinkedList<LinkedList<Integer>>> k=DancingLinks.exactCover(DancingLinks.init(M));
		
		Image2D frame= new Image2D(1000,1000);
		Image2dViewer test2 = new Image2dViewer(frame);  
		
		for (LinkedList<LinkedList<Integer>> l:k){
			for(LinkedList<Integer> m:l){
				Polyomino.displayPolyomino(Cover.toPolyomino(m, primes, origin), 100, frame, Color.black, new int[] {0,0});
			}
		}
		


		
	}
	
	public static void test6(){ // Affichage
		
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);

		Polyomino poly = new Polyomino("[(0,0), (0,1)]",primes);
		
		Image2D frame= new Image2D(1000,1000);
		Image2dViewer test2 = new Image2dViewer(frame);
		Polyomino.displayPolyomino(poly, 100, frame, Color.black, new int[] {0,0});
		
	}
	
	public static void test(int[] l2, int[] l){
		System.out.println();
	}
	
	

}
