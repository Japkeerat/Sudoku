package sudoku.hint;

import java.io.IOException;
import java.util.Random;
import sudoku.solve.solve;

public class hint 
{
    private byte[][] matrix = new byte[9][9];
    private byte[][] grid = new byte[9][9];
    
    solve sol = new solve();
    Random rand = new Random();
    
    private void giveHint()
    {
        grid = sol.hintGenerator(matrix);
        int a = randomNumber();
    }
    private int randomNumber()
    {
        int s = rand.nextInt(73);
        if(matrix[s/9][s%9]==0)
        {
            matrix[s/9][s%9] = grid[s/9][s%9];
        }
        else
        {
            return randomNumber();
        }
        return 0;
    }
    private void display()
    {
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
            System.out.println();
        }
    }
    public void theGame() throws IOException
    {
        sol.getMatrix();
        matrix = sol.matrix;
        giveHint();
        display();
    }
}
