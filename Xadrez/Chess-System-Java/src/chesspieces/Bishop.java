package chesspieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece{

	public Bishop(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "X";	
	}

	@Override
	public boolean[][] possibleMoves() {
		
		boolean[][] mat = new boolean[getBoard().getRow()][getBoard().getColumn()];
		Position p = new Position(0,0);
		
		//Up - Right
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		while (getBoard().positionExist(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() + 1);
		}
		if(getBoard().positionExist(p) && isThereOpponentPiece(p)) {mat[p.getRow()][p.getColumn()] = true;}
		
		//Up - Left
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		while (getBoard().positionExist(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() - 1);
		}
		if(getBoard().positionExist(p) && isThereOpponentPiece(p)) {mat[p.getRow()][p.getColumn()] = true;}
		
		//Down - Right
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		while (getBoard().positionExist(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() + 1);
		}
		if(getBoard().positionExist(p) && isThereOpponentPiece(p)) {mat[p.getRow()][p.getColumn()] = true;}
		
		//Down - Left
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		while (getBoard().positionExist(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() - 1);
		}
		if(getBoard().positionExist(p) && isThereOpponentPiece(p)) {mat[p.getRow()][p.getColumn()] = true;}
		
		return mat;
	}
}