package Tests;
import Game.*;

import java.util.HashSet;

/**
 * Tests class includes general functions for implementing testings. Each test classes extends this class.
 */
public abstract class Tests {
    protected Game setBoard() {
        Game game = new Game(true);

        game.blackCells = new HashSet<Cell>();
        game.whiteCells = new HashSet<Cell>();

        return game;

    }

    /**
     * Add cell to the HashSet of cells depending on color
     * @param game
     * @param x
     * @param y
     * @param c
     */
    private void addCell(Game game, int x, int y, Color c) {
        if (c == null) {
            System.out.println("Tests::addKing color == null");
        }
        if (c == Color.WHITE) {
            game.whiteCells.add(game.board[x][y]);
        } else {
            game.blackCells.add(game.board[x][y]);
        }
    }

    /**
     * Add king to the board. When king is added it should also be added to whiteKingCell or blackKingCell.
     * @param game
     * @param x
     * @param y
     * @param c
     */
    protected void addKing(Game game, int x, int y, Color c) {
        if (c == null) {
            System.out.println("Tests::addKing color == null");
        }
        if (c == Color.WHITE) {
            game.whiteKingCell = game.board[x][y];
            game.whiteKingCell.piece = new King(Color.WHITE);
        } else {
            game.blackKingCell= game.board[x][y];
            game.blackKingCell.piece = new King(Color.WHITE);
        }
        addCell(game, x, y, c);
    }

    protected Piece addBishop(Game game, int x, int y, Color c) {
        game.board[x][y].piece = new Bishop(c);
        addCell(game, x, y, c);
        return game.board[x][y].piece;
    }

    protected Piece addRook(Game game, int x, int y, Color c) {
        game.board[x][y].piece = new Rook(c);
        addCell(game, x, y, c);
        return game.board[x][y].piece;
    }

    protected Piece addWazir(Game game, int x, int y, Color c) {
        game.board[x][y].piece = new Wazir(c);
        addCell(game, x, y, c);
        return game.board[x][y].piece;
    }

    protected Piece addFerz(Game game, int x, int y, Color c) {
        game.board[x][y].piece = new Ferz(c);
        addCell(game, x, y, c);
        return game.board[x][y].piece;
    }

    protected Piece addKnight(Game game, int x, int y, Color c) {
        game.board[x][y].piece = new Knight(c);
        addCell(game, x, y, c);
        return game.board[x][y].piece;
    }

    protected Piece addQueen(Game game, int x, int y, Color c) {
        game.board[x][y].piece = new Queen(c);
        addCell(game, x, y, c);
        return game.board[x][y].piece;
    }
    protected Piece addPawn(Game game, int x, int y, Color c) {
        game.board[x][y].piece = new Pawn(c);
        addCell(game, x, y, c);
        return game.board[x][y].piece;
    }

    protected void setTurn(Game game, Color c) {
        game.setTurn(c);
    }

}
