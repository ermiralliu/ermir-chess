public class Pawn extends chessPiece{
	private boolean canBeEnPassed = false;
	private int enPassantTurn;
	
	public void setPassant(int turn){
			this.enPassantTurn = turn;
			this.canBeEnPassed = true;
	}
	public boolean getPassant() {
			return this.canBeEnPassed;
	}
	public void unSetPassant(int turn) {
		if(turn - enPassantTurn >=2)
			this.canBeEnPassed = false;
	}
	public Pawn(colorType WhiteOr){
    	super(WhiteOr);
    	if(WhiteOr == colorType.WHITE)
            this.setImageString("pieces/w-pawn.png", 'P');
        else
            this.setImageString("pieces/b-pawn.png", 'p');
    }

    public void checkMove(int x, int y, ChessBox[][] boxes){
    	int i=1;
    	if(this.pieceColor == colorType.WHITE)
    		i=-1;
    	this.moveOrTake(x, y+i, boxes);
    	
    	if(!movedOnce)
    		this.doubleMove(x, y+2*i, boxes);
    	//if( y== 3.5 + 0.5*i )
    		this.enPassant(x, y, i, boxes);
    }
    @Override
    public boolean moveOrTake(int x, int y, ChessBox[][] boxes){
    	if(y >= 0 && y <8) {
    		if(boxes[y][x].Type == colorType.EMPTY)	
    			this.canMove[x][y]=true;
    		if(x-1>=0 && boxes[y][x-1].Type != colorType.EMPTY && boxes[y][x-1].Type != this.pieceColor)
    			this.canMove[x-1][y]= true;
    		if(x+1<8 && boxes[y][x+1].Type != colorType.EMPTY && boxes[y][x+1].Type != this.pieceColor)
    			this.canMove[x+1][y]= true;
    	}
    	return true;
    }
    
    public void enPassant(int x, int y, int i ,ChessBox[][] boxes) {
    	if(x-1>=0 && boxes[y][x-1].Type != this.pieceColor && boxes[y][x-1].pawnPassant())
			this.canMove[x-1][y+i]= true;
		if(x+1<8 && boxes[y][x+1].Type != this.pieceColor && boxes[y][x+1].pawnPassant())
			this.canMove[x+1][y+i]= true;
    }
    
    public void doubleMove(int x, int y, ChessBox[][] boxes) {
    	if(boxes[y][x].Type == colorType.EMPTY)	
			this.canMove[x][y]=true;
    }
}