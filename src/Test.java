import java.awt.Color;
import java.awt.Dimension;
import java.util.LinkedList;

public class Test {

	public static void main(String[] args) {
		
		// Dans toutes les questions, l'argument size correspond à la taille de l'affichage des Polyominos. Adaptez le pour votre écran
		
		//Question 1
		
		//testAffichage(100);  //Argument : size 
		//testRotation(100);   //Argument : size 
		//testSymetrie(100);   //Argument : size 
		//testDilatation(3, 30);   //Arguments : k, size 
		//testLecture(60);   //Argument : size 
		
		
		// Question 2
		//generateFixedV1(5);   //Arguments : n
		//generateFreeV1(5);   //Arguments : n
		
		//generateFixedPrintV12(5, 30);   //Arguments : n,size 
		//generateFreePrintV1(5, 30);   //Arguments : n,size
		
		
		// Question 3
		//generateFixedV2(5);   //Arguments : n
		//generateFreeV2(5);   //Arguments : n
		
		//generateFixedPrintV2(5, 30);   //Arguments : n,size 
		//generateFreePrintV2(5, 40);   //Arguments : n,size
		
		//compareFixed(12);   // Argument : n
		//compareFree(9);   // Argument : n
		
		
		// Question 4
		//allSizeSubsets(2,6); //Arguments : k,n
		//allSubsets(5); //Arguments : n
		
		//Question 6
		//dancingExample1();
		//dancingAllSizeSubsets(2,4);
		//dancingAllSubsets(10);
		
		// Question 8
		//tilings1(60);  // Argument : size
		//tilings2(60);  // Argument : size
		//tilings3(60);  // Argument : size
		
		//rectangleFree(5,10,6,60);  //Arguments : n, longueur, largeur, size  
		//rectangleFixed(4,3,4,60);  //Arguments : n, longueur, largeur, size 
		
		//expandNK(8,4,30);  //Arguments : n, k, size 
		
		
		
	}
	
	// Question 1
	
	public static void testAffichage(int size){  // Affiche le tetramino L
		
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);

		Polyomino poly = new Polyomino("[(0,0), (0,1),(0,2),(1,2)]",primes);
		
		Image2D frame= new Image2D(width,height);
		Image2dViewer test2 = new Image2dViewer(frame);
		Polyomino.displayPolyomino(poly, 100, frame, Color.black, new int[] {0,0});
		
	}
	
	public static void testRotation(int size){ // Affiche les 4 rotations du tetramino L
		
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);

		Polyomino poly = new Polyomino("[(0,0), (0,1),(0,2),(1,2)]",primes);
		
		LinkedList<Polyomino> L = new LinkedList<Polyomino>();
		L.add(poly);
		poly.rotate(true, primes);
		L.add(poly.copy(primes));
		poly.rotate(true, primes);
		L.add(poly.copy(primes));
		poly.rotate(true, primes);
		L.add(poly.copy(primes));
		
		Image2D frame= new Image2D(width,height);
		Image2dViewer test2 = new Image2dViewer(frame);
		Polyomino.displayPolyominos(L, frame, 100, Color.black, new int[] {0,0});
	}
	
	public static void testSymetrie(int size){ // Affiche L et ses 2 symétries 
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);

		Polyomino poly = new Polyomino("[(0,0), (0,1),(0,2),(1,2)]",primes);
		
		LinkedList<Polyomino> L = new LinkedList<Polyomino>();
		L.add(poly);
		L.add(poly.reflection(true,primes));
		L.add(poly.reflection(false,primes));
		
		Image2D frame= new Image2D(width,height);
		Image2dViewer test2 = new Image2dViewer(frame);
		Polyomino.displayPolyominos(L, frame, 100, Color.black, new int[] {0,0});
	}
	
	public static void testDilatation(int k, int size){ // Affiche le Tetramino L et sa k-dilatation
		
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);

		Polyomino poly = new Polyomino("[(0,0), (0,1),(0,2),(1,2)]",primes);
		
		LinkedList<Polyomino> L = new LinkedList<Polyomino>();
		L.add(poly);
		L.add(Polyomino.expand(poly, 3, primes));
		
		Image2D frame= new Image2D(width,height);
		Image2dViewer test2 = new Image2dViewer(frame);
		Polyomino.displayPolyominos(L, frame, 30, Color.black, new int[] {0,0});
	}
	
	public static void testLecture(int size){ // Lit le fichier texte fourni par l'énonce et affiche les Polyominos créés
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);
		
		LinkedList<Polyomino> L = Polyomino.Create("test.txt", primes);
		LinkedList<Polyomino> L2 = new LinkedList<Polyomino>();

		for(Polyomino p:L){
			L2.add(p.reflection(false, primes));
		}

		Image2D frame= new Image2D(width,height);
		Image2dViewer test2 = new Image2dViewer(frame);
		Polyomino.displayPolyominos(L2, frame, 60, Color.black, new int[] {0,0});
	}

	
	//Question 2
	
	public static void generateFixedV1(int n){ //Génère les Polyominos fixes de taille n de manière "naive"
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);
		
		LinkedList<Polyomino> L = Polyomino.genFixed(n,primes);
		
		System.out.println("Nombre de Polyomino : " + L.size());

	}
	
	public static void generateFreeV1(int n){ //Génère les Polyominos libres de taille n de manière "naive"
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);
		
		LinkedList<Polyomino> L = Polyomino.genFree(n,primes);
		
		System.out.println("Nombre de Polyomino : " + L.size());

	}
	
	public static void generateFixedPrintV1(int n, int size){ //Génère les Polyominos fixes de taille n de manière "naive"
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);
		
		LinkedList<Polyomino> L = Polyomino.genFixed(n,primes);
		
		System.out.println("Nombre de Polyomino : " + L.size());


		Image2D frame= new Image2D(width,height);
		Image2dViewer test2 = new Image2dViewer(frame);
		Polyomino.displayPolyominos(L, frame, size, Color.black, new int[] {0,0});
	}
	
	public static void generateFreePrintV1(int n, int size){ //Génère les Polyominos libres de taille n de manière "naive"
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);
		
		LinkedList<Polyomino> L = Polyomino.genFree(n,primes);
		
		System.out.println("Nombre de Polyomino : " + L.size());

		Image2D frame= new Image2D(width,height);
		Image2dViewer test2 = new Image2dViewer(frame);
		Polyomino.displayPolyominos(L, frame, size, Color.black, new int[] {0,0});
	}
	
	
	// Question 3
	
	public static void generateFixedV2(int n){ //Génère les Polyominos fixes de taille n de manière optimisee
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);
		
		LinkedList<Polyomino> L = Polyomino.fixed(n,primes);

		System.out.println("Nombre de Polyomino : " + L.size());

	}
	
	public static void generateFreeV2(int n){ //Génère les Polyominos fixes de taille n de manière optimisée
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);
		
		LinkedList<Polyomino> L = Polyomino.free(n,primes);

		System.out.println("Nombre de Polyomino : " + L.size());

	}
	
	public static void generateFixedPrintV2(int n, int size){ //Génère les Polyominos fixes de taille n de manière optimisee
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);
		
		LinkedList<Polyomino> L = Polyomino.fixed(n,primes);

		System.out.println("Nombre de Polyomino : " + L.size());

		Image2D frame= new Image2D(width,height);
		Image2dViewer test2 = new Image2dViewer(frame);
		Polyomino.displayPolyominos(L, frame, size, Color.black, new int[] {0,0});
	}
	
	public static void generateFreePrintV2(int n, int size){ //Génère les Polyominos fixes de taille n de manière optimisée
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);
		
		LinkedList<Polyomino> L = Polyomino.free(n,primes);

		System.out.println("Nombre de Polyomino : " + L.size());

		Image2D frame= new Image2D(width,height);
		Image2dViewer test2 = new Image2dViewer(frame);
		Polyomino.displayPolyominos(L, frame, size, Color.black, new int[] {0,0});
	}
	
	public static void compareFixed(int n){ //Compare les performances des 2 implémentations de génération des Polyominos fixés
	
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);
		
		
		long startTime = System.currentTimeMillis();
		LinkedList<Polyomino> L = Polyomino.genFixed(n,primes);
		long stopTime = System.currentTimeMillis();
	    System.out.println("Méthode naïve : " + (stopTime-startTime) + " Millisecondes");

	    startTime = System.currentTimeMillis();
		L = Polyomino.fixed(n,primes);
		stopTime = System.currentTimeMillis();
	    System.out.println("Méthode optimisée : " + (stopTime-startTime) + " Millisecondes");

		
	}
	
	public static void compareFree(int n){ //Compare les performances des 2 implémentations de génération des Polyominos libres
		
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);
		
		
		long startTime = System.currentTimeMillis();
		LinkedList<Polyomino> L = Polyomino.genFree(n,primes);
		long stopTime = System.currentTimeMillis();
	    System.out.println("Méthode naïve : " + (stopTime-startTime) + " Millisecondes");

	    startTime = System.currentTimeMillis();
		L = Polyomino.free(n,primes);
		stopTime = System.currentTimeMillis();
	    System.out.println("Méthode optimisée : " + (stopTime-startTime) + " Millisecondes");

	}
	
	// Question 4
	

	
	
	public static LinkedList<LinkedList<Integer>> subsets(int k,LinkedList<Integer> list){
		LinkedList<LinkedList<Integer>> l=new LinkedList<LinkedList<Integer>>();
		if (k==1 ) for (int i:list){LinkedList<Integer> a=new LinkedList<Integer>();a.add(i); l.add(a);}
		else {
			LinkedList<Integer> m=new LinkedList<Integer>();
			for (int i:list){m.add(i);}
			for (Integer i:m){
				list.remove(i);
				LinkedList<Integer> p=new LinkedList<Integer>();
				for (int j:list){p.add(j);}
				LinkedList<LinkedList<Integer>> L=subsets(k-1,p);
				for (LinkedList<Integer> v:L){
					v.add(i);
					l.add(v);
				}
 			}
			
		}
		return l;
	}
	
	public static void allSizeSubsets(int k,int n){
		LinkedList<Integer> list=new LinkedList<Integer>();
		for (int i=0;i<n;i++){list.add(i+1);}
		LinkedList<LinkedList<Integer>> l=subsets(k,list);
		System.out.println(l.size());
		int[][] m = new int[l.size()][n];
		int compteur=0;
		while(l.size()!=0){
			LinkedList<Integer> a=l.pop();
			while (a.size()!=0){
				int i=a.pop();
				m[compteur][i-1]=1;
			}
			compteur ++;
		}
		LinkedList<Integer> L=new LinkedList<Integer>();
		for (int i=0;i<n;i++){L.add(i+1);}
		LinkedList<Integer> L1=new LinkedList<Integer>();
		for (int i=0;i<n;i++){L1.add(i+1);}
		
		LinkedList<LinkedList<LinkedList<Integer>>> o=Polyomino.exactCover(L1, m, subsets(k,L));
		for (LinkedList<LinkedList<Integer>> u:o){
			System.out.println(u);
		}
		
	}
	public static void allSubsets(int n){
		int[][] m= new int[(int)Math.pow((double)2,(double) n)][n];
		LinkedList<LinkedList<Integer>> L=new LinkedList<LinkedList<Integer>>();
		int compteur=0;
		for (int k=1;k<=n;k++){
			LinkedList<Integer> list=new LinkedList<Integer>();
			for (int i=0;i<n;i++){list.add(i+1);}
			LinkedList<LinkedList<Integer>> l=subsets(k,list);
			L.addAll(l);
			while(l.size()!=0){
				LinkedList<Integer> a=l.pop();
				while (a.size()!=0){
					int i=a.pop();
					m[compteur][i-1]=1;
				}
				compteur ++;
			}
		}
		LinkedList<Integer> L1=new LinkedList<Integer>();
		for (int i=0;i<n;i++){L1.add(i+1);}
		LinkedList<LinkedList<LinkedList<Integer>>> o=Polyomino.exactCover(L1, m, L);
		for (LinkedList<LinkedList<Integer>> u:o){
			System.out.println(u);}
	}
	
	//Question 6
	public static void dancingExample1(){
			int[][] M = new int[][] {{0,0,1,0,1,1,0},{1,0,0,1,0,0,1},{0,1,1,0,0,1,0},{1,0,0,1,0,0,0},{0,1,0,0,0,0,1},{0,0,0,1,1,0,1}};
			Data H = DancingLinks.init(M);
			LinkedList<LinkedList<LinkedList<Integer>>> k=DancingLinks.exactCover(DancingLinks.init(M));
			for (LinkedList<LinkedList<Integer>> l:k){
				System.out.println(l);
			}
	}
	
	public static void dancingAllSizeSubsets(int k, int n){
		LinkedList<Integer> list=new LinkedList<Integer>();
		for (int i=0;i<n;i++){list.add(i+1);}
		LinkedList<LinkedList<Integer>> l=subsets(k,list);
		int[][] m = new int[l.size()][n];
		int compteur=0;
		while(l.size()!=0){
			LinkedList<Integer> a=l.pop();
			while (a.size()!=0){
				int i=a.pop();
				m[compteur][i-1]=1;
			}
			compteur ++;
		}
		Data H = DancingLinks.init(m);
		LinkedList<LinkedList<LinkedList<Integer>>> o=DancingLinks.exactCover(DancingLinks.init(m));
		for (LinkedList<LinkedList<Integer>> u:o){
			System.out.println(u);
		}
	}
	public static void dancingAllSubsets(int n){
		int[][] m= new int[(int)Math.pow((double)2,(double) n)][n];
		LinkedList<LinkedList<Integer>> L=new LinkedList<LinkedList<Integer>>();
		int compteur=0;
		for (int k=1;k<=n;k++){
			LinkedList<Integer> list=new LinkedList<Integer>();
			for (int i=0;i<n;i++){list.add(i+1);}
			LinkedList<LinkedList<Integer>> l=subsets(k,list);
			L.addAll(l);
			while(l.size()!=0){
				LinkedList<Integer> a=l.pop();
				while (a.size()!=0){
					int i=a.pop();
					m[compteur][i-1]=1;
				}
				compteur ++;
			}
		}
		Data H = DancingLinks.init(m);
		LinkedList<LinkedList<LinkedList<Integer>>> o=DancingLinks.exactCover(DancingLinks.init(m));
		System.out.println(o.size());
//		for (LinkedList<LinkedList<Integer>> u:o){
//			System.out.println(u);
//		}

	}
	
	//Question 8
	
	public static void tilings1(int size){  //Pave la première figure de l'énoncé 
		
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);

		Polyomino poly = new Polyomino("[(0,0), (1,0),(0,1),(1,1),(2,1),(3,1),(0,2),(1,2),(2,2),(3,2),(4,2),(5,2),(0,3),(1,3),(2,3),(3,3),(4,3),(5,3),(6,3),(7,3),(0,4),(1,4),(2,4),(3,4),(4,4),(5,4),(6,4),(7,4),(8,4),(9,4),(1,5),(2,5),(3,5),(4,5),(5,5),(6,5),(7,5),(8,5),(9,5),(10,5),(3,6),(4,6),(5,6),(6,6),(7,6),(8,6),(9,6),(10,6),(5,7),(6,7),(7,7),(8,7),(9,7),(10,7),(7,8),(8,8),(9,8),(10,8),(9,9),(10,9)]",primes);
		
		LinkedList<Polyomino> L = Polyomino.genFree(5, primes);
		
		System.out.println("Nombre de Polyominos : "+L.size());

		int[][] origin = new int[poly.cases.size()][2];
		
		int[][] M =Cover.toExactCoverNoRepetFree(L, poly,origin, primes);
		
		LinkedList<LinkedList<LinkedList<Integer>>> k=DancingLinks.exactCover(DancingLinks.init(M));
		
		System.out.println("Nombre de solutions : "+k.size());
		
		Image2D frame= new Image2D(width,height);
		Image2dViewer test2 = new Image2dViewer(frame); 
		
		LinkedList<LinkedList<Integer>> l = k.pop();
			for(LinkedList<Integer> m:l){
				Polyomino.displayPolyomino(Cover.toPolyomino(m, primes, origin), size, frame, Color.black, new int[] {0,0});
			}
		
	}
	
	public static void tilings2(int size){  //Pave la deuxième figure de l'énoncé
		
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);

		Polyomino poly = new Polyomino("[(4,0,(5,0,(4,1,(5,1,(3,2,(4,2,(5,2,(6,2,(3,3,(4,3,(5,3,(6,3,(2,4,(3,4,(4,4,(5,4,(6,4,(7,4,(2,5,(3,5,(4,5,(5,5,(6,5,(7,5,(1,6,(2,6,(3,6,(4,6,(5,6,(6,6,(7,6,(8,6,(1,7,(2,7,(3,7,(4,7,(5,7,(6,7,(7,7,(8,7,(0,8,(1,8,(2,8,(3,8,(4,8,(5,8,(6,8,(7,8,(8,8,(9,8,(0,9,(1,9,(2,9,(3,9,4,9,5,9,6,9,7,9,8,9,9,9]",primes);
		
		LinkedList<Polyomino> L = Polyomino.genFree(5, primes);
		
		System.out.println("Nombre de Polyominos : "+L.size());


		
		
		int[][] origin = new int[poly.cases.size()][2];
		
		int[][] M =Cover.toExactCoverNoRepetFree(L, poly,origin, primes);
		
		

		LinkedList<LinkedList<LinkedList<Integer>>> k=DancingLinks.exactCover(DancingLinks.init(M));
		
		System.out.println("Nombre de solutions : "+k.size());
		
		Image2D frame= new Image2D(width,height);
		Image2dViewer test2 = new Image2dViewer(frame); 
		
		LinkedList<LinkedList<Integer>> l = k.pop();
			for(LinkedList<Integer> m:l){
				Polyomino.displayPolyomino(Cover.toPolyomino(m, primes, origin), size, frame, Color.black, new int[] {0,0});
			}
		
	}
	
	public static void tilings3(int size){  //Pave la troisième figure de l'énoncé   
		
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		
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
		
		Image2D frame= new Image2D(width,height);
		Image2dViewer test2 = new Image2dViewer(frame); 
		
		LinkedList<LinkedList<Integer>> l = k.pop();
			for(LinkedList<Integer> m:l){
				Polyomino.displayPolyomino(Cover.toPolyomino(m, primes, origin), size, frame, Color.black, new int[] {0,0});
			}

	}

	
	public static void rectangleFree(int n, int longueur, int largeur, int size){  //Trouve tous les pavages d'un rectangle par des polyominos libres de taille n, sans répétition, en comptant les symétries. (Diviser par 4 pour le nombre de solutions uniques)
		
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);

		Polyomino poly = Polyomino.Rectangle(longueur, largeur, primes);  // Changer ici pour les dimension du rectangle
		
		System.out.println("Nombre de Cases : "+poly.size);

		
		LinkedList<Polyomino> L = Polyomino.free(n, primes);  // Taille des polyominos
		
		System.out.println("Nombre de Polyominos : "+L.size());


		
		
		int[][] origin = new int[poly.cases.size()][2];
		
		int[][] M =Cover.toExactCoverNoRepetFree(L, poly,origin, primes);
		
		

		LinkedList<LinkedList<LinkedList<Integer>>> k=DancingLinks.exactCover(DancingLinks.init(M));
		
		System.out.println("Nombre de solutions : "+k.size());
		
		if(k.size()==0) return;
		
		Image2D frame= new Image2D(width,height);
		Image2dViewer test2 = new Image2dViewer(frame); 
		
		LinkedList<LinkedList<Integer>> l = k.pop();
		for(LinkedList<Integer> m:l){
			Polyomino.displayPolyomino(Cover.toPolyomino(m, primes, origin), size, frame, Color.black, new int[] {0,0});
		}

		

		
	}
	
	public static void rectangleFixed(int n, int longueur, int largeur, int size){ //Trouve tous les pavages d'un rectangle par des polyominos fixes de taille n, sans répétition
		
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
				
		
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);

		Polyomino poly = Polyomino.Rectangle(longueur, largeur, primes);  // Changer ici pour les dimension du rectangle
		
		System.out.println("Nombre de Cases : "+poly.size);

		
		LinkedList<Polyomino> L = Polyomino.fixed(n, primes);  // Taille des polyominos
		
		System.out.println("Nombre de Polyominos : "+L.size());


		
		
		int[][] origin = new int[poly.cases.size()][2];
		
		int[][] M =Cover.toExactCover(L, poly,origin);
		
		

		LinkedList<LinkedList<LinkedList<Integer>>> k=DancingLinks.exactCover(DancingLinks.init(M));
		
		System.out.println("Nombre de solutions : "+k.size());
		
		if(k.size()==0) return;
		
		Image2D frame= new Image2D(width,height);
		Image2dViewer test2 = new Image2dViewer(frame); 
		
		LinkedList<LinkedList<Integer>> l = k.pop();
		
		for(LinkedList<Integer> m:l){
			Polyomino.displayPolyomino(Cover.toPolyomino(m, primes, origin), size, frame, Color.black, new int[] {0,0});
		}


	}
	
	public static void expandNK(int n, int k, int size){
		
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
				
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);
		LinkedList<Polyomino> L = Polyomino.genFree(n, primes);
		LinkedList<Polyomino> result = new LinkedList<Polyomino>();
		
		int compteur=0;
		
		for(Polyomino p:L){
			LinkedList<Polyomino> l = new LinkedList<Polyomino>();
			l.add(p);

			Polyomino poly = Polyomino.expand(p, k, primes);
			int[][] origin = new int[poly.cases.size()][2];
			int[][] M =Cover.toExactCoverFree(l, poly,origin, primes);
			LinkedList<LinkedList<LinkedList<Integer>>> sol=DancingLinks.exactCover(DancingLinks.init(M));
			if(sol.size()!=0){
				compteur++;
				result.add(p);
			}
		}
		System.out.println("Il y a " +compteur + " Polyonimos de taille "+n+" qui pavent leur "+k+"-dilatation.");
		Image2D frame= new Image2D(width,height);
		Image2dViewer test2 = new Image2dViewer(frame); 
		Polyomino.displayPolyominos(result, frame, size, Color.BLACK, primes);
	}
	
}

	
	


