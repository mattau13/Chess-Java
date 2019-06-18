import java.awt.image.BufferedImage;

public class Knight extends Piece {
	
	private Position pos;
	private BufferedImage img;
	
	public Knight(boolean isBlack, int x, int y){
		super(isBlack);
		if(isBlack){
				img = loadBufferedImage("/Pictures/Knight-B.png");
		}else{
				img = loadBufferedImage("/Pictures/Knight-W.png");
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
		return Math.sqrt(Math.pow(x-pos.getX(),2) + Math.pow(y-pos.getY(),2)) == Math.sqrt(5);
	}
}
