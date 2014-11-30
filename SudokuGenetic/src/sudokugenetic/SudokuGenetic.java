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
public class SudokuGenetic {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        ArrayList<RandomMatrix> solutii = new ArrayList<>();
        int [][] s = new int[9][9];
        s[0][1] = 3; s[5][4] = 6; s[7][2] = 2;
        solutii = init(s);
    }
    
    public static ArrayList<RandomMatrix> init(int[][] sol)
    {
        ArrayList<RandomMatrix> s = new ArrayList<>();
        for(int i=0; i<30; i++)
        {
            RandomMatrix matrice = new RandomMatrix(sol);
            s.add(matrice);
            s.get(i).printMatrix();
            System.out.println();
        }
        return s;
    }
    
    public static int col(RandomMatrix sol, int col)
    {
        ArrayList<Integer> nr = new ArrayList<>();
        int[][] s = sol.getMatrix();
        nr.add(s[0][col]);
        for(int i=1; i<9; i++)
        {
            if(nr.contains(s[i][col]))
                return 0;
            nr.add(s[i][col]);
        }
        return 1;
    }
    
    public static int box(RandomMatrix sol, int lin, int col)
    {
        ArrayList<Integer> nr = new ArrayList<>();
        int[][] s = sol.getMatrix();
        for(int i=lin; i<lin+3; i++)
        {
            for(int j=col; j<col+3; j++)
            {
                if(nr.contains(s[i][j]))
                    return 0;
                nr.add(s[i][j]);
            }
        }
        return 1;
    }
    
    public static int[] fitness(ArrayList<RandomMatrix> s)
    {
        int[] fit = new int[30];
        for(int i=0; i<s.size(); i++)
        {
            int sum = 0;
            for(int j=0; j<9; j++)
                sum += col(s.get(i), j);
            for(int j=0; j<9; j=j+3)
                for(int k=0; k<9; k=k+3)
                    sum += box(s.get(i), j, k);
            fit[i] = sum;
        }
        return fit;
    }
    
    public static ArrayList<RandomMatrix> selection(ArrayList<RandomMatrix> s, int[] f)
    {
        ArrayList<RandomMatrix> sol = new ArrayList<>();
        Random r =  new Random();
        for(int i=0; i<s.size(); i++)
        {
            int k = r.nextInt(s.size());
            if(f[i]>f[k])
                sol.add(s.get(i));
            else 
                sol.add(s.get(k));
        }
        return sol;
    }
    
    public static boolean verify(int[][] s, int l, int a, int b)
    {
        for(int i=a; i<=b; i++)
            if(s[l][i] != 0)
                return false;
        return true;
    }
    
    public static ArrayList<RandomMatrix> crossOver(ArrayList<RandomMatrix> sol, int[][] s)
    {
        return sol;
    }
    
    public static ArrayList<RandomMatrix> mutation(ArrayList<RandomMatrix> sol, int[][] s)
    {
        Random r =  new Random();
        for(int i=0; i<sol.size(); i++)
        {
            if(r.nextFloat()<.03)
            {
                int l = r.nextInt(9);
                int a = r.nextInt(9);
                int b = r.nextInt(9);
                while(s[l][a] != 0 || s[l][b] != 0)
                {
                    l = r.nextInt(9);
                    a = r.nextInt(9);
                    b = r.nextInt(9);
                }
                sol.get(i).swap(l, a, b);
            }
        } 
        return sol;
    }
    
}
