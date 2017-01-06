import java.awt.Color;

public class Edge {
	int x1,x2,y1,y2;
	int width;
	public Color color;
	
	public Edge(int x1, int y1, int x2, int y2, int width, Color color){
		this.x1=x1;
		this.x2=x2;
		this.y1=y1;
		this.y2=y2;
		this.width=width;
		this.color=color;
	}
	
	public void setColor(Color c){
		this.color=c;
	}
	
	  @Override
	  public boolean equals(Object o) {
	    return (this.hashCode()==o.hashCode());
	  }

	  @Override
	  public int hashCode() {  // Pb dépassage
		 return Cantor(this.x1,this.y1,this.x2,this.y2);
	  }
	  
	  public int Cantor(int x, int y){
		return ((x+y)*(x+y)+3*x+y)/2;
      }
	  
	  public int Cantor(int x1, int y1 , int x2, int y2){
		  
		  int x=Math.min(x1, x2);
		  int y=Math.max(x1, x2);
		  int z=Math.min(y1, y2);
		  int t=Math.max(y1, y2);
		  
		  
		return(Cantor(Cantor(Cantor(x,y),z),t));
	  }
}
