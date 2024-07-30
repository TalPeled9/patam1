package test;

import java.util.ArrayList;

import test.Tile.Bag;

public class Board {
    public final Tile[][] boardTiles;
    private static Board board = null;

    static final int boardSize = 15;
    
    enum CrossWord {
        NOT_CROSSING,
        LEGAL_CROSSING,
        ILLEGAL_CROSSING
      }
    
    private Board(){
        this.boardTiles = new Tile[boardSize][boardSize];
    }

    public static Board getBoard(){
        if (board == null)
            board = new Board();
        return board;
    }

    public Tile[][] getTiles(){
        Tile[][] boardCopy = new Tile[boardSize][boardSize];
        for(int i=0;i<boardSize;i++)
            boardCopy[i] = this.boardTiles[i].clone();
        return boardCopy;
    }

    private boolean isInBoard(Word word){
        return !(word.getRow() < 0 || word.getRow() >= boardSize || word.getCol() < 0 || word.getCol() >= boardSize ||
                word.lastLetterIndex()[0] >= boardSize || word.lastLetterIndex()[1] >= boardSize);
    }

    private boolean isOnTile(Word word, int row, int col){
        if(word.isVertical() && word.getCol() == col
            && word.getRow() <= row && word.lastLetterIndex()[0] >= row)
            return true;
        if (word.isHorizontal() && word.getRow() == row
            && word.getCol() <= col && word.lastLetterIndex()[1] >= col)
            return true;
        return false;
    }

    private boolean isNextToWordVertical(Word word){
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

    private boolean isNextToWordHorizontal(Word word){
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

    private boolean isNextToWord(Word word){
        if(word.isVertical())
            return isNextToWordVertical(word);
        else
            return isNextToWordHorizontal(word);

    }

    private CrossWord isLegalCrossing(Word word){
        CrossWord flag = CrossWord.NOT_CROSSING;
        int row_index = word.getRow();
        int col_index = word.getCol();
        for (int i = 0; i < word.getTiles().length; i++){
            if(word.isVertical())
                row_index ++;
            if(word.isHorizontal())
                col_index ++;
            if(this.boardTiles[row_index][col_index] != null){
                if (word.getTiles()[i] == this.boardTiles[row_index][col_index])
                    flag = CrossWord.LEGAL_CROSSING;
                else
                    return CrossWord.ILLEGAL_CROSSING;
            }
        }
        return flag;
    }

    public boolean boardLegal(Word word){
        if (!this.isInBoard(word))
            return false;
        if (this.boardTiles[7][7] == null)
            return this.isOnTile(word, 7, 7);
        CrossWord isCrossing = this.isLegalCrossing(word);
        switch (isCrossing) {
            case NOT_CROSSING:
                return this.isNextToWord(word);
            case LEGAL_CROSSING:
                return true;
            case ILLEGAL_CROSSING:
                return false;
        }
    }

    public boolean dictionaryLegal(Word word){
        return true;
    }

    private Word extractWordHorizontal(Word word){
        ArrayList<Tile> wordTilesAl = new ArrayList<Tile>();
        for (int i = 0; i < word.getTiles().length; i++)
            wordTilesAl.add(word.getTiles()[i]);
        int col_index = word.getCol() - 1;
        while(col_index >= 0 && this.boardTiles[word.getRow()][col_index] != null){ 
            wordTilesAl.add(0, this.boardTiles[word.getRow()][col_index]);
            col_index--;
        }
        int firstCol = col_index + 1;
        col_index = word.lastLetterIndex()[1] + 1;
        while(col_index < boardSize && this.boardTiles[word.getRow()][col_index] != null){
            wordTilesAl.add(this.boardTiles[word.getRow()][col_index]);
            col_index++;
        }
        if (wordTilesAl.size() == 1)
            return null;
        Tile[] wordTilesArr = new Tile[wordTilesAl.size()];
        wordTilesArr = wordTilesAl.toArray(wordTilesArr);
        return new Word(wordTilesArr, word.getRow() , firstCol, false);
    }

    private Word extractWordVertical(Word word){
        ArrayList<Tile> wordTilesAl = new ArrayList<Tile>();
        for (int i = 0; i < word.getTiles().length; i++)
            wordTilesAl.add(word.getTiles()[i]);
        int row_index = word.getRow() - 1;
        while(row_index >= 0 && this.boardTiles[row_index][word.getCol()] != null){ 
            wordTilesAl.add(0, this.boardTiles[row_index][word.getCol()]);
            row_index--;
        }
        int firstRow = row_index + 1;
        row_index = word.lastLetterIndex()[0] + 1;
        while(row_index < boardSize && this.boardTiles[row_index][word.getCol()] != null){
            wordTilesAl.add(this.boardTiles[row_index][word.getCol()]);
            row_index++;
        }
        if (wordTilesAl.size() == 1)
            return null;
        Tile[] wordTilesArr = new Tile[wordTilesAl.size()];
        wordTilesArr = wordTilesAl.toArray(wordTilesArr);
        return new Word(wordTilesArr, firstRow , word.getCol(), true);
    }

    public ArrayList<Word> getWords(Word word){
        ArrayList<Word> words = new ArrayList<Word>();
        if(word.isVertical())
            words.add(this.extractWordVertical(word));
        if(word.isHorizontal())
            words.add(this.extractWordHorizontal(word));
        Word newWord = null;
        int row_index = word.getRow();
        int col_index = word.getCol();
        for (int i = 0; i < word.getTiles().length; i++){
            if(word.isVertical()){
                newWord = this.extractWordHorizontal(new Word(new Tile[] {word.getTiles()[i]}, row_index, col_index, false));
                row_index ++;
            }
            else if(word.isHorizontal()){
                newWord = this.extractWordVertical(new Word(new Tile[] {word.getTiles()[i]}, row_index, col_index, true));
                col_index ++;
            }
            if (newWord != null)
                words.add(newWord);
            
        }

        return words;
    }

    public int getScore(Word word){}

    private boolean replaceUnderscores(Word word){
        int row_index = word.getRow();
        int col_index = word.getCol();
        for (int i = 0; i < word.getTiles().length; i++){
            if(word.isVertical())
                row_index ++;
            if(word.isHorizontal())
                col_index ++;
            if (word.getTiles()[i].letter == '_'){
                if(this.boardTiles[row_index][col_index] == null)
                    return false;
                else
                    word.getTiles()[i] = this.boardTiles[row_index][col_index];
            }
        }
        return true;

    }

    public int tryPlaceWord(Word word){
        if (!this.replaceUnderscores(word))
            return 0;
        if(!this.boardLegal(word))
            return 0;
        ArrayList<Word> words = this.getWords(word);
        int score = 0;
        for (Word i : words) {
            if (!this.dictionaryLegal(i))
                return 0;
            score += this.getScore(i);
        }
        return score;
        }
        
    }

