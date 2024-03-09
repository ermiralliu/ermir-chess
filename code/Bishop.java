public class Bishop extends chessPiece{
    public Bishop(colorType WhiteOr){
    	super(WhiteOr);
    	if(WhiteOr == colorType.WHITE)
            this.setImageString("pieces/w-bishop.png", 'B');
        else
            this.setImageString("pieces/b-bishop.png", 'b');
    }

    public void checkMove(int x, int y, ChessBox[][] boxes){
    	
    	for(int i=1; i<8; i++) {
        	if(this.moveOrTake(x+i, y+i, boxes))
        		break;
    	}
        	
    	for(int i=-1; i>-8; i--) {
    		if(this.moveOrTake(x+i, y+i, boxes))
        		break;
    	}
    	
    	for(int i=1; i<8; i++)
    		if(this.moveOrTake(x+i, y-i, boxes))
    			break;
    		
    	
    	for(int i=-1; i>-8; i--) {
    		if(this.moveOrTake(x+i, y-i, boxes))
    			break;
    	}
    		
    		
    }
}