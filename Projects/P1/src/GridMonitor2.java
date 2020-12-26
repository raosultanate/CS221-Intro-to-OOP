/**
 * The programs helps determine which cell is in the danger of exploding
 * @author qurnainrao
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GridMonitor2 implements GridMonitorInterface{
private double[][] grid;
private double[][] enGrid;
private double[][] sum;
private double[][] average;
private double[][] enAverage;
private double[][] delta;
private double[][] max;
private double[][] min;
private double[][] negDelta;
private boolean[][] enDelta;
private boolean[][] result;
private double[][] enSum;
String[] dimension;

/**
 * Initializes variables and scans the file and creates the grid with the table data
 * @param filename
 * @throws FileNotFoundException
 */
	public GridMonitor2(String filename) throws FileNotFoundException
	{
		
		File file = new File(filename);
		Scanner scan = new Scanner(file);
	
		dimension = new String[2];
		
		String line1 = scan.nextLine();
		Scanner sc = new Scanner(line1);
		
		for(int i = 0; i<2;i++)
		{
			
			dimension[i] = sc.next();
			
		}	
			
	
		int row = Integer.parseInt(dimension[0]);
		int column = Integer.parseInt(dimension[1]);
		
		grid = new double[row][column];
		enGrid = new double[row][column];
		sum= new double[row][column];
		average= new double[row][column];
		delta =  new double[row][column];
		min =  new double[row][column];
		max=  new double[row][column];
		result=  new boolean[row][column];
		negDelta = new double[row][column];
		enSum = new double[row][column];
		enAverage= new double[row][column];
		enDelta = new boolean[row][column];
		
		
		for(int i = 0; i<grid.length;i++)
		{
			for(int j = 0; j<grid[i].length;j++)
			{
					
					String cell = scan.next();
					grid[i][j]=Double.parseDouble(cell);
			}
		}
		
		for(int i = 0; i<grid.length;i++)
		{
			for(int j =0; j<grid[i].length; j++)
			{
				if(grid.length==1 && grid[i].length==1)
				{
					sum[i][j] = grid[i][j]+grid[i][j]+grid[i][j]+grid[i][j];
				}	
				else {
						
					if(i==0 && j==0)
							{
							sum[i][j] = grid[i][j]+grid[i][j]+grid[i+1][j]+grid[i][j+1];
							}
					
					
					if (i==0 && j==grid[i].length-1)
					{
						sum[i][j]= grid[i][j]+grid[i][j]+grid[i+1][j]+grid[i][j-1];
					}
					if (i==0 && j!=0 && j!=grid[i].length-1)
					{
						sum[i][j]= grid[i][j]+grid[i+1][j]+grid[i][j-1]+grid[i][j+1];
					}
					if(j==0)
					{
						if(i==grid.length-1)
						{
							sum[i][j]= grid[i][j]+grid[i][j]+grid[i-1][j]+grid[i][j+1];
						}
						if(i!=0 && i!=grid.length-1)
						{
							sum[i][j]= grid[i][j]+grid[i][j+1]+grid[i-1][j]+grid[i+1][j];
						}
					}
					if(j==grid[i].length-1)
					{
						if(i==grid.length-1)
						{
							sum[i][j]= grid[i][j]+grid[i][j]+grid[i-1][j]+grid[i][j-1];
						}
						if(i!=0 && i!= grid.length-1)
						{
						sum[i][j]= grid[i][j]+grid[i][j-1]+grid[i-1][j]+grid[i+1][j];
						}
					}
					if(i==grid.length-1 && j!= 0 && j!=grid[i].length-1)
					{
						sum[i][j]= grid[i][j]+grid[i-1][j]+grid[i][j-1]+grid[i][j+1];
					}
					if(i!=grid.length-1 && j!= 0 && j!=grid[i].length-1 && i!=0)
					{
						sum[i][j]= grid[i+1][j]+grid[i-1][j]+grid[i][j-1]+grid[i][j+1];
					}
				}
			}
			
		}
		
		for(int i = 0; i<grid.length;i++)
		{
			for(int j =0; j<grid[i].length; j++)
			{
				average[i][j]= sum[i][j]/4;
			}
		}
		for(int i = 0; i<grid.length;i++)
		{
			for(int j =0; j<grid[i].length; j++)
			{
				delta[i][j]= average[i][j]/2;
				negDelta[i][j]=Math.abs(delta[i][j]);
			}
		}
		
		for(int i = 0; i<grid.length;i++)
		{
			for(int j =0; j<grid[i].length; j++)
			{
				min[i][j] = negDelta[i][j];
				max[i][j] = negDelta[i][j]+Math.abs(average[i][j]);
				
			}
		}
		
	}
	
	/**
	 * returns grid in string format through overriding the toString
	 * @return strgrid
	 */
	public String toString()
	{
		String strgrid = grid.toString();
		return strgrid;
	}
	/**
	 *  returns the grid with proper dimension
	 * @return enGrid 
	 */
	@Override
	public double[][] getBaseGrid() {
		// TODO Auto-generated method stub
		for(int i = 0; i<grid.length;i++)
		{
			for(int j =0; j<grid[i].length; j++)
			{
				enGrid[i][j] = grid[i][j]; 
			}
		}
		
		return enGrid;
	}
	/**
	 *  returns the sum of surrounding cells in a protected form
	 * @return enSum which protects the data from being changed in another class
	 */
	@Override
	public double[][] getSurroundingSumGrid() {
		// TODO Auto-generated method stub
		for(int i = 0; i<grid.length;i++)
		{
			for(int j =0; j<grid[i].length; j++)
			{
				enSum[i][j] = sum[i][j]; 
			}
		}
		return enSum;
	}
	/**
	 * returns the average of the sum of surrounding cells
	 * @return enAverage by dividing enSum by four.
	 */
	@Override
	public double[][] getSurroundingAvgGrid() {
		// TODO Auto-generated method stub
		for(int i = 0; i<grid.length;i++)
		{
			for(int j =0; j<grid[i].length; j++)
			{
				enAverage[i][j] = average[i][j]; 
			}
		}
		return enAverage;
	}
	
	/**
	 * Returns the delta of the average of the sum of surrounding cells and makes sure it is positive
	 * @return negDelta which is calculated by dividing enAverage by two
	 */
	@Override
	public double[][] getDeltaGrid() {
		// TODO Auto-generated method stub
		
		return negDelta;
	}
	
	/**
	 * Returns whether the cell is going to explode or not.
	 * @return results; true or false.
	 */
	@Override
	public boolean[][] getDangerGrid() {
		// TODO Auto-generated method stub
		for(int i = 0; i<grid.length;i++)
		{
			for(int j =0; j<grid[i].length; j++)
			{
				if(Math.abs(grid[i][j])>= min[i][j] && Math.abs(grid[i][j])<= max[i][j])
				{
					result[i][j] = false;
				}
				else
				{
					result[i][j]= true;
				}
				enDelta[i][j] = result[i][j]; 
			}
		}
		return result;
	}

}
