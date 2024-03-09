public class King extends chessPiece{
//public int posX;
//public int posY;
	
    public King(colorType WhiteOr){
    	super(WhiteOr);
    	if(WhiteOr == colorType.WHITE)
            this.setImageString("pieces/w-king.png", 'K');
        else
        	this.setImageString("pieces/b-king.png", 'k');
    }

    public void checkMove(int x, int y, ChessBox[][] boxes){
    	for(int i=-1; i<=1; i++){
    		for(int j=-1; j<=1;j++){
    			if(i ==0 && j==0)
    				continue;	
    			this.moveOrTake(x+i, y+j, boxes);
    			
    		}
    	}
    }
}