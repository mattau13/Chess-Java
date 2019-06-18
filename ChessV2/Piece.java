/*
 * Matt Au
 * APCSA Per. 3A
 * May 2017
*/
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Piece {
	
	private boolean isBlack;
	private boolean isCaptured;
	private int moveCount;
	public Piece(boolean isBlack){
		this.setBlack(isBlack);
		this.isCaptured = false;
	}
	
	public void captured(){
		isCaptured = true;
	}
	
	public BufferedImage getImage() {
		return null;
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

	public boolean isBlack() {
		return isBlack;
	}

	public void setBlack(boolean isBlack) {
		this.isBlack = isBlack;
	}
	
	public boolean getIsBlack(){
		return isBlack();
	}
	public boolean check(int x, int y){
		return false;
	}
	public double move(int x, int y){
		return 0.0;
	}
	public void checkPromote(int x, int y){
		return;
	}
	public String getName(){
		return "";
	}
	public int getMoveCount(){
		return moveCount;
	}
}
