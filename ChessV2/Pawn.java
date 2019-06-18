import java.awt.image.BufferedImage;

public class Pawn extends Piece {
	
	private Position pos;
	private BufferedImage img;
	
	public Pawn(boolean isBlack, int x, int y){
		super(isBlack);
		if(isBlack){
				img = loadBufferedImage("/Pictures/Pawn-B.png");
		}else{
				img = loadBufferedImage("/Pictures/Pawn-W.png");
		}
		
		pos = new Position(x,y);
	}
	
	public boolean check(int x, int y){ 
		if(N.chessBoard[x][y] != null && Math.sqrt(Math.pow((pos.getX() - x), 2) + Math.pow((pos.getY() - y), 2)) == 1) return false;
		if(pos.getX() == x && pos.getY() == y) return false;
		
		if(isBlack()){
			if(y < pos.getY()) return false;
			if(pos.getY() == 1){
				if(Math.sqrt(Math.pow((pos.getX() - x), 2) + Math.pow((pos.getY() - y), 2)) == 2&& x==pos.getX()) return true;
				else if(Math.sqrt(Math.pow((pos.getX() - x), 2) + Math.pow((pos.getY() - y), 2)) == 1&&x==pos.getX() || (Math.pow((pos.getX() - x), 2) + Math.pow((pos.getY() - y), 2) == (2)) && N.chessBoard[x][y]!= null) return true;
			}else if(((Math.sqrt(Math.pow((pos.getX() - x), 2) + Math.pow(pos.getY() - y, 2)) == 1 && x == pos.getX()) || (Math.pow((pos.getX() - x), 2) + Math.pow((pos.getY() - y), 2) == (2)) && N.chessBoard[x][y]!= null)) return true;
		}else if(!isBlack()){
			if(y > pos.getY()) return false;
			if(pos.getY() ==  6){
				if(Math.sqrt(Math.pow((pos.getX() - x), 2) + Math.pow((pos.getY() - y), 2)) == 2 && x==pos.getX()) return true;
				else if(Math.sqrt(Math.pow((pos.getX() - x), 2) + Math.pow((pos.getY() - y), 2)) == 1 && x==pos.getX() || (Math.pow((pos.getX() - x), 2) + Math.pow((pos.getY() - y), 2) == (2)) && N.chessBoard[x][y]!= null) return true;
			}else if(((Math.sqrt(Math.pow((pos.getX() - x), 2) + Math.pow((pos.getY() - y), 2)) == 1 && x == pos.getX()) || (Math.pow((pos.getX() - x), 2) + Math.pow((pos.getY() - y), 2) == (2))&& N.chessBoard[x][y]!= null)) return true;
		}
		return false;
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
	
	public void checkPromote(int x, int y){
		
		if(!isBlack()){
			if(y == 0){
				N.chessBoard[x][y] = new Queen(false, x, y);
			}
		}else if(isBlack()){
			if(y == 7){
				N.chessBoard[x][y] = new Queen(true, x, y);
			}
		}
	}
	
}
