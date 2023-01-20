public class Pawns {
    // three private varibles, String array board, and intgers for the number of white and black pawns captures
    private String[][] board = new String[8][8];
    private int whitePawnsCaptured = 0;
    private int blackPawnsCaptured = 0; 
    public Pawns(){
        //genric constructor just fills the first row with white pawns and the seventh row with black pawns
        fillBoardWithPawns();
    }
    private void fillBoardWithPawns(){
        fillBoard();
        //fills the board with '..' 
        // then fills row 1 and 7 with white and black pawns respectivly. 
        for(int i=0; i<board[0].length; i++){
            board[1][i] = "WP"; board[board.length - 2][i] = "BP"; 
        }
    }
    private void fillBoard(){
        // Fill board with '..' looks nice; 
        for(int row=0; row<board.length; row++){
            for(int col = 0; col<board[row].length; col++){
                board[row][col] = ".."; 
            }
        }
    }

    private void flipBoard(){
        // flip board method
        String[] temp; 
        // temp remembers the row instead of a single element
        // to flip the board horizontally, we can just swap the rows instead of a single element at a time
        for(int i=(board.length-1) / 2; i>= 0; i--){
            // the loop goes for half the amount of iterations because it swaps out the oppostie row. i.e 1 & 6 are swaped, 2 & 5, or 3 & 4, 0 & 7
            temp = board[i];
            board[i] = board[board.length-1-i];
            board[board.length-1-i] = temp; 
        }
    }

    public void move(int row, int col, boolean firstMove){
        if(board[row][col].equals("..")){
            //checks if square is empty; returns message
            System.out.println("There is no pawn there.");
        }
        else{
            // finds wether pawn is white or black then changes it to an empty square 
            boolean isWhitePawn = board[row][col].equals("WP"); 
            board[row][col] = ".."; 
            if(isWhitePawn){
                // it is a white pawn then it checks whether two row above has a pawn, if it does the pawn does not move
                if(row == 1 && firstMove){
                    if(board[row+2][col].equals("WP") || board[row+2][col].equals("BP")){
                        System.out.println("Cannot move there, another pawn already occupies that square");
                        board[row][col] = "WP";
                    }else{
                        // moves two rows up 
                        board[row+2][col] = "WP";
                    }
                }
                else{
                    // if it is not the first move or the first move boolean is not true then we check wether the row above has a pawn
                    if(row + 1 <= 7 && (board[row+1][col].equals("WP") || board[row+1][col].equals("BP"))){
                        System.out.println("Cannot move there, another pawn already occupies that square");
                        board[row][col] = "WP";
                    }else{
                        //then we check wether the pawn is at the end. if true then we promote the pawn into a Queen. 
                        if(row+1 == board.length-1){
                            board[row+1][col] = "WQ"; 
                        }
                        // else just move it up by one row. 
                        else{
                            board[row+1][col] = "WP";
                        }
                    }
                    
                }
            }
            else{
                // same thing except the row - 1 so the pawn moves upward 
                if(row==board.length-2 && firstMove){
                    if(board[row-2][col].equals("BP") || board[row-2][col].equals("WP")){
                        System.out.println("Cannot move there, another pawn already occupies that square");
                        board[row][col] = "BP";
                    }else{
                        board[row-2][col] = "BP";
                    }
                }
                else{
                    if(row-1 <= 0 && (board[row-1][col].equals("BP") || board[row-1][col].equals("WP"))){
                        System.out.println("Cannot move there, another pawn already occupies that square");
                        board[row][col] = "BP";
                    }else{
                        if(row-1 == 0){
                            board[row+1][col] = "BQ"; 
                        }
                        else{
                            board[row-1][col] = "BP";
                        }
                        
                    }
                    
                }
            }
        }
    }

    public void attack(int row, int col, boolean attackRight){
        if(board[row][col].equals("..") ){
            System.out.println("There is no pawn there.");
        }
        // dummy checker message. 
        else{
            //checks white pawn and checks the attackRight boolean
            boolean isWhitePawn = board[row][col].equals("WP");
            board[row][col] = ".."; 
            if(isWhitePawn){
                if(attackRight){
                    //if true, it checks wether the colunm exceeds the array. or if a pawn already occupies the sqaure 
                    if(col+1 < board.length-1 && board[row+1][col+1].equals("BP")){
                        board[row+1][col+1] = "WP";
                        blackPawnsCaptured++; 
                        }
                        else{
                            System.out.println("Cannot attack there"); 
                            board[row][col] = "WP";
                        }
                    }

                else{
                    // checks the same thing except for the left hand side col - 1
                    if(col-1 >= 0 && board[row+1][col-1].equals("BP")){
                        board[row+1][col-1] = "WP";
                        blackPawnsCaptured++; 
                        // blackpawns captured increases
                    }
                    else{
                        System.out.println("Cannot attack there"); 
                        board[row][col] = "WP";
                        }
                    }
                }
            //black pawn     
            else{
                //same logic just for the black pawn
                // row - 1 to go up the board
                if(attackRight){
                    if(col+1 < board.length-1 && board[row-1][col+1].equals("WP")){
                        board[row-1][col+1] = "BP";
                        whitePawnsCaptured++;
                        //whitePawncaptures increase
                        }
                        else{
                            System.out.println("Cannot attack there"); 
                            board[row][col] = "BP";
                        }
                    }

                else{
                    if(col-1 >= 0 && board[row-1][col-1].equals("WP")){
                        board[row-1][col-1] = "BP";
                        whitePawnsCaptured++; 
                    }
                    else{
                        System.out.println("Cannot attack there"); 
                        board[row][col] = "BP";
                        }
                    }
                }
                
            }
        }

    public String[][] getBoard(){
        return board; 
    }

    public void setBoard(String[][] board){
        this.board = board; 
    }

    public String toString(){
        String output = "";
        output += "White Pawns Captured: " + whitePawnsCaptured + "\n"; 
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                output += board[i][j] + " ";
            }
            output += "\n"; 
        }
        output += "Black Pawns Captured: " + blackPawnsCaptured + "\n"; 
        return output; 
    }
}





