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
//		
	
		

		testSudo();


		
	}
	
	public static void fixed(int n){
		int[] primes=Polyomino.initPrimes("Primes.txt",3000);
		LinkedList<Polyomino> a = Polyomino.fixed(n,primes);
		//System.out.println(a.size());
		Image2D frame= new Image2D(2000,200);
		Image2dViewer test2 = new Image2dViewer(frame); 
		Polyomino.displayPolyominos(a, frame,20, Color.black,primes);
	}
	
	public static void free(int n){
		int[] primes=Polyomino.initPrimes("Primes.txt",3000);
		LinkedList<Polyomino> a = Polyomino.free(n,primes);
		//System.out.println(a.size());
		Image2D frame= new Image2D(2000,200);
		Image2dViewer test2 = new Image2dViewer(frame); 
		Polyomino.displayPolyominos(a, frame,20, Color.black,primes);
	}
	
	public static void testExpand(int k){ // Test expand
		int[] primes=Polyomino.initPrimes("Primes.txt",3000);
		Polyomino poly = new Polyomino("[(0,0), (0,2),(1,1),(0,1)]",primes);
		Polyomino p = Polyomino.expand(poly, k, primes);
		Image2D frame= new Image2D(2000,200);
		Image2dViewer test2 = new Image2dViewer(frame); 
		p.recentre(primes);
		Polyomino.displayPolyomino(poly, 20,frame, Color.black,primes);

	}
	
	public static void test811(){
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);

		Polyomino poly = new Polyomino("[(0,0), (0,2),(1,1),(1,0),(1,2),(2,1)]",primes);
		
		Polyomino p = new Polyomino("[(0,0)]",primes);
		LinkedList<Polyomino> L = new LinkedList<Polyomino>();
		L.add(p);
		
		int[][] origin = new int[poly.cases.size()][2];
		
		int[][] M =Cover.toExactCoverNoRepet(L, poly,origin,primes);
		
		//System.out.println(Arrays.deepToString(M));
		
		//System.out.println("ok");
		LinkedList<LinkedList<LinkedList<Integer>>> k=DancingLinks.exactCover(DancingLinks.init(M));
		for (LinkedList<LinkedList<Integer>> l:k){
			System.out.println(l);
		}


	}
	
	public static void test999(){
		//int[][] M = new int[][] {{0,0,1,0,1,1,0},{1,0,0,1,0,0,1},{0,1,1,0,0,1,0},{1,0,0,1,0,0,0},{0,1,0,0,0,0,1},{0,0,0,1,1,0,1},{1,1,1,1,1,1,1}};
		int[][] M = new int[][] {{0,0,1},{0,1,0},{1,0,1}};
		//System.out.println(Arrays.deepToString(M));
		
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
		
		int[][] M =Cover.toExactCoverNoRepet(L, poly,origin,primes);
		
		//System.out.println(Arrays.deepToString(M));
		
		//System.out.println("ok");
		LinkedList<LinkedList<LinkedList<Integer>>> k=DancingLinks.exactCover(DancingLinks.init(M));
		for (LinkedList<LinkedList<Integer>> l:k){
			System.out.println(l);
		}
	}
	public static void test2(){
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);

		Polyomino poly = new Polyomino("[(0,0), (0,2),(1,1),(1,0),(1,2),(2,1),(2,0),(2,2),(0,1)]",primes);
		Polyomino p2 = new Polyomino("[(0,0),(0,1),(0,2)]",primes);
		Polyomino p3 =new Polyomino("[(0,0),(0,1),(0,2)]",primes);
		Polyomino p = new Polyomino("[(0,0),(0,1)]",primes);
		Polyomino p1 =new Polyomino("[(0,0),(0,1),(0,2)]",primes);
		LinkedList<Polyomino> L = new LinkedList<Polyomino>();
		L.add(p);
		L.add(p1);
		L.add(p2);
		L.add(p3);
		int[][] origin = new int[poly.cases.size()][2];
		
		int[][] M =Cover.toExactCover(L, poly,origin);

		LinkedList<LinkedList<LinkedList<Integer>>> k=DancingLinks.exactCover(DancingLinks.init(M));
		System.out.println("Nombre de solutions "+k.size());
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
		
		int[][] M =Cover.toExactCoverNoRepet(L, poly,origin,primes);
		//System.out.println(Arrays.deepToString(M));
		LinkedList<LinkedList<LinkedList<Integer>>> k=DancingLinks.exactCover(DancingLinks.init(M));
		//System.out.println(k.size());
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
		//System.out.println(poly.height+"  "+poly.width);
		int[][] origin = new int[poly.cases.size()][2];
		
		int[][] M =Cover.toExactCover(L, poly,origin);
		//System.out.println(Arrays.deepToString(M));
		
		LinkedList<LinkedList<LinkedList<Integer>>> k=DancingLinks.exactCover(DancingLinks.init(M));
		//System.out.println(k.size());
		for (LinkedList<LinkedList<Integer>> l:k){
			System.out.println(l);
		}
		
		Image2D frame= new Image2D(1000,1000);
		Image2dViewer test2 = new Image2dViewer(frame);  
		
		for (LinkedList<LinkedList<Integer>> l:k){
			for(LinkedList<Integer> m:l){
				Polyomino.displayPolyomino(Cover.toPolyomino(m, primes, origin), 100, frame, Color.black, new int[] {0,0});
			}
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
		
		int[][] M =Cover.toExactCoverNoRepet(L, poly,origin,primes);
		//System.out.println(Arrays.deepToString(origin));

		
		//System.out.println(Arrays.deepToString(M));
		
		//System.out.println("ok");
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
	
	public static void test7(){  //Q8.1 affichage
		
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);

		Polyomino poly = new Polyomino("[(0,0), (1,0),(0,1),(1,1),(2,1),(3,1),(0,2),(1,2),(2,2),(3,2),(4,2),(5,2),(0,3),(1,3),(2,3),(3,3),(4,3),(5,3),(6,3),(7,3),(0,4),(1,4),(2,4),(3,4),(4,4),(5,4),(6,4),(7,4),(8,4),(9,4),(1,5),(2,5),(3,5),(4,5),(5,5),(6,5),(7,5),(8,5),(9,5),(10,5),(3,6),(4,6),(5,6),(6,6),(7,6),(8,6),(9,6),(10,6),(5,7),(6,7),(7,7),(8,7),(9,7),(10,7),(7,8),(8,8),(9,8),(10,8),(9,9),(10,9)]",primes);
		
		Image2D frame= new Image2D(1000,1000);
		Image2dViewer test2 = new Image2dViewer(frame);
		Polyomino.displayPolyomino(poly, 100, frame, Color.blue, new int[] {0,0});
		
	}
	
	public static void test8(){  //Q8.2  affichage
		
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);

		Polyomino poly = new Polyomino("[(4,0,(5,0,(4,1,(5,1,(3,2,(4,2,(5,2,(6,2,(3,3,(4,3,(5,3,(6,3,(2,4,(3,4,(4,4,(5,4,(6,4,(7,4,(2,5,(3,5,(4,5,(5,5,(6,5,(7,5,(1,6,(2,6,(3,6,(4,6,(5,6,(6,6,(7,6,(8,6,(1,7,(2,7,(3,7,(4,7,(5,7,(6,7,(7,7,(8,7,(0,8,(1,8,(2,8,(3,8,(4,8,(5,8,(6,8,(7,8,(8,8,(9,8,(0,9,(1,9,(2,9,(3,9,4,9,5,9,6,9,7,9,8,9,9,9]",primes);
		
		Image2D frame= new Image2D(1000,1000);
		Image2dViewer test2 = new Image2dViewer(frame);
		Polyomino.displayPolyomino(poly, 100, frame, Color.blue, new int[] {0,0});
		
	}
	
	public static void test9(){  //Q8.3  affichage
		
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);

		Polyomino poly = new Polyomino("[4,0,5,0,3,1,4,1,5,1,6,1,2,2,3,2,4,2,5,2,6,2,7,2,1,3,2,3,3,3,4,3,5,3,6,3,7,3,8,3,0,4,1,4,2,4,3,4,4,4,5,4,6,4,7,4,8,4,9,4,0,5,1,5,2,5,3,5,4,5,5,5,6,5,7,5,8,5,9,5,1,6,2,6,3,6,4,6,5,6,6,6,7,6,8,6,2,7,3,7,4,7,5,7,6,7,7,7,3,8,4,8,5,8,6,8,4,9,5,9]",primes);
		
		Image2D frame= new Image2D(1000,1000);
		Image2dViewer test2 = new Image2dViewer(frame);
		Polyomino.displayPolyomino(poly, 100, frame, Color.blue, new int[] {0,0});
		
	}
	
	public static void test71(){  //Q8.1  
		
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);

		Polyomino poly = new Polyomino("[(0,0), (1,0),(0,1),(1,1),(2,1),(3,1),(0,2),(1,2),(2,2),(3,2),(4,2),(5,2),(0,3),(1,3),(2,3),(3,3),(4,3),(5,3),(6,3),(7,3),(0,4),(1,4),(2,4),(3,4),(4,4),(5,4),(6,4),(7,4),(8,4),(9,4),(1,5),(2,5),(3,5),(4,5),(5,5),(6,5),(7,5),(8,5),(9,5),(10,5),(3,6),(4,6),(5,6),(6,6),(7,6),(8,6),(9,6),(10,6),(5,7),(6,7),(7,7),(8,7),(9,7),(10,7),(7,8),(8,8),(9,8),(10,8),(9,9),(10,9)]",primes);
		
		LinkedList<Polyomino> L = Polyomino.genFree(5, primes);
		
		System.out.println("Nombre de Polyominos : "+L.size());


		
		
		int[][] origin = new int[poly.cases.size()][2];
		
		int[][] M =Cover.toExactCoverNoRepetFree(L, poly,origin, primes);
		
		

		LinkedList<LinkedList<LinkedList<Integer>>> k=DancingLinks.exactCover(DancingLinks.init(M));
		
		System.out.println("Nombre de solutions : "+k.size());
		
		Image2D frame= new Image2D(1000,1000);
		Image2dViewer test2 = new Image2dViewer(frame); 
		
		LinkedList<LinkedList<Integer>> l = k.pop();
			for(LinkedList<Integer> m:l){
				Polyomino.displayPolyomino(Cover.toPolyomino(m, primes, origin), 100, frame, Color.black, new int[] {0,0});
			}

		
		//for (LinkedList<LinkedList<Integer>> l:k){
		//	for(LinkedList<Integer> m:l){
		//		Polyomino.displayPolyomino(Cover.toPolyomino(m, primes, origin), 100, frame, Color.black, new int[] {0,0});
		//	}
		//}
		
	}
	
	public static void test81(){  //Q8.2  
		
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);

		Polyomino poly = new Polyomino("[(4,0,(5,0,(4,1,(5,1,(3,2,(4,2,(5,2,(6,2,(3,3,(4,3,(5,3,(6,3,(2,4,(3,4,(4,4,(5,4,(6,4,(7,4,(2,5,(3,5,(4,5,(5,5,(6,5,(7,5,(1,6,(2,6,(3,6,(4,6,(5,6,(6,6,(7,6,(8,6,(1,7,(2,7,(3,7,(4,7,(5,7,(6,7,(7,7,(8,7,(0,8,(1,8,(2,8,(3,8,(4,8,(5,8,(6,8,(7,8,(8,8,(9,8,(0,9,(1,9,(2,9,(3,9,4,9,5,9,6,9,7,9,8,9,9,9]",primes);
		
		LinkedList<Polyomino> L = Polyomino.genFree(5, primes);
		
		System.out.println("Nombre de Polyominos : "+L.size());


		
		
		int[][] origin = new int[poly.cases.size()][2];
		
		int[][] M =Cover.toExactCoverNoRepetFree(L, poly,origin, primes);
		
		

		LinkedList<LinkedList<LinkedList<Integer>>> k=DancingLinks.exactCover(DancingLinks.init(M));
		
		System.out.println("Nombre de solutions : "+k.size());
		
		Image2D frame= new Image2D(1000,1000);
		Image2dViewer test2 = new Image2dViewer(frame); 
		
		LinkedList<LinkedList<Integer>> l = k.pop();
			for(LinkedList<Integer> m:l){
				Polyomino.displayPolyomino(Cover.toPolyomino(m, primes, origin), 100, frame, Color.black, new int[] {0,0});
			}

		
		//for (LinkedList<LinkedList<Integer>> l:k){
		//	for(LinkedList<Integer> m:l){
		//		Polyomino.displayPolyomino(Cover.toPolyomino(m, primes, origin), 100, frame, Color.black, new int[] {0,0});
		//	}
		//}
		
	}
	
	public static void test91(){  //Q8.3  
		
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);

		Polyomino poly = new Polyomino("[4,0,5,0,3,1,4,1,5,1,6,1,2,2,3,2,4,2,5,2,6,2,7,2,1,3,2,3,3,3,4,3,5,3,6,3,7,3,8,3,0,4,1,4,2,4,3,4,4,4,5,4,6,4,7,4,8,4,9,4,0,5,1,5,2,5,3,5,4,5,5,5,6,5,7,5,8,5,9,5,1,6,2,6,3,6,4,6,5,6,6,6,7,6,8,6,2,7,3,7,4,7,5,7,6,7,7,7,3,8,4,8,5,8,6,8,4,9,5,9]",primes);
		
		System.out.println("Nombre de cases : "+poly.size);

		
		LinkedList<Polyomino> L = Polyomino.genFree(5, primes);
		
		System.out.println("Nombre de Polyominos : "+L.size());


		
		
		int[][] origin = new int[poly.cases.size()][2];
		
		int[][] M =Cover.toExactCoverNoRepetFree(L, poly,origin, primes);
		
		

		LinkedList<LinkedList<LinkedList<Integer>>> k=DancingLinks.exactCover(DancingLinks.init(M));
		
		System.out.println("Nombre de solutions : "+k.size());
		
		if(k.size()==0) return;
		
		Image2D frame= new Image2D(1000,1000);
		Image2dViewer test2 = new Image2dViewer(frame); 
		
		LinkedList<LinkedList<Integer>> l = k.pop();
			for(LinkedList<Integer> m:l){
				Polyomino.displayPolyomino(Cover.toPolyomino(m, primes, origin), 100, frame, Color.black, new int[] {0,0});
			}

		
		//for (LinkedList<LinkedList<Integer>> l:k){
		//	for(LinkedList<Integer> m:l){
		//		Polyomino.displayPolyomino(Cover.toPolyomino(m, primes, origin), 100, frame, Color.black, new int[] {0,0});
		//	}
		//}
		
	}
	
	public static void testRectangle(){  //Trouve tous les pavages par des free pentaminoes, sans r�p�tition, en comptant les sym�tries. (Diviser par 4 pour le nombre de solutions uniques)
		
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);

		Polyomino poly = Polyomino.Rectangle(3, 20, primes);  // Changer ici pour les dimension du rectangle
		
		System.out.println("Nombre de cases : "+poly.size);

		
		LinkedList<Polyomino> L = Polyomino.free(5, primes);  // Taille des polyominos
		
		System.out.println("Nombre de Polyominos : "+L.size());


		
		
		int[][] origin = new int[poly.cases.size()][2];
		
		int[][] M =Cover.toExactCoverNoRepetFree(L, poly,origin, primes);
		
		

		LinkedList<LinkedList<LinkedList<Integer>>> k=DancingLinks.exactCover(DancingLinks.init(M));
		
		System.out.println("Nombre de solutions : "+k.size());
		
		if(k.size()==0) return;
		
		Image2D frame= new Image2D(1000,1000);
		Image2dViewer test2 = new Image2dViewer(frame); 
		
		LinkedList<LinkedList<Integer>> l = k.pop();
		for(LinkedList<Integer> m:l){
			Polyomino.displayPolyomino(Cover.toPolyomino(m, primes, origin), 100, frame, Color.black, new int[] {0,0});
		}

		

		
	}
	
	public static void testRectangle2(){  //Trouve tous les pavages par des fixed pentaminoes, avec r�p�tition, en comptant les sym�tries. (Diviser par 4 pour le nombre de solutions uniques)
		
		Color[] color = new Color[]{Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW};
		
		
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);

		Polyomino poly = Polyomino.Rectangle(6, 5, primes);  // Changer ici pour les dimension du rectangle
		
		System.out.println("Nombre de cases : "+poly.size);

		
		LinkedList<Polyomino> L = Polyomino.fixed(5, primes);  // Taille des polyominos
		
		System.out.println("Nombre de Polyominos : "+L.size());


		
		
		int[][] origin = new int[poly.cases.size()][2];
		
		int[][] M =Cover.toExactCover(L, poly,origin);
		
		

		LinkedList<LinkedList<LinkedList<Integer>>> k=DancingLinks.exactCover(DancingLinks.init(M));
		
		System.out.println("Nombre de solutions : "+k.size());
		
		if(k.size()==0) return;
		
		Image2D frame= new Image2D(1000,1000);
		Image2dViewer test2 = new Image2dViewer(frame); 
		
		LinkedList<LinkedList<Integer>> l = k.pop();
		
		int i=0;
		for(LinkedList<Integer> m:l){
			Polyomino.displayPolyomino(Cover.toPolyomino(m, primes, origin), 100, frame, color[i%color.length], new int[] {0,0});
			i++;
		}

		

		
	}
	
	public static void expandNK(int n, int k){
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);
		LinkedList<Polyomino> L = Polyomino.genFree(n, primes);
		
		int compteur=0;
		
		for(Polyomino p:L){
			LinkedList<Polyomino> l = new LinkedList<Polyomino>();
			l.add(p);

			Polyomino poly = Polyomino.expand(p, k, primes);
			int[][] origin = new int[poly.cases.size()][2];
			int[][] M =Cover.toExactCoverFree(l, poly,origin, primes);
			LinkedList<LinkedList<LinkedList<Integer>>> sol=DancingLinks.exactCover(DancingLinks.init(M));
			if(sol.size()!=0) compteur++;
		}
		System.out.println(compteur);

	}
	public static void testSudo(){
		int[][] sudoku= new int[][]{{0,3,7,0,0,0,9,5,0},{0,0,5,7,2,0,0,0,4},{0,0,4,0,0,1,7,0,2},{0,0,1,0,0,7,0,0,3},{4,6,0,3,0,5,0,7,9},{9,0,0,6,0,0,8,0,0},{7,0,6,2,0,0,4,0,0},{5,0,0,0,7,4,3,0,0},{0,4,9,0,0,0,5,2,0}};
		DancingLinks.sudokuSolver(sudoku);
	}
	
	public static void test(){
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);
		
		LinkedList<Polyomino> poly = Polyomino.genFree(5, primes);

		LinkedList<Polyomino> liste2 = new LinkedList<Polyomino>();

		for(Polyomino p : poly){
			
			p.recentre(primes);
			LinkedList<Polyomino> liste = new LinkedList<Polyomino>();
			liste.add(p.copy(primes));
			
			p.rotate(true, primes);
			p.recentre(primes);
			liste.add(p.copy(primes));
			p.rotate(true, primes);
			p.recentre(primes);
			liste.add(p.copy(primes));
			p.rotate(true, primes);
			p.recentre(primes);
			liste.add(p.copy(primes));
			
			
			p=p.reflection(true, primes);
			
			p.recentre(primes);
			liste.add(p.copy(primes));
			p.rotate(true, primes);
			p.recentre(primes);
			liste.add(p.copy(primes));
			p.rotate(true, primes);
			p.recentre(primes);
			liste.add(p.copy(primes));
			p.rotate(true, primes);
			p.recentre(primes);
			liste.add(p.copy(primes));
			
			LinkedList<Integer> listeInt = new LinkedList<Integer>();
			
			for(Polyomino po:liste){
				Integer a = po.key;
				if(!listeInt.contains(a)){
					liste2.add(po);
					listeInt.add(a);
				}
				
			}
		}
		
		System.out.println(liste2.size());
		
	}
	
	

}

