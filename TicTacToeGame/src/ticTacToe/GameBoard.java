package ticTacToe;

public class GameBoard 
{
	private char [][] board = new char [3][3];
	private int plays;
	
	public GameBoard()
	{
		// set all board locations to a space
		for(int i = 0;i<3;i++)
			for(int j = 0;j<3;j++)
			{
				board[i][j] = ' ';
			}//GAMEBOARD
	}//GAMEBOARD
	
	public boolean play(boolean x, int row, int column)
	{
		if (x == true)
		{
			// setting location to X if empty
			if (board[row][column] == ' ')
			{
				board[row][column] = 'X';
				return true;
			}//IF
			// display invalid move
			else
			{
				System.out.println("Invalid move, choose another location");
				return false;
			}//ELSE
		}//IF
		else
		{
			// Replace with O if empty
			if (board[row][column] == ' ')
			{
				board[row][column] = 'O';
				return true;
			}//IF
			else
			{
				return false;
			}//ELSE
		}//ELSE
	}//PLAY
	
	public int validPlayCount()
	{
		plays++;
		return plays;
		
	}//VALIDPLAYCOUNT
	
	public char checkWin()
	{
		// Rows
		if(board[0][0]=='X'&&board[0][1]=='X'&&board[0][2]=='X')
			return 'X';
		if(board[1][0]=='X'&&board[1][1]=='X'&&board[1][2]=='X')
			return 'X';
		if(board[2][0]=='X'&&board[2][1]=='X'&&board[2][2]=='X')
			return 'X';
		// Columns
		if(board[0][0]=='X'&&board[1][0]=='X'&&board[2][0]=='X')
			return 'X';
		if(board[0][1]=='X'&&board[1][1]=='X'&&board[2][1]=='X')
			return 'X';
		if(board[0][2]=='X'&&board[1][2]=='X'&&board[2][2]=='X')
			return 'X';
		// Diagonals
		if(board[0][0]=='X'&&board[1][1]=='X'&&board[2][2]=='X')
			return 'X';
		if(board[2][0]=='X'&&board[1][1]=='X'&&board[0][2]=='X')
			return 'X';
		// Rows
		if(board[0][0]=='O'&&board[0][1]=='O'&&board[0][2]=='O')
			return 'O';
		if(board[1][0]=='O'&&board[1][1]=='O'&&board[1][2]=='O')
			return 'O';
		if(board[2][0]=='O'&&board[2][1]=='O'&&board[2][2]=='O')
			return 'O';
		// Columns
		if(board[0][0]=='O'&&board[1][0]=='O'&&board[2][0]=='O')
			return 'O';
		if(board[0][1]=='O'&&board[1][1]=='O'&&board[2][1]=='O')
			return 'O';
		if(board[0][2]=='O'&&board[1][2]=='O'&&board[2][2]=='O')
			return 'O';
		// Diagonals
		if(board[0][0]=='O'&&board[1][1]=='O'&&board[2][2]=='O')
			return 'O';
		if(board[2][0]=='O'&&board[1][1]=='O'&&board[0][2]=='O')
			return 'O';
		// Tie
		else if (board[0][0] !=' '&& board[0][1] !=' '&& board[0][2] !=' '&& board[1][0] !=' '&& board[1][1] !=' '&& board[1][2] !=' '&& board[2][0] !=' '&& board[2][1] !=' '&& board[2][2] !=' ')
			return 'T';
		// Keep going
		return ' ';
		
	}//CHECKWIN
	
	public void showBoard()
	{
		System.out.println(" " + board[0][0] + " " + "|" + " " + board[0][1] + " " + "|" + " " + board[0][2] + " ");
		System.out.println("---+---+---");
		System.out.println(" " + board[1][0] + " " + "|" + " " + board[1][1] + " " + "|" + " " + board[1][2] + " ");
		System.out.println("---+---+---");
		System.out.println(" " + board[2][0] + " " + "|" + " " + board[2][1] + " " + "|" + " " + board[2][2] + " ");
	}//SHOWBOARD
}//CLASS
