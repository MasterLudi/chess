package Game;


public class Cell {
    public Piece piece;
    public int x;
    public int y;

    public Cell(int x_, int y_) {
        piece = null;
        x = x_;
        y = y_;
    }

    public boolean isEmpty() {
        return (piece == null);
    }

    public Color getColor() {
        if (piece == null)
            return null;
        return piece.color;
    }

    public void releasePiece() {
        this.piece = null;
    }

    public void addPiece(Piece pc) {
        this.piece = pc;
    }

}
