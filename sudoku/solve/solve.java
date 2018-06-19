package sudoku.solve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class solve
{
    public byte[][] matrix = new byte[9][9]; //initialising sudoku grid
    private final String note = "Enter elements row-wise. Use '.' in place of empty boxes.";
    private String row = null; 
    private char c;
    private boolean check = false;
    private static final int UNASSIGNED = 0;
    
    InputStreamReader read = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(read);
    
    public void getMatrix() throws IOException
    {
        //This method asks user to enter grid elements.
        System.out.println(note);
        for(int i=0; i<9; i++)
        {
            row = br.readLine();
            for(int j=0; j<9; j++)
            {
                if(row.charAt(j)!='.')
                {
                    matrix[i][j] = (byte)((int)row.charAt(j) - 48);
                }
            }
        }
    }
    public boolean verifyMatrix()
    {
        //This method confirms user about the grid he/she has entered
        System.out.println("Is this correct?(y/n)");
        for(int i=0; i<9; i++)
        {
            for(int j=0; j<9; j++)
            {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.print("\n");
        }
        try 
        {
            c = br.readLine().charAt(0);
        } 
        catch (IOException ex) 
        {
            System.out.println("Error occurred. Try again later.");
        }
        return c=='y'||c=='Y';
    }
    private boolean solveGame()
    {
        //this method does all the solving using backtracking algorithm
        for(int row=0;row<9;row++)
        {
            for(int col=0;col<9;col++)
            {
                if(matrix[row][col]==UNASSIGNED)
                {
                    for(byte number=1;number<=9;number++)
                    {
                        if(checkSafety(row, col, number))
                        {
                            matrix[row][col] = number;
                            if(solveGame())
                            {
                                return true;
                            }
                            else
                            {
                                matrix[row][col] = UNASSIGNED;
                            }
                        }
                    }
                    return false;
                }
            }
        }
       
           return true;
    }
    private boolean checkSafety(int row, int col,byte number)
    {
        //This method checks if a number is safe to put at particular index.
        return !(presentInRow(row, number) || 
                presentInCol(col, number) || 
                presentInBox(row, col, number));
    }
    private boolean presentInRow(int row,byte number)
    {
        //This checks if that particular number is already in row or not.
        for(int i=0;i<9;i++)
        {
            if(matrix[row][i]==number)
            {
                return true;
            }
        }
        return false;
    }
    private boolean presentInCol(int col,byte number)
    {
        //This method checks whether that particular number is present in column or not.
        for(int i=0;i<9;i++)
        {
            if(matrix[i][col]==number)
            {
                return true;
            }
        }
        return false;
    }  
    private boolean presentInBox(int row, int col,byte number)
    {
        //This method checks whether that particular element is in that box or not.
        int r = row - row%3;
        int c = col - col%3;
        for(int i = r ; i< r+3 ; i++)
        {
            for(int j = c; j < c+3 ; j++)
            {
                if(matrix[i][j]==number)
                {
                    return true;
                }
            }
        }
        return false;
    }
    private void smashSolution()
    {
        for(int i=0; i<9; i++)
        {
            for(int j=0; j<9; j++)
            {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.print("\n");
        }
    }
    public void theGame() throws IOException 
    {
    //SudokuGame.java calls this method which further directs to other member functions.
        while(check==false)
        {
            getMatrix(); //this method is called to take input from user for the sudoku he/she wants to solve.
            check = verifyMatrix(); //verifying puzzle to decrease computer memory wastage due to typing errors.
        }
        boolean s = solveGame();
        smashSolution(); //like a boss!
    }
    public byte[][] gridGeneratorHelper(byte[][] grid)
    {
    //This class is only called during generation of sudoku grid.
    	matrix = grid;
        boolean s = solveGame();
        return matrix;
    }
    public byte[][] hintGenerator(byte[][] grid)
    {
        matrix = grid;
        boolean s = solveGame();
        return matrix;
    }
}
