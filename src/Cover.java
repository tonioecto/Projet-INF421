import java.util.LinkedList;
import java.util.Arrays;

public class Cover { // Ce fichier contient les fonctions n�cessaires pour
						// transformer un probl�me de recouvrement en un
						// probl�me d'exact cover

	public static int[][] convert(Polyomino p, int[][] origin) { // On converti
																	// le
																	// Polyomino
																	// de
																	// r�ference
																	// en une
																	// matrice
		int i = 1;
		int[][] M = new int[p.width][p.height];
		for (Case c : p.cases) {
			M[c.abscisse][c.ordonnee] = i; // A chaque Case, on associe un
											// entier
			origin[i - 1][0] = c.abscisse; // La matrice origin permet de
											// retrouver les cases en
											// connaissant son ID (entier)
			origin[i - 1][1] = c.ordonnee;
			i++;
		}
		return M;
	}

	public static int[] place(Polyomino p, int[][] M, int orX, int orY, int size) { // size=
																					// nombre
																					// de
																					// cases
																					// non
																					// nulles
																					// de
																					// M
																					// Cette
																					// fonction
																					// permet
																					// de
																					// placer
																					// un
																					// Polyomino
																					// sur
																					// celui
																					// de
																					// base

		int[] L = new int[size]; // Matrice mise a plat

		for (Case c : p.cases) {
			if (M[c.abscisse + orX][c.ordonnee + orY] == 0)
				return null; // Le Polyomino ne rentre pas sur la base
			L[M[c.abscisse + orX][c.ordonnee + orY] - 1] = 1;
		}

		return L;
	}

	// Les 4 fonctions suivantes convertissent un probl�me de tilling en un
	// problem d'exact cover, avec diff�rents param�tres

	public static int[][] toExactCover(LinkedList<Polyomino> poly, Polyomino base, int[][] origin) { // R�p�titions
																										// autoris�es,
																										// Polyominos
																										// fixes
																										// (Pas
																										// de
																										// rotation,
																										// pas
																										// de
																										// sym�tries
																										// )

		int[][] M = convert(base, origin);
		int size = base.size;

		LinkedList<int[]> L = new LinkedList<int[]>(); // On ne connait pas �
														// priori les dimensions
														// de la matrice finale,
														// on commence avec une
														// LinkedList<int[]>

		for (Polyomino p : poly) {
			for (int i = 0; i <= M.length - p.width; i++) {
				for (int j = 0; j <= M[0].length - p.height; j++) {

					int[] l = (place(p, M, i, j, size)); // pour chaque
															// Polyomino, on
															// code tous les
															// placements
															// possibles
					if (l != null)
						L.add(l);
				}
			}
		}

		int[][] EV = new int[L.size()][size]; // On transforme la
												// LinkedList<int[]> en int[][]
		int i = 0;
		for (int[] l : L) {
			EV[i] = l;
			i++;
		}

		return EV;
	}

	public static int[][] toExactCoverNoRepet(LinkedList<Polyomino> poly, Polyomino base, int[][] origin,
			int[] primes) { // Pas de r�p�titions, Polyominos fixes (Pas de
							// rotation, pas de sym�tries )

		int[][] M = convert(base, origin);
		int size = base.size;

		LinkedList<int[]> L = new LinkedList<int[]>();
		int k = 0;

		for (Polyomino p : poly) {

			p.recentre(primes);

			for (int i = 0; i <= M.length - p.width; i++) {
				for (int j = 0; j <= M[0].length - p.height; j++) {

					int[] l = (place(p, M, i, j, size));

					int[] l2 = new int[poly.size()]; // On s'assure que chaque
														// Polyomino n'est
														// utilis� qu'une seule
														// fois
					l2[k] = 1;

					if (l != null)
						L.add(join(l, l2));
				}
			}
			k++;
		}


		int[][] EV = new int[L.size()][size];
		int i = 0;
		for (int[] l : L) {
			EV[i] = l;
			i++;
		}

		return EV;
	}

	public static int[][] toExactCoverNoRepetFree(LinkedList<Polyomino> poly, Polyomino base, int[][] origin, int[] primes) { // Pas de r�p�tition, possibilit� de tourner et de prendre le sym�trique des Polyominos

		int[][] M = convert(base, origin);
		int size = base.size;

		LinkedList<int[]> L = new LinkedList<int[]>();
		int k = 0;

		for (Polyomino p : poly) { // Pour chaque Polyomino, on se permet
									// d'utiliser �galement ses transformations
									// �l�mentaires (sym�tries, rotations)

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

			p = p.reflection(true, primes);

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

			LinkedList<Polyomino> liste2 = new LinkedList<Polyomino>();
			LinkedList<Integer> listeInt = new LinkedList<Integer>();

			for (Polyomino po : liste) { // On enl�ve les doublons
				Integer a = po.key;
				if (!listeInt.contains(a)) {
					liste2.add(po);
					listeInt.add(a);
				}

			}

			for (Polyomino p3 : liste2) { // M�me algorithme que pr�cedemment,
											// on essaie toutes le positions
											// possibles en s'assurant une
											// utilisation unique de chaque
											// Polyomino
				for (int i = 0; i <= M.length - p3.width; i++) {
					for (int j = 0; j <= M[0].length - p3.height; j++) {

						int[] l = (place(p3, M, i, j, size));

						int[] l2 = new int[poly.size()];
						l2[k] = 1;

						if (l != null)
							L.add(join(l, l2));
					}
				}
			}
			k++;
		}

		

		int[][] EV = new int[L.size()][size];
		int i = 0;
		for (int[] l : L) {
			EV[i] = l;
			i++;
		}

		return EV;
	}

	public static int[][] toExactCoverFree(LinkedList<Polyomino> poly, Polyomino base, int[][] origin, int[] primes) { // R�p�tition
																														// et
																														// transformations
																														// �l�mentaires
																														// autoris�es

		int[][] M = convert(base, origin);
		int size = base.size;

		LinkedList<int[]> L = new LinkedList<int[]>();

		for (Polyomino p : poly) {

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

			p = p.reflection(true, primes);

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

			LinkedList<Polyomino> liste2 = new LinkedList<Polyomino>();
			LinkedList<Integer> listeInt = new LinkedList<Integer>();

			for (Polyomino po : liste) {
				Integer a = po.key;
				if (!listeInt.contains(a)) {
					liste2.add(po);
					listeInt.add(a);
				}

			}

			for (Polyomino p3 : liste2) {
				for (int i = 0; i <= M.length - p3.width; i++) {
					for (int j = 0; j <= M[0].length - p3.height; j++) {

						int[] l = (place(p3, M, i, j, size));

						if (l != null)
							L.add(l);
					}
				}
			}

		}

		int[][] EV = new int[L.size()][size];
		int i = 0;
		for (int[] l : L) {
			EV[i] = l;
			i++;
		}

		return EV;
	}

	public static int[] join(int[] l2, int[] l) { // On joint bout � bout 2
													// listes
		int[] L = new int[l.length + l2.length];
		for (int i = 0; i < l2.length; i++) {
			L[i] = l2[i];
		}
		for (int j = l2.length; j < L.length; j++) {
			L[j] = l[j - l2.length];
		}

		return L;

	}

	public static Polyomino toPolyomino(LinkedList<Integer> L, int[] primes, int[][] origin) { // Avec le codage d'un placement de Polyomino, on le reconstruit
																
		Polyomino poly = new Polyomino();
		for (int i : L) {
			if (i < origin.length)
				poly.addCase(new Case(origin[i][0], origin[i][1]), primes);
		}
		return poly;
	}

}
