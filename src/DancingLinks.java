
public class DancingLinks {
	static public Data init(int[][] M){
		int i=0;
		Data current = new Data((Character.toString((char) (i+65))));
		Data[] last = new Data[M[0].length];
		last[i] = current;
		for(i=1; i<M[0].length; i++){
			Data nouv = new Data((Character.toString((char) (i+65))));
			Data temp = current.R;
			current.R=nouv;
			nouv.L=current;
			nouv.R=temp;
			temp.L=nouv;
			current=nouv;
			last[i]=current;
		}
		
		Data fin = new Data((Character.toString((char) (i+65)))); 

		current=current.R;

		
		Data column=current;
		Data[] vertical = new Data[M.length];

		for(i=0; i<M.length; i++){
			for(int j=0; j<M[i].length; j++){
				if(M[i][j]==1){
					column.S++;
					Data data = new Data();
					data.C=column;
					Data temp=last[j].D;
					data.U=last[j];
					last[j].D=data;
					data.D=temp;
					temp.U=data;
					last[j]=data;
					
					
					if(vertical[i]==null){
						vertical[i]=data;
					}
					else{
						temp = vertical[i].R;
						data.L=vertical[i];
						vertical[i].R=data;
						data.R=temp;
						temp.L=data;
						vertical[i]=data;
					}
				}
				column=column.R;
			}
			
		}
		
		Data temp = current.L; 
		current.L=fin;
		fin.R=current;
		fin.L=temp;
		temp.R=fin;
		
//		for(i=0; i<10; i++){
//		System.out.println(current.N);
//		current=current.R;
//	}
		
		return current.L;  // return H
	}
}
