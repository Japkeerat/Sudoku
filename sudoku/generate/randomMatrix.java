package sudoku.generate;

import java.util.Random;
import sudoku.solve.*;

public class randomMatrix 
{
    //This class generates a random matrix that is completely filled and satisfies Sudoku rules.
    private byte[][] matrix;
    private byte sum;
    private byte[] num = new byte[9];
    
    Random rand = new Random();

    public randomMatrix() //constructor
    {
        this.matrix = new byte[9][9];
    }
    
    private void randomNumber()
    {
        //This method generates a series of random numbers.
    	for(int i=0; i<9; i++)
    	{
            num[i] = 0;
    	}
        for(int i=0; i<9; i++)
        {
            byte a = (byte)(rand.nextInt(9) + 1);
            if(search(a)==false)
            {
            	num[i] = a;
            }
            else
            {
            	i--;
            }
        }
    }
    private void constructMatrix()
    {
        //This method constructs the matrix. (Backbone of this class)
    	fillDiagonalBoxes();
        solve sol = new solve();
        matrix = sol.gridGeneratorHelper(matrix);
    }
    private void fillDiagonalBoxes()
    {
        //This method fills in 3 diagonal boxes using randomNumber() as elements in
        //diagonal boxes have no relation.
    	//upper left box
    	randomNumber();
    	for(int i=0; i<3; i++)
    	{
            for(int j=0; j<3; j++)
            {
                matrix[i][j] = num[3*i+j];
            }
    	}
    	//middle box
    	randomNumber();
    	for(int i=0; i<3; i++)
    	{
            for(int j=0; j<3; j++)
            {
    		matrix[i+3][j+3] = num[3*i+j];
            }
    	}
    	//lower right box
    	randomNumber();
    	for(int i=0; i<3; i++)
    	{
            for(int j=0; j<3; j++)
            {
    		matrix[i+6][j+6] = num[3*i+j];
            }
    	}
    }
    private boolean search(byte n)
    {
        //this method confirms that a number generated randomly is unique.
    	for(int i=0; i<9; i++)
    	{
            if(num[i]==n)
            {
                return true;
            }
    	}
    	return false;
    }
    public byte[][] makeMatrix()
    {
    	constructMatrix();
        return matrix;
    }
}
