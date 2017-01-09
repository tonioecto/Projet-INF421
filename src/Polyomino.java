import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

public class Polyomino { // Pas de nï¿½gatif, tout est centrï¿½
	int size;
	LinkedList<Case> cases;
	int width;
	int height;
	int key; // Chaque Polyomino a une clé unique, permettant un test d'égalité
				// en temps constant
	int maxx;
	int maxy;
	int minx;
	int miny;

	public Polyomino(String str, int[] primes) { // On instancie un Polyomino à
													// partir d'une chaine de
													// caractère et d'une liste
													// de nombre entier
		str = str.replace("[", "");
		str = str.replace("]", "");
		str = str.replace("(", "");
		str = str.replace(")", "");
		str = str.replace(" ", "");
		String[] coor = str.split(",");

		maxx = Integer.MIN_VALUE;
		maxy = Integer.MIN_VALUE;
		minx = 0;
		miny = 0;
		key = 1;

		size = coor.length / 2;
		cases = new LinkedList<Case>();
		;

		for (int i = 0; i < coor.length; i += 2) {
			int a = Integer.parseInt(coor[i]);
			int b = Integer.parseInt(coor[i + 1]);
			cases.add(new Case(a, b));
			if (a > maxx)
				maxx = a;
			if (b > maxy)
				maxy = b;
		}
		for (Case c : this.cases) {
			miny = Math.min(miny, c.ordonnee);
			minx = Math.min(minx, c.abscisse);
		}
		height = maxy - miny + 1;
		width = maxx - minx + 1;
		this.key = this.valKey(primes);
	}

	public Polyomino() {
		size = 0;
		minx = 0;
		miny = 0;
		cases = new LinkedList<Case>();
		width = 0;
		height = 0;
		key = 1;
	}

	public static int[] initPrimes(String nameFile, int n) { // on initialise la
																// liste de
																// nombre
																// premier,
																// stockée sur
																// un fichier
																// texte.
		int[] primes = new int[n];
		int i = 0;
		try {
			File f = new File(nameFile);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);

			try {
				String line = br.readLine();

				while (i < n) {
					primes[i] = Integer.parseInt(line);
					i++;
					line = br.readLine();

				}

				br.close();
				fr.close();
			} catch (IOException exception) {
				System.out.println("Erreur lors de la lecture : " + exception.getMessage());
			}
		} catch (FileNotFoundException exception) {
			System.out.println("Fichier introuvable");
		}
		return primes;
	}

	public static int bijN(int a, int b) {
		// fait la bijection de N^2 dans N
		return ((a + b) * (a + b) + 3 * a + b) / 2;
	}

	public void addCase(Case c, int[] primes) { // On rajoute une case à un
												// Polyomino

		this.cases.add(c);
		this.size++;

		int a = c.abscisse;
		int b = c.ordonnee;
		if (size == 1) {
			this.minx = a;
			this.miny = b;
			this.maxx = a;
			this.maxy = b;
		} else {
			if (a < this.minx)
				this.minx = a;
			if (b < this.miny)
				this.miny = b;
			if (a > this.maxx)
				this.maxx = a;
			if (b > this.maxy)
				this.maxy = b;
		}

		this.height = this.maxy - this.miny + 1;
		this.width = this.maxx - this.minx + 1;
		this.key = this.valKey(primes);

	}

	public void translate(int[] coor, int[] primes) { // On translate le
														// Polyomino
		this.key = 1;
		for (Case c : this.cases) {
			c.abscisse = c.abscisse + coor[0];
			c.ordonnee = c.ordonnee + coor[1];
		}
		this.maxy += coor[1];
		this.maxx += coor[0];
		this.miny += coor[1];
		this.minx += coor[0];
		this.key = this.valKey(primes);
	}

	public void rotate(boolean trigo, int[] primes) { // On pivote un Polyomino,
														// dans le sens trigo ou
														// non
		this.key = 1;
		if (trigo) {
			for (Case c : this.cases) {
				int temp;
				temp = -c.abscisse + this.maxx;
				c.abscisse = c.ordonnee;
				c.ordonnee = temp;
			}
		}

		else {
			for (Case c : this.cases) {
				int temp;
				temp = c.abscisse;
				c.abscisse = -c.ordonnee + this.maxy;
				c.ordonnee = temp;
			}
		}

		int temp = this.width;
		this.width = this.height;
		this.height = temp;
		temp = this.minx;
		this.minx = this.miny;
		this.miny = temp;
		temp = this.maxx;
		this.maxx = this.maxy;
		this.maxy = temp;
		this.key = this.valKey(primes);

	}

	public Polyomino reflection(boolean vertical, int[] primes) { // On fait la
																	// symétrie
																	// d'un
																	// Polyomino,
																	// par
																	// rapport à
																	// un axe
																	// vertical
																	// ou non
		Polyomino poly = new Polyomino();

		if (vertical) {
			for (Case c : this.cases) {
				poly.addCase(new Case(this.maxx - c.abscisse, c.ordonnee), primes);
			}
		} else {
			for (Case c : this.cases) {
				poly.addCase(new Case(c.abscisse, this.maxy - c.ordonnee), primes);
			}
		}
		poly.minx = this.minx;
		poly.miny = this.miny;
		poly.maxx = this.maxx;
		poly.maxy = this.maxy;
		poly.height = this.height;
		poly.width = this.width;
		poly.key = poly.valKey(primes);
		return poly;

	}
	

	public static Polyomino Rectangle(int longueur, int largeur, int[] primes) { // On crée une Polyomino de forme rectangulaire
		Polyomino poly = new Polyomino();
		for (int i = 0; i < longueur; i++) {
			for (int j = 0; j < largeur; j++) {
				poly.addCase(new Case(i, j), primes);
			}
		}
		return poly;
	}

	public static Polyomino expand(Polyomino p, int k, int[] primes) { // on
																		// étend
																		// un
																		// Polyomino
																		// d'un
																		// facteur
																		// k

		Polyomino poly = new Polyomino();
		for (Case c : p.cases) {
			for (int i = 0; i < k; i++) {
				for (int j = 0; j < k; j++) {
					poly.addCase(new Case(c.abscisse * k + i, c.ordonnee * k + j), primes);
				}
			}
		}
		return poly;

	}

	static public LinkedList<Polyomino> Create(String file, int[] primes) { // On crée une liste de Polyomino à partir d'un document texte (task1)

		LinkedList<Polyomino> liste = new LinkedList<Polyomino>();

		try {
			File f = new File(file);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);

			try {
				String line = br.readLine();

				while (line != null) {
					liste.add(new Polyomino(line, primes));
					line = br.readLine();

				}

				br.close();
				fr.close();
			} catch (IOException exception) {
				System.out.println("Erreur lors de la lecture : " + exception.getMessage());
			}
		} catch (FileNotFoundException exception) {
			System.out.println("Le fichier n'a pas ï¿½tï¿½ trouvï¿½");
		}

		return liste;

	}

	public static void createFrame(int width, int heigth) { // On crée une
															// Image2D
		Image2D frame = new Image2D(width, heigth);
		Image2dViewer frameV = new Image2dViewer(frame);
	}

	public static void displayPolyomino(Polyomino p, int size, Image2D frame, Color color, int[] origin) { // on
																											// affiche
																											// un
																											// unique
																											// Polyomino
		HashMap<Integer, Edge> M = new HashMap<Integer, Edge>();
		for (Case c : p.cases) {
			frame.addCase((origin[0] + c.abscisse) * size, (origin[1] + c.ordonnee) * size, color, size);

			// Pour les edges, on utilise une HashMap pour vérifier qu'un edge
			// existe déjà ou non.
			// Si il existe déjà, c'est qu'il est à l'intérieur du Polyomino, on
			// change sa couleur et son épaisseur
			// Sinon, c'est que c'est un edge extérieur qu'il faut garder

			Edge e1 = new Edge((origin[0] + c.abscisse), (origin[1] + c.ordonnee), (origin[0] + c.abscisse + 1),
					(origin[1] + c.ordonnee), 5, Color.WHITE);
			Edge e2 = new Edge((origin[0] + c.abscisse), (origin[1] + c.ordonnee), (origin[0] + c.abscisse),
					(origin[1] + c.ordonnee + 1), 5, Color.WHITE);
			Edge e3 = new Edge((origin[0] + c.abscisse + 1), (origin[1] + c.ordonnee), (origin[0] + c.abscisse + 1),
					(origin[1] + c.ordonnee + 1), 5, Color.WHITE);
			Edge e4 = new Edge((origin[0] + c.abscisse), (origin[1] + c.ordonnee + 1), (origin[0] + c.abscisse + 1),
					(origin[1] + c.ordonnee + 1), 5, Color.WHITE);

			if (M.containsKey(e1.hashCode())) {
				M.get(e1.hashCode()).setColor(Color.DARK_GRAY);
				M.get(e1.hashCode()).width = 0;
			}

			else {
				M.put(e1.hashCode(), e1);

			}

			if (M.containsKey(e2.hashCode())) {
				M.get(e2.hashCode()).setColor(Color.DARK_GRAY);
				M.get(e2.hashCode()).width = 0;

			} else {
				M.put(e2.hashCode(), e2);
			}

			if (M.containsKey(e3.hashCode())) {
				M.get(e3.hashCode()).setColor(Color.DARK_GRAY);
				M.get(e3.hashCode()).width = 0;
			} else {
				M.put(e3.hashCode(), e3);
			}

			if (M.containsKey(e4.hashCode())) {
				M.get(e4.hashCode()).setColor(Color.DARK_GRAY);
				M.get(e4.hashCode()).width = 0;
			} else {
				M.put(e4.hashCode(), e4);
			}

		}

		for (Edge e : M.values()) { // A cause d'un dépassement de capacité dans
									// le calcul des polynomes de Cantor, il a
									// fallu "rétrécir" les edges au départ, et
									// ce n'est que maintenant qu'ils ont leur
									// positions réelle
			frame.addEdge(new Edge(e.x1 * size, e.y1 * size, e.x2 * size, e.y2 * size, e.width, e.color));
		}
	}

	public static void displayPolyominos(LinkedList<Polyomino> poly, Image2D frame, int size, Color color, int[] primes) { // On affiche une liste de Plyomino
		int[] pointeur = new int[] { 1, 0 };
		
		
		for (Polyomino p : poly) {
			if(pointeur[0]+p.size>frame.getWidth()/size){
				pointeur[1]+=p.size;
				pointeur[0]=0;
			}
			displayPolyomino(p, size, frame, color, pointeur);
			pointeur[0] += p.width + 1;
			
		}
	}


	public static boolean equalsFixed(Polyomino a, Polyomino b) { // On vérifie si 2 Polyomino sont strictement égaux
		return a.key == b.key;
	}
	
	public static boolean equalsFree(Polyomino a, Polyomino b, int[] primes) { // On vérifie si 2 Polyomino sont égaux, à des opérations élémentaires près
		if (equalsFixed(a, b)) {
			return true;
		}
		Polyomino p = b.copy(primes);
		p.rotate(true, primes);
		if (equalsFixed(a, p)) {
			return true;
		}
		p.rotate(true, primes);
		if (equalsFixed(a, p)) {
			return true;
		}
		p.rotate(true, primes);
		if (equalsFixed(a, p)) {
			return true;
		}
		Polyomino p1 = b.copy(primes);
		if (equalsFixed(a, p1.reflection(true, primes))) {
			return true;
		}
		if (equalsFixed(a, p1.reflection(false, primes))) {
			return true;
		}
		Polyomino p2 = b.copy(primes);
		p2.rotate(true, primes);
		if (equalsFixed(a, p2.reflection(true, primes))) {
			return true;
		}
		if (equalsFixed(a, p2.reflection(false, primes))) {
			return true;
		}
		return false;
	}

	public boolean Contains(Case c, int[] primes) {  // On vérifie si un Polyomino contient une case

		if (c.abscisse >= minx && c.ordonnee >= miny) {
			return this.key % (primes[bijN(c.abscisse - minx, c.ordonnee - miny)]) == 0;
		} else
			return false;
	}

	public Polyomino copy(int[] primes) { // On copie un Polyomino
		Polyomino poly = new Polyomino();
		for (Case c : this.cases) {
			poly.addCase(new Case(c.abscisse, c.ordonnee), primes);
		}
		return poly;
	}

	public int valKey(int[] primes) {  // on calcule la valeur de la clé
		int res = 1;
		for (Case c : this.cases) {
			int bij = bijN(c.abscisse - this.minx, c.ordonnee - this.miny);
			res = res * primes[bij];
		}
		return res;

	}

	public void recentre(int[] primes) {  // On recentre le Polyomino (des abscisses et des ordonnées positives mais le plus faible possible)
		for (Case c : this.cases) {
			miny = Math.min(miny, c.ordonnee);
			minx = Math.min(minx, c.abscisse);
		}
		this.translate(new int[] { -minx, -miny }, primes);
	}

	public boolean inFixedList(LinkedList<Polyomino> l) {  //On vérifie si un Polyomino est dans une liste
		for (Polyomino p : l) {
			if (equalsFixed(this, p)) {
				return true;
			}
		}
		return false;
	}
	
	// Task2

	public static LinkedList<Polyomino> genFixed(int n, int[] primes) { // On génère les Polyminos fixes (task2)
		LinkedList<Polyomino> result = new LinkedList<Polyomino>();
		if (n == 1) {
			Polyomino p = new Polyomino();
			p.addCase(new Case(0, 0), primes);
			result.add(p);
		} else {
			LinkedList<Polyomino> stock = genFixed(n - 1, primes);
			for (Polyomino po : stock) {
				Polyomino p = po.copy(primes);

				p.translate(new int[] { 1, 1 }, primes);

				for (Case c : p.cases) {
					Case c1 = new Case(c.abscisse, c.ordonnee + 1);
					if (!p.Contains(c1, primes)) {
						Polyomino p1 = p.copy(primes);
						p1.addCase(c1, primes);
						if (!p1.inFixedList(result)) {
							p1.recentre(primes);
							result.add(p1);
						}
					}
					c1 = new Case(c.abscisse, c.ordonnee - 1);
					if (!p.Contains(c1, primes)) {
						Polyomino p4 = p.copy(primes);
						p4.addCase(c1, primes);
						if (!p4.inFixedList(result)) {
							p4.recentre(primes);
							result.add(p4);
						}
					}
					c1 = new Case(c.abscisse + 1, c.ordonnee);
					if (!p.Contains(c1, primes)) {
						Polyomino p2 = p.copy(primes);
						p2.addCase(c1, primes);
						if (!p2.inFixedList(result)) {
							p2.recentre(primes);
							result.add(p2);
						}
					}
					c1 = new Case(c.abscisse - 1, c.ordonnee);
					if (!p.Contains(c1, primes)) {
						Polyomino p3 = p.copy(primes);
						p3.addCase(c1, primes);
						if (!p3.inFixedList(result)) {
							p3.recentre(primes);
							result.add(p3);
						}
					}

				}
				p.recentre(primes);
			}
		}
		return result;
	}

	public boolean inFreeList(LinkedList<Polyomino> l, int[] primes) {  // On vérifie si un Polyomino est dans une liste, à des opérations élémentaires près.
		for (Polyomino p : l) {
			if (equalsFree(this, p, primes)) {
				return true;
			}
		}
		return false;
	}



	public static LinkedList<Polyomino> genFree(int n, int[] primes) {  // On génère tous les Polyminos libres (task2)
		LinkedList<Polyomino> result = new LinkedList<Polyomino>();
		if (n == 1) {
			Polyomino p = new Polyomino();
			p.addCase(new Case(0, 0), primes);
			result.add(p);
		} else {
			LinkedList<Polyomino> stock = genFree(n - 1, primes);
			for (Polyomino po : stock) {
				Polyomino p = po.copy(primes);

				p.translate(new int[] { 1, 1 }, primes);

				for (Case c : p.cases) {
					Case c1 = new Case(c.abscisse, c.ordonnee + 1);
					if (!p.Contains(c1, primes)) {
						Polyomino p1 = p.copy(primes);
						p1.addCase(c1, primes);
						if (!p1.inFreeList(result, primes)) {
							p1.recentre(primes);
							result.add(p1);
						}
					}
					c1 = new Case(c.abscisse, c.ordonnee - 1);
					if (!p.Contains(c1, primes)) {
						Polyomino p4 = p.copy(primes);
						p4.addCase(c1, primes);
						if (!p4.inFreeList(result, primes)) {
							p4.recentre(primes);
							result.add(p4);
						}
					}
					c1 = new Case(c.abscisse + 1, c.ordonnee);
					if (!p.Contains(c1, primes)) {
						Polyomino p2 = p.copy(primes);
						p2.addCase(c1, primes);
						if (!p2.inFreeList(result, primes)) {
							p2.recentre(primes);
							result.add(p2);
						}
					}
					c1 = new Case(c.abscisse - 1, c.ordonnee);
					if (!p.Contains(c1, primes)) {
						Polyomino p3 = p.copy(primes);
						p3.addCase(c1, primes);
						if (!p3.inFreeList(result, primes)) {
							p3.recentre(primes);
							result.add(p3);
						}
					}

				}
				p.recentre(primes);
			}
		}
		return result;
	}

	// Task3
	public static LinkedList<Polyomino> add(LinkedList<Polyomino> a, LinkedList<Polyomino> b) {  // On concatène 2 LinkedLists
		for (Polyomino p : a) {
			b.add(p);
		}
		return b;
	}

	public static LinkedList<Case> copy(LinkedList<Case> b) {  // On copie une liste de case
		LinkedList<Case> a = new LinkedList<Case>();
		for (Case c : b) {
			a.add(new Case(c.abscisse, c.ordonnee));
		}
		return a;
	}

	public static int[][] copy(int[][] t, int n) { 
		int[][] res = new int[2 * n + 4][n + 4];
		for (int i = 0; i < 2 * n + 4; i++) {
			for (int j = 0; j < n + 4; j++) {
				res[i][j] = t[i][j];
			}
		}
		return res;
	}

	public static LinkedList<Polyomino> fixed(int n, int[] primes) {  // On énumère efficacement toutes les Polyominos fixes de taille n
		int[][] table = new int[2 * n + 4][n + 4];
		for (int i = 0; i < n; i++) {
			table[i][0] = 1;
		}
		LinkedList<Case> ca = new LinkedList<Case>();
		ca.add(new Case(0, 0));
		Polyomino p = new Polyomino();
		LinkedList<Polyomino> a = interm(table, ca, p, n, primes);

		for (Polyomino p2 : a) {
			p2.recentre(primes);
		}

		return a;
	}

	public static LinkedList<Polyomino> interm(int[][] tab, LinkedList<Case> st, Polyomino p, int n, int[] primes) {  
		LinkedList<Polyomino> result = new LinkedList<Polyomino>();
		if (!st.isEmpty()) {
			Case c = st.pop();
			tab[c.abscisse + n][c.ordonnee] = 1;
			Polyomino p1 = p.copy(primes);
			p1.addCase(new Case(c.abscisse, c.ordonnee), primes);
			if (p1.size == n) {
				result.add(p1);
			}
			if (p1.size < n) {
				int compteur = 0;
				int[][] tabl = copy(tab, n);
				LinkedList<Case> sta = copy(st);

				if (c.abscisse + n < 2 * n + 4 && c.ordonnee + 1 < n + 4 && tabl[c.abscisse + n][c.ordonnee + 1] == 0) {
					sta.add(new Case(c.abscisse, c.ordonnee + 1));
					compteur++;
					tabl[c.abscisse + n][c.ordonnee + 1] = 1;
				}
				if (c.abscisse + n + 1 < 2 * n + 4 && c.ordonnee < n + 4 && tabl[c.abscisse + 1 + n][c.ordonnee] == 0) {
					sta.add(new Case(c.abscisse + 1, c.ordonnee));
					compteur++;
					tabl[c.abscisse + n + 1][c.ordonnee] = 1;
				}
				if (c.ordonnee - 1 >= 0 && c.abscisse + n < 2 * n + 4) {
					if (tabl[c.abscisse + n][c.ordonnee - 1] == 0) {
						sta.add(new Case(c.abscisse, c.ordonnee - 1));
						compteur++;
						tabl[c.abscisse + n][c.ordonnee - 1] = 1;
					}
					;
				}
				if (c.abscisse - 1 + n < 2 * n + 4 && tabl[c.abscisse - 1 + n][c.ordonnee] == 0) {
					sta.add(new Case(c.abscisse - 1, c.ordonnee));
					compteur++;
					tabl[c.abscisse + n - 1][c.ordonnee] = 1;
				}
				result = add(result, interm(tabl, sta, p1, n, primes));

			}
			result = add(interm(tab, st, p, n, primes), result);
		}

		return result;

	}

	
	public static LinkedList<Polyomino> free(int n, int[] primes) {  // On énumère efficacement tous les polyomino libres
		LinkedList<Polyomino> list = fixed(n, primes);
		LinkedList<Polyomino> result = new LinkedList<Polyomino>();
		
		
	    HashMap<Integer, Polyomino> M = new HashMap<Integer, Polyomino>();
		
		for (Polyomino p : list) {
			p.recentre(primes);
			
			M.put(p.freeKey(primes), p);
		}
		
		Collection<Polyomino> C = M.values();

		for (Polyomino p2 : C) {
			p2.recentre(primes);
			result.add(p2);
		}
		return result;
	}
	
	
	public int freeKey(int[] primes){  // On calcule la key d'un Polyomino libre (la key minimal de tous les fixes qui en découle)
		int minKey=this.key;
		
		this.rotate(true,primes);
		this.recentre(primes);
		minKey=Math.min(minKey, this.key);
		
		this.rotate(true,primes);
		this.recentre(primes);
		minKey=Math.min(minKey, this.key);
		
		this.rotate(true,primes);
		this.recentre(primes);
		minKey=Math.min(minKey, this.key);
		
		Polyomino p = this.reflection(true, primes);
		
		p.recentre(primes);
		minKey=Math.min(minKey, p.key);
		
		p.rotate(true, primes);
		p.recentre(primes);
		minKey=Math.min(minKey, p.key);
		
		p.rotate(true, primes);
		p.recentre(primes);
		minKey=Math.min(minKey, p.key);
		
		p.rotate(true, primes);
		p.recentre(primes);
		minKey=Math.min(minKey, p.key);
		
		return minKey;
	}
	
	




}