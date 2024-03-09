public class Rook extends chessPiece{
    public Rook(colorType WhiteOr){
    	super(WhiteOr);
    	if(WhiteOr == colorType.WHITE)
            this.setImageString("pieces/w-rook.png", 'R');
        else
            this.setImageString("pieces/b-rook.png", 'r');
    }

    public void checkMove(int x, int y, ChessBox[][] boxes){
    	for(int i=1; i<8; i++) {
    		if(this.moveOrTake(x, y+i, boxes))
        		break;
    	}
        	
    	for(int i=1; i<8; i++) {
    		if(this.moveOrTake(x, y-i, boxes))
        		break;
    	}
    	
    	for(int i=1; i<8; i++) {
    		if(this.moveOrTake(x+i, y, boxes))
    			break;
    	}
    	
    	for(int i=1; i < 8; i++) {
    		if(this.moveOrTake(x-i, y, boxes))
    			break;
    	}
    }
}