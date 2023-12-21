package chess;

import boardgame.Board;
import chesspieces.Bishop;
import chesspieces.King;
import chesspieces.Knight;
import chesspieces.Pawn;
import chesspieces.Queen;
import chesspieces.Rook;

public class ChessMatch {
	private Board board;
	
	public ChessMatch() {
		board = new Board(8, 8);
	}
	
	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat = new ChessPiece[board.getRow()][board.getColumn()];
		for (int i = 0; i < board.getRow(); i++) {
			for (int j = 0; j < board.getColumn(); j++) {
				mat[i][j] = (ChessPiece)board.getPiece(i, j);
			}
		}
		return mat;
	}
	
	public void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}
	
	public void initialSetup() {
		for (int i = 0; i <= board.getColumn() - 1; i++) { placeNewPiece((char)('a'+i), 2,  new Pawn(board, Color.WHITE)); }
		placeNewPiece('a', 1, new Rook  (board, Color.WHITE));
		placeNewPiece('b', 1, new Knight(board, Color.WHITE));
		placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
		placeNewPiece('d', 1, new Queen (board, Color.WHITE));
		placeNewPiece('e', 1, new King  (board, Color.WHITE));
		placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
		placeNewPiece('g', 1, new Knight(board, Color.WHITE));
		placeNewPiece('h', 1, new Rook  (board, Color.WHITE));
		
		
		for (int i = 0; i <= board.getColumn() - 1; i++) { placeNewPiece((char)('a'+i), 7,  new Pawn(board, Color.BLACK)); }
		placeNewPiece('a', 8, new Rook  (board, Color.BLACK));
		placeNewPiece('b', 8, new Knight(board, Color.BLACK));
		placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
		placeNewPiece('d', 8, new Queen (board, Color.BLACK));
		placeNewPiece('e', 8, new King  (board, Color.BLACK));
		placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
		placeNewPiece('g', 8, new Knight(board, Color.BLACK));
		placeNewPiece('h', 8, new Rook  (board, Color.BLACK));
	}
}