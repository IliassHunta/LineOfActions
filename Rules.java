import java.util.ArrayList;
import java.util.Stack;

public class Rules {
    

    public boolean isVictory(Board board, Pawn pawnColor){
        ArrayList<Position> positionsPions = new ArrayList<>();

        for(int i=0; i<board.getBoard().length;i++){
            for(int j=0; j<board.getBoard()[i].length;j++){
                if(board.getBoard()[i][j]==pawnColor){
                    positionsPions.add(new Position(i,j));
                }
            }
        }
        // faire un graphe qui regarder si tous les position sont connecter
        
        return isPawnConnected(positionsPions);
    }

    private boolean isPawnConnected(ArrayList<Position> positions){
        if(positions.size() <2){
            return true;
        }

        Position premierCordonnee = positions.get(0);
        int startX = premierCordonnee.getRow();
        int startY = premierCordonnee.getCol();
        
        boolean[][] visited = new boolean[8][8];
        Stack<Position> stack = new Stack<>();
        stack.push(new Position(startX,startY));
        visited[startX][startY]= true;

        while(!stack.isEmpty()){
            Position currentPosition = stack.pop();

            for(int i=0;i< positions.size();i++){
                Position neighbor = positions.get(i);
                int neighborX = neighbor.getRow();
                int neighborY = neighbor.getCol();

                if(!visited[neighborX][neighborY] && areConnected(currentPosition, neighbor)){
                    stack.push(neighbor);
                    visited[neighborX][neighborY]=true;
                }
            }
        }

        for(int i=0; i<positions.size();i++){
            Position position = positions.get(i);
            if(!visited[position.getRow()][position.getCol()]){
                return false;
            }
        }

        return true;
    }

    private boolean areConnected(Position position1, Position position2){
        int x1 = position1.getRow();
        int y1 = position1.getCol();
        int x2 = position2.getRow();
        int y2 = position2.getCol();

        if(Math.abs(x1-x2)<=1 && Math.abs(y1-y2)<=1)
            return true;
        else
            return false;    
    }
}
