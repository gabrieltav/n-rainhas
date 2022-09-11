package trabalhoianrainhas.com;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args)throws IOException, ClassNotFoundException {
		int answer=-1;
		int nq;
		double ttime=0;
		double k=0;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean flag=false;
		while(!flag){
			try{
				do{
					System.out.println("\nEscolhas\n1)Executar métodos");
					System.out.println("2)Saída do programa");
					System.out.print("Escolha uma resposta:");
					String input = br.readLine();
					answer= Integer.parseInt(input);
				}while(answer<0&&answer>2);
			}catch(NumberFormatException e){
				System.out.println("Sua resposta não corresponde a um número. Tente novamente");
			} 
			
			
	        if(answer==1){
	        	do{System.out.print("Dê o número maior que 3:");
	        	String input = br.readLine();
	        	nq=Integer.parseInt(input);
	        	}while(nq<=3);
	        		        	SolutionMethods sol=new SolutionMethods(nq); 
	        	for (int i=0;i<10;i++) {
	        		System.out.print("\n----Loop----=="+i);
	        		sol.hillclimb();
	        		
	       	}
	        	k=sol.getInits();
	    		k=k/10;
	    		ttime=sol.getTime();
	    		ttime=ttime/10;
	    		System.out.println("------------------->Resultados:");
	    		System.out.println("------------------->Número médio de grades de inicialização:"+k);
	    		System.out.println("------------------->Número médio de tempo:"+ttime);
	        	for (int i=0;i<10;i++) {
	        		System.out.print("\n----Loop----=="+i);
	        		sol.constraintSAT();
	        	}
	        	System.out.println("------------------->Satisfação da restrição de resultados:");
	    		ttime=sol.getTttime();
	    		ttime=ttime/10;
	    		k=sol.getLoads();
	    		k=k/10;
	    		
	    		System.out.println("------------------->Número médio de cargas:"+k);
	    		System.out.println("------------------->Número médio de tempo:"+ttime);
	        	flag=false;
	        }if(answer==2)
	        	flag=true;
		}
		System.out.println("\n\nFim do programa\nGOODBYE\n");
	}
}
