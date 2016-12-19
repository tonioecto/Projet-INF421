
public class Data {
	Data U,D,L,R;
	Data C;
	String N;
	int S;
	
	public Data(String name){
		this.N=name;
		this.S=0;
		this.U=this;
		this.D=this;
		this.R=this;
		this.L=this;
	}
	
	public Data(){
		this.U=this;
		this.D=this;
		this.R=this;
		this.L=this;
	}
}
