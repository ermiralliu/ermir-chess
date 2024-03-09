public class Knight extends chessPiece{

    public Knight(colorType WhiteOr){
    	super(WhiteOr);
    	if(WhiteOr == colorType.WHITE)
            this.setImageString("pieces/w-knight.png", 'N');
        else
            this.setImageString("pieces/b-knight.png", 'n');
    }

    public void checkMove(int x, int y, ChessBox[][] boxes){
        for(int i=-2; i<=2; i++){
        	if(i==0)
            	continue;
            
        	if(i%2==0){                
        		this.moveOrTake(x+i, y-1, boxes);
                this.moveOrTake(x+i, y+1, boxes);
            
        	}
            else{
                this.moveOrTake(x+i, y-2, boxes);
                this.moveOrTake(x+i, y+2, boxes);
            }
        }
    }

}