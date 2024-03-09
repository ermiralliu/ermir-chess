import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class chessBoard implements ActionListener{
	private ChessBox[][] boxes = new ChessBox[8][8];
//	private ChessBox KingOfThis;
	private int kingX;
	private int kingY;
//	private ChessBox blackKingBox;
//	private ChessBox whiteKingBox;
	private colorType turnColor= colorType.WHITE;
	private ChessBox activeBox;
	private JFrame frame = new JFrame("Chess");
	private int initY;
	private int StupidTurn;
	private Color first = new Color(250, 240, 220);
	private Color second = new Color(90, 60, 50);
	
	public void setNormalBackground() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
        	if ((i+j)%2 == 0)	
    			boxes[j][i].setBackground(first);
    		else
    			boxes[j][i].setBackground(second);
			}
        }
	}
	
	public void nextTurn() {
		StupidTurn++;
		setNormalBackground();
		unPassant();
		if(turnColor == colorType.WHITE) {
			turnColor = colorType.BLACK;
		}
		else {		
			turnColor = colorType.WHITE; 
		}	
		weNeedTheKing();
	}

    public chessBoard() {
    	initChessBoard();
    }
    
    public void unPassant() {
    	for(int i=0; i<8;i++) {
    		for(int j=0; j<8; j++) {
    			boxes[j][i].pawnUnPassant(StupidTurn);
    		}
    	}
    }
	
	public void initChessBoard() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(815, 838);
		frame.setLayout(new GridLayout(8,8,0,0));
		boxes = ChessBox.initializeButtons();
//		whiteKingBox = boxes[7][4];
//		blackKingBox = boxes[0][4];
		setNormalBackground();
		for(int i=0; i<8;i++) {
			for(int j=0; j<8; j++) {
				frame.add(boxes[i][j]);
				boxes[j][i].addActionListener((ActionListener) this);
			}
		}
		frame.setVisible(true);
	}
	
	public void weNeedTheKing() {
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++){
				if(boxes[j][i].getPieceType() == King.class && boxes[j][i].getType() == turnColor) {
					kingX = i;
					kingY = j;
				}	
			}
		}
	}
//the most important out of any	
	public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < 8; i++) {
			  for (int j = 0; j < 8; j++) {
				  if( boxes[j][i] == e.getSource() && activeBox ==null && boxes[j][i].getType() == turnColor) {
					  activeBox= boxes[j][i];
					  activeBox.activateButton(i,j,boxes,true);
					  initY=j;
				  
				  }
				  else if(boxes[j][i] == e.getSource() && boxes[j][i].getType() == turnColor) {
					  activeBox= boxes[j][i];
					  setNormalBackground();
					  activeBox.activateButton(i,j,boxes,true);
					  initY=j;
				  }
				  else if(boxes[j][i] == e.getSource() && activeBox != null && boxes[j][i].getType() !=turnColor) { //regullo qe mos nderroje turnin deh mos levize kur esht yourPiece 					  
					  if(activeBox.move(i,j, boxes[j][i], boxes)) {
						  if(boxes[j][i].getPieceType()== Pawn.class && (j-initY ==2 || j-initY == -2))
							  boxes[j][i].pawnSupport(StupidTurn);
						  //if(KingInCheck()) //Fuck, goBack
							  
						  nextTurn();
					  }
					  activeBox=null;					  
				  }  
			  }
			}
	}
	
	public boolean KingInCheck() {
    	for(int i=0; i<8; i++) {
    			for(int j=0; j<8;j++) {
    				if(boxes[j][i] != null && boxes[j][i].getType() != boxes[kingY][kingX].getType()) {
    					boxes[j][i].activateButton(i,j, boxes, false);
    					if(boxes[j][i].allowMove(kingX,kingY))
    						return true;	
    				}
    			}
    	}
    	return false;
    }
	
}