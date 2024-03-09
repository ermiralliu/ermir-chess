import java.awt.Color;
import javax.swing.JButton;

public class ChessBox extends JButton{
	private static final long serialVersionUID = 2259931303000799391L;
	private chessPiece piece;
	public colorType Type= colorType.EMPTY;
	boolean activatedOnce = false;
	Color SussyColor = new Color(50, 50, 250);
	
	public static ChessBox[][] initializeButtons() {
		ChessBox[][] boxes= new ChessBox[8][8];
		
		for(int i=0;i<64;i++) {
			boxes[i/8][i%8]= new ChessBox();	
		}
/*		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
			boxes[j][i]= new ChessBox();
        	if ((i+j)%2 == 0)	
    			boxes[j][i].setBackground(new Color(250, 240, 220));
    		else
    			boxes[j][i].setBackground(new Color(90, 60, 50));
			}
        }
*/		
		for(int i=0; i<8; i+=7){
            boxes[0][i].piece = new Rook(colorType.BLACK);
            boxes[7][7-i].piece = new Rook(colorType.WHITE);
        }
        for(int i=1; i<8; i+=5){
            boxes[0][i].piece = new Knight(colorType.BLACK);
            boxes[7][7-i].piece = new Knight(colorType.WHITE);
        }
        for(int i=2; i<8; i+=3){
            boxes[0][i].piece= new Bishop(colorType.BLACK);
            boxes[7][7-i].piece = new Bishop(colorType.WHITE);
        }

        boxes[0][3].piece = new Queen(colorType.BLACK);
        boxes[7][3].piece = new Queen(colorType.WHITE);
        boxes[0][4].piece = new King(colorType.BLACK);
        //blackKing = boxes[4];
        boxes[7][4].piece = new King(colorType.WHITE);
        //whiteKing= boxes[27];
        

        for(int i=0; i<8; i++){
            boxes[1][i].piece = new Pawn(colorType.BLACK);
            boxes[6][7-i].piece = new Pawn(colorType.WHITE);
        }
        
        for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if(i==0 || i==1) {
					boxes[i][j].setIcon(boxes[i][j].piece.getImage());
					boxes[i][j].Type = colorType.BLACK;
				}
					
				else if(i==6 || i==7) {
					boxes[i][j].setIcon(boxes[i][j].piece.getImage());
					boxes[i][j].Type = colorType.WHITE	;
				}	
			}
        }
		return boxes;
	}
	
	public boolean move(int x, int y, ChessBox second) { //maybe use this. but idk e bera boolean qe ta kontrolloje
			second.piece = this.piece;
			second.piece.reInitCanMove();
			second.setIcon(this.piece.getImage());
			second.Type = this.Type;
		
			this.setIcon(null);
			this.piece = null;
			this.Type = colorType.EMPTY;
			return true;
	}
	
	public boolean allowMove(int x,int y) {
		return this.piece.allowMove(x, y);
	}
	
	public boolean move(int x, int y, ChessBox second, ChessBox[][] boxes) {
		if(this.piece.allowMove(x,y)) { //enPassant
		int i =1;
		if(this.Type == colorType.WHITE)
			i=-1;	
		if( this.piece instanceof Pawn && this.Type != boxes[y-i][x].Type && boxes[y-i][x].getPieceType()== Pawn.class) {
			
			boxes[y-i][x].setIcon(null);
			boxes[y-i][x].piece = null;
			boxes[y-i][x].Type = colorType.EMPTY;
			return (move(x, y, second));
		}
		else
			return (move(x, y, second));
		}
		return false;
	}
	
	public colorType getType() {
		return this.Type;
	}

	public Class<? extends chessPiece> getPieceType() {
		if(this.piece != null)
			return this.piece.getClass();
		else
			return null;
	}
	
	public void pawnSupport(int Turn) {
			((Pawn) this.piece).setPassant(Turn);
	}
	
	public boolean pawnPassant() {
		if(this.piece != null && this.piece.getClass() == Pawn.class)
			return ((Pawn)this.piece).getPassant();
		else
			return false;
	}
	public void pawnUnPassant(int Turn) {
		if(this.piece != null && this.piece.getClass() == Pawn.class)
			((Pawn)this.piece).unSetPassant(Turn);
	}
	
	public void activateButton(int posX, int posY, ChessBox[][] boxes, boolean showMoves) {
		if(!activatedOnce) {
			this.piece.checkMove(posX, posY, boxes);
			activatedOnce = true;
		}
		if(showMoves)
		for(int i=0; i<8; i++) {
			for(int j=0; j<8;j++) {
				if(this.piece.allowMove(i, j))
					boxes[j][i].setBackground(SussyColor);
			}
		}
	}
	
	public String getGameState(ChessBox[][] boxes) {
		String gameState = "";
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if(boxes[i][j].Type == colorType.EMPTY)
					gameState+='0';
				else
					gameState+= boxes[i][j].piece.getId();					
			}
        }
		return gameState;
	}
	
}