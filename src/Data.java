
public class Data {   // L'objet Data utilis� pour la structure de Dancing Links
	Data U,D,L,R;
	Data C;
	Integer N;
	int S;
	
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
