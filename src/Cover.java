import java.util.LinkedList;
import java.util.Arrays;

public class Cover { // Ce fichier contient les fonctions nécessaires pour
						// transformer un problème de recouvrement en un
						// problème d'exact cover, ainsi qu'un algorithme naïf de résolution  d'Exact Cover
	
	
	// Task 4
	public static int[][] copy(int[][] m) {  // On copie une matrice d'entier
		int[][] result = new int[m.length][m[0].length];
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				result[i][j] = m[i][j];
			}
		}
		return result;

	}

	public static LinkedList<Integer> copyInt(LinkedList<Integer> l) {// fonction pour faire des copies de LinkedList
		LinkedList<Integer> result = new LinkedList<Integer>();
		for (int j : l) {
			result.add(j);
		}
		return result;
	}

	public static LinkedList<LinkedList<Integer>> copyList(LinkedList<LinkedList<Integer>> l) {//fonction de copie
		LinkedList<LinkedList<Integer>> result = new LinkedList<LinkedList<Integer>>();
		for (LinkedList<Integer> i : l) {
			result.add(copyInt(i));
		}
		return result;
	}

	public static LinkedList<LinkedList<LinkedList<Integer>>> exactCover(LinkedList<Integer> a, int[][] m,LinkedList<LinkedList<Integer>> c) {//fonction qui réalise l'exact cover
		if (a == null || a.size() == 0) {
			LinkedList<LinkedList<LinkedList<Integer>>> p = new LinkedList<LinkedList<LinkedList<Integer>>>();
			p.add(new LinkedList<LinkedList<Integer>>());
			return p;
		} else {
			int x = a.getFirst();
			LinkedList<LinkedList<LinkedList<Integer>>> p = new LinkedList<LinkedList<LinkedList<Integer>>>();
			for (int i = 0; i < m.length; i++) {
				LinkedList<Integer> s = new LinkedList<Integer>();
				if (m[i][x - 1] == 1) {
					// for S, xeS
					LinkedList<Integer> a1 = copyInt(a);
					LinkedList<LinkedList<Integer>> c1 = copyList(c);
					int[][] m1 = copy(m);
					for (int j = 0; j < m[i].length; j++) {
						if (m[i][j] == 1) {
							// for y , yeS
							s.add(j + 1);
							Integer y = new Integer(j + 1);
							a1.remove(y);
							for (int k = 0; k < m.length; k++) {
								if (m[k][j] == 1) {
									// for T, yeT
									LinkedList<Integer> b = new LinkedList<Integer>();
									for (int l = 0; l < m[k].length; l++) {
										if (m[k][l] == 1) {
											// for l, leT
											b.add(l + 1);
											m1[k][l] = 0;
										}
									}
									c1.remove(b);
								}
							}
							m1[i][j] = 0;
						}
					}
					for (LinkedList<LinkedList<Integer>> p1 : exactCover(a1, m1, c1)) {
						p1.add(s);
						p.add(p1);
					}
				}
			}
			return p;
		}
	}
	
	// Task 7

	public static int[][] convert(Polyomino p, int[][] origin) { // On converti
																	// le
																	// Polyomino
																	// de
																	// réference
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

	// Les 4 fonctions suivantes convertissent un problème de tilling en un
	// problem d'exact cover, avec différents paramètres

	public static int[][] toExactCover(LinkedList<Polyomino> poly, Polyomino base, int[][] origin) { // Répétitions
																										// autorisées,
																										// Polyominos
																										// fixes
																										// (Pas
																										// de
																										// rotation,
																										// pas
																										// de
																										// symétries
																										// )

		int[][] M = convert(base, origin);
		int size = base.size;

		LinkedList<int[]> L = new LinkedList<int[]>(); // On ne connait pas à
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
			int[] primes) { // Pas de répétitions, Polyominos fixes (Pas de
							// rotation, pas de symétries )

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
														// utilisé qu'une seule
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

	public static int[][] toExactCoverNoRepetFree(LinkedList<Polyomino> poly, Polyomino base, int[][] origin, int[] primes) { // Pas de répétition, possibilité de tourner et de prendre le symétrique des Polyominos

		int[][] M = convert(base, origin);
		int size = base.size;

		LinkedList<int[]> L = new LinkedList<int[]>();
		int k = 0;

		for (Polyomino p : poly) { // Pour chaque Polyomino, on se permet
									// d'utiliser également ses transformations
									// élémentaires (symétries, rotations)

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

			for (Polyomino po : liste) { // On enlève les doublons
				Integer a = po.key;
				if (!listeInt.contains(a)) {
					liste2.add(po);
					listeInt.add(a);
				}

			}

			for (Polyomino p3 : liste2) { // Même algorithme que précedemment,
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

	public static int[][] toExactCoverFree(LinkedList<Polyomino> poly, Polyomino base, int[][] origin, int[] primes) { // Répétition
																														// et
																														// transformations
																														// élémentaires
																														// autorisées

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

	public static int[] join(int[] l2, int[] l) { // On joint bout à bout 2
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
