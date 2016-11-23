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
	
	int key;  // A implementer
	
	public Polyomino(String str){
		str=str.replace("[", "");
		str=str.replace("]", "");
		str=str.replace("(", "");
		str=str.replace(")", "");
		str=str.replace(" ", "");
		String[] coor = str.split(",");
		
		width= Integer.MIN_VALUE;
		height = Integer.MIN_VALUE;

		
		size= coor.length/2;
		cases = new LinkedList<Case>();;

		for(int i=0; i<coor.length;i+=2 ){
			cases.add(new Case(coor[i],coor[i+1]));
			if(Integer.parseInt(coor[i])>width) width=Integer.parseInt(coor[i]);
			if(Integer.parseInt(coor[i+1])>height) height=Integer.parseInt(coor[i+1]);

		}
	
	}
	
	
	public Polyomino(){	
		size=0;
		cases = new LinkedList<Case>();
		width=0;
		height=0;
	}
	
	public void addCase(Case c){  // Pour les cases positives
		this.cases.add(c);
		this.size++;
		if(c.abscisse>this.width) this.width=c.abscisse;
		if(c.ordonnee>this.height) this.height=c.ordonnee;
	}
	
	public void translate(int[] coor){ // A test
		for(Case c: this.cases){
			c.abscisse=c.abscisse+coor[0];
			c.ordonnee=c.ordonnee+coor[1];
		}
		this.height+=coor[1];
		this.width+=coor[0];
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
	
	
	
	
}
