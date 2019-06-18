import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class N extends JFrame{
	
	public static Piece[][] chessBoard = new Piece[8][8];
	public static boolean[][] whiteControl = new boolean[8][8];
	public static boolean[][] blackControl = new boolean[8][8];

	public N(String title){
		super(title);

		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setResizable(false);
		
		G panel = new G();
		panel.setPreferredSize(new Dimension(800, 600));
		super.add(panel);
		super.pack();
		
	}
	
	public static void main(String[] args){
		loadBoard();
		
		N window = new N("Chess");
		window.repaint(); //Re-Calls Paint Component
		
	}
	
	public static void loadBoard(){
		for(int rep = 0; rep < chessBoard.length; rep++){
			chessBoard[rep][1] = new Pawn(true, rep, 1);
			chessBoard[rep][6] = new Pawn(false, rep, 6);
		}
		//Queens
		chessBoard[3][7] = new Queen(false, 3, 7);
		chessBoard[3][0] = new Queen(true, 3, 0);
		//Kings
		chessBoard[4][7] = new King(false, 4, 7);
		chessBoard[4][0] = new King(true, 4, 0);
		//Rooks
		chessBoard[0][0] = new Rook(true, 0, 0);
		chessBoard[7][0] = new Rook(true, 7, 0);
		chessBoard[0][7] = new Rook(false, 0, 7);
		chessBoard[7][7] = new Rook(false, 7, 7);
		//Bishop
		chessBoard[2][0] = new Bishop(true, 2, 0);
		chessBoard[5][0] = new Bishop(true, 5, 0);
		chessBoard[2][7] = new Bishop(false, 2, 7);
		chessBoard[5][7] = new Bishop(false, 5, 7);
		//Knight
		chessBoard[1][0] = new Knight(true, 1, 0);
		chessBoard[6][0] = new Knight(true, 6, 0);
		chessBoard[1][7] = new Knight(false, 1, 7);
		chessBoard[6][7] = new Knight(false, 6, 7);
		
	}	
}
