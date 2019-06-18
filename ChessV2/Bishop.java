/*
 * Matt Au
 * APCSA Per. 3A
 * May 2017
*/
import java.awt.image.BufferedImage;

public class Bishop extends Piece {
	
	private Position pos;
	private BufferedImage img;
	
	public Bishop(boolean isBlack, int x, int y){
		super(isBlack);
		if(isBlack){
				img = loadBufferedImage("/Pictures/Bishop-B.png");
		}else{
				img = loadBufferedImage("/Pictures/Bishop-W.png");
		}
		
		pos = new Position(x,y);
	}
	
	public BufferedImage getImage(){
		return img;
	}
	
	public double move(int x, int y){
		N.chessBoard[pos.getX()][pos.getY()] = null;
		pos.changePosition(x, y);
		N.chessBoard[x][y] = this;	
		
		return Math.sqrt(Math.pow((pos.getX() - x), 2) + Math.pow((pos.getY() - y), 2));
	}
	
	public boolean check(int x, int y){
		if(pos.getX() == x || pos.getY() == y) return false;
		
		for(int row = 0; row < N.chessBoard.length; row++){
			for(int col = 0; col < N.chessBoard[0].length; col++){
				if(row > Math.min(y, pos.getY()) && row < Math.max(y, pos.getY())){
					if(col > Math.min(x, pos.getX()) && col < Math.max(x, pos.getX())){
						if((col - pos.getX()) == ((y-pos.getY())/(x-pos.getX())*(row - pos.getY()))){
							if(N.chessBoard[col][row] != null) return false;
						}
					}
				}
			}
		}
		
		return (Math.abs(((double)y-pos.getY())/(x-pos.getX()))==1);
	}
}