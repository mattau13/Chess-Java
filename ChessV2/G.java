import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class G extends JPanel implements MouseListener {
	public static int x;
	public static int y;
	public static int whiteMove = 1; //1 = White's Move, 0 = Black's Move.
	public static boolean moveOn;
	public static boolean MOVED = false;
	public final BufferedImage t = loadBufferedImage("/Pictures/Transparent.png");
	
	public static boolean possMoves[][] = new boolean[8][8];
	
	public G(){
		addMouseListener(this);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		int tileSize = 50;
		int width = 8;
		int height = 8;
		//int printCount = 0;
		g2d.setColor(new Color(49,46,43));
		g2d.fillRect(0, 0, 1000, 1000);
		g2d.setColor(new Color(80, 80, 80));
		g2d.fillRect(180, 80, 440, 440);
		String lets = "abcdefgh";
		
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				if((x+y)%2 == 0) g2d.setColor(new Color(229,185,128));
				else g2d.setColor(new Color(158,111,50));
				
				g2d.fillRect((x*tileSize)+ 200, (y*tileSize) + 100, tileSize, tileSize);
				
				if(x == 0){ 
					g2d.setColor(new Color(229,185,128));
					g2d.drawString(Integer.toString((8-y)), (x*tileSize)+185, (y*tileSize)+125);
				} 
				if(y == 7){
					g2d.setColor(new Color(229,185,128));
					g2d.drawString(lets.substring(x, x+ 1), (x*tileSize)+215, (y*tileSize)+165);
				}
			}
		}
		
		
		g2d.setColor(new Color(255, 255, 255));
		for(int row = 0; row < 8; row++){
			for(int col = 0; col < 8; col++){
				if(possMoves[row][col]){
					g2d.drawImage(t, (row*tileSize)+ 200, (col*tileSize) + 100, tileSize, tileSize, null);
				}
			}
		}
		
		for(int row = 0; row < N.chessBoard.length; row++){
			for(int col = 0; col < N.chessBoard[0].length; col++){
				if(N.chessBoard[row][col] == null) continue;
				g2d.drawImage(N.chessBoard[row][col].getImage(), (row*tileSize)+ 200, (col*tileSize) + 100, 50, 50, null);
			}
		}
	}
	
	public void checkMove(int fX, int fY){
		int tempX = x;
		int tempY = y;
		
		Piece p = N.chessBoard[fX][fY];
		
		System.out.println(fX +" "+  fY + " " + N.chessBoard[fX][fY]);
		if(N.chessBoard[fX][fY] == null || (N.chessBoard[fX][fY] != null && N.chessBoard[x][y].getIsBlack() != N.chessBoard[fX][fY].getIsBlack())){
				if(N.chessBoard[x][y].check(fX, fY)){
					checkCastle(N.chessBoard[x][y].move(fX, fY), fX, fY);
					if(!checkCheck(whiteMove)){
						N.chessBoard[fX][fY].checkPromote(fX, fY);
						System.out.println(N.chessBoard[fX][fY]);
						System.out.println("MOVED");
						whiteMove = (1+whiteMove)%2;
						
						MOVED = true;
						this.repaint();
						moveOn = false;
					}else{
						System.out.println("V!");
						N.chessBoard[fX][fY].move(tempX, tempY);
						N.chessBoard[fX][fY] = p;
						moveOn = false;
					}
				}else{
					System.out.println("Illegal Move!, Try Again");
					moveOn = false;
				}
		} 
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(!moveOn){
			x = (e.getX() - 202)/50;
			y = (e.getY() - 100)/50;
			System.out.println(x + " " + y);
			if(N.chessBoard[x][y] == null) return;
			
			if(!N.chessBoard[x][y].getIsBlack() && whiteMove == 0){
				System.out.println("Black's Move!");
				return;
			}else if(N.chessBoard[x][y].getIsBlack() && whiteMove == 1){
				System.out.println("White's Move");
				return;
			}
			
			moveOn = true;
			viableMoves(x, y);
		}else{
			possMoves = new boolean[8][8];
			repaint();
			checkMove((e.getX()-202)/50, (e.getY()-100)/50);

		}
	}
	
	public void checkCastle(double length, int fX, int fY){
		if(!N.chessBoard[fX][fY].getName().equalsIgnoreCase("King")) return;
		System.out.println(length);
		if(length == 2.0){
			if(fX == 6 && fY == 7){
				N.chessBoard[7][7] = null;
				N.chessBoard[5][7] = new Rook(false, 5, 7);
			}else if(fX == 2 && fY == 7){
				N.chessBoard[0][7] = null;
				N.chessBoard[3][7] = new Rook(false, 3, 7);
			}else if(fX == 2 && fY == 0){
				N.chessBoard[0][0] = null;
				N.chessBoard[3][0] = new Rook(true, 2, 0);
			}else if(fX == 6 && fY == 0){
				N.chessBoard[7][0] = null;
				N.chessBoard[5][0] = new Rook(true, 2, 0);
			}
		}
	}
	
	public boolean checkCheck(int colorVal){ // 1 is White, 0 is Black
		Position king = findKing(colorVal);
		for(int row = 0; row < 8; row++){
			for(int col = 0; col < 8; col++){
				if(N.chessBoard[row][col] == null) continue;
				if(row == king.getX() && col == king.getY()) continue;
				if(N.chessBoard[row][col].getIsBlack() != N.chessBoard[king.getX()][king.getY()].getIsBlack()){
					if(N.chessBoard[row][col].check(king.getX(), king.getY())){ 
						System.out.println(row + " x " + col);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public Position findKing(int colorVal){ // 1 is White, 0 is Black
		if(colorVal == 1){
			for(int row = 7; row >=0; row--){
				for(int col = 7; col >= 0; col--){
					if(N.chessBoard[row][col] == null) continue;
					if(N.chessBoard[row][col].getName().equalsIgnoreCase("King") && !N.chessBoard[row][col].isBlack()){
						return new Position(row, col);
					}
				}
			}
		}else if(colorVal == 0){
			for(int row = 7; row >=0; row--){
				for(int col = 7; col >= 0; col--){
					if(N.chessBoard[row][col] == null) continue;
					if(N.chessBoard[row][col].getName().equalsIgnoreCase("King") && N.chessBoard[row][col].isBlack()){
						return new Position(row, col);
					}
				}
			}
		}
		
		return null;
	}
	
	public void viableMoves(int x, int y){
		possMoves = new boolean[8][8];
		for(int row = 0; row < 8; row++){
			for(int col = 0; col < 8; col++){
				if(N.chessBoard[x][y].check(row, col) && (N.chessBoard[row][col] == null || (N.chessBoard[row][col] != null && N.chessBoard[x][y].getIsBlack() != N.chessBoard[row][col].getIsBlack()))){
					possMoves[row][col] = true;
				}
			}
		}
		repaint();
	}
	
	public BufferedImage loadBufferedImage(String string) //Credit
	{
	    try
	    {
	        BufferedImage bi = ImageIO.read(this.getClass().getResource(string));
	        return bi;
	    } catch (IOException e)
	    {
	        e.printStackTrace();
	    }
	    return null;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
