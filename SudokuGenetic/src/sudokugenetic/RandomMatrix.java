/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokugenetic;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author George
 */
public class RandomMatrix 
{
    private int [][] matrice;
    
    public RandomMatrix()
    {
        matrice = new int [9][9];
        randomRows();
    }
    
    public RandomMatrix(int[][] s)
    {
        matrice = new int[9][9];
        for(int i=0; i<9; i++)
            for(int j=0; j<9; j++)
                matrice[i][j] = s[i][j];
        randomRows();
    }
    
    public int[][] getMatrix()
    {
        return matrice;
    }
    
    public void swap(int l, int a, int b)
    {
        int k = matrice[l][a];
        matrice[l][a] = matrice[l][b];
        matrice[l][b] = k;
    }
    
    private void randomRows()
    {
        Random r = new Random();
        for(int i=0; i<9; i++)
        {
            ArrayList<Integer> nr = new ArrayList<Integer>();
            for(int k=1; k<10; k++)
                nr.add(k);
            for(int j=0; j<9; j++)
                if(matrice[i][j] != 0)
                    nr.remove((Integer)matrice[i][j]);
            for(int j=0; j<9; j++) 
                if(matrice[i][j] == 0)
                {
                    int k = nr.get(r.nextInt(nr.size()));
                    matrice[i][j] = k;
                    nr.remove((Integer)k);
                }
        }
    }
    
    public void printMatrix()	
    {
	for(int i=0; i<9; i++)
	{
            for(int j=0; j<9; j++)
            {
		System.out.print(matrice[i][j] + " ");
            }
            System.out.println();
	}
    }
}
