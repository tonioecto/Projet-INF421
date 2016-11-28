import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Polyomino {  // Pas de n�gatif, tout est centr�
	int size;
	LinkedList<Case> cases;
	int width;
	int height;
	int key;
	int minx;
	int miny;
	final int[] primes = initPrimes("Primes.txt",3000); // Un calcul par instance ?

	public Polyomino(String str){
		str=str.replace("[", "");
		str=str.replace("]", "");
		str=str.replace("(", "");
		str=str.replace(")", "");
		str=str.replace(" ", "");
		String[] coor = str.split(",");

		width= Integer.MIN_VALUE;
		height = Integer.MIN_VALUE;
		minx=0;
		miny=0;
		key=1;


		size= coor.length/2;
		cases = new LinkedList<Case>();;

		for(int i=0; i<coor.length;i+=2 ){
			int a =Integer.parseInt(coor[i]);
			int b= Integer.parseInt(coor[i+1]);
			cases.add(new Case(a,b));
			if(a>width) width=a;
			if(b>height) height=b;
		}
		for (Case c:this.cases){miny=Math.min(miny, c.ordonnee);minx=Math.min(minx, c.abscisse);}
		this.key=this.valKey();
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
		    System.out.println ("Le fichier n'a pas �t� trouv�");
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

	public Case removeLast(){
		Case c = this.cases.pop();
		this.key/=primes[((c.abscisse+c.ordonnee)*(c.abscisse+c.ordonnee+1)/2+c.ordonnee)];
		return c;
	}

	public void addCase(Case c){  // Pour les cases positives, avec la cl�, on pourrait s'assurer qu'on ne rajoute pas 2 fois la m�me case
//		if(c.abscisse<0 || c.ordonnee<0){
//			this.translate(new int[] {-Math.min(0,c.abscisse),-Math.min(0,c.ordonnee)} );
//			c.abscisse+=-Math.min(0,c.abscisse);
//			c.ordonnee+=-Math.min(0,c.ordonnee);
//
//		}

		if(c.abscisse<0 && c.ordonnee<0){
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
		}


		
		this.cases.add(c);
		this.size++;

		int a = c.abscisse;
		int b = c.ordonnee;
		if (size==1){this.minx=a;this.miny=b;}
		else {
			if(a<this.minx) this.minx=a;
			if(b<this.miny) this.miny=b;
		}
		if(a>this.width) this.width=a;
		if(b>this.height) this.height=b;

		this.key=this.valKey();

	}

	public void translate(int[] coor){ // A test
		this.key=1;
		for(Case c: this.cases){
			c.abscisse=c.abscisse+coor[0];
			c.ordonnee=c.ordonnee+coor[1];
		}
		this.height+=coor[1];
		this.width+=coor[0];
		this.miny+=coor[1];
		this.minx+=coor[0];
		this.key=this.valKey();
	}

	public void rotate(boolean trigo){ // A test (bon ?)
		this.key=1;
		if(trigo){
			for(Case c:this.cases){
				int temp;
				temp=-c.abscisse+this.width; // ?
				c.abscisse=c.ordonnee;
				c.ordonnee=temp;
			}
		}

		else{
			for(Case c:this.cases){
				int temp;
				temp=c.abscisse;
				c.abscisse=-c.ordonnee+this.height; // ?
				c.ordonnee=temp;
			}
		}

		int temp=this.width;
		this.width=this.height;
		this.height=temp;
		temp=this.minx;
		this.minx=this.miny;
		this.miny=temp;
		this.key=this.valKey();

	}

	public Polyomino reflection(boolean vertical){
		Polyomino poly = new Polyomino();

		if(vertical){
			for(Case c : this.cases){
				poly.addCase(new Case(this.width-c.abscisse,c.ordonnee));
			}

			poly.height=this.height; // utile ?
			poly.width=this.width;

		}
		else{
			for(Case c : this.cases){
				poly.addCase(new Case(c.abscisse,this.height-c.ordonnee));

			}

			poly.height=this.height; // utile ?
			poly.width=this.width;
		}
		return poly;
	}
	//peut etre probleme sur cette fonction avec les minx miny

	// Rajouter une origine ?
	// Comment rajouter une case en n�gatif? (En temps acceptable �videmment) Pour l'instant O(n)
	// Dilatations
	// Faire des fonctions qui renvoient des polyomino/statiques ?

	static public LinkedList<Polyomino> Create(String file){

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
		            liste.add(new Polyomino(line));
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
		    System.out.println ("Le fichier n'a pas �t� trouv�");
		}

		return liste;

	}

	public static void createFrame(int width, int heigth){
		Image2D frame= new Image2D(width,heigth);
		Image2dViewer frameV = new Image2dViewer(frame);
	}

	public static void displayPolyomino(Polyomino p, int size, Image2D frame, Color color, int[] origin){
		for(Case c : p.cases){
			frame.addCase((origin[0]+c.abscisse)*size,(origin[1]+c.ordonnee)*size,color,size);
		}
	}

	public static void displayPolyominos(LinkedList<Polyomino>  poly, Image2D frame, int size, Color color){  // Rajouter une liste de couleur ?
		int[] pointeur=new int[] {1,0};
		for(Polyomino p:poly){
			displayPolyomino(p,size,frame,color,pointeur);
			pointeur[0]+=p.width+2;
		}
	}

	public static void displayPolyominosSym(LinkedList<Polyomino>  poly, Image2D frame, int size, Color color){  // Rajouter une liste de couleur ?
		int[] pointeur=new int[] {1,0};
		for(Polyomino p:poly){
			displayPolyomino(p.reflection(false),size,frame,color,pointeur);
			pointeur[0]+=p.width+2;
		}
	}

	public static boolean equalsFixed(Polyomino a, Polyomino b){
		return a.key==b.key;
	}

	public static void enumFixed(int n){

		Image2D frame = new Image2D(1000,1000);
		Image2dViewer frameV = new Image2dViewer(frame);

		LinkedList<Tuple> liste = new LinkedList<Tuple>();
		Polyomino poly = new Polyomino();
		LinkedList<Case> listeCase = new LinkedList<Case>();
		listeCase.add(new Case(0,0));
		Tuple tuple = new Tuple(poly,listeCase);
		liste.add(tuple);

		enumFixed(n,liste, new LinkedList<Integer>(), frame, frameV);
	}

	public static void enumFixed(int n, LinkedList<Tuple> liste, LinkedList<Integer> keys, Image2D frame, Image2dViewer frameV){
		if(n==0) return;

		LinkedList<Tuple> listef = new LinkedList<Tuple>();



		for(Tuple tuple :liste){

			for(Case c : tuple.listeCase){

				Polyomino poly=tuple.poly.copy();
				poly.addCase(c);

				if(!keys.contains(poly.key)){

					keys.add(poly.key);
					System.out.println(poly.key);


					frame.clear();
					displayPolyomino(poly, 100, frame, Color.black, new int[] {0,0});


					frameV.img2d.repaint();


					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					LinkedList<Case> L2 = (LinkedList<Case>) tuple.listeCase.clone();
					L2.remove(c);

					Case c2 = new Case(c.abscisse+1,c.ordonnee);
					if(!poly.Contains(c2)) L2.add(c2);

					c2= new Case(c.abscisse,c.ordonnee+1);
					if(!poly.Contains(c2)) L2.add(c2);

					c2= new Case(c.abscisse-1,c.ordonnee);
					if(c2.abscisse<0) L2.add(c2);
					else{
						if(!poly.Contains(c2)) L2.add(c2);
					}

					c2= new Case(c.abscisse,c.ordonnee-1);
					if(c2.ordonnee<0) L2.add(c2);
					else{
						if(!poly.Contains(c2)) L2.add(c2);
					}


					listef.add(new Tuple(poly,L2));

				}
			}
		}
		System.out.println(keys.size());
		enumFixed(n-1, listef,keys,frame, frameV);
	}
	public boolean Contains(Case c){
		//il faut que la case soit bien positionnee par rapport au polyomino considere
		if (c.abscisse>=minx && c.ordonnee>=miny){
		return this.key%(this.primes[bijN(c.abscisse-minx,c.ordonnee-miny)])==0;}
		else return false; 
	}
	
	public Polyomino copy(){
		Polyomino poly = new Polyomino();
		for(Case c:this.cases){
			poly.addCase(new Case(c.abscisse,c.ordonnee));
		}
		return poly;
	}
		
	public int valKey(){
		int res=1;
		for (Case c:this.cases){
			int bij=bijN(c.abscisse-this.minx,c.ordonnee-this.miny);
			res= res*primes[bij];
			}
		return res;

	}
	public void recentre(){
		for (Case c:this.cases){miny=Math.min(miny, c.ordonnee);minx=Math.min(minx, c.abscisse);}
		this.translate(new int[] {-minx,-miny});
	}

	public boolean inFixedList(LinkedList<Polyomino> l){
		for (Polyomino p:l){
			if(equalsFixed(this,p)){return true;}
		}
		return false;
	}
	public static LinkedList<Polyomino> genFixed(int n){
		LinkedList<Polyomino> result= new LinkedList<Polyomino>();
		if (n==1){Polyomino p=new Polyomino();p.addCase(new Case(0,0));result.add(p);}
		else{
			LinkedList<Polyomino> stock=genFixed(n-1);
			//result=genFixed(n-1);
			for (Polyomino po:stock){
				Polyomino p=po.copy();
				
				p.translate(new int[] {1,1} );
				
				for (Case c:p.cases){
					Case c1=new Case(c.abscisse,c.ordonnee+1);
					if (!p.Contains(c1)){
						Polyomino p1=p.copy();
						p1.addCase(c1);
						if(!p1.inFixedList(result)){p1.recentre();result.add(p1);}
					}
					c1=new Case(c.abscisse,c.ordonnee-1);
					if (!p.Contains(c1)){
						Polyomino p4=p.copy();
						p4.addCase(c1);
						if(!p4.inFixedList(result)){p4.recentre();result.add(p4);}}
					c1=new Case(c.abscisse+1,c.ordonnee);
					if (!p.Contains(c1)){
						Polyomino p2=p.copy();
						p2.addCase(c1);
						if(!p2.inFixedList(result)){p2.recentre();result.add(p2);}
					}
					c1=new Case(c.abscisse-1,c.ordonnee);
					if (!p.Contains(c1)){
						Polyomino p3=p.copy();
						p3.addCase(c1);
						if(!p3.inFixedList(result)){p3.recentre();result.add(p3);} }

				}
				p.recentre( );
			}
		}
		return result;
	}
}
