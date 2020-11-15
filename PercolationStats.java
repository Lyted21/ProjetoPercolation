
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
	
	private double[] trialsResults;
    private int trials;
    private int tamanho;
    private int count;
    private Percolation percolation; 
    
    public PercolationStats(int n, int trials) {
    	
    	this.count = 0;
    	
    	tamanho = n+1;
    	
    	this.trials = trials;
    	
    	if(tamanho < 1 || trials < 1 )
    		
    		throw new IllegalArgumentException("Tamanho da grelha e de trials tem de ser mais que 0");
    	
    	trialsResults = new double[trials];
    
    for (int i = 0; i < trials; i++) {
    	
    	percolation = new Percolation(tamanho);
    	
        int row =  StdRandom.uniform(1, tamanho-1);
        
        int col = StdRandom.uniform(1, tamanho-1);
        
        percolation.open(row, col);
        
        for (int a = 0; a < trials; a++) {
       
        count = percolation.numberOfOpenSites(); }
        
        trialsResults[i] = (double) count / (double) (tamanho * tamanho);
        
        
        
    }
    
}
    
    public double mean() {
    	
        return StdStats.mean(trialsResults);
        
    }
    
    public double stddev() {
    	
        return StdStats.stddev(trialsResults);
        
    }
    
    public double confidenceLo() {
    	
        return mean() - (1.96 * stddev() / Math.sqrt(trialsResults.length));
        
    }
    
    public double confidenceHi() {
    	
        return mean() + (1.96 * stddev() / Math.sqrt(trialsResults.length));
        
    }
    
    public static void main(String[] args) {
    	
        int N = Integer.parseInt(args[0]);
        
        int T = Integer.parseInt(args[1]);
        
       PercolationStats percolationStats = new PercolationStats(N, T);
        
       System.out.printf("mean                     = %f\n", percolationStats.mean());   
       
       System.out.printf("stddev                   = %f\n", percolationStats.stddev());
      
       System.out.printf("95%% confidence interval = %f, %f\n", percolationStats.confidenceLo(), percolationStats.confidenceHi());

       System.out.println("------");
        
        System.out.println("Percolates?" + " " + percolationStats.percolation.percolates());
        
    }

}
