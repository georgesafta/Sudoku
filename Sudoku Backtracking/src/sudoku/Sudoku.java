/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.util.ArrayList;

/**
 *
 * @author George
 */
public class Sudoku {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<Solutie> solutii = new ArrayList<Solutie>();
        solutii = init();
    }
    public static ArrayList<Solutie> init()
    {
        ArrayList<Solutie> s = new ArrayList<Solutie>();
        for(int i=0; i<10; i++)
        {
            Solutie sol = new Solutie();
            s.add(sol);
            s.get(i).printGrid();
            System.out.println();
        }
        return s;
    }
}
