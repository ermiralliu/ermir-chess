public class Queen extends chessPiece{
    public Queen(colorType WhiteOr){
    	super(WhiteOr);
    	if(WhiteOr == colorType.WHITE)
            this.setImageString("pieces/w-queen.png", 'Q');
        else
            this.setImageString("pieces/b-queen.png", 'q');
    }

    public void checkMove(int x, int y, ChessBox[][] boxes){
    	
    	for(int i=1; i<8; i++) {
    		if(this.moveOrTake(x, y+i, boxes))
        		break;
    	}
        	
    	for(int i=-1; i>-8; i--) {
    		if(this.moveOrTake(x, y+i, boxes))
        		break;
    	}
    	
    	for(int i=1; i<8; i++) {
    		if(this.moveOrTake(x+i, y, boxes))
    			break;
    	}
    	
    	for(int i=-1; i>-8; i--) {
    		if(this.moveOrTake(x+i, y, boxes))
    			break;
    	}
    	
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