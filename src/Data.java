
public class Data {   // L'objet Data utilisé pour la structure de Dancing Links
	Data U,D,L,R;
	Data C;
	Integer N; // Le "nom" de l'objet si c'est une colonne
	int S;    // La size de la colonne (Combien d'éléments elle contient)
	
	int i,j;
	
	public Data(Integer name){
		this.N=name;
		this.S=0;
		this.U=this;
		this.D=this;
		this.R=this;
		this.L=this;
		this.C=this;
	}
	
	public Data(){
		this.U=this;
		this.D=this;
		this.R=this;
		this.L=this;
		this.C=this;
	}
}
