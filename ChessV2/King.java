import java.awt.image.BufferedImage;

public class King extends Piece {
	
	private int moveCount;
	
	private Position pos;
	private BufferedImage img;
	
	public King(boolean isBlack, int x, int y){
		super(isBlack);
		if(isBlack){
				img = loadBufferedImage("/Pictures/King-B.png");
		}else{
				img = loadBufferedImage("/Pictures/King-W.png");
		}
		
		pos = new Position(x,y);
		
		moveCount = 0;
	}
	
	public BufferedImage getImage(){
		return img;
	}
	
	public double move(int x, int y){
		double val = Math.sqrt((double)(Math.pow((pos.getX() - x), 2) + Math.pow((pos.getY() - y), 2)));
		
		N.chessBoard[pos.getX()][pos.getY()] = null;
		pos.changePosition(x, y);
		N.chessBoard[x][y] = this;	
		moveCount++;
		
		return val;
	}
	
	public boolean check(int x, int y){
		if(pos.getX() == x && pos.getY() == y) return false;
		
		if( moveCount == 0 && y == pos.getY() && Math.sqrt(Math.pow((pos.getX() - x), 2) + Math.pow((pos.getY() - y), 2)) == 2){
			for(int rep = 0; rep < 8; rep++){
				if(rep < Math.max(x, pos.getX()) && rep > Math.min(x, pos.getX())){
					if(N.chessBoard[rep][y] != null) return false;
				}
			}
			
			if(x == 6 && y == 7 && N.chessBoard[7][7].getMoveCount() == 0) return true;
			if(x == 2 && y == 7 && N.chessBoard[0][7].getMoveCount() == 0) return true;
			if(x == 2 && y == 0 && N.chessBoard[0][0].getMoveCount() == 0) return true;
			if(x == 6 && y == 0 && N.chessBoard[7][0].getMoveCount() == 0) return true;
		}
		
		return Math.sqrt(Math.pow((pos.getX() - x), 2) + Math.pow((pos.getY() - y), 2)) == 1 || Math.sqrt(Math.pow((pos.getX() - x), 2) + Math.pow((pos.getY() - y), 2)) == Math.sqrt(2) ;
	}
	
	public String getName(){
		return "King";
	}
	
}
