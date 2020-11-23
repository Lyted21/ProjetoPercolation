import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.QuickUnionUF;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.UF;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private boolean[][] matriz;
	public WeightedQuickUnionUF unir;
	private int top;
	private int bottom;
	private int tamanho;
	private int numOpenSites;
	private int linha;
	private int coluna;
	
	public Percolation(int n) {
		if (n <= 0) throw new java.lang.IllegalArgumentException("Illegal Argument");
		
		this.top = 1;
		
		matriz = new boolean[n+1][n+1];
		
		unir = new WeightedQuickUnionUF(n*n + 2);
		
		for(int i = 0; i < n; i++)
			
			for(int j = 0; j < n; j++)
				
				matriz[i][j] = false;
		
		bottom = n*n + 1;
		
		tamanho = n+1;
	}
	
	public boolean isOpen(int linha, int coluna) {
		
		if(linha < tamanho && coluna < tamanho) {
			
		return matriz[linha][coluna]; }
		
		else return false;
		
	}
	
	public void open(int x, int y) {
		this.linha = x;
		this.coluna = y;
		
		matriz[linha][coluna] = true;
		
		if(linha == 0) 
			
			unir.union(getSquarePos(linha, coluna), top);
		
		if(linha == tamanho) 
			
			unir.union(getSquarePos(linha, coluna), bottom);
		
		if(linha > 1 && isOpen(linha-1, coluna))
			
			unir.union(getSquarePos(linha, coluna), getSquarePos(linha-1, coluna) );
		
		if(linha < tamanho && isOpen(linha+1, coluna))
			
			unir.union(getSquarePos(linha, coluna), getSquarePos(linha+1, coluna));
		
		if(coluna > 1 && isOpen(linha, coluna-1))
			
			unir.union(getSquarePos(linha, coluna), getSquarePos(linha, coluna-1));
		
		if(coluna < tamanho && isOpen(linha, coluna+1))
			
			unir.union(getSquarePos(linha, coluna), getSquarePos(linha, coluna+1));	
		
		numOpenSites++;
		
	}
	
	private int getSquarePos(int i, int j) {
		
        return Math.abs(tamanho * (i - 1) + j) ;
        
    }
	
	public int numberOfOpenSites() {
		
		return numOpenSites; 
		
	}
	
	private boolean isSiteFull(int linha, int coluna) {
		
		if (linha <= 0 || linha > tamanho || coluna <= 0 || coluna > tamanho) throw new IndexOutOfBoundsException(); 
		
		return unir.connected(0, getSquarePos(linha, coluna));
		
	}
	
    public boolean percolates() {
    	
    	boolean sim;
    	
            if (tamanho == 1) {
            	
                sim = matriz[0][0];
                
            }
        
            else {
            	
            	sim = unir.connected(1, getSquarePos(linha, coluna));
                
                
            }
            
            return sim;
            
        }
    
    public static void main(String[] args) {
    	  
    	int t = 5;
    	int tt = 20;
    	Percolation p = new Percolation(t);
    	
    	  	
    	 for (int i = 0; i < tt; i++) {
    	    	
    	        int row =  StdRandom.uniform(1, t);
    	        
    	        int col = StdRandom.uniform(1, t);
    	        
    	        p.open(row, col);
    	        
    	    } 	
    	
    	System.out.println(p.percolates());
    	
	}
	
	@Test

	public void testPercolation() {
		assertEquals(true, percolates());
	}

}
