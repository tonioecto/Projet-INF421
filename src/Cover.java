import java.util.LinkedList;
import java.util.Arrays;

public class Cover {  // Tout est centré ?
	
	public static int[][] convert(Polyomino p , int[][] Origin){
		int i=1;
		int[][] M = new int[p.width][p.height];
		for(Case c : p.cases){
			M[c.abscisse][c.ordonnee]=i;
			Origin[i-1][0]=c.abscisse;
			Origin[i-1][1]=c.ordonnee;
			i++;
		}
		return M;
	}
	
	public static int[] place(Polyomino p, int[][] M, int orX, int orY, int size){  //size= nombre de cases de M
		//if(orX+p.width>M[0].length) return null; // A vérfier
		//if(orY+p.height>M.length) return null; // A vérifier
		
		int[] L = new int[size];   // Matrice mise a plat
		
		
		for(Case c : p.cases){
			if(M[c.abscisse+orX][c.ordonnee+orY]==0) return null;
			L[M[c.abscisse+orX][c.ordonnee+orY]-1]=1;
		}
		
		return L;
	}
	
	public static int[][] toExactCover(LinkedList<Polyomino> poly, Polyomino base, int[][] origin){
		
		int[][] M = convert(base, origin);
		int size=base.size;
		
		LinkedList<int[]> L = new LinkedList<int[]>();
		
		for(Polyomino p : poly){
			for(int i = 0; i<=M.length-p.width; i++){
				for(int j=0; j<=M[0].length-p.height; j++){
					
					int[] l=(place(p,M,i,j,size));
					if(l!=null) L.add(l);
				}
			}
		}
		
		System.out.println(L.size());
		
		int[][] EV = new int[L.size()][size];
		int i=0;
		for(int[] l:L){
			EV[i]=l;
			i++;
		}
		
		
		return EV;
	}
	
	// Changer DancingLinks pour retrouver les polyominos originels ? Il faudrait que danconglinks renvoient aussi la position des int[] retenus
	
	public static int[][] toExactCoverNoRepet(LinkedList<Polyomino> poly, Polyomino base, int[][] origin){
		
		int[][] M = convert(base,origin);
		int size=base.size;
		
		LinkedList<int[]> L = new LinkedList<int[]>();
		int k=0;
		
		for(Polyomino p : poly){
			for(int i = 0; i<=M.length-p.width; i++){
				for(int j=0; j<=M[0].length-p.height; j++){
					
					int[] l=(place(p,M,i,j,size));
					
					int[] l2=new int[poly.size()];
					l2[k]=1;
					
					
					if(l!=null) L.add(join(l, l2));
				}
			}
			k++;
		}
		
		System.out.println(L.size());
		
		int[][] EV = new int[L.size()][size];
		int i=0;
		for(int[] l:L){
			EV[i]=l;
			i++;
		}
		
		
		return EV;
	}

	public static int[] join(int[] l2, int[] l) { // Pas fini !
		// TODO Auto-generated method stub
		int[] L = new int[l.length+l2.length];
		for(int i=0; i<l2.length; i++){
			L[i]=l2[i];
		}
		for(int j=l2.length; j<L.length; j++){
			L[j]=l[j-l2.length];
		}
		
		return L;
				
	}
	
	public static Polyomino toPolyomino(LinkedList<Integer> L, int[] primes, int[][] origin){
		Polyomino poly = new Polyomino();
		for(int i:L){
			System.out.println(i);
			if(i<origin.length)
			poly.addCase(new Case(origin[i][0],origin[i][1]), primes);
		}
		return poly;
	}
	
	

}
