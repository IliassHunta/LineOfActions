import java.util.ArrayList;
import java.util.Random;

public class Board{

    private final int BOARD_SIZE = 8;
    //private Pion[][] board;
    private Pawn[][] board;
    //Pion pions;
    public Board(){
        board = new Pawn[BOARD_SIZE][BOARD_SIZE];
        initializeBoard();
    }

    private void initializeBoard(){
        for(int i=0; i<board.length;i++){
            for(int j=0; j<board[i].length;j++){
                board[i][j] = Pawn.E;
            }
        }

        board[2][0] = Pawn.N;
        board[3][0] = Pawn.N;
        //board[4][0] = Pawn.N;
        //board[2][1] = Pawn.N;
        // cree une victoire avec les rouges
        board[1][2] = Pawn.R;
        board[2][3] = Pawn.R;
        board[2][4] = Pawn.R;
        board[1][4] = Pawn.R;

        /* 
        // rangée du haut
        board[0][1] = Pawn.N;
        board[0][2] = Pawn.N;
        board[0][3] = Pawn.N;
        board[0][4] = Pawn.N;
        board[0][5] = Pawn.N;
        board[0][6] = Pawn.N;
        // rangée du bas
        board[7][1] = Pawn.N;
        board[7][2] = Pawn.N;
        board[7][3] = Pawn.N;
        board[7][4] = Pawn.N;
        board[7][5] = Pawn.N;
        board[7][6] = Pawn.N;
        // première ligne
        board[1][0] = Pawn.R;
        board[2][0] = Pawn.R;
        board[3][0] = Pawn.R;
        board[4][0] = Pawn.R;
        board[5][0] = Pawn.R;
        board[6][0] = Pawn.R;
        //dernière ligne
        board[1][7] = Pawn.R;
        board[2][7] = Pawn.R;
        board[3][7] = Pawn.R;
        board[4][7] = Pawn.R;
        board[5][7] = Pawn.R;
        board[6][7] = Pawn.R;
        */
    }

    public Pawn[][] getBoard(){
        return this.board;
    }

    public ArrayList<Integer> getPawnRange(Pawn pawn, Position position){

        int nbPawnRow = rowMoveRange(pawn, position);
        int nbPawnCol = colMoveRange(pawn, position);
        int nbPawnDiagonal = diagonalMoveRange(pawn, position);
        int nbPawnAntidiagonal = antiDiagonalMoveRange(pawn, position);


        //donne le pas quil peut faire par direction -> [row,col,diago,antiDiago]
        ArrayList<Integer> moveRangePawn = new ArrayList<>();
        moveRangePawn.add(nbPawnRow);
        moveRangePawn.add(nbPawnCol);
        moveRangePawn.add(nbPawnDiagonal);
        moveRangePawn.add(nbPawnAntidiagonal);

        return moveRangePawn;
    }

    private int rowMoveRange(Pawn pawn, Position position){
        int nmbPawnRow = 8;

        for(int i=0; i<board[0].length;i++){
            if(board[position.getRow()][i].equals(Pawn.E)){
                nmbPawnRow--;
            }
        }        
        return nmbPawnRow;
    }

    private int colMoveRange(Pawn pawn, Position position){
        int nmbPawnCol = 8;
        for(int i=0; i<board.length;i++){
            if(board[i][position.getCol()].equals(Pawn.E)){
                nmbPawnCol--;
            }
        }
        return nmbPawnCol;
    }

    private int diagonalMoveRange(Pawn pawn,  Position position){
        int nmbPawnDiagonal =1;
        int pawnRow = position.getRow();
        int pawnCol = position.getCol();

        // Diagonal Nord-Ouest
        while(pawnRow>0 && pawnCol>0){
            pawnRow--;
            pawnCol--;
            if(!board[pawnRow][pawnCol].equals(Pawn.E)){
                nmbPawnDiagonal++;
            }
        }
        pawnRow = position.getRow();
        pawnCol = position.getCol();
        // Diagonal Sud-Est 
        while(pawnRow < 7 && pawnCol < 7){
            pawnRow++;
            pawnCol++;
            if(board[pawnRow][pawnCol] != Pawn.E){
                nmbPawnDiagonal++;
            }
        }
        
        return nmbPawnDiagonal;
    }
    private int antiDiagonalMoveRange(Pawn pawn,  Position position){
        int nmbPawnAntiDiagonal = 1;
        int pawnRow = position.getRow();
        int pawnCol = position.getCol();
        //Diagonal Nord-Est
        while(pawnRow>0 && pawnCol < 7){
            pawnRow--;
            pawnCol++;
            if(board[pawnRow][pawnCol] != Pawn.E){
                nmbPawnAntiDiagonal++;
            }
        }
        pawnRow = position.getRow();
        pawnCol=position.getCol();
        //Diagonal Sud-Ouest
        while (pawnRow < 7 && pawnCol >0) {
            pawnRow++;
            pawnCol--;
            if(board[pawnRow][pawnCol]!= Pawn.E){
                nmbPawnAntiDiagonal++;
            }
            
        }
        return nmbPawnAntiDiagonal;
    }

    public ArrayList<Position> movesPerLine(Position position, ArrayList<Integer> nbOfPawns)
    {
        int rowSearch = 0;
        int colSearch = 1;
        int diagSearch = 2;
        int antiDiagSearch = 3;

        int pawnRow = position.getRow();
        int pawnCol = position.getCol();

        int rowStepSize = nbOfPawns.get(rowSearch);
        int colStepSize = nbOfPawns.get(colSearch);
        int diagStepSize = nbOfPawns.get(diagSearch);
        int antiDiagStepSize = nbOfPawns.get(antiDiagSearch);

        ArrayList<Position> movesList = new ArrayList<>();

        //Coup dans la même rangée à gauche du pion
        if(pawnCol - rowStepSize >= 0)
        {
            if(!board[pawnRow][pawnCol].equals(board[pawnRow][pawnCol-rowStepSize]) || board[pawnRow][pawnCol-rowStepSize].equals(Pawn.E))
            {
                movesList.add(new Position(pawnRow, pawnCol-rowStepSize));
            }
        }
        //Coup dans la même rangée à droite du pion 
        if(pawnCol + rowStepSize < 8)
        {
            if(!board[pawnRow][pawnCol].equals(board[pawnRow][pawnCol+rowStepSize]) 
                || board[pawnRow][pawnCol+rowStepSize].equals(Pawn.E))
            {
                movesList.add(new Position(pawnRow, pawnCol+rowStepSize));
            }
        }
        //Coup dans la même colonne en haut du pion
        if(pawnRow - colStepSize >= 0)
        {
            if(!board[pawnRow][pawnCol].equals(board[pawnRow-colStepSize][pawnCol])
                || board[pawnRow-colStepSize][pawnCol].equals(Pawn.E))
            {
                movesList.add(new Position(pawnRow-colStepSize, pawnCol));
            }
        }
        //Coup dans la même colonne en bas du pion
        if(pawnRow + colStepSize < 8)
        {
            if(!board[pawnRow][pawnCol].equals(board[pawnRow+colStepSize][pawnCol])
                || board[pawnRow+colStepSize][pawnCol].equals(Pawn.E))
            {
                movesList.add(new Position(pawnRow+colStepSize, pawnCol));
            }
        }
        //Coup dans la même diagonale en haut du pion
        if(pawnRow - diagStepSize >= 0 && pawnCol - diagStepSize >= 0)
        {
            if(!board[pawnRow][pawnCol].equals(board[pawnRow-diagStepSize][pawnCol-diagStepSize])
                || board[pawnRow-diagStepSize][pawnCol-diagStepSize].equals(Pawn.E))
            {
                movesList.add(new Position(pawnRow-diagStepSize, pawnCol-diagStepSize));
            }
        }
        //Coup dans la même diagonale en bas du pion
        if(pawnRow + diagStepSize < 8 && pawnCol + diagStepSize < 8)
        {
            if(!board[pawnRow][pawnCol].equals(board[pawnRow+diagStepSize][pawnCol+diagStepSize])
                || board[pawnRow+diagStepSize][pawnCol+diagStepSize].equals(Pawn.E))
            {
                movesList.add(new Position(pawnRow+diagStepSize, pawnCol+diagStepSize));
            }
        }
        //Coup dans la même antidiagonale en haut du pion
        if(pawnRow - antiDiagStepSize >= 0 && pawnCol + antiDiagStepSize < 8)
        {
            if(!board[pawnRow][pawnCol].equals(board[pawnRow-antiDiagStepSize][pawnCol+antiDiagStepSize])
                || board[pawnRow-antiDiagStepSize][pawnCol+antiDiagStepSize].equals(Pawn.E))
            {
                movesList.add(new Position(pawnRow-antiDiagStepSize, pawnCol+antiDiagStepSize));
            }
        }
        //Coup dans la même antidiagonale en bas du pion
        if(pawnRow + antiDiagStepSize < 8 && pawnCol - antiDiagStepSize >= 0)
        {
            if(!board[pawnRow][pawnCol].equals(board[pawnRow+antiDiagStepSize][pawnCol-antiDiagStepSize])
                || board[pawnRow+antiDiagStepSize][pawnCol-antiDiagStepSize].equals(Pawn.E))
            {
                movesList.add(new Position(pawnRow+antiDiagStepSize, pawnCol-antiDiagStepSize));
            }
        }
        return movesList;
    }

    public Position movePawn(Position p, Position position){
        Pawn pawnToMove = board[p.getRow()][p.getCol()];

        if(pawnToMove!= Pawn.E){
            ArrayList<Position> possibleMoves = movesPerLine(p, getPawnRange(pawnToMove, p));
            for(int i = 0; i < possibleMoves.size(); i++)
            {
                System.out.println(possibleMoves.get(i).getRow() + ", " + possibleMoves.get(i).getCol());
            }
            //Random random = new Random();
            //int randomNumber = random.nextInt(possibleMoves.size());
            //Position position = possibleMoves.get(randomNumber);

            System.out.println("Row: "+position.getRow()+" ,Col: "+position.getCol());
            board[position.getRow()][position.getCol()] = pawnToMove;
            board[p.getRow()][p.getCol()] = Pawn.E;

            return position;

        }
        return null;
    }

    public void undoMove(Position p){

        
        board[p.getRow()][p.getCol()] = Pawn.E;
    }

    public String toString(){
        StringBuilder result = new StringBuilder();

        for(int i=0; i< this.board.length; i++){
            for(int j=0; j<this.board[i].length;j++){
                result.append(this.board[i][j]).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }
}