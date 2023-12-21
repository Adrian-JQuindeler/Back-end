package chess;

import boardgame.Position;

public class ChessPosition {
	
	private char column;
	private int row;
	
	public ChessPosition(char column, int row) {
		if (column < 'a'|| column > 'h' || row < 1 || row > 8) { throw new ChessException("Invalid position, columns must be between 'a' and 'h' and rows between 1 and 8"); }
		this.column = column;
		this.row = row;
	}
	
	public Position toPosition() {
		return new Position(8 - row, column - 'a');
	}
	
	public static ChessPosition fromPosition(Position position) {
		return new ChessPosition((char)('a' + position.getColumn()), 8 - position.getRow());
	}
	

	@Override
	public String toString() {
		return column + ", " + row;
	}

	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}
}