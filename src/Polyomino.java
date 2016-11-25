import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Polyomino {  // Pas de négatif, tout est centré
	
	int size;
	LinkedList<Case> cases;
	int width;
	int height;
	int minx;
	int miny;
	
	
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
		
		size= coor.length/2;
		cases = new LinkedList<Case>();;

		for(int i=0; i<coor.length;i+=2 ){
			cases.add(new Case(coor[i],coor[i+1]));
			if(Integer.parseInt(coor[i])>width) width=Integer.parseInt(coor[i]);
			if(Integer.parseInt(coor[i+1])>height) height=Integer.parseInt(coor[i+1]);

		}
		for (Case c:this.cases){miny=Math.min(miny, c.ordonnee);minx=Math.min(minx, c.abscisse);}
	
	}
	
	
	public Polyomino(){	
		size=0;
		minx=0;
		miny=0;
		cases = new LinkedList<Case>();
		width=0;
		height=0;
	}
	
	public void addCase(Case c){  // Pour les cases positives
		this.cases.add(c);
		this.size++;
		if(c.abscisse>this.width) this.width=c.abscisse;
		if(c.ordonnee>this.height) this.height=c.ordonnee;
		if(c.abscisse<this.minx) this.minx=c.abscisse;
		if(c.ordonnee<this.miny) this.miny=c.ordonnee;
	}
	
	public void translate(int[] coor){ // A test
		for(Case c: this.cases){
			c.abscisse=c.abscisse+coor[0];
			c.ordonnee=c.ordonnee+coor[1];
		}
		this.height+=coor[1];
		this.width+=coor[0];
		this.miny+=coor[1];
		this.minx+=coor[0];
	}
	
	public void rotate(boolean trigo){ // A test (bon ?)
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
	// Comment rajouter une case en négatif? (En temps acceptable évidemment)
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
		    System.out.println ("Le fichier n'a pas été trouvé");
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
	
	
	public static int bijN(int a,int b){
		//fait la bijection des entiers dans le plan des entiers
		return ((a+b)^2+3*a+b)/2;
	}
	public Polyomino recentre(){
		//recentre un polynome le plus dans le coin droite possible
		Polyomino res=new Polyomino();
		for (Case c:this.cases){Case c1=new Case(c.abscisse-minx,c.ordonnee-miny);res.addCase(c1);}	
		return res;
	}
	public int valeurPrem(){
		String[] prem = "premier.txt".split(" ");
		int res=1;
		for (Case c:this.cases){
			int bij=bijN(c.abscisse,c.ordonnee);
			res= res*Integer.parseInt(prem[bij]);
			}
		return res;
		
	}
	public boolean testFixed(Polyomino a, Polyomino b){
		if (a.recentre().valeurPrem()==b.recentre().valeurPrem()){return true;}
		return false;
	}
	public boolean inPoly(Case c){
		for (Case k:this.cases){
			if (k.abscisse==c.abscisse && k.ordonnee==c.ordonnee){return true;}
		}
		return false;
	}
	public boolean inFixedList(LinkedList<Polyomino> l){
		for (Polyomino p:l){
			if(testFixed(this,p)){return true;}
		}
		return false;
	}
	public LinkedList<Polyomino> genFixed(int n){
		LinkedList<Polyomino> result= new LinkedList<Polyomino>();
		if (n==1){Polyomino p=new Polyomino();p.addCase(new Case(0,0));result.add(p);}
		else{
			LinkedList<Polyomino> stock=genFixed(n-1);
			for (Polyomino p:stock){
				int[] t=new int[1] ;
				t[0]=1;
				t[1]=1;
				p.recentre().translate(t);
				for (Case c:p.cases){
					Case c1=new Case(c.abscisse,c.ordonnee+1);
					if (!p.inPoly(c1)){
						Polyomino p1=p;
						p1.addCase(c1);
						if(!p1.inFixedList(result)){result.add(p1);}
					}
					c1=new Case(c.abscisse,c.ordonnee-1);
					if (!p.inPoly(c1)){
						Polyomino p1=p;
						p1.addCase(c1);
						if(!p1.inFixedList(result)){result.add(p1);}
					}
					c1=new Case(c.abscisse+1,c.ordonnee);
					if (!p.inPoly(c1)){
						Polyomino p1=p;
						p1.addCase(c1);
						if(!p1.inFixedList(result)){result.add(p1);}
					}
					c1=new Case(c.abscisse-1,c.ordonnee);
					if (!p.inPoly(c1)){
						Polyomino p1=p;
						p1.addCase(c1);
						if(!p1.inFixedList(result)){result.add(p1);}
					}
					
				}
			}
		}
		return result;
	}
}
