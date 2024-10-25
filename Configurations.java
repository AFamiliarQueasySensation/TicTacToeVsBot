/*****************************************************************************
 * Author: Grant Von Hagen (251307427)
 * Date: 7/24/2024
 * Program: Configurations.java
 * Description: Implements methods needed by algorithm computerPlay
 ******************************************************************************/





public class Configurations {
	
	
	private int boardSize;
	private int lengthToWin;
	private int maxLevels;
	private char[][] board; // game board
	
	
	public Configurations(int boardSize,int lengthToWin, int maxLevels)
	{
		this.boardSize = boardSize;
		this.lengthToWin = lengthToWin;
		this.maxLevels = maxLevels;
		this.board = new char[boardSize][boardSize];
		//initialize the board to all -
		for (int i = 0; i < boardSize; i++)
		{
			for (int j = 0; j < boardSize; j++)
			{
				board[i][j] = ' ';
			}
		}
		
	}
	
		
	/*
	 * Initializes a new dictionary of size 8k, at a prime number
	 */
	public HashDictionary createDictionary() {
		
		HashDictionary Dict = new HashDictionary(8081);
		
		return Dict;
		
		
	}
	/*
	 * Helper method, returns a string representation of the board by iterating through the board, and appending it to a string
	 */
	
	private String boardToString() {
		String holder = "";
		
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[i].length; j++) {
				holder += this.board[i][j];
			}
		}
		return holder;
	}
	
	/*
	 * Finds if the board is already on the hashtable, returns -1 if not on the board, else returns its score
	 */
	public int repeatedConfiguration(HashDictionary hashTable) {
		
		return hashTable.get(boardToString());
		
	}
	/*
	 * First adds the board and score into a Node, then stores the node in the hashDictionary
	 */
	public void addConfiguration(HashDictionary hashDictionary, int score)
	{
		//if not already in the hashDict
		if (repeatedConfiguration(hashDictionary) != -1)
		{
			Data insert = new Data(boardToString(), score);
			hashDictionary.put(insert);
		}
	}
	/*
	 * Stores a symbol on the board
	 */
	public void savePlay(int row, int col, char symbol) {
		this.board[row][col] = symbol;
	}
	
	/*
	 * Returns true if the square is empty
	 * Input: int row, int column
	 */
	public boolean squareIsEmpty (int row, int col) {
		if (board[row][col] == ' ') return true;
		return false;
	}
	/*
	 * Input: Symbol type ('X' or 'O')
	 * 
	 * Iterates through the table, copmaring current to values to the right, diagonal right, down, and diagonal left for sequences,
	 * iterates through top left to right, top to bottom
	 * 
	 * Returns true if there is a continuous sequence of length at least k formed by tiles of the kind symbol on the board,
	 * where k is the length of the vertical or horizontal of diagonal line needed to win the game.
	 * 
	 */
	public boolean wins(char symbol) {
		String strBoard = boardToString();
		char current = symbol;
		int counter = 1; // increments if there is an instance of a sequence
		int i = 0;
		int j = i;
		//it cannot be ' '
		while (i < strBoard.length())
		{
			
			if(strBoard.charAt(i) == current) 
			{
				
				//check right -> horizontally
				//Assign current must be X or O
				// j will increment throught the while loop
				j = i;
				counter = 1;
				
				//check if its in the same row, not out of the string length, and the next is same as current
				while ((j + 1) < strBoard.length() && strBoard.charAt(j + 1) == current)
				{
					//finds if its a jump from right side of board to left
					if ((j + 1) % (this.boardSize) == 0 && (j % this.boardSize) == (this.boardSize -1))
						{
							break;
						}
					
					//increment occurance, and index
					
					j++;
					counter++;
					//sequence to win game
					if(counter == this.lengthToWin)
						{
							return true;
						}
				}
				
				
				
				
				
				//checks if it is downwards
				//reset counter and j
				j = i;
				counter = 1;
				
				//checks if it is downwards, 
				while ((j + this.boardSize) < strBoard.length() && strBoard.charAt(j + this.boardSize) == current)
				{
					j = j + this.boardSize;
					counter++;
					if (counter == this.lengthToWin)
					{
						return true;
					}
				}
				
				
				
				
				
				
				//Diagonal finds down right sequences
				//reset counter and j
				j = i;
				counter = 1;
				
				
				while ((j + this.boardSize + 1) < strBoard.length() && strBoard.charAt(j + this.boardSize + 1) == current)
				{
					//must be right side, which has no diagonal rights
					if ((j + 1) % this.boardSize == 0)
					{
						break;
					}
					//increment occurance, and index
					j = j + (this.boardSize + 1);
					counter++;
					//sequence to win game
					if (counter == this.lengthToWin)
					{
						return true;
					}
				}
				
				
				
				
				
				//Diagonal finds down left sequences
				//reset counter and j
				j = i;
				counter = 1;
				
				while ((j + this.boardSize - 1) < strBoard.length() && strBoard.charAt(j + this.boardSize -1 ) == current)
				{
					//must be a left side, which has no diagonal lefts
					if (j % this.boardSize == 0)
					{
						break;
					}
					//increment occurance, and index
					j = j + this.boardSize -1;
					counter++;
					//sequence to win game
					if (counter == this.lengthToWin)
					{
						return true;
					}
				}
				//reset counter and j
				j = i;
				counter = 1;
			}
			//reset counter
			counter = 1;
			//increment position
			i++;
			
		}
		
		return false;
	}
	
	
	
	
	
	/*
	 * returns true if there are no plays to be made,
	 * and the board is full.
	 */
	public boolean isDraw() {
		//Check nobody won the game
		if(wins('X') || wins('O'))
		{
			return false;
		}
		for (int i = 0; i < this.boardSize; i++)
		{
			for (int j = 0; j < this.boardSize; j++)
			{
				if(squareIsEmpty(i, j)) return false;
			}
		}
		//is draw
		return true;
	}
	
	
	
	
	
	
	/* 3. if computer won 'O'
	 * 0. if human player won 'X'
	 * 2. if game is draw
	 * 1. if still empty positions on the board and nobody has won yet 
	 * 
	 */
	public int evalBoard() {
		if (wins('O')) return 3;
		if (wins('X')) return 0;
		if (isDraw())  return 2;
		return 1;
	}
}

