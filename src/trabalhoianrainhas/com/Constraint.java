package trabalhoianrainhas.com;

public class Constraint {

	private int[][] grid;
	private int[][] initialGrid;
	private int[][] collisionPairs;
	private int N;
	public static final boolean DEBUG_MODE = false;
	
	public Constraint(int N,int [][] ggrid) {
		this.grid = new int[N][N];
		this.initialGrid = new int[N][N];
		this.collisionPairs = new int[N][N];
		this.N = N;

		for(int i=0; i<N; i++) {
		    for(int j=0; j<N; j++) {
		    	grid[i][j] = 0;
		    	initialGrid[i][j] = 0;
		    }
		}
		
		for(int i=0; i<N; i++) {
		    for(int j=0; j<N; j++) {
		    	collisionPairs[i][j] = 0;
		    }
		}
		for(int i=0; i<N; i++) {
		    for(int j=0; j<N; j++) {
		    	grid[i][j] = ggrid[i][j];
		    }
		}
	}
	
	public int get(int x, int y) {
		return grid[x][y];
	}

	public void printGrid() {

    	System.out.println("\n\n\nEste é o tabuleiro de xadrez agora");
    	System.out.println("----------------------------------------\n");
		for(int i=0; i<N; i++) {
		    for(int j=0; j<N; j++) {
		    	char x = (grid[i][j]>0) ? 'R' : '-';
		    	System.out.print("| "+x+" ");
		    }
	    	System.out.println("|");
		}
		
	}
	
	public void printCollisionPairs() {

    	System.out.println("\n\n\n");
		for(int i=0; i<N; i++) {
		    for(int j=0; j<N; j++) {
		    	System.out.print("| "+collisionPairs[i][j]+" ");
		    }
	    	System.out.println("|");
		}
		
	}
	
	
	
	public int getCollisions(int x, int y) {
		int collisions = 0;

		for (int i=0; i<N; i++) {
			if (isQueen(x,i) && i!=y) {
				collisions++;
			}
		}
		
		for (int i=0; i<N; i++) {
			if (isQueen(i,y) && i!=x) {
				collisions++;
			}
		}
		
		int i,j;

		//Diagonal - Sudeste
		i=x+1;
		j=y+1;
		while (i<N && j<N) {
			if (isQueen(i,j))
				collisions++;
			i++;
			j++;
		}

		//Diagonal - Nordeste
		i=x-1;
		j=y+1;
		while (i>=0 && j<N) {
			if (isQueen(i,j))
				collisions++;
			i--;
			j++;
		}
		
		//Diagonal - Noroeste
		i=x-1;
		j=y-1;
		while (i>=0 && j>=0) {
			if (isQueen(i,j))
				collisions++;
			i--;
			j--;
		}
		
		//Diagonal - Sudoeste
		i=x+1;
		j=y-1;
		while (i<N && j>=0) {
			if (isQueen(i,j))
				collisions++;
			i++;
			j--;
		}
		
		return collisions;
	}
	
	public boolean isQueen (int x, int y) {
		return grid[x][y] > 0 ;
	}

	public int totalCollisions() {

		int collisions = 0;

		for(int i=0; i<N; i++) {
		    for(int j=0; j<N; j++) {
		    	if (isQueen(i,j)) {
		    		collisions += getCollisions(i,j);
		    	}
		    }
		}
		
		return collisions;
	}
	

	public void findCollisionPairs(int x, int y) {
		
		int queen = grid[x][y];
		int col = 1;

		for (int i=0; i<N; i++) {
			if (isQueen(x,i) && i!=y) {
				collisionPairs[queen-1][col] = grid[x][i];
				col++;
			}
		}
		
		for (int i=0; i<N; i++) {
			if (isQueen(i,y) && i!=x) {
				collisionPairs[queen-1][col] = grid[i][y];
				col++;
			}
		}
		
		int i,j;

		//Diagonal - Sudeste
		i=x+1;
		j=y+1;
		while (i<N && j<N) {
			if (isQueen(i,j)) {
				collisionPairs[queen-1][col] = grid[i][j];
				col++;
			}
			i++;
			j++;
		}

		//Diagonal - Nordeste
		i=x-1;
		j=y+1;
		while (i>=0 && j<N) {
			if (isQueen(i,j)) {
				collisionPairs[queen-1][col] = grid[i][j];
				col++;
			}
			i--;
			j++;
		}
		
		//Diagonal - Nordeste
		i=x-1;
		j=y-1;
		while (i>=0 && j>=0) {
			if (isQueen(i,j)) {
				collisionPairs[queen-1][col] = grid[i][j];
				col++;
			}
			i--;
			j--;
		}
		
		//Diagonal - Sudoeste
		i=x+1;
		j=y-1;
		while (i<N && j>=0) {
			if (isQueen(i,j)) {
				collisionPairs[queen-1][col] = grid[i][j];
				col++;
			}
			i++;
			j--;
		}
		
	}
	
	
	public int totalCollisionsPaired() {


		for(int i=0; i<N; i++) {
		    for(int j=0; j<N; j++) {
		    	if (isQueen(i,j)) {
		    		collisionPairs[  grid[i][j] - 1   ][0] = grid[i][j];

		    		findCollisionPairs(i, j);
		    	}
		    }
		}
		
		//printCollisionPairs();
		
		for(int i=0; i<N; i++) {
		    for(int j=1; j<N; j++) {
		    	if (collisionPairs[i][j] > 0) {
		    		
		    		int row = collisionPairs[i][j] - 1;
		    		for (int k = 1; k < N; k++ ) {
		    			if (collisionPairs[row][k] == collisionPairs[i][0]) {
		    				collisionPairs[row][k] = 0;
		    			}
		    		}
		    		
		    		
		    	}
		    }
		}
		
		//printCollisionPairs();

		
		int h = 0;
		
		for(int i=0; i<N; i++) {
		    for(int j=1; j<N; j++) {
		    	if (collisionPairs[i][j] > 0)
		    		h++;
		    }
		}
		
		
		
		return h;
	}
	
	public boolean allQueensSingle() {
	    for(int j=0; j<N; j++) {
	    	if (!isQueen(0,j))
	    		return false;
	    }
	    return true;
	}
	
	public void singleColumnTransformation() {
		int moves = 0;
		
		while (!allQueensSingle()) {
			
			int[] queensVisited = new int[N];
			for (int i=0; i<N; i++)
				queensVisited[i] = 0;
			
			
			int column = 0;
			for(int i=0; i<N; i++) {
			    for(int j=0; j<N; j++) {
			    	if (isQueen(i,j) && queensVisited[grid[i][j]-1]==0) {
			    		
			    		//visitou.
			    		queensVisited[grid[i][j]-1] = 1;
			    		
			    		//transfere de <j> para <coluna>
			    		
			    		if (j==column) {
			    			if (i==0) {
				    			//Fazer Nada.
			    			}
			    			else {
				    			if (i!=0) {
					    			grid[0][column] = grid[i][column];
					    			grid[i][column] = 0;
					    			moves++;
				    			}
			    			}
			    			column++;
			    		}
			    		
			    		else if (j>column) {
			    			
				    		//verifique se a rainha pode se mover horizontalmente
				    		boolean can = true;
				    		for (int k=j-1; k>=column; k--) {
				    			if (isQueen(i,k))
				    				can = false;
				    		}
				    		
				    		if (can) {
				    			grid[i][column] = grid[i][j];
				    			grid[i][j] = 0;
				    			moves++;
				    			
				    			if (DEBUG_MODE)
				    				printGrid();
				    			
				    			if (i!=0) {
					    			grid[0][column] = grid[i][column];
					    			grid[i][column] = 0;
					    			moves++;
				    			}
				    		}
				    		else {
				    			//Fazer Nada.
				    		}
			    			column++;
	
		    			}
			    		else {
	
				    		//verifique se a rainha pode se mover horizontalmente
				    		boolean can = true;
				    		for (int k=j+1; k<=column; k++) {
				    			if (isQueen(i,k))
				    				can = false;
				    		}
				    		
				    		if (can) {
				    			grid[i][column] = grid[i][j];
				    			grid[i][j] = 0;
				    			moves++;

				    			if (DEBUG_MODE)
				    				printGrid();
	
				    			if (i!=0) {
					    			grid[0][column] = grid[i][column];
					    			grid[i][column] = 0;
					    			moves++;
				    			}
				    		}
				    		else {
				    			//fazer Nada.
				    		}
			    			column++;
			    		}
			    		
			    		
			    		

		    			if (DEBUG_MODE)
		    				printGrid();
			    		
			    		
			    		
			    	}
			    }
			}
			
			
		}
	
		System.out.println("Foram necessarios "+moves+" movimentos para inicializar o tabuleiro de xadrez.");
	}

	public void saveState() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				initialGrid[i][j] = grid[i][j];
			}
		}
	}
	
	public void loadState() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				grid[i][j] = initialGrid[i][j];
			}
		}
	}
	
	
	public int[] nQueensProblem(int[][] forceQueens) {
		
		int constraints[][] = new int[N][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				constraints[i][j] = 0;
			}
		}


		if (DEBUG_MODE)
			printConstraints(constraints);

		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				grid[i][j] = forceQueens[i][j];
			}
		}
		
		for (int j=0; j<N; j++) {

			
			int queenRow = 0;
			while (!isQueen(queenRow,j))
				queenRow++;
			//Encontramos nossa Rainha.
			
			
			if (constraints[queenRow][j]<0) {
				//Isso significa que estamos ameaçados.

				boolean isThreatened = constraints[queenRow][j]<0;
				//Vamos agora encontrar um local seguro para nossa rainha.
				int i = -1;
				while (isThreatened && i<N-1) {
					i++;
					isThreatened = constraints[i][j]<0;
				}
				
				if (isThreatened) {
					//Esta rainha não tem movimentos disponíveis.
					//Vá a rainha anterior. 
					//Se ela tiver mais de 1 movimento disponível, então a escolhemos para prosseguir.
					//Caso contrário, vamos a rainha antes da anterior.
					//SE estamos sem rainhas para mover, GAME OVER.
					int[] res = new int[2];
					int availableMoves = 0;
					int firstMove = 0;
					for (int k=j-1; k>=0; k--) {
						
						availableMoves = 0;
						firstMove = 0;
						
						//Encontre a Rainha.
						int q = 0;
						while (!isQueen(q,k))
							q++;
						
						//Calcule quantos movimentos ela pode fazer.
						for (int z=q+1; z<N; z++) {
							if (constraints[z][k]==0) {
								availableMoves++;
								if (availableMoves == 1)
									firstMove = z;
							}
						}
						
						int limit = (N > 25) ? 2 : 1;
						if (N > 50) limit = 3;
						
						//Se ela tiver pelo menos dois movimentos disponíveis, nós a usamos.
						if (availableMoves > limit) {
							//Return which queen to move.
							res[0] = k;
							//Return where you want to move her.
							res[1] = firstMove;
							return res;
						}
						//System.out.println(availableMoves);
					}
					
					int[] lose = new int[2];
					lose[0] = -2;
					lose[1] = -2;
					return lose;
				}
				else {
					//Isso significa que encontramos um local seguro.
					
					if (i == queenRow) {
						//Nós ficamos em nosso lugar seguro.
		    			if (DEBUG_MODE)
		    				System.out.println("A rainha está segura aqui.");
					}
					else {
						//Nós nos movemos para o local seguro.
		    			if (DEBUG_MODE)
		    				System.out.println("The queen will move.");
						grid[i][j] = grid[queenRow][j];
						grid[queenRow][j] = 0;
						queenRow = i;
					}
				}
			}
			
			//Agora atualizamos as restrições.

			if (j == N-1) {
				//We won.
				printConstraints(constraints);
				int[] win = new int[2];
				win[0] = -1;
				win[1] = -1;
				return win;
			}
			
			//Horizontal
			for (int k=j+1; k<N; k++) {
				constraints[queenRow][k] = -1;
			}

			if (DEBUG_MODE)
				printConstraints(constraints);

			//Nordeste
			for (int i=queenRow-1, k = j+1; i>=0 && k<N; i--,k++) {
				constraints[i][k] = -1;
			}

			if (DEBUG_MODE)
				printConstraints(constraints);
			
			
			//Sudeste
			for (int i=queenRow+1, k = j+1; i<N && k<N; i++,k++) {
				constraints[i][k] = -1;
			}
			
			
			
			
			
			

			if (DEBUG_MODE)
				printConstraints(constraints);
			
			
		}

		int[] error = new int[2];
		error[0] = -2;
		error[1] = -2;
		return error;
		
		
	}
	

	public void printConstraints(int[][] constraints) {

    	System.out.println("\n\n\nEste é o tabuleiro de xadrez agora");
    	System.out.println("----------------------------------------\n");
		for(int i=0; i<N; i++) {
		    for(int j=0; j<N; j++) {
		    	char x;
		    	if (grid[i][j]>0 && constraints[i][j]<0) {
		    		x = '+';
		    	}
		    	else if (grid[i][j]>0) {
		    		x = 'Q';
		    	}
		    	else if (constraints[i][j]==0) {
		    		x = '-';
		    	}
		    	else {
		    		x = '*';
		    	}
		    	
		    	System.out.print("| "+x+" ");
		    }
	    	System.out.println("|");
		}
		
	}
}

