	public class Case{  // On caractérise une case d'un Polyomino par son abscisse et son ordonnée
		int abscisse;
		int ordonnee;
		
		public Case(int a, int b){
			abscisse = a;
			ordonnee = b;
		}
		
		public Case(int[] a){
			abscisse = a[0];
			ordonnee = a[1];
		}
		
		public Case(String a, String b){
			abscisse = Integer.parseInt(a);
			ordonnee = Integer.parseInt(b);
		}
	}