/*
 * Matt Au
 * APCSA Per. 3A
 * May 2017
*/
import java.awt.image.BufferedImage;

public class Queen extends Piece {
	
	private Position pos;
	private BufferedImage img;
	
	public Queen(boolean isBlack, int x, int y){
		super(isBlack);
		if(isBlack){
				img = loadBufferedImage("/Pictures/Queen-B.png");
		}else{
				img = loadBufferedImage("/Pictures/Queen-W.png");
		}
		
		pos = new Position(x,y);
	}
	
	public BufferedImage getImage(){
		return img;
	}
	
	public boolean check(int x, int y){
		if(pos.getX() == x && pos.getY() == y) return false;
		
		if(x != pos.getX() && y != pos.getY()){
			for(int row = 0; row < N.chessBoard.length; row++){
				for(int col = 0; col < N.chessBoard[0].length; col++){
					if(row > Math.min(y, pos.getY()) && row < Math.max(y, pos.getY())){
							if((col - pos.getX()) == ((x-pos.getX())/(y-pos.getY())*(row - pos.getY()))){
								if(N.chessBoard[col][row] != null) return false;
							}
					}
				}
			}
		}

		if(x == pos.getX()){
			for(int rep = 0; rep < 8; rep++){
				if(rep < Math.max(y, pos.getY()) && rep > Math.min(y, pos.getY())){
					if(N.chessBoard[x][rep] != null) return false;
				}
			}
		}else if(y == pos.getY()){
			for(int rep = 0; rep < 8; rep++){
				if(rep < Math.max(x, pos.getX()) && rep > Math.min(x, pos.getX())){
					if(N.chessBoard[rep][y] != null) return false;
				}
			}
		}
		
		return (x== pos.getX()) || (y==pos.getY()) || (Math.abs(((double)y-pos.getY())/(x-pos.getX()))==1);
	}
	public double move(int x, int y){
		N.chessBoard[pos.getX()][pos.getY()] = null;
		pos.changePosition(x, y);
		N.chessBoard[x][y] = this;	
		
		return Math.sqrt(Math.pow((pos.getX() - x), 2) + Math.pow((pos.getY() - y), 2));
	}
}
