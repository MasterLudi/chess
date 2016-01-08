package Game;


public class Player {

    public Color color;

    private King king;
    private Queen queen;
    private Knight knight1;
    private Knight knight2;
    private Rook rook1;
    private Rook rook2;
    private Bishop bishop1;
    private Bishop bishop2;
    private Pawn pawn1;
    private Pawn pawn2;
    private Pawn pawn3;
    private Pawn pawn4;
    private Pawn pawn5;
    private Pawn pawn6;
    private Pawn pawn7;
    private Pawn pawn8;

    /* pieces' position initialized in constructor */
    public Player(Color c) {
        color = c;

        // allocate pieces and set the initial position by constructor
        if (color == Color.WHITE) {

        } else if (color == Color.BLACK) {

        }
    }



}
