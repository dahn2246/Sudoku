import java.util.*;

/*
 * Sudoku Solver Version 1
 * Solves Sudoku puzzle through recursion and backtracking. 
 * 
 */

public class Sudoku 
{
	//Hard Puzzle
	final static int[][] puzzle1 = new int[][]
	{
			{9,6,4,3,0,7,0,0,0},    
			{0,0,0,5,0,0,6,0,7},
			{0,7,0,8,0,1,3,4,0},
			{8,0,1,9,0,0,0,0,0},
			{4,0,0,2,0,6,0,0,8},
			{0,0,0,0,0,8,7,0,1},
			{0,2,6,7,0,3,0,8,0},
			{5,0,9,0,0,2,0,0,0},
			{0,0,0,1,0,4,2,9,6} 
	};
	
	//Hard Puzzle
	final static int[][] puzzle2 = new int[][]
	{
		{0,0,2,0,0,0,0,0,8},    
		{0,0,0,0,0,8,0,0,1},
		{5,0,0,7,3,0,4,0,0},
		{0,0,0,0,2,0,0,5,7},
		{0,5,0,0,4,0,0,9,0},
		{9,8,0,0,7,0,0,0,0},
		{0,0,5,0,1,4,0,0,9},
		{1,0,0,3,0,0,0,0,0},
		{3,0,0,0,0,0,1,0,0} 
	};
	
	public static void printSudoku(int [][] a)
	{
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				System.out.print(a[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
	
	public static void printArray(int [] a)
	{
		for(int i = 0; i < a.length; i ++)
		{
			System.out.print(a[i] + " ");
		}
	}
	
	//Sees if value is in array
	public static boolean inArray(int [] a , int value)
	{
		for(int i = 0; i < a.length; i++)
		{
			if(a[i] == value)
			{
				return true;
			}
		}
		return false; 
	}
	
	//Returns row 
	public static int [] returnRow(int [][] a, int x)
	{
		int returnRow [] = {0,0,0,0,0,0,0,0,0};
		for(int i = 0 ; i < 9 ; i++)
		{
			returnRow[i] = a[x][i];
		}
		return returnRow;
	}
	
	//Returns row
	public static int [] returnColumn(int [][]a, int y)
	{
		int returnColumn [] = {0,0,0,0,0,0,0,0,0};
		for(int i = 0 ; i < 9 ; i ++)
		{
			returnColumn[i] = a[i][y];
		}
		return returnColumn;
	}
	
	//Sees if a contradiction exists in Sudoku puzzle
	//by looking at each line and section 
	//Returns true if contradiction exist
	//Returns false if contradiction does not exist
	public static boolean contradictionExists(int [][] a)
	{
		//Looks at line for rows and columns
		for(int i = 0; i < 9; i++)
		{
			int valuesRows[] = {0,0,0,0,0,0,0,0,0};
			int indexRows = 0;
			
			int valuesColumns[] = {0,0,0,0,0,0,0,0,0};
			int indexColumns = 0;
			
			for(int j = 0; j < 9; j++)
			{
				//Looks at rows
				if(a[i][j] != 0 && inArray(valuesRows,a[i][j]) == false)
				{
					valuesRows[indexRows] = a[i][j];
					indexRows ++;
				}
				else if(a[i][j] != 0 && inArray(valuesRows,a[i][j]) == true)
				{
					return true; 
				}
				
				//Looks at columns
				if(a[j][i] != 0 && inArray(valuesColumns,a[j][i]) == false){
					valuesColumns[indexColumns] = a[j][i];
					indexColumns ++;
				}
				else if(a[j][i] != 0 && inArray(valuesColumns,a[j][i]) == true)
				{
					return true; 
				}
			}
		}
		
		//Looks at each 3x3 section
		int index1[] = {0,3,6,0,3,6,0,3,6};
		int index2[] = {0,0,0,3,3,3,6,6,6};
		for(int i = 0; i < 9;i++)
		{
			int valuesSection[] = {0,0,0,0,0,0,0,0,0};
			int indexSection = 0;
			for(int j = index1[i]; j < index1[i] + 3; j++)
			{
				for(int k = index2[i]; k < index2[i] + 3; k++)
				{
					if(a[j][k] != 0 &&  inArray(valuesSection,a[j][k]) == false)
					{
						valuesSection[indexSection] = a[j][k];
						indexSection ++;
					}
					else if(a[j][k] != 0 && inArray(valuesSection,a[j][k]) == true)
					{
						return true;
					}
				}
			}
			
		}
		
		return false; 
	}
	
	//Returns possible values at position x,y
	//x is row
	//y is column
	public static int [] possibleValues(int [][] a, int x, int y)
	{
		int possibles [] = {0,0,0,0,0,0,0,0,0};
		int counter = 0;
		int notPossibles [] = {0,0,0,0,0,0,0,0,0};
		int temp[];

		//Checks rows 
		temp = returnRow(a,x);
		for(int i = 0; i < 9; i++)
		{
			if(temp[i] != 0 && inArray(notPossibles, temp[i]) == false)
			{
				notPossibles[counter] = temp[i];
				counter++;
			}
		}
		
		//Checks columns
		temp = returnColumn(a,y);
		for(int i = 0; i < 9; i++)
		{
			if(temp[i] != 0 && inArray(notPossibles, temp[i]) == false)
			{
				notPossibles[counter] = temp[i];
				counter++;
			}
		}
		
		//Creates possibles
		counter = 0;
		for(int i = 1; i < 10; i++)
		{
			if(inArray(notPossibles,i) == false)
			{
				possibles[counter] = i;
				counter ++;
			}
		}
		//printArray(possibles);
		return possibles;
	}
	
	public static boolean complete(int [][] a)
	{
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				if(a[i][j] == 0)
				{
					return false;
				}
			}
		}
		return true;
	}
	
	//Returns solution through recursive guessing
	public static boolean recursiveSolver(int [][] a)
	{
		//finds a zero
		int x = 0;
		int y = 0;
		outerloop:
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				if(a[i][j] == 0)
				{
					x = i;
					y = j;
					break outerloop;
				}
			}
		}	
		int possibles [] = possibleValues(a,x,y);
		
		if(complete(a) == true && contradictionExists(a) == false)
		{
			printSudoku(a);
			return true; 
		}
		else if(possibles[0] == 0)
		{
			return false;
		}
		else if(contradictionExists(a) == true)
		{
			return false;
			
		}
		
		
		//solve puzzle
		int i = 0;
		int[][] temp = new int[][]
		{
			{0,0,0,0,0,0,0,0,0},    
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0} 
		};
		int temp2 = 0;
		int dummy = 0;
		while(possibles[i] != 0)
		{
			//Copy a to temp 
			for(int n = 0; n < 9 ; n++)
			{
				for(int m = 0; m < 9; m++)
				{
					temp2 = a[n][m];
					temp[n][m] = temp2;
				}
			}
			
			a[x][y] = possibles[i];
			
			if(recursiveSolver(a)==false)
			{
				//Copy temp to a
				for(int n = 0; n < 9 ; n++)
				{
					for(int m = 0; m < 9; m++)
					{
						temp2 = temp[n][m];
						a[n][m] = temp2;
					}
				}
				i++;
			}
			else
			{
				return true;
			}
		}

		return false;
		
	}
	

	public static void main(String[] args)
	{
		int temp [];
		System.out.println("Solve Hard Puzzle # 2");
		printSudoku(puzzle2);
		System.out.println("");
		System.out.println("Solved...");
		recursiveSolver(puzzle2);
	}
}
