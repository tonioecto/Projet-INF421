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
		generateFreePrintV1(5, 30);   //Arguments : n,size
		
		// Question 3
		//generateFixedV2(5);   //Arguments : n
		//generateFreeV2(5);   //Arguments : n
		
		//generateFixedPrintV2(5, 30);   //Arguments : n,size 
		//generateFreePrintV2(5, 30);   //Arguments : n,size
		
		//compareFixed(8);   // Argument : n
		//compareFree(8);   // Argument : n
		
		// Question 4
		
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
	
	public static void generateFixedV1(int n){ //Génère les Polyominos fixes de taille n de manière optimisée
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);
		
		LinkedList<Polyomino> L = Polyomino.genFixed(n,primes);
		
		System.out.println("Nombre de Polyomino : " + L.size());

	}
	
	public static void generateFreeV1(int n){ //Génère les Polyominos libres de taille n de manière optimisée
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);
		
		LinkedList<Polyomino> L = Polyomino.genFree(n,primes);
		
		System.out.println("Nombre de Polyomino : " + L.size());

	}
	
	public static void generateFixedPrintV1(int n, int size){ //Génère les Polyominos fixes de taille n de manière optimisée
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);
		
		LinkedList<Polyomino> L = Polyomino.genFixed(n,primes);
		
		System.out.println("Nombre de Polyomino : " + L.size());


		Image2D frame= new Image2D(width,height);
		Image2dViewer test2 = new Image2dViewer(frame);
		Polyomino.displayPolyominos(L, frame, 30, Color.black, new int[] {0,0});
	}
	
	public static void generateFreePrintV1(int n, int size){ //Génère les Polyominos libres de taille n de manière optimisée
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);
		
		LinkedList<Polyomino> L = Polyomino.genFree(n,primes);
		
		System.out.println("Nombre de Polyomino : " + L.size());

		Image2D frame= new Image2D(width,height);
		Image2dViewer test2 = new Image2dViewer(frame);
		Polyomino.displayPolyominos(L, frame, 30, Color.black, new int[] {0,0});
	}
	
	// Question 3
	
	public static void generateFixedV2(int n){ //Génère les Polyominos fixes de taille n de manière "naïve"
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
	
	public static void generateFixedPrintV2(int n, int size){ //Génère les Polyominos fixes de taille n de manière "naïve"
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		
		int[] primes = Polyomino.initPrimes("Primes.txt",3000);
		
		LinkedList<Polyomino> L = Polyomino.fixed(n,primes);

		System.out.println("Nombre de Polyomino : " + L.size());

		Image2D frame= new Image2D(width,height);
		Image2dViewer test2 = new Image2dViewer(frame);
		Polyomino.displayPolyominos(L, frame, 30, Color.black, new int[] {0,0});
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
		Polyomino.displayPolyominos(L, frame, 30, Color.black, new int[] {0,0});
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
	
	//Question 8
	
	
}

	
	


