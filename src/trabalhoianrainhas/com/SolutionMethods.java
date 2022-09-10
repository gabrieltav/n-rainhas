package trabalhoianrainhas.com;

public class SolutionMethods {
	private int N;
	private int[][] ggrid;
	protected double inits;
	protected double time;
	protected double tttime;
	protected int loads;
	public double getInits() {
		return inits;
	}


	public void setInits(double inits) {
		this.inits = this.inits+inits;
	}


	public SolutionMethods(int NQ) {
		N=NQ;
		ggrid = new int[N][N];

		for(int i=0; i<N; i++) {
		    for(int j=0; j<N; j++) {
		    	ggrid[i][j] = 0;
		    }
		}
	}
	
	
	public  void hillclimb() {
		long startTime = System.nanoTime();//Variavel de inicialização de Tempo
		long estimatedTime = System.nanoTime() - startTime;
		Local grid = new Local(N);//Criamos grades
		double numofinits=1;
		double tt =0;
		
		for(int i=0; i<N; i++) {
		    for(int j=0; j<N; j++) {//Salvamos a grade para que ela também esteja disponível para o 2º algoritmo
		    	ggrid[i][j] = grid.getGridval(i, j);
		    }
		}
		
		System.out.println("\n\n\n\n");
		int h,h2;
		int[][] initgrid=new int[N][N];
		int[][] bestgrid=new int[N][N];
		
		for (int k=0;k<N;k++) {
			for(int l=0;l<N;l++) {//A grade é salva
				initgrid[k][l]=grid.getGridval(k, l);
			}
		}
		
		h=grid.totalCollisionsPaired(grid.getGrid()); //da grade original

    	System.out.println("\nEstes são os pares de colisão para a placa agora");
    	System.out.println("------------------------------------------");
		grid.printCollisionPairs();
		
		System.out.println("Total de Colisão:"+h);
		System.out.println("<---Processamento de tabuleiro de xadrez:--->");
		System.out.println("<----------------->");
		while(h!=0) {//Acreditamos que a melhor solução é aquela em que nenhuma rainha chama
			h2=grid.chooseQueen();//Chamar fog para selecionar rainhas e cria uma grade com n reduzido
			//Se terminarmos com repetições do mesmo ou para todos os reinados, não há solução melhor
			if(h2<h) {//Se h diminuiu à medida que n são calculados em série para cada rainha, então atingimos um mínimo local
				h=h2; //
				for (int k=0;k<N;k++) {
					for(int l=0;l<N;l++) {
						bestgrid[k][l]=grid.getGridval(k, l);//À medida que n fica menor, a melhor solução é salva. 
					}
				}
			
				
			}
			grid.setGridval(initgrid);//A grade retorna ao seu estado inicial e o algoritmo continua desde o início
			numofinits++;
		}
		//Uma solução foi encontrada
		setInits(numofinits);
		
		grid.printGrid(bestgrid); //Imprima a grade com a melhor solução

		estimatedTime = System.nanoTime() - startTime;
		
		
		tt=estimatedTime / Math.pow(10, 9);
		setTime(tt);
		System.out.println("Demorou " + tt + " segundos para terminar.");
		System.out.println("-------------------->Melhor heurística---->:"+h);
		System.out.println("------/(O tabuleiro de xadrez é limpo)/-----------");
		System.out.println("------------------------------------------");
		System.out.println("----------------Até mais--------------------\n");
	}
	
	

	public double getTime() {
		return time;
	}


	public void setTime(double time) {
		this.time = this.time+time;
	}


	public void constraintSAT() {
		int k=1;
		Constraint grid = new Constraint(N,ggrid);
		grid.printGrid();
		long startTime = System.nanoTime();
		grid.singleColumnTransformation();
		double estimatedTime = System.nanoTime() - startTime;

		System.out.println("Demorou " + estimatedTime / Math.pow(10, 9) + " segundos para inicializar a placa..");
		grid.printGrid();
		
		startTime = System.nanoTime();
		
		int[][] forceQueens = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if (i!=0)
					forceQueens[i][j] = 0;
				else
					forceQueens[i][j] = i+1;
			}
		}
		

		int[] result = new int[2];
		
		while (result[0]>=0) {
			grid.saveState();
			result = grid.nQueensProblem(forceQueens);
			
		
			if (result[0] == -1)
				System.out.println("Win.");
			if (result[0] == -2)
				System.out.println("Lose.");
		
			if (result[0] >= 0) {

				for(int i=0; i<N; i++) {
					forceQueens[i][result[0]] = 0;
				}
				
				//Mover rainha.
				forceQueens[result[1]][result[0]] = result[0]+1;
				
				//Desabilite esta linha para desempenho.
				//System.out.println("Mover rainha "+result[0]+" para fileira "+result[1]);
			}

			
			grid.loadState();
			k++;
			
		}
		setLoads(k);
		estimatedTime = (System.nanoTime() - startTime)/Math.pow(10, 9);
		setTttime(estimatedTime);
		System.out.println("Levou " + estimatedTime   + " segundos para terminar.");
	}


	public double getTttime() {
		return tttime;
	}


	public void setTttime(double tttime) {
		this.tttime =this.tttime+ tttime;
	}


	public int getLoads() {
		return loads;
	}


	public void setLoads(int loads) {
		this.loads =this.loads+ loads;
	}
		
}

