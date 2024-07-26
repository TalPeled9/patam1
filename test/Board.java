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

    private boolean isInBoard(Word word){
        if (word.getRow() < 0 || word.getRow() > 14 || word.getCol() < 0 || word.getCol() > 14 ||
            word.lastLetterIndex()[0] > 14 || word.lastLetterIndex()[1] > 14)
            return false;
        return true;
    }

    private boolean isOnTile(Word word, int row, int col){
        if(word.isVertical() && word.getCol() == col
            && word.getRow() <= row && word.lastLetterIndex()[0] >= row)
            return true;
        if (!word.isVertical() && word.getRow() == row
            && word.getCol() <= col && word.lastLetterIndex()[1] >= col)
            return true;
        return false;
    }

    private boolean isNextToWord(Word word){
        if(word.isVertical()){
            if ((word.getRow() != 0 && this.boardTiles[word.getRow()-1][word.getCol()] != null) ||
                (word.lastLetterIndex()[0] != 14 && this.boardTiles[word.lastLetterIndex()[0] + 1][word.getCol()] != null))
                return true;
            for(int i =0; i < word.getTiles().length; i++){
                if((word.getCol() != 0 && this.boardTiles[word.getRow() + i][word.getCol() - 1] != null) ||
                    (word.getCol() != 14 && this.boardTiles[word.getRow() + i][word.getCol() + 1] != null))
                    return true;
            }
            return false;
        }
        else{
            if ((word.getCol() != 0 && this.boardTiles[word.getRow()][word.getCol() - 1] != null) ||
                (word.lastLetterIndex()[1] != 14 && this.boardTiles[word.getRow()][word.lastLetterIndex()[1] + 1] != null))
                return true;
            for(int i =0; i < word.getTiles().length; i++){
                if((word.getRow() != 0 && this.boardTiles[word.getRow() - 1][word.getCol() + i] != null) ||
                    (word.getRow() != 14 && this.boardTiles[word.getRow() + 1][word.getCol() + i] != null))
                    return true;
            }
            return false;
        }
    }

    private boolean isCrossWord(Word word){}

    public boolean boardLegal(Word word){
        if ((!this.isInBoard(word)) ||
            (this.boardTiles[7][7] == null && !this.isOnTile(word, 7, 7)) ||
            (!this.isNextToWord(word) && !isCrossWord(word)))
            return false;
        return true;
    }


}
