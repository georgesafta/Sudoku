/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author George
 */
public class Solutie 
{
    private int[][] sol;
    
    public Solutie()
    {
        sol = new int [9][9];
        sol = randomRow();
        sudokuSolve(sol);
    }
    
    public Solutie (int [][] s)
    {
        for(int i=0; i<9; i++)
            for(int j=0; j<9; j++)
                sol[i][j] = s[i][j];
        sudokuSolve(sol);
    }
    
    public int elementAt(int i, int j)
    {
        return sol[i][j];
    }
    
    private int[][] randomRow()
    {
        int[][] s = new int [9][9];
        Random r = new Random();
        ArrayList<Integer> nr = new ArrayList<Integer>();
        for(int k=1; k<10; k++)
            nr.add(k);
        for(int j=0; j<9; j++)
        { 
            int k = nr.get(r.nextInt(nr.size()));
            s[0][j] = k;
            nr.remove((Integer)k);
        }
        return s;
    }

    private boolean sudokuSolve(int[][] grid)
    {
	wrapperInt row = new wrapperInt();
	wrapperInt col = new wrapperInt();
	if(!findUnassigned(grid, row, col))
            return true;
	int num;
	for(num=1; num<=9; num++)
	{
            if(isSafe(grid, row.value, col.value, num))
            {
		int rowValue = row.value, colValue = col.value;
		grid[rowValue][colValue] = num;
		if(sudokuSolve(grid))
                    return true;
		grid[rowValue][colValue]=0; 
            }
        }
        return false;
    }
	
    private boolean findUnassigned(int[][] grid, wrapperInt row, wrapperInt col)
    {
        for(row.value=0; row.value<9; row.value++)
	{
            for(col.value=0; col.value<9; col.value++)
            {
                if(grid[row.value][col.value] == 0)
                    return true;
            }
	}
	return false;
    }
	
    private boolean isSafe(int[][] grid, int rowValue, int colValue, int num)
    {
        if(!usedInRow(grid, rowValue, num) && !usedInCol(grid, colValue, num) && !usedInBox(grid, rowValue-rowValue%3, colValue-colValue%3, num))
	{
            return true;
	}
        return false;
    }
	
    private boolean usedInRow(int[][] grid, int rowValue, int num)
    {
	for(int y=0; y<9; y++)	
	{
            if(grid[rowValue][y] == num)
		return true;
	}
	return false;
    }
	
    private boolean usedInCol(int[][] grid, int colValue, int num)
    {
	for(int x=0; x<9; x++)	
	{
            if(grid[x][colValue] == num)
		return true;
	}
	return false;
    }
	
    private boolean usedInBox(int[][] grid, int rowValue, int colValue, int num)
    {
	for(int x=0; x<3; x++)
	{
            for(int y=0; y<3; y++)	
		if(grid[rowValue+x][colValue+y] == num)
                    return true;	
	}		
	return false;
    }
	
    public void printGrid()	
    {
	for(int i=0; i<9; i++)
	{
            for(int j=0; j<9; j++)
            {
		System.out.print(sol[i][j] + " ");
            }
            System.out.println();
	}
    }
}

class wrapperInt
{
    public int value;
    
    public wrapperInt(int d)
    {
        value = d;
    }
    public wrapperInt()
    {
        value = 0;
    }
}