import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;
public class Polyomino {  // Pas de nï¿½gatif, tout est centrï¿½
	int size;
	LinkedList<Case> cases;
	int width;
	int height;
	int key;
	int maxx;
	int maxy;
	int minx;
	int miny;
	//final int[] primes = initPrimes("Primes.txt",3000); // Un calcul par instance ?

	public Polyomino(String str,int[] primes){
		str=str.replace("[", "");
		str=str.replace("]", "");
		str=str.replace("(", "");
		str=str.replace(")", "");
		str=str.replace(" ", "");
		String[] coor = str.split(",");

		maxx= Integer.MIN_VALUE;
		maxy = Integer.MIN_VALUE;
		minx=0;
		miny=0;
		key=1;


		size= coor.length/2;
		cases = new LinkedList<Case>();;
		
		//System.out.println(coor.length);

		for(int i=0; i<coor.length;i+=2 ){
			int a =Integer.parseInt(coor[i]);
			int b= Integer.parseInt(coor[i+1]);
			cases.add(new Case(a,b));
			if(a>maxx) maxx=a;
			if(b>maxy) maxy=b;
		}
		for (Case c:this.cases){miny=Math.min(miny, c.ordonnee);minx=Math.min(minx, c.abscisse);}
		height = maxy-miny+1;  
		width = maxx-minx+1;
		this.key=this.valKey( primes);
	}

	public static int[] initPrimes(String nameFile,int n){
		int[] primes = new int[n];
		int i=0;
		try
		{
		    File f = new File (nameFile);
		    FileReader fr = new FileReader (f);
		    BufferedReader br = new BufferedReader (fr);

		    try
		    {
		        String line = br.readLine();

		        while (i<n)
		        {
		            //System.out.println (line);
		            primes[i] =  Integer.parseInt(line);
		            i++;
		            line=br.readLine();

		        }

		        br.close();
		        fr.close();
		    }
		    catch (IOException exception)
		    {
		        System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
		    }
		}
		catch (FileNotFoundException exception)
		{
		    System.out.println ("Le fichier n'a pas ï¿½tï¿½ trouvï¿½");
		}
		return primes;
	}
	public static int bijN(int a,int b){
		//fait la bijection des entiers dans le plan des entiers
		return ((a+b)*(a+b)+3*a+b)/2;}

	public Polyomino(){
		size=0;
		minx=0;
		miny=0;
		cases = new LinkedList<Case>();
		width=0;
		height=0;
		key=1;
	}


	public void addCase(Case c,int[] primes){  // Pour les cases positives, avec la clï¿½, on pourrait s'assurer qu'on ne rajoute pas 2 fois la mï¿½me case
//		if(c.abscisse<0 || c.ordonnee<0){
//			this.translate(new int[] {-Math.min(0,c.abscisse),-Math.min(0,c.ordonnee)} );
//			c.abscisse+=-Math.min(0,c.abscisse);
//			c.ordonnee+=-Math.min(0,c.ordonnee);
//
//		}

		/*if(c.abscisse<0 && c.ordonnee<0){
			this.translate(new int[] {-c.abscisse,-c.ordonnee} );
			c.abscisse=0;
			c.ordonnee=0;
		}
		else if (c.abscisse<0){
			this.translate(new int[] {-c.abscisse,0} );
			c.abscisse=0;
		}

		else if (c.ordonnee<0){
			this.translate(new int[] {0,-c.ordonnee} );
			c.ordonnee=0;
		}*/


		
		this.cases.add(c);
		this.size++;

		int a = c.abscisse;
		int b = c.ordonnee;
		if (size==1){this.minx=a;this.miny=b;this.maxx=a;this.maxy=b;}
		else {
			if(a<this.minx) this.minx=a;
			if(b<this.miny) this.miny=b;
			if(a>this.maxx) this.maxx=a;
			if(b>this.maxy) this.maxy=b;
		}
		
		this.height = this.maxy-this.miny+1;  
		this.width =this.maxx-this.minx+1;
		this.key=this.valKey(primes);

	}

	public void translate(int[] coor,int[] primes){ // A test
		this.key=1;
		for(Case c: this.cases){
			c.abscisse=c.abscisse+coor[0];
			c.ordonnee=c.ordonnee+coor[1];
		}
		this.maxy+=coor[1];
		this.maxx+=coor[0];
		this.miny+=coor[1];
		this.minx+=coor[0];
		this.key=this.valKey(primes);
	}

	public void rotate(boolean trigo,int[] primes){ // A test (bon ?)
		this.key=1;
		if(trigo){
			for(Case c:this.cases){
				int temp;
				temp=-c.abscisse+this.maxx; // ?
				c.abscisse=c.ordonnee;
				c.ordonnee=temp;
			}
		}

		else{
			for(Case c:this.cases){
				int temp;
				temp=c.abscisse;
				c.abscisse=-c.ordonnee+this.maxy; // ?
				c.ordonnee=temp;
			}
		}

		int temp=this.width;
		this.width=this.height;
		this.height=temp;
		temp=this.minx;
		this.minx=this.miny;
		this.miny=temp;
		temp=this.maxx;
		this.maxx=this.maxy;
		this.maxy=temp;
		this.key=this.valKey(primes);
		//this.recentre(primes);  // Ajout

	}

	public Polyomino reflection(boolean vertical,int[] primes){
		Polyomino poly = new Polyomino();

		if(vertical){
			for(Case c : this.cases){
				poly.addCase(new Case(this.maxx-c.abscisse,c.ordonnee),primes);
			}
		}
		else{
			for(Case c : this.cases){
				poly.addCase(new Case(c.abscisse,this.maxy-c.ordonnee),primes);
			}
		}
		poly.minx=this.minx;
		poly.miny=this.miny;
		poly.maxx=this.maxx;
		poly.maxy=this.maxy;
		poly.height=this.height; // utile ?
		poly.width=this.width;
		//for (Case c:poly.cases){miny=Math.min(miny, c.ordonnee);minx=Math.min(minx, c.abscisse);}
		poly.key=poly.valKey(primes);
		//poly.recentre(primes);   // Ajout
		return poly;
		
	}
	//peut etre probleme sur cette fonction avec les minx miny

	// Rajouter une origine ?
	// Comment rajouter une case en nï¿½gatif? (En temps acceptable ï¿½videmment) Pour l'instant O(n)
	// Dilatations
	// Faire des fonctions qui renvoient des polyomino/statiques ?

	static public LinkedList<Polyomino> Create(String file,int[] primes){

		LinkedList<Polyomino> liste = new LinkedList<Polyomino>();

		try
		{
		    File f = new File (file);
		    FileReader fr = new FileReader (f);
		    BufferedReader br = new BufferedReader (fr);

		    try
		    {
		        String line = br.readLine();

		        while (line != null)
		        {
		            //System.out.println (line);
		            liste.add(new Polyomino(line,primes));
		            line = br.readLine();

		        }

		        br.close();
		        fr.close();
		    }
		    catch (IOException exception)
		    {
		        System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
		    }
		}
		catch (FileNotFoundException exception)
		{
		    System.out.println ("Le fichier n'a pas ï¿½tï¿½ trouvï¿½");
		}

		return liste;

	}

	public static void createFrame(int width, int heigth){
		Image2D frame= new Image2D(width,heigth);
		Image2dViewer frameV = new Image2dViewer(frame);
	}

	public static void displayPolyomino(Polyomino p, int size, Image2D frame, Color color, int[] origin){
		HashMap<Integer,Edge> M = new HashMap<Integer,Edge>();
		for(Case c : p.cases){
			frame.addCase((origin[0]+c.abscisse)*size,(origin[1]+c.ordonnee)*size,color,size);
			//System.out.println(c.abscisse+"   "+c.ordonnee);
			
			// PB de dépassemment de integer
			
			Edge e1 = new Edge((origin[0]+c.abscisse),(origin[1]+c.ordonnee),(origin[0]+c.abscisse+1),(origin[1]+c.ordonnee),2,Color.WHITE);
			Edge e2 = new Edge((origin[0]+c.abscisse),(origin[1]+c.ordonnee),(origin[0]+c.abscisse),(origin[1]+c.ordonnee+1),2,Color.WHITE);
			Edge e3 = new Edge((origin[0]+c.abscisse+1),(origin[1]+c.ordonnee),(origin[0]+c.abscisse+1),(origin[1]+c.ordonnee+1),2,Color.WHITE);
			Edge e4 = new Edge((origin[0]+c.abscisse),(origin[1]+c.ordonnee+1),(origin[0]+c.abscisse+1),(origin[1]+c.ordonnee+1),2,Color.WHITE);
			
			if(M.containsKey(e1.hashCode())){
				M.get(e1.hashCode()).setColor(Color.RED);
				//System.out.println("ok1");
			}
			
			else{
				M.put(e1.hashCode(), e1);
				
			}
			//System.out.println(e1.hashCode());
			//System.out.println(e3.hashCode());
			
			if(M.containsKey(e2.hashCode())){
				M.get(e2.hashCode()).setColor(Color.RED);
				//System.out.println("ok2");
			}
			else{
				M.put(e2.hashCode(), e2);
			}
			
			if(M.containsKey(e3.hashCode())){
				M.get(e3.hashCode()).setColor(Color.RED);
				//System.out.println("ok3");
			}
			else{
				M.put(e3.hashCode(), e3);
			}
			
			if(M.containsKey(e4.hashCode())){
				M.get(e4.hashCode()).setColor(Color.RED);
				//System.out.println("ok4");
			}
			else{
				M.put(e4.hashCode(), e4);
			}

			
			
		}
		
		for(Edge e:M.values()){
			frame.addEdge(new Edge(e.x1*size,e.y1*size,e.x2*size,e.y2*size,e.width,e.color));
		}
	}

	public static void displayPolyominos(LinkedList<Polyomino>  poly, Image2D frame, int size, Color color,int[] primes){  // Rajouter une liste de couleur ?
		int[] pointeur=new int[] {1,0};
		for(Polyomino p:poly){
			//p.recentre(primes);
			
			System.out.println(p.width+"   "+p.height);
			
			//p=p.reflection(false);
			displayPolyomino(p,size,frame,color,pointeur);
			//System.out.println(p.width);
			pointeur[0]+=p.width+1;
		}
	}

	public static void displayPolyominosSym(LinkedList<Polyomino>  poly, Image2D frame, int size, Color color,int[] primes){  // Rajouter une liste de couleur ?
		int[] pointeur=new int[] {1,0};
		for(Polyomino p:poly){
			displayPolyomino(p.reflection(false,primes),size,frame,color,pointeur);
			pointeur[0]+=p.width+2;
		}
	}

	public static boolean equalsFixed(Polyomino a, Polyomino b){
		return a.key==b.key;
	}

	
	public boolean Contains(Case c,int[] primes){
		//il faut que la case soit bien positionnee par rapport au polyomino considere
		if (c.abscisse>=minx && c.ordonnee>=miny){
		return this.key%(primes[bijN(c.abscisse-minx,c.ordonnee-miny)])==0;}
		else return false; 
	}
	
	public Polyomino copy(int[] primes){
		Polyomino poly = new Polyomino();
		for(Case c:this.cases){
			poly.addCase(new Case(c.abscisse,c.ordonnee),primes);
		}
		return poly;
	}
		
	public int valKey(int[] primes){
		int res=1;
		for (Case c:this.cases){
			int bij=bijN(c.abscisse-this.minx,c.ordonnee-this.miny);
			res= res*primes[bij];
			}
		return res;

	}
	public void recentre(int[] primes){
		for (Case c:this.cases){miny=Math.min(miny, c.ordonnee);minx=Math.min(minx, c.abscisse);}
		this.translate(new int[] {-minx,-miny},primes);
	}

	public boolean inFixedList(LinkedList<Polyomino> l){
		for (Polyomino p:l){
			if(equalsFixed(this,p)){return true;}
		}
		return false;
	}
	public static LinkedList<Polyomino> genFixed(int n,int[] primes){
		LinkedList<Polyomino> result= new LinkedList<Polyomino>();
		if (n==1){Polyomino p=new Polyomino();p.addCase(new Case(0,0),primes);result.add(p);}
		else{
			LinkedList<Polyomino> stock=genFixed(n-1,primes);
			//result=genFixed(n-1);
			for (Polyomino po:stock){
				Polyomino p=po.copy(primes);
				
				p.translate(new int[] {1,1},primes );
				
				for (Case c:p.cases){
					Case c1=new Case(c.abscisse,c.ordonnee+1);
					if (!p.Contains(c1,primes)){
						Polyomino p1=p.copy(primes);
						p1.addCase(c1,primes);
						if(!p1.inFixedList(result)){p1.recentre(primes);result.add(p1);}
					}
					c1=new Case(c.abscisse,c.ordonnee-1);
					if (!p.Contains(c1,primes)){
						Polyomino p4=p.copy(primes);
						p4.addCase(c1,primes);
						if(!p4.inFixedList(result)){p4.recentre(primes);result.add(p4);}}
					c1=new Case(c.abscisse+1,c.ordonnee);
					if (!p.Contains(c1,primes)){
						Polyomino p2=p.copy(primes);
						p2.addCase(c1,primes);
						if(!p2.inFixedList(result)){p2.recentre(primes);result.add(p2);}
					}
					c1=new Case(c.abscisse-1,c.ordonnee);
					if (!p.Contains(c1,primes)){
						Polyomino p3=p.copy(primes);
						p3.addCase(c1,primes);
						if(!p3.inFixedList(result)){p3.recentre(primes);result.add(p3);} }

				}
				p.recentre(primes );
			}
		}
		return result;
	}
	public boolean inFreeList(LinkedList<Polyomino> l,int[] primes){
		for (Polyomino p:l){
			if(equalsFree(this,p,primes)){return true;}
		}
		return false;
	}
	public static boolean equalsFree(Polyomino a,Polyomino b,int[] primes){
		if (equalsFixed(a,b)){return true;}
		Polyomino p=b.copy(primes);
		p.rotate(true,primes);
		if (equalsFixed(a,p)){return true;}
		p.rotate(true,primes);
		if (equalsFixed(a,p)){return true;}
		p.rotate(true,primes);
		if (equalsFixed(a,p)){return true;}
		Polyomino p1=b.copy(primes);
		if (equalsFixed(a,p1.reflection(true,primes))){return true;}
		if (equalsFixed(a,p1.reflection(false,primes))){return true;}
		Polyomino p2=b.copy(primes);
		p2.rotate(true,primes);
		if (equalsFixed(a,p2.reflection(true,primes))){return true;}
		if (equalsFixed(a,p2.reflection(false,primes))){return true;}
		return false;
	}
	public static LinkedList<Polyomino> genFree(int n,int[] primes){
		LinkedList<Polyomino> result= new LinkedList<Polyomino>();
		if (n==1){Polyomino p=new Polyomino();p.addCase(new Case(0,0),primes);result.add(p);}
		else{
			LinkedList<Polyomino> stock=genFree(n-1,primes);
			//result=genFixed(n-1);
			for (Polyomino po:stock){
				Polyomino p=po.copy(primes);
				
				p.translate(new int[] {1,1},primes );
				
				for (Case c:p.cases){
					Case c1=new Case(c.abscisse,c.ordonnee+1);
					if (!p.Contains(c1,primes)){
						Polyomino p1=p.copy(primes);
						p1.addCase(c1,primes);
						if(!p1.inFreeList(result,primes)){p1.recentre(primes);result.add(p1);}
					}
					c1=new Case(c.abscisse,c.ordonnee-1);
					if (!p.Contains(c1,primes)){
						Polyomino p4=p.copy(primes);
						p4.addCase(c1,primes);
						if(!p4.inFreeList(result,primes)){p4.recentre(primes);result.add(p4);}}
					c1=new Case(c.abscisse+1,c.ordonnee);
					if (!p.Contains(c1,primes)){
						Polyomino p2=p.copy(primes);
						p2.addCase(c1,primes);
						if(!p2.inFreeList(result,primes)){p2.recentre(primes);result.add(p2);}
					}
					c1=new Case(c.abscisse-1,c.ordonnee);
					if (!p.Contains(c1,primes)){
						Polyomino p3=p.copy(primes);
						p3.addCase(c1,primes);
						if(!p3.inFreeList(result,primes)){p3.recentre(primes);result.add(p3);} }

				}
				p.recentre(primes );
			}
		}
		//System.out.print(result.size());
		return result;
	}
	
	//debut de la question 3
	public static LinkedList<Polyomino> add(LinkedList<Polyomino> a,LinkedList<Polyomino> b){
		for (Polyomino p:a){
			b.add(p);
		}
		return b;
	}
	public static LinkedList<Case> copy(LinkedList<Case> b){
		LinkedList<Case> a=new LinkedList<Case>();
		for (Case c:b){a.add(new Case(c.abscisse,c.ordonnee));}
		return a;
	}
	public static int[][] copy(int[][] t,int n){
		int [][] res= new int[2*n+4][n+4];
		for (int i=0;i<2*n+4;i++){for (int j=0;j<n+4;j++){res[i][j]=t[i][j];}}
		return res;
	}
	public static LinkedList<Polyomino> fixed( int n,int[] primes){
		int[][] table=new int[2*n+4][n+4];
		for (int i=0;i<n;i++){table[i][0]=1;}
		LinkedList<Case> ca=new LinkedList<Case>();
		ca.add(new Case(0,0));
		Polyomino p=new Polyomino();
		LinkedList<Polyomino> a=interm(table,ca,p,n,primes);
		return a;
	}

	public static LinkedList<Polyomino> interm(int[][] tab, LinkedList<Case> st,Polyomino p,int n,int[] primes){
		LinkedList<Polyomino> result=new LinkedList<Polyomino>();
		if (!st.isEmpty()){
			Case c=st.pop();
			tab[c.abscisse+n][c.ordonnee]=1;
			Polyomino p1=p.copy(primes);
			p1.addCase(new Case(c.abscisse,c.ordonnee),primes);
			if (p1.size==n){result.add(p1);}
			if (p1.size<n){
				int compteur=0;
				int [][] tabl=copy(tab,n);
				LinkedList<Case> sta=copy(st);
				
				if (c.abscisse+n<2*n+4 && c.ordonnee+1<n+4 &&tabl[c.abscisse+n][c.ordonnee+1]==0){sta.add(new Case(c.abscisse,c.ordonnee+1));compteur++;tabl[c.abscisse+n][c.ordonnee+1]=1;}
				if (c.abscisse+n+1<2*n+4 && c.ordonnee <n+4 &&tabl[c.abscisse+1+n][c.ordonnee]==0){sta.add(new Case(c.abscisse+1,c.ordonnee));compteur++;tabl[c.abscisse+n+1][c.ordonnee]=1;}
				if (c.ordonnee-1>=0 && c.abscisse+n<2*n+4){if (tabl[c.abscisse+n][c.ordonnee-1]==0){sta.add(new Case(c.abscisse,c.ordonnee-1));compteur++;tabl[c.abscisse+n][c.ordonnee-1]=1;};}
				if (c.abscisse-1+n<2*n+4 &&tabl[c.abscisse-1+n][c.ordonnee]==0){sta.add(new Case(c.abscisse-1,c.ordonnee));compteur++;tabl[c.abscisse+n-1][c.ordonnee]=1;}
				result=add(result, interm(tabl,sta,p1,n,primes));
				
			}
			result=add(interm(tab,st,p,n,primes),result);}
		
		return result;
		
	}
	
	public static LinkedList<Polyomino> free(int n,int[] primes){
		LinkedList<Polyomino> list= fixed(n,primes);
		LinkedList<Polyomino> result=new LinkedList<Polyomino>();
		for (Polyomino p:list){
			p.recentre(primes);
			if (result.isEmpty()){result.add(p);}
			else {
				boolean a=true;
				for (Polyomino po:result){
					if (equalsFree(po,p,primes)){a=false;}
				}
				if (a){result.add(p);}
			}
		}
		return result;
	}
	

	
// Task 4
	public static int[][] copy(int[][]m){
		int[][] result= new int[m.length][m[0].length];
		for (int i=0;i<m.length;i++){
			for (int j=0;j<m[0].length;j++){
				result[i][j]=m[i][j];
			}
		}
		return result;
		
	}
	public static LinkedList<Integer> copyInt(LinkedList<Integer> l){
		LinkedList<Integer> result=new LinkedList<Integer>();
		for (int j:l){
			result.add(j);
		}
		return result;
	}
	public static LinkedList<LinkedList<Integer>> copyList(LinkedList<LinkedList<Integer>> l){
		LinkedList<LinkedList<Integer>> result=new LinkedList<LinkedList<Integer>>();
		for (LinkedList<Integer> i:l){
			result.add(copyInt(i));
		}
		return result;
	}

	public static LinkedList<LinkedList<LinkedList<Integer>>> exactCover(LinkedList<Integer> a,int[][] m,LinkedList<LinkedList<Integer>> c){
		if (a==null || a.size()==0){LinkedList<LinkedList<LinkedList<Integer>>> p=  new LinkedList<LinkedList<LinkedList<Integer>>>();p.add(new LinkedList<LinkedList<Integer>>());return p;}
		else {
			int x=a.getFirst();
			LinkedList<LinkedList<LinkedList<Integer>>> p=new LinkedList<LinkedList<LinkedList<Integer>>>();
			for (int i=0;i<m.length;i++){
				LinkedList<Integer> s=new LinkedList<Integer>();
				if (m[i][x-1]==1){
					// for S, xeS
					LinkedList<Integer> a1=copyInt(a);
					LinkedList<LinkedList<Integer>> c1=copyList(c);
					int[][] m1=copy(m);
					for (int j=0;j<m[i].length;j++){
						if (m[i][j]==1){
							//for y , yeS
							s.add(j+1);
							Integer y=new Integer (j+1);
							a1.remove(y);
							for (int k=0;k<m.length;k++){
								if (m[k][j]==1){
									//for T, yeT
									LinkedList<Integer> b=new LinkedList<Integer>();
									for (int l=0;l<m[k].length;l++){
										if (m[k][l]==1){
											//for l, leT
											b.add(l+1);
											m1[k][l]=0;
										}
									}
									c1.remove(b);
								}
							}
							m1[i][j]=0;
						}
					}
					for (LinkedList<LinkedList<Integer>> p1:exactCover(a1,m1,c1)){
						p1.add(s);
						p.add(p1);
					}
				}
			}
		return p;}
	}
	
	
	//Task 5
	

}