package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chesspieces.Bishop;
import chesspieces.King;
import chesspieces.Knight;
import chesspieces.Queen;
import chesspieces.Rook;

public class ChessMatch {
	private int turn;
    private Color currentPlayer;
	private Board board;
	private boolean check;
	private boolean checkMate;
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
	
	public ChessMatch() {
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
	}
	
	private void nextTurn(){
	    turn++;
	    currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
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
	
	public ChessPiece performChessMove(ChessPosition sourcePos, ChessPosition targetPos) {
		
		Position source = sourcePos.toPosition();
		Position target = targetPos.toPosition();
		
		validatePosition(source);
		validatePosition(source, target);
		
		Piece captured = makeAMove(source, target);
		
		if(testCheck(currentPlayer)) { 
			undoMove(source, target, (ChessPiece)captured);
			throw new ChessException("You can't put yourself in check"); 
		}
		
		check = testCheck(opponent(currentPlayer));
		if(testChekcMate(opponent(currentPlayer))) { checkMate = true; }
		else { nextTurn(); }
		
		return (ChessPiece)captured;
	}

	private Piece makeAMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		Piece captured = board.removePiece(target);
		board.placePiece(p, target);
		if(captured != null) {
			piecesOnTheBoard.remove(captured);
			capturedPieces.add(captured);
		}
		return captured;
	}
	
	private void undoMove(Position source, Position target, ChessPiece capturedPiece) {
		Piece p = board.getPiece(target);
		board.removePiece(target);
		board.placePiece(p, source);
		
		if(capturedPiece != null) {
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
			board.placePiece(capturedPiece, target);
		}
	}

	private void validatePosition(Position source) {
		if(!board.thereIsAPiece(source)) { throw new ChessException("There is no piece on source position!"); }
		
		if(currentPlayer != ((ChessPiece)board.getPiece(source)).getColor()){ throw new ChessException("This piece isn't yours!"); }
		
		if(!board.getPiece(source).isThereAnyPossibleMoves()) { throw new ChessException("There is no possible move for this piece!"); }
	}
	
	private void validatePosition(Position source, Position target) {
		if(!board.getPiece(source).possibleMove(target)) {
			throw new ChessException("The chosen piecen can't move to target position");
		}
	}

	public void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
	}
	
	public boolean[][] possibleMoves(ChessPosition chessPosition){
		Position position = chessPosition.toPosition();
		validatePosition(position);
		return board.getPiece(position).possibleMoves();
	}
	
	public Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	public ChessPiece king(Color color) {
		List<Piece> pieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for (Piece piece : pieces) {
			if (piece instanceof King) {
				return (ChessPiece)piece;
			}
		}
		throw new IllegalStateException("There is no" + color + "King on the board");
	}
	
	public boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Piece> pieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
		for (Piece piece : pieces) {
			boolean[][] mat = piece.possibleMoves();
			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		return false;
	}
	
	public boolean testChekcMate(Color color) {
		if(!testCheck(color)) {
			return false;
		}
		
		List<Piece> pieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for (Piece piece : pieces) {
			boolean[][] mat = piece.possibleMoves();
			for (int i = 0; i < board.getRow(); i++) {
				for (int j = 0; j < board.getColumn(); j++) {
					if (mat[i][j]) {
						Position source = ((ChessPiece)piece).getChessPosition().toPosition();
						Position target = new Position(i, j);
						Piece captured = makeAMove(source, target);
						boolean check = testCheck(color);
						undoMove(source, target, (ChessPiece)captured);
						if (!check) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	public void initialSetup() {
		/*for (int i = 0; i <= board.getColumn() - 1; i++) { placeNewPiece((char)('a'+i), 2,  new Pawn(board, Color.WHITE)); }
		placeNewPiece('a', 1, new Rook  (board, Color.WHITE));
		placeNewPiece('b', 1, new Knight(board, Color.WHITE));
		placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
		placeNewPiece('d', 1, new Queen (board, Color.WHITE));
		placeNewPiece('e', 1, new King  (board, Color.WHITE));
		placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
		placeNewPiece('g', 1, new Knight(board, Color.WHITE));
		placeNewPiece('h', 1, new Rook  (board, Color.WHITE));
		
		
		//for (int i = 0; i <= board.getColumn() - 1; i++) { placeNewPiece((char)('a'+i), 7,  new Pawn(board, Color.BLACK)); }
		placeNewPiece('a', 8, new Rook  (board, Color.BLACK));
		placeNewPiece('b', 8, new Knight(board, Color.BLACK));
		placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
		placeNewPiece('d', 8, new Queen (board, Color.BLACK));
		placeNewPiece('e', 8, new King  (board, Color.BLACK));
		placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
		placeNewPiece('g', 8, new Knight(board, Color.BLACK));
		placeNewPiece('h', 8, new Rook  (board, Color.BLACK));*/
		placeNewPiece('b', 1, new Rook  (board, Color.WHITE));
		placeNewPiece('e', 1, new King  (board, Color.WHITE));
		placeNewPiece('h', 1, new Rook  (board, Color.WHITE));
		placeNewPiece('b', 8, new Rook  (board, Color.BLACK));
		placeNewPiece('a', 8, new King  (board, Color.BLACK));
		placeNewPiece('h', 8, new Rook  (board, Color.BLACK));

	}
	
	public int getTurn() {
		return turn;
	}
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean isCheck() {
		return check;
	}

	public boolean isCheckMate() {
		return checkMate;
	}

}
