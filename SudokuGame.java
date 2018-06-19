/**@author: Japkeerat Singh
* @version: 1.0.0 (without hint functionality)
* This is the implementation of a Sudoku Game. It can perform multiple tasks including solving a sudoku and generating 1 for you.
* Main aim was to learn benefits of: Encapsulation, Inner Classes, Packages.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import sudoku.solve.solve;
import sudoku.generate.generator;
import sudoku.hint.hint;

public class SudokuGame 
{
    private int task;
    private final String message1 = "Hello! Enter the character corresponding the task you want me to do for you";
    private final String message2 = "Solve the problem: 1";
    private final String message3 = "Give a hint to the problem: 2";
    private final String message4 = "Throw me a problem: 3";
    
    InputStreamReader read = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(read);
    
    private void inputTask()
    {
    	/*Takes input from the user regarding the wirk it wants program to perform.*/
        System.out.println(message1+"\n"+message2+"\n"+message3+"\n"+message4);
        try 
        {
            task = Integer.parseInt(br.readLine());
        } 
        catch (IOException ex) 
        {
            System.out.println("Error while taking input. Try again later");
        }
    }
    private void callClass() throws IOException
    {
    	/*If user wants program to generate a new problem statement, it redirects to generate.java
    	  If he/she wants program to solve a problem, it redirects to solve.java*/
        switch (task) {
            case 1:
                {
                    solve obj = new solve();
                    obj.theGame();
                    break;
                }
            case 2: 
                {
                    hint obj = new hint();
                    obj.theGame();
                    break;
                }
            case 3:
            {
                generator gen = new generator();
                gen.theGame();
                break;
            }
            default:
                break;
        }
    }
    
    public static void main(String[] args) throws IOException 
    {
        SudokuGame game = new SudokuGame(); //creating object of SudokuGame class.
        game.inputTask(); //taking input from console regarding task to perform.
        game.callClass(); //according to the user input, further directs to other classes to do the work.
    }
}
