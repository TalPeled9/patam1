package test;

import test.Tile.Bag;

public class Board {
    public final Tile[][] boardTiles;

    private static Board board = null;

    private Board(){
        this.boardTiles = new Tile[15][15];
    }

    public static Board getBoard(){
            if (board == null)
                board = new Board();
            return board;
    }

    public Tile[][] getTiles(){
        Tile[][] boardCopy = new Tile[15][15];
        if(this.boardTiles != null){
            for(int i=0;i<15;i++)
                boardCopy[i] = this.boardTiles[i].clone();
        }
        return boardCopy;
    }

    private boolean isOnTile(Word word, int row, int col){
        if(word.isVertical() && word.getCol() == col
            && word.getRow() <= row && word.getRow() + word.getTiles().length >= row)
            return true;
        else if (!word.isVertical() && word.getRow() == row
                && word.getCol() <= col && word.getCol() + word.getTiles().length >= col)
            return true;
        return false;
    }

    private boolean isNextToWord(Word word){}

    private boolean isCrossWord(Word word){}

    public boolean boardLegal(Word word){
        if (word.getRow() < 0 || word.getRow() > 14 || word.getCol() < 0 || word.getCol() > 14)
            return false;
        int wordLength = word.getTiles().length;
        if ((word.isVertical() && word.getRow() + wordLength > 14) ||
            (!word.isVertical() && word.getCol() + wordLength > 14))
            return false;
        if (this.boardTiles[7][7] == null && !this.isOnTile(word, 7, 7))
            return false;
        if (!this.isNextToWord(word) || !isCrossWord(word))
            return true;
        return true;
    }


}
