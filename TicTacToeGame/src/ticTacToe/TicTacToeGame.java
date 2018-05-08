package ticTacToe;
import java.util.*;
import java.util.Random;

import javax.swing.JOptionPane;
public class TicTacToeGame 
{
	public static void main(String [] args)
	{
		Scanner s = new Scanner(System.in);
		GameBoard game = new GameBoard();
		while(game.checkWin() == ' ')
		{
			String stat="fail";
			while (stat.equals("fail"))
			{
				// Player move
				System.out.println("Where would you like to place an X? (row column)");
				int r = s.nextInt();
				int c = s.nextInt();
				try
				{
					stat="pass";
				while(game.play(true, r, c)==false)
					{
						c = s.nextInt();
						r = s.nextInt();
					}//WHILE
				}//TRY
				catch(ArrayIndexOutOfBoundsException e)
				{
					JOptionPane.showMessageDialog(null,"Out of range (0 to 2) try again.");
					stat = "fail";
				}//CATCH
			}//WHILE
			game.showBoard();
			game.validPlayCount();
			game.checkWin();
			if(game.checkWin()==' ')
			{
				// Computer move
				// Randomize play location
				Random rand = new Random();
				Random rand1 = new Random();
				int x = rand.nextInt(3);
				int y = rand1.nextInt(3);
				// Retrying until valid location
				while(game.play(false, x, y) == false)
				{
					rand = new Random();
					rand1 = new Random();
					x = rand.nextInt(3);
					y = rand1.nextInt(3);
				}//WHILE
				System.out.println("The computer played @ " + x + "," + y);
				game.showBoard();
				game.validPlayCount();
				game.checkWin();
			}//IF
		}//WHILE
		if(game.checkWin()=='T')
		{
			JOptionPane.showMessageDialog(null,"Its a tie, GG. \n" + (game.validPlayCount()-1) + " plays");
		}//IF
		if(game.checkWin()=='X')
		{
			System.out.println("You win, congrats! \n" + (game.validPlayCount()-1) + " plays");
		}//IF
		if(game.checkWin()=='O')
		{
			System.out.println("You lose, too bad. \n" + (game.validPlayCount()-1) + " plays");
		}//IF
	}//MAIN
}//CLASS
