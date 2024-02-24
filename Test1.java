public class Test1 {
    public static void main(String[] args){

        Board plateau = new Board();
        System.out.println(plateau.toString());
        Rules rulesChecker = new Rules();
        boolean isAVictory = rulesChecker.isVictory(plateau, Pawn.N);
        
        System.out.println("une victoire : "+isAVictory);

    }
}
