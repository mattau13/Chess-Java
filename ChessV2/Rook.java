/*
 * Matt Au
 * APCSA Per. 3A
 * May 2017
*/
import java.awt.image.BufferedImage;

public class Rook extends Piece {
	
	private int moveCount;
	
	private Position pos;
	private BufferedImage img;
	
	public Rook(boolean isBlack, int x, int y){
		super(isBlack);
		if(isBlack){
				img = loadBufferedImage("/Pictures/Rook-B.png");
		}else{
				img = loadBufferedImage("/Pictures/Rook-W.png");
		}
		
		pos = new Position(x,y);
		moveCount = 0;
	}
	
	public BufferedImage getImage(){
		return img;
	}
	
	public double move(int x, int y){
		N.chessBoard[pos.getX()][pos.getY()] = null;
		pos.changePosition(x, y);
		N.chessBoard[x][y] = this;	
		moveCount++;
		
		return Math.sqrt(Math.pow((pos.getX() - x), 2) + Math.pow((pos.getY() - y), 2));
	}
	
	public boolean check(int x, int y){
		if(pos.getX() == x && pos.getY() == y) return false;
		
		if(x == pos.getX()){
			for(int rep = 0; rep < 8; rep++){
				if(rep < Math.max(y, pos.getY()) && rep > Math.min(y, pos.getY())){
					if(N.chessBoard[x][rep] != null) return false;
				}
			}
		}else{
			for(int rep = 0; rep < 8; rep++){
				if(rep < Math.max(x, pos.getX()) && rep > Math.min(x, pos.getX())){
					if(N.chessBoard[rep][y] != null) return false;
				}
			}
		}
		
		return ( x == pos.getX()) || ( y == pos.getY());
	}
	
}