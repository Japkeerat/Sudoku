package sudoku.generate;

import java.util.Random;

public class randomRemove 
{
    //This class randomly removes digits from the grid.
    private byte[][] matrix = new byte[9][9];
    private char level;
    private byte deletions;
    
    private void calculateDeletions()
    {
        //81: Total boxes in matrix.
        //Remaining digits: Digits left filled in sudoku after deleting some elements.
        switch (level) 
        {
            case 'e':
                deletions = 81-48;//keep 48 elements in the grid for easy games
                break;
            case 'm':
                deletions = 81-35;//keep 35 elements in the grid for medium games
                break;
            case 'h':
                deletions = 81-26;//keep 26 elements in the grid for tough games
                break;
            case 'd':
                deletions = 81-17;//keep 17 elements in the grid for extreme games
                break;
            default:
                break;
        }
    }
    
    Random rand = new Random();
    
    private void deleteData()
    {
        for(byte i=0; i<deletions; i++)
        {
            int num = rand.nextInt(73);
            if(matrix[num/9][num%9]!=0)
            {
                matrix[num/9][num%9]=0;
            }
            else
            {
                i--;
            }
        }
    }
    public byte[][] removal(char diff, byte[][] box)
    {
        matrix = box;
        level = diff;
        calculateDeletions();
        deleteData();
        return matrix;
    }
}
