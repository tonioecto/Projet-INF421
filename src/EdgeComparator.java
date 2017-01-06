import java.util.Comparator;
public class EdgeComparator implements Comparator<Edge> {
	
	@Override
	public int compare(Edge e1, Edge e2) {
        int i = Cantor(e1.x1,e1.x2,e1.y1,e1.y2);
        int j = Cantor(e2.x1,e2.x2,e2.y1,e2.y2);
        if( i < j )
            return -1;
        else if( i > j )
            return 1;
        else
            return 0;
    }
	
	
	public int Cantor(int x, int y){
		return ((x+y)*(x+y)+3*x+y)/2;
	}
	public int Cantor(int x, int y , int z, int t){
		return(Cantor(Cantor(Cantor(x,y),z),t));
	}

}
