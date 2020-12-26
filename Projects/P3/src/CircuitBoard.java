import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Represents a 2D circuit board as read from an input file.
 * 
 * @author danielrao
 */
public class CircuitBoard {
	/** current contents of the board */
	private char[][] board;
	/** location of row,col for '1' */
	private Point startingPoint;
	/** location of row,col for '2' */
	private Point endingPoint;

	// constants you may find useful
	private final int ROWS; // initialized in constructor
	private final int COLS; // initialized in constructor
	private final char OPEN = 'O'; // capital 'o'
	private final char CLOSED = 'X';
	private final char TRACE = 'T';
	private final char START = '1';
	private final char END = '2';
	private final String ALLOWED_CHARS = "OXT12";

	/**
	 * Construct a CircuitBoard from a given board input file, where the first line
	 * contains the number of rows and columns as ints and each subsequent line is
	 * one row of characters representing the contents of that position. Valid
	 * characters are as follows: 'O' an open position 'X' an occupied, unavailable
	 * position '1' first of two components needing to be connected '2' second of
	 * two components needing to be connected 'T' is not expected in input files -
	 * represents part of the trace connecting components 1 and 2 in the solution
	 * 
	 * @param filename file containing a grid of characters
	 * @throws FileNotFoundException      if Scanner cannot read the file
	 * @throws InvalidFileFormatException for any other format or content issue that
	 *                                    prevents reading a valid input file
	 */
	@SuppressWarnings("resource")
	public CircuitBoard(String filename) throws FileNotFoundException {
		Scanner fileScan = new Scanner(new File(filename));

		// TODO: parse the given file to populate the char[][]
		// throw FileNotFoundException if Scanner cannot read the file
		// throw InvalidFileFormatException if any formatting or parsing issues are
		// encountered

		// ROWS = 0; //replace with initialization statements using values from file
		// COLS = 0;

		String line = fileScan.nextLine().trim(); //gets rid of spaces.
		String[] charac = line.split(" "); //creates an array and stores the values in the charac
		if (charac.length > 2) {
			throw new InvalidFileFormatException("Invalid dimension"); //if the dimensions in the file are incorrect.
		}
		try {
		@SuppressWarnings("unused")
		int rowTest =Integer.parseInt(charac[0]);  //storing the row dimension, will help us create the board.
		@SuppressWarnings("unused")
		int colTest =Integer.parseInt(charac[1]);}  //storing the column dimension, will help us create the board.
		catch(NumberFormatException e) {
		throw new InvalidFileFormatException("Invalid Dimension");} //if the dimensions are not parsed to the int, than this is thrown
		
		ROWS = Integer.parseInt(charac[0]); //contains the row size.
		COLS = Integer.parseInt(charac[1]); //contains the column size.
		
		
		board = new char[ROWS][COLS]; //board gets instantiated.
		int startCounter = 0; //keeps track of all the 1's in the board.
		int endCounter = 0; //keeps track of all the 2's in the board.
		
		//iterates over the column mentioned according to the first line in the code.(rows)
		for (int i = 0; i < ROWS; i++) {
			if(!fileScan.hasNextLine()) {
				throw new InvalidFileFormatException("Data Limit Exceeded"); //if the row lacks a line.
			}
			
			//Scanner scans the next line
			Scanner lineScan = new Scanner(fileScan.nextLine());
			//iterates over the column mentioned according to the first line in the code.(column)
			for (int j = 0; j < COLS; j++) {
				if(!lineScan.hasNext()) {
					lineScan.close();
					throw new InvalidFileFormatException("Data Limit Exceeded"); //if the column lacks a token.
				}
				String temp = lineScan.next(); //String contains the next token.
				board[i][j] = temp.charAt(0); //assignments of all the values  to the board.
				if (board[i][j] == START) { //checks if at each iteration we find 1.
					startingPoint = new Point(i, j); //stores the point in the starting point.
					startCounter++; //increments the start counter.
				}
				if (board[i][j] == END) { //if 2 is found at certain iteration in the board.
					endingPoint = new Point(i, j); //stores the point in the endingPoint.
					endCounter++; //increments the endCounter.

				}
				//checks the condition if is false.
				if (!ALLOWED_CHARS.contains(temp)) {
					lineScan.close();  //if the temp contains any other character apart from 0, T, 1, 2, X than this exception is thrown.
					throw new InvalidFileFormatException(" Only uses chars O, T, 1, 2, X-  Invalid format"); 
				}

			
			}
			
			//if the column contains an extra token than this exception is thrown.
			if(lineScan.hasNext()) {
				lineScan.close();
				throw new InvalidFileFormatException("Data Limit Exceeded");
			}
			lineScan.close();
			
		}
		//if the file contains an extra row than this exception is thrown.
		if(fileScan.hasNextLine()) {
			throw new InvalidFileFormatException("Data Limit Exceeded");
		}
		//checks if there are multiple 1's in the board.
		if(startCounter != 1) {
			throw new InvalidFileFormatException("Invalid File");
			
		}
		//checks if there are multiple 2's in the board.
		if(endCounter != 1) {
			throw new InvalidFileFormatException("Invalid File");
			
		}
		//Scanner is closed.

		fileScan.close();
	}

	/**
	 * Copy constructor - duplicates original board
	 * 
	 * @param original board to copy
	 */
	public CircuitBoard(CircuitBoard original) {
		board = original.getBoard();
		startingPoint = new Point(original.startingPoint);
		endingPoint = new Point(original.endingPoint);
		ROWS = original.numRows();
		COLS = original.numCols();
	}

	/**
	 * utility method for copy constructor
	 * 
	 * @return copy of board array
	 */
	private char[][] getBoard() {
		char[][] copy = new char[board.length][board[0].length];
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				copy[row][col] = board[row][col];
			}
		}
		return copy;
	}

	/**
	 * Return the char at board position x,y
	 * 
	 * @param row row coordinate
	 * @param col col coordinate
	 * @return char at row, col
	 */
	public char charAt(int row, int col) {
		return board[row][col];
	}

	/**
	 * Return whether given board position is open
	 * 
	 * @param row
	 * @param col
	 * @return true if position at (row, col) is open
	 */
	public boolean isOpen(int row, int col) {
		if (row < 0 || row >= board.length || col < 0 || col >= board[row].length) {
			return false;
		}
		return board[row][col] == OPEN;
	}

	/**
	 * Set given position to be a 'T'
	 * 
	 * @param row
	 * @param col
	 * @throws OccupiedPositionException if given position is not open
	 */
	public void makeTrace(int row, int col) {
		if (isOpen(row, col)) {
			board[row][col] = TRACE;
		} else {
			throw new OccupiedPositionException("row " + row + ", col " + col + "contains '" + board[row][col] + "'");
		}
	}

	/** @return starting Point(row,col) */
	public Point getStartingPoint() {
		return new Point(startingPoint);
	}

	/** @return ending Point(row,col) */
	public Point getEndingPoint() {
		return new Point(endingPoint);
	}

	/** @return number of rows in this CircuitBoard */
	public int numRows() {
		return ROWS;
	}

	/** @return number of columns in this CircuitBoard */
	public int numCols() {
		return COLS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				str.append(board[row][col] + " ");
			}
			str.append("\n");
		}
		return str.toString();
	}

}// class CircuitBoard
