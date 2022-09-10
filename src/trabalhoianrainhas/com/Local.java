package trabalhoianrainhas.com;

import java.util.Random;

public class Local {
	private int[][] grid;
	private int[][] collisionPairs;

	private int numberofQueens;
	
	public Local(int N) {		//Construtor para criar grade
		this.grid = new int[N][N];
		this.collisionPairs = new int[N][N]; 	//Gamão contendo conjuntos do jogador adversário
		numberofQueens = N;


		for(int i=0; i<numberofQueens; i++) {
		    for(int j=0; j<numberofQueens; j++) {	//Inicialize a grade para 0
		    	grid[i][j] = 0;
		    }
		}
		
		for(int i=0; i<numberofQueens; i++) {
		    for(int j=0; j<numberofQueens; j++) { //Inicialize o gamão com colisões em 0
		    	collisionPairs[i][j] = 0;		//Queremos salvar para todo o seu reinado seus inimigos
		    }									
		}
		generateQueens();	//Chamada para colocar reis em posições aleatórias
		printGrid(grid);

	}
	
	public int[][] getGrid() {		
		return grid;
	}
	public int getGridval(int x,int y) {
		return grid[x][y];
	}

	public void generateQueens() {
		int id = 1;		// A rainha é representada por um n ascendente
		Random rand = new Random();	//A colocação da rainha é aleatória
		
		for(int i=0; i<numberofQueens; i++) {
			int col = rand.nextInt(numberofQueens-1); //Posição da coluna aleatória
			int row = rand.nextInt(numberofQueens-1);//Posição aleatória na linha

	    	while (getGridval(row,col)>0) {	//Verifique se já existe uma rainha na posição π para a nova entrada da rainha
				col = rand.nextInt(numberofQueens-1);	//Nova posição aleatória
				row = rand.nextInt(numberofQueens-1);
				}
	    	
	    	grid[row][col] = id; //Para saber que é uma rainha, colocamos o id na posição na grade e aumentamos
	    	id++;
		}
	}
	
	public void printGrid(int[][] ggrid) {

    	System.out.println("\n Este é o tabuleiro de xadrez agora");
    	System.out.println("----------------------------------------\n");
		for(int i=0; i<numberofQueens; i++) {
		    for(int j=0; j<numberofQueens; j++) {	//Se este for um valor positivo, qualquer posição é representada por uma rainha com R
		    	char x =ggrid[i][j]>0 ? 'R' : '-';
		    	System.out.print("| "+x+" ");
		    }
	    	System.out.println("|");
		}
		
	}
	

	public void findCollisionPairs(int x, int y, int[][] tempGrid) {//Encontra os conflitos de uma rainha para determinado grau
		int queen = tempGrid[x][y];
		int col = 1;	
		
		for (int i=0; i<numberofQueens; i++) {	//encontre as colisões p estão na mesma linha
			if (tempGrid[x][i]>0 && i!=y) {
				collisionPairs[queen-1][col] = tempGrid[x][i];//As correlações são armazenadas na tabela
				col++;}}			//1 encontra-se no 1º parágrafo do 1º parágrafo e o resto nas teses seguintes no 1º parágrafo, etc.
		
		for (int i=0; i<numberofQueens; i++) {			//encontre as colisões p estão na mesma coluna
			if (tempGrid[i][y]>0 && i!=x) {
				collisionPairs[queen-1][col] = tempGrid[i][y];//Correlações envolvendo inimigos também são armazenadas na mesma coluna
				col++;
			}
		}
		
		int i,j;
		i=x+1;
		j=y+1;		//Diagonal - Sudeste
		while (i<numberofQueens && j<numberofQueens) { //Da mesma forma para todos os inimigos diagonais
			if (tempGrid[i][j]>0) {
				collisionPairs[queen-1][col] = tempGrid[i][j];
				col++;}
			i++;
			j++;}

		
		i=x-1;
		j=y+1;//Diagonal - Nordeste
		while (i>=0 && j<numberofQueens) {
			if (tempGrid[i][j]>0) {
				collisionPairs[queen-1][col] = tempGrid[i][j];
				col++;}
			i--;
			j++;}
		
		i=x-1;
		j=y-1;//DIAGONIA - NOROESTE
		while (i>=0 && j>=0) {
			if (tempGrid[i][j]>0) {
				collisionPairs[queen-1][col] = tempGrid[i][j];
				col++;}
			i--;
			j--;}
		
		i=x+1;
		j=y-1;//Diagonal - Sudoeste
		while (i<numberofQueens && j>=0) {
			if (tempGrid[i][j]>0) {
				collisionPairs[queen-1][col] = tempGrid[i][j];
				col++;}
			i++;
			j--;}
		}
	
	
	public int totalCollisionsPaired(int[][] tempGrid) {//Encontra o número de colisões da tabela de pares de colisões
		for(int i=0;i<numberofQueens;i++) {
			for (int j=0;j<numberofQueens;j++){
				collisionPairs[i][j]=0;
			}
		}
		
		for(int i=0; i<numberofQueens; i++) {		
		    for(int j=0; j<numberofQueens; j++) {
		    	if (tempGrid[i][j] > 0) {//Se ela é uma rainha
		    		collisionPairs[  tempGrid[i][j] - 1   ][0] = tempGrid[i][j];//Lugares em 1h sthlh se kathe grammh mia vasillises

		    		findCollisionPairs(i, j, tempGrid);	}}}//Chame o phystian que encontra os inimigos para cada rainha
		for(int i=0; i<numberofQueens; i++) {
		    for(int j=1; j<numberofQueens; j++) {
		    	if (collisionPairs[i][j] > 0) {	//Eu quero que a proporção 1-5, 5-1 exista uma vez
		    									//Então, uma vez que a removemos do adesivo
		    		int row = collisionPairs[i][j] - 1;
		    		for (int k = 1; k < numberofQueens; k++ ) {	
		    			if (collisionPairs[row][k] == collisionPairs[i][0]) {//Verificando se o relacionamento existe 2 fores
		    				collisionPairs[row][k] = 0;}}}}}		//Demoramos 2 horas para aparecer (No último reino não há conflitos)
		
		int h = 0;	
		
		for(int i=0; i<numberofQueens; i++) {
		    for(int j=1; j<numberofQueens; j++) {//Cálculo de pares de colisão
		    	if (collisionPairs[i][j] > 0)	//Hackeamos os valores da tabela de pares adjacentes que não são 0
		    		h++;
		    }
		}

		return h;
	}
	
	public void printCollisionPairs() {

    	System.out.println("\n");
		for(int i=0; i<numberofQueens; i++) {		//Imprima a tabela de pares de colisão. Ela estará contida no relatório
		    for(int j=0; j<numberofQueens; j++) {
		    	System.out.print("| "+collisionPairs[i][j]+" ");
		    }
	    	System.out.println("|");
		}
		
	}
	
	

	public int[] findAvailableMoves(int queen) {//Descoberta de neblina para uma rainha da posição de grade disponível em cada direção
		
		int x;
		int y;
		int thesia = 0,thesib = 0;
        
        		for (int i=0;i<numberofQueens;i++){
			for(int j=0;j<numberofQueens;j++){
				if(grid[i][j]==queen){
				 thesia=i;	//
				 thesib=j;
				 break;}}}
		x=thesia;
		y=thesib;
		int minpositionx=0;		//Variável para a gama de assentos disponíveis para a rainha na fila
		int minpositiony = 0;	//Variável para o intervalo de posições disponíveis para a rainha em uma coluna
		int maxpositiony=0;
		int maxpositionx=0;
		
		while(x>=0)//Encontra a menor posição que a rainha pode assumir na linha em particular
		{
			x--;
			if (x<0) {minpositionx=0;break;}
			if (getGridval(x,y)==0)  minpositionx=x;
			else {minpositionx=x+1;	break;}//Se houver uma rainha bloqueando a passagem, a menor posição é uma posição antes dessa rainha
		}//Adicionamos 1 gia name paroume ekeinh ti thesi
		//Retornamos o peão para a posição t para encontrar a maior posição que ele pode ocupar
		x=thesia;
		while(x<=numberofQueens-1)//Ele encontra a posição mais alta que a rainha pode assumir na linha privada
		{		
			x++;
			if (x==numberofQueens) {maxpositionx=numberofQueens-1;break;}
			if (getGridval(x,y)==0)  maxpositionx=x;
			else {maxpositionx=x-1;	break;}	//Se houver uma rainha bloqueando a passagem, a maior posição é uma posição antes dessa rainha
		}//Subtraímos 1 para a lei que obtemos daqui
		x=thesia;//Nós redefinimos a posição

		//gia sthles 
		while(y>=0)//Da mesma forma, encontramos o ponto mínimo para a coluna onde a rainha é encontrada 
		{
			y--;
			if (y<0) {minpositiony=0;break;}
			if (getGridval(x,y)==0)  minpositiony=y;
			else {minpositiony=y+1;	break;}		
		}
		y=thesib;
		
		while(y<=numberofQueens-1)//O ponto máximo que a rainha pode obter
		{ 
			y++;
			if (y==numberofQueens) {maxpositiony=numberofQueens-1;break;}
			if (getGridval(x,y)==0)  maxpositiony=y;
			else {maxpositiony=y-1;	break;}		    
		}
		y=thesib;
		
		//Para os elementos diagonais, usamos variáveis ​​para saber quantos passos para
		// mover os ponteiros de coluna e linha para encontrar a localização
		int southeast = 0;	//Sudeste
		while(x<numberofQueens-1 && y<numberofQueens-1) {
			y++;x++;
			if (getGridval(x,y)==0)  
				southeast++;
			else			
				break;}
	
		//As variáveis ​​são sudeste, nordeste, sudoeste, nordeste
		x=thesia;y=thesib;int northeast = 0;
		while(x>0 && y<numberofQueens-1) {//Nordeste
			y++;x--;
			if (getGridval(x,y)==0)  
				northeast++;
			else			
				break;}

		
		x=thesia;y=thesib;int northwest = 0;
		while(x>0 && y>0) {//Noroeste
			y--;x--;
			if (getGridval(x,y)==0)  
				northwest++;
			else			
				break;
		   	}
		
		
		x=thesia;y=thesib;int southwest = 0;
		while(x<numberofQueens-1 && y>0) {//Sudoeste
			y--;x++;
			if (getGridval(x,y)==0)  
				southwest++;
			else			
				break;
		}
		
		int[] availableSpace = {minpositionx, maxpositionx, minpositiony, maxpositiony, thesia, thesib, northeast, northwest, southeast, southwest};
		return availableSpace;
	
	}
	
	
	public int successorFunc(int queen) {//Nevoeiro computando a heurística após um movimento melhor
		
		int[][] tempGrid = new int[numberofQueens][numberofQueens];//Usamos uma grade sobressalente para posicionar o peão e verificar a posição do novo h.
		for (int i=0; i<numberofQueens; i++) {
			for (int j=0; j<numberofQueens; j++) {
				tempGrid[i][j] = grid[i][j];}}
		
		for(int i=0; i<numberofQueens; i++) {
		    for(int o=0; o<numberofQueens; o++) {   	//Inicialize para o par de colisão afou alazei para a grade
		    		collisionPairs[i][o] = 0;	
		    }
		}
		
			
			
		int[] space = new int[10];//Variáveis ​​com as informações que precisamos para os intervalos de assentos disponíveis
		space = findAvailableMoves(queen);
		int minpositionx = space[0];
		int maxpositionx = space[1];
		int minpositiony = space[2];
		int maxpositiony = space[3];
		int queenX = space[4]; //A linha p é onde o peão está
		int queenY = space[5];//A coluna p é o peão
		int northEastMoves = space[6];
		int northWestMoves = space[7];
		int southEastMoves = space[8];
		int southWestMoves = space[9];
		int minH = numberofQueens*numberofQueens + numberofQueens;//Usamos limites para encontrar a melhor posição para uma rainha
		int bestPositionX = numberofQueens;
		int bestPositionY = numberofQueens;
		
		
		//Para todas as posições disponíveis que uma rainha pode ocupar, aquela com o menor n é pesquisada e a grade é atualizada
		for (int j=minpositiony; j<=maxpositiony; j++) { //Alterar para todas as posições de linha disponíveis
			for (int k=0; k<numberofQueens; k++) {
				for (int l=0; l<numberofQueens; l++) {//Reconstruímos a grade em temp para fazer edições
					tempGrid[k][l] = grid[k][l];}
			}
			tempGrid[queenX][queenY] = 0;	//A posição antiga torna-se 0. Portanto, a rainha não existe em sua posição antiga
			tempGrid[queenX][j] = queen;	//Nós nos sentamos na rainha
			int h = totalCollisionsPaired(tempGrid);

			
			if (h < minH) {//Se der menor, mantemos a posição do campo e a heurística
				minH = h;
				bestPositionX = queenX;
				bestPositionY = j;
			}
		}
		
		for (int i=minpositionx; i<=maxpositionx; i++) {//Alterar a posição do peão para as posições da coluna
			
			for (int k=0; k<numberofQueens; k++) {
				for (int l=0; l<numberofQueens; l++) {//Reconstruímos a grade em temp para fazer edições
					tempGrid[k][l] = grid[k][l];}
			}
			tempGrid[queenX][queenY] = 0;
			tempGrid[i][queenY] = queen;//O processo para a heurística é repetido
			int h = totalCollisionsPaired(tempGrid);

			//E a melhor posição é salva
			
			if (h < minH) {
				minH = h;
				bestPositionX = i;
				bestPositionY = queenY;
			}
		}
		//Da mesma forma para todas as posições diagonais o mesmo procedimento
		//Cada saída é atualizada no backup da grade
		//A antiga posição da rainha está mudando
		//Coloque a rainha na nova posição
		//Calculado para heurística de deslocamento de grade
		//Se for menor, a posição é salva
		for (int i=1; i<=northEastMoves; i++) {
			for (int k=0; k<numberofQueens; k++) {
				for (int l=0; l<numberofQueens; l++) {//Reconstruímos a grade em temp para fazer edições
					tempGrid[k][l] = grid[k][l];}
			}
			tempGrid[queenX][queenY] = 0;
			tempGrid[queenX - i][queenY + i] = queen;
			int h = totalCollisionsPaired(tempGrid);

			if (h <= minH) {
				minH = h;
				bestPositionX = queenX - i;
				bestPositionY = queenY + i;
			}
		}
		for (int i=1; i<=northWestMoves; i++) {
			for (int k=0; k<numberofQueens; k++) {
				for (int l=0; l<numberofQueens; l++) {//Reconstruímos a grade em temp para fazer edições
					tempGrid[k][l] = grid[k][l];}
			}
			tempGrid[queenX][queenY] = 0;
			tempGrid[queenX - i][queenY - i] = queen;
			int h = totalCollisionsPaired(tempGrid);

			if (h <= minH) {
				minH = h;
				bestPositionX = queenX - i;
				bestPositionY = queenY - i;
			}
		}
		for (int i=1; i<=southEastMoves; i++) {
			for (int k=0; k<numberofQueens; k++) {
				for (int l=0; l<numberofQueens; l++) {//Reconstruímos a grade em temp para fazer edições
					tempGrid[k][l] = grid[k][l];}
			}
			tempGrid[queenX + i][queenY + i] = queen;
			tempGrid[queenX][queenY] = 0;
			int h = totalCollisionsPaired(tempGrid);

			if (h <= minH) {
				minH = h;
				bestPositionX = queenX + i;
				bestPositionY = queenY + i;
			}
		}
		for (int i=1; i<=southWestMoves; i++) {
			for (int k=0; k<numberofQueens; k++) {
				for (int l=0; l<numberofQueens; l++) {//Reconstruímos a grade em temp para fazer edições
					tempGrid[k][l] = grid[k][l];}
			}
			tempGrid[queenX + i][queenY - i] = queen;
			tempGrid[queenX][queenY] = 0;
			int h = totalCollisionsPaired(tempGrid);

			if (h <= minH) {
				
				minH = h;
				bestPositionX = queenX + i;
				bestPositionY = queenY - i;
			}
		}
		grid[queenX][queenY] = 0;
		grid[bestPositionX][bestPositionY] = queen;//Finalmente, a grade muda com a melhor posição calculada
		int h = totalCollisionsPaired(grid);//Retorno n da nova grade

		return h;		
		
		
		
	}
	
	public void setGridval(int [][] ggrid) {//O regulador para a preparação da rede apo allo
		for (int k=0;k<numberofQueens;k++) {
			for(int l=0;l<numberofQueens;l++) {
				grid[k][l]=ggrid[k][l];
			}
		}
	}

	public int chooseQueen() {	//Nevoeiro que escolhe a rainha para se mover
		Random rn=new Random();
		int h1=totalCollisionsPaired(grid);	//Calcula o n inicial
		int queen=0;
		int limit=-1;//Achamos que quando nos aproximamos de uma solução podemos estar em
		//extremos locais e queremos ver se existe uma solução melhor
		boolean flag=false;
		if (numberofQueens>30) {
			limit=5;
		}//Duas modificações do limite para o propósito acima
		else limit=3;
		while (h1!=0) {//Temos uma solução quando η=0
			if(h1>=limit){
				queen=rn.nextInt(numberofQueens)+1; //Para os grandes escolhemos aleatoriamente
				h1=successorFunc(queen);
			}
			else {
				int h=totalCollisionsPaired(grid);
				for (int i=0;i<numberofQueens;i++) {
					queen=i;//Quando atingimos o limite, verificamos em série as rédeas para ver se atingimos o limite
					h1=successorFunc(queen+1);
					
					if (h1<h ){ //Se ela ficar menor, não olhe para o resto dela
						flag=true;
						break;}
					else
						flag=false;//Considere que todos eles não têm tráfego melhor
				}
				if (flag==false)//Mínimo local se todos os reinados foram esgotados e nenhum oferece uma solução melhor
					break;
			}		
		}
		return h1;
		
	}
}

