/** @Author: Japkeerat Singh
* @version: 0.9.9
* This class generates a Sudoku according to specifications
*/

package sudoku.generate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class generator 
{
    private char difficulty;
    private byte[][] matrix = new byte[9][9];
    
    InputStreamReader read = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(read);
    
    private void getDifficulty()
    {
        //This method asks user for difficulty level.
        System.out.println("Choose difficulty");
        System.out.println("Easy: e");
        System.out.println("Medium: m");
        System.out.println("Hard: h");
        System.out.println("(Try this at your own risk!)Dangerously hard: d");
        try
        {
            difficulty = br.readLine().charAt(0);
        }
        catch(IOException e)
        {
            System.out.println("Error occurred. Try again later.");
        }
    }
    private void getFilledMatrix()
    {
        //This method generates a fully filled sudoku grid.
        randomMatrix rand = new randomMatrix();
    	matrix = rand.makeMatrix();
    }
    private void removeElements()
    {
        //This method removes random numbers from grid based on difficulty level chosen.
        randomRemove rem = new randomRemove();
        matrix = rem.removal(difficulty, matrix);
    }
    private void giveProblem()
    {
        //This method displays a problem
        System.out.println("Here '.' specifies empty box");
        for(int i=0; i<9; i++)
        {
            for(int j=0; j<9; j++)
            {
                if(matrix[i][j]!=0)
                {
                    System.out.print(matrix[i][j]);
                }
                else
                {
                    System.out.print(".");
                }
            }
            System.out.print("\n");
        }
    }
    public void theGame()
    {
        getDifficulty();
        getFilledMatrix();
        removeElements();
        giveProblem();
    }
}
