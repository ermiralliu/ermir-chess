import javax.swing.ImageIcon;

public abstract class chessPiece {
	protected boolean movedOnce =false;
    protected colorType pieceColor;
    protected boolean[][] canMove;
    private ImageIcon image;
    private char identifier; //lowercase for blacks, uppercase for whites, n for knights
    
    public chessPiece(colorType WhiteOr){
        this.pieceColor = WhiteOr;
        canMove = new boolean[8][8];
    }
    
    public char getId() {
    	return this.identifier;
    }
    
    public void reInitCanMove() {
    	movedOnce=true;
    	this.canMove = new boolean [8][8];
    }
    
    public Boolean isWhite() {
    		return (pieceColor == colorType.WHITE);
    }
    
    public void setImageString(String str, char id) {
        this.image = new ImageIcon(str);
        this.identifier = id;
    }
       
   public boolean allowMove(int x,int y) {
	   return canMove[x][y];
   }
   
   public void cantMove(int x, int y) {
	   canMove[x][y] = false;
   }
    public ImageIcon getImage() {
    	return this.image;
    }

    public abstract void checkMove(int posX, int posY, ChessBox[][] boxes); // checks possible moves

    public boolean moveOrTake(int x, int y, ChessBox[][] boxes){
    	
    	if(x >=0 && x <8 && y >= 0 && y <8) {
    		colorType Type = boxes[y][x].getType();
    		if(this.pieceColor != Type) {
    			this.canMove[x][y] = true;
    			if(Type == colorType.EMPTY)	
    				return false;
    			else
    				return true;
    		}
    	}
    	return true;
    }
}