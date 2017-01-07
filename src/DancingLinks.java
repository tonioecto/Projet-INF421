import java.util.LinkedList;

public class DancingLinks { // Dans cette classe, on trouve les fonctions
							// nécessaires au fonctionnement de l'algorithme de
							// Dancing Links

	static public Data init(int[][] M) { // Task 5 : On transforme une matrice
											// en un DancingLinks
		int i = 0;
		Data current = new Data(i);
		Data[] last = new Data[M[0].length];
		last[i] = current;
		for (i = 1; i < M[0].length; i++) { // On crée la première ligne
			Data nouv = new Data(i);
			Data temp = current.R;
			current.R = nouv;
			nouv.L = current;
			nouv.R = temp;
			temp.L = nouv;
			current = nouv;
			last[i] = current;
		}

		Data fin = new Data(i);

		current = current.R;

		Data column = current;
		Data[] vertical = new Data[M.length];

		for (i = 0; i < M.length; i++) {
			for (int j = 0; j < M[i].length; j++) {
				if (M[i][j] == 1) {
					column.S++;
					Data data = new Data(); // On fait les connections
											// verticales nécessaires
					data.C = column;
					Data temp = last[j].D;
					data.U = last[j];
					last[j].D = data;
					data.D = temp;
					temp.U = data;
					last[j] = data;

					if (vertical[i] == null) { // On fait les connections
												// horizontales nécessaires
						vertical[i] = data;
					} else {
						temp = vertical[i].R;
						data.L = vertical[i];
						vertical[i].R = data;
						data.R = temp;
						temp.L = data;
						vertical[i] = data;
					}
				}
				column = column.R;
			}

		}

		Data temp = current.L; // On rajoute le dernier Data, le "H" dans
								// l'exemple
		current.L = fin;
		fin.R = current;
		fin.L = temp;
		temp.R = fin;

		return current.L; // On renvoit l'équivalent du "H" sur l'exemple donné
	}

	public static void coverColumn(Data x) { // L'algorithme donné dans le sujet
		x = x.C;
		x.R.L = x.L;
		x.L.R = x.R;
		Data t = x.D;
		while (t != x) {
			Data y = t.R;
			while (y != t) {
				y.D.U = y.U;
				y.U.D = y.D;
				y.C.S = y.C.S - 1;
				y = y.R;
			}
			t = t.D;
		}
	}

	public static void uncoverColumn(Data x) { // L'algorithme donné dans le
												// sujet
		x = x.C;
		x.R.L = x;
		x.L.R = x;
		Data t = x.U;
		while (t != x) {
			Data y = t.L;
			while (y != t) {
				y.D.U = y;
				y.U.D = y;
				y.C.S = y.C.S + 1;
				y = y.L;
			}
			t = t.U;
		}
	}

	public static LinkedList<LinkedList<LinkedList<Integer>>> exactCover(Data h) {
		if (h.R == h) {
			LinkedList<LinkedList<LinkedList<Integer>>> p = new LinkedList<LinkedList<LinkedList<Integer>>>();
			p.add(new LinkedList<LinkedList<Integer>>());
			return p;
		} else {
			LinkedList<LinkedList<LinkedList<Integer>>> p = new LinkedList<LinkedList<LinkedList<Integer>>>();
			Data x = h.R;
			Data z = x.R;
			int i = 0;
			while (z != h) {
				if (z.S < x.S) {
					x = z;
				}
				z = z.R;
				i++;
			}
			coverColumn(x);
			Data t = x.U;
			while (t != x) {
				LinkedList<Integer> s = new LinkedList<Integer>();
				s.add(t.C.N);
				Data y = t.L;
				while (y != t) {

					s.add(y.C.N);
					coverColumn(y);
					y = y.L;
				}
				LinkedList<LinkedList<LinkedList<Integer>>> p2 = exactCover(h);
				for (LinkedList<LinkedList<Integer>> p1 : p2) {
					p1.add(s);
					p.add(p1);
				}
				Data k = t.R;
				while (k != t) {
					uncoverColumn(k);
					k = k.R;
				}
				t = t.U;
			}
			uncoverColumn(x);

			return p;
		}
	}
}