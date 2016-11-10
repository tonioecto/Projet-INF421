import java.awt.Color;
import java.awt.Polygon;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Polymino {
	int size;
	//Case[] form; 
	int[] xCoords;
	int[] yCoords;
	int width;
	int height;
	int maxx;
	int maxy;
	int minx;
	int miny;
	
	int origineX;
	int origineY;
	
	public Polymino(String str){
		str=str.replace("[", "");
		str=str.replace("]", "");
		str=str.replace("(", "");
		str=str.replace(")", "");
		str=str.replace(" ", "");
		String[] coor = str.split(",");
		
		int maxx= Integer.MIN_VALUE;
		int maxy = Integer.MIN_VALUE;
		int minx= Integer.MAX_VALUE;
		int miny= Integer.MAX_VALUE;
		
		size= coor.length/2;
		//form = new Case[size];
		xCoords = new int[size];
		yCoords = new int[size];
		for(int i=0; i<coor.length;i+=2 ){
			//form[i/2]=new Case(coor[i],coor[i+1]);
			
			xCoords[i/2]=Integer.parseInt(coor[i]);
			yCoords[i/2]=Integer.parseInt(coor[i+1]);
			if(xCoords[i/2]>maxx) maxx = xCoords[i/2];
			if(xCoords[i/2]<minx) minx = xCoords[i/2];
			if(yCoords[i/2]>maxy) maxy = xCoords[i/2];
			if(yCoords[i/2]<miny) miny = xCoords[i/2];
		}
		width = maxy-miny;  
		height = maxx-minx;	
	}
	

	
	

	public Polymino(int n){
		size=n;
		xCoords=new int[n];
		yCoords= new int[n];
	}
	
	public void translate(int[] coor){
//		for(Case c:this.form){
//			c.abscisse=c.abscisse+coor[0];
//			c.ordonnee=c.ordonnee+coor[1];
//		}
		
		for(int i = 0; i<this.size; i++){
			xCoords[i]+=coor[0];
			yCoords[i]+=coor[1];
		}
		this.maxx+=coor[0];
		this.minx+=coor[0];
		this.maxy+=coor[1];
		this.miny+=coor[1];
		
	}
	
	public void rotate(boolean trigo){
		if(trigo){
//			for(Case c:this.form){
//				int temp;
//				temp=-c.ordonnee;
//				c.ordonnee=c.abscisse;
//				c.abscisse=temp;
//			}
			for(int i = 0; i<this.size; i++){
				int temp;
				temp = -yCoords[i];
				yCoords[i]=xCoords[i];
				xCoords[i]=temp;
			}
			int temp1=this.maxx;
			int temp2=this.minx;
			this.maxx=-this.miny;
			this.minx=-this.maxy;
			this.maxy=temp1;
			this.miny=temp2;
		}
		
		else{
//			for(Case c:this.form){
//				int temp;
//				temp=c.ordonnee;
//				c.ordonnee=-c.abscisse;
//				c.abscisse=temp;
//			}
			for(int i = 0; i<this.size; i++){
				int temp;
				temp = yCoords[i];
				yCoords[i]=-xCoords[i];
				xCoords[i]=temp;
			}
			int temp1=this.maxx;
			int temp2=this.minx;
			this.maxx=this.maxy;
			this.minx=this.miny;
			this.maxy=-temp2;
			this.miny=-temp1;
		}
		
		int temp=this.width;
		this.width=this.height;
		this.height=temp;
	}
	
	public void reframe(){ 
		int maxa=Integer.MAX_VALUE;
		int maxo=Integer.MAX_VALUE;
//		for(Case c:this.form){
//			if(c.abscisse<maxa) maxa=c.abscisse;
//			if(c.ordonnee<maxo) maxo=c.ordonnee;
//		}
		for(int i = 0; i<this.size; i++){
			if(xCoords[i]<maxa) maxa= xCoords[i];
			if(yCoords[i]<maxo) maxo= yCoords[i];
		}
		
		this.translate(new int[]{-maxa,-maxo});
	}
	
	static public Polygon toPolygone(Polymino p){
		return new Polygon(p.xCoords, p.yCoords, p.size);
	}
	
	static public ColoredPolygon toColoredPolygone(Polymino p, Color color){
		return new ColoredPolygon(p.xCoords, p.yCoords, color);
	}
	
	public Polygon toPolygone(){
		return new Polygon(this.xCoords, this.yCoords, this.size);
	}
	
	public Polymino expand(int n){
		Polymino poly = new Polymino(this.size*n*n);
		for(int i = 0; i<this.size; i++){
			for(int j=0; j<n; j++){
				for(int k=0; k<n; k++){
					poly.xCoords[i*n*n+j*n+k]=this.xCoords[i]*n+j;
					//System.out.println(this.xCoords[i]*n+j);
					//System.out.println(this.yCoords[i]*n+k);
					poly.yCoords[i*n*n+j*n+k]=this.yCoords[i]*n+k;
				}

			}
		}
		
		poly.width=this.width*n;
		poly.height=this.height*n;
		poly.maxx=this.maxx*n;
		poly.maxy=this.maxy*n;
		poly.minx=this.minx*n;
		poly.miny=this.miny*n;
		
		return poly;
	}
	
	
	
	static public LinkedList<Polymino> Create(String file){
		
		LinkedList<Polymino> liste = new LinkedList<Polymino>();
		
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
		            liste.add(new Polymino(line));
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
	
	public static void affiche(String str){
		LinkedList<Polymino> liste = Create(str);
		LinkedList<Polymino> liste2 = new LinkedList<Polymino>();
		int width=10; // marge à gauche
		int height=0;
		for(Polymino poly2:liste){
			//Polymino poly = poly2.expand(30);
			Polymino poly = poly2;
			liste2.add(poly);
			width+=poly.width+20;
			if(height<poly.height) height=poly.height;
		}
		Image2D image= new Image2D(width,height+40);
		Image2dViewer imagev = new Image2dViewer(image);
		int curseur=0;
		for(Polymino poly:liste2){
			poly.translate(new int[] {curseur+10, 20});
			curseur+=10+poly.width;
			image.addPixel(poly.xCoords, poly.yCoords, Color.RED);
			curseur+=10;
		}
	}



	public class Case{
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

}