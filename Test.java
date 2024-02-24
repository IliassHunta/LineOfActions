import java.util.ArrayList;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        Board plateau = new Board();


        //CODE QUI PERMET DE FAIRE UNE PARTIE CONTRE LE BOT, NE PAS OUBLIER DE METTRE EN COMMENTAIRE LES 4 LIGNES CI-DESSUS
        for(int j = 0; j < 10; j++)
        {
            System.out.print(plateau.toString());
            
            System.out.println("Quelle est la rangée du pion que vous voulez jouer ");
            int row = console.nextInt();
            System.out.println("Quelle est la colonne du pion que vous voulez jouer");
            int col = console.nextInt();
            Position pos = new Position(row,col);

            ArrayList<Integer> listePions = plateau.getPawnRange(plateau.getBoard()[pos.getRow()][pos.getCol()], pos);
            System.out.println("-------" + listePions);

            ArrayList<Position>  listePositions = plateau.movesPerLine(pos, listePions);
            for(int i = 0; i < listePositions.size(); i++){
                System.out.println(listePositions.get(i).getRow() + ", " + listePositions.get(i).getCol());
            }

            System.out.println("Quelle est la rangée du pion que vous voulez jouer ");
            int newRow = console.nextInt();
            System.out.println("Quelle est la colonne du pion que vous voulez jouer");
            int newCol = console.nextInt();
            Position newPos = new Position(newRow,newCol);

            plateau.movePawn(pos, newPos);

            System.out.println(plateau.toString());
        }
    }
}