import java.util.LinkedList;

public class Tuple {
	Polyomino poly;
	LinkedList<Case> listeCase;
	
	public Tuple(Polyomino poly, LinkedList<Case> listeCase){
		this.poly=poly;
		this.listeCase=listeCase;
	}
	
}
