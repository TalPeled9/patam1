package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import test.Tile.Bag;

public class Board {
    public final Tile[][] boardTiles;
    public final HashMap<String, BonusTiles> boardBonusTiles;
    HashSet<Word> words;
    private static Board board = null;

    static final int boardSize = 15;
    
    enum CrossWord {
        NOT_CROSSING,
        LEGAL_CROSSING,
        ILLEGAL_CROSSING
      }

    enum BonusTiles {
        DOUBLE_LETTER,
        TRIPLE_LETTER,
        DOUBLE_WORD,
        TRIPLE_WORD
    }
    
    private static void addDoubleLetters(HashMap<String, BonusTiles> boardBonusTiles){
        boardBonusTiles.put("0,3", BonusTiles.DOUBLE_LETTER);
        boardBonusTiles.put("0,11", BonusTiles.DOUBLE_LETTER);
        boardBonusTiles.put("2,6", BonusTiles.DOUBLE_LETTER);
        boardBonusTiles.put("2,8", BonusTiles.DOUBLE_LETTER);
        boardBonusTiles.put("3,0", BonusTiles.DOUBLE_LETTER);
        boardBonusTiles.put("3,7", BonusTiles.DOUBLE_LETTER);
        boardBonusTiles.put("3,14", BonusTiles.DOUBLE_LETTER);
        boardBonusTiles.put("6,2", BonusTiles.DOUBLE_LETTER);
        boardBonusTiles.put("6,6", BonusTiles.DOUBLE_LETTER);
        boardBonusTiles.put("6,8", BonusTiles.DOUBLE_LETTER);
        boardBonusTiles.put("6,12", BonusTiles.DOUBLE_LETTER);
        
        boardBonusTiles.put("7,3", BonusTiles.DOUBLE_LETTER);
        boardBonusTiles.put("7,11", BonusTiles.DOUBLE_LETTER);

        boardBonusTiles.put("14,3", BonusTiles.DOUBLE_LETTER);
        boardBonusTiles.put("14,11", BonusTiles.DOUBLE_LETTER);
        boardBonusTiles.put("12,6", BonusTiles.DOUBLE_LETTER);
        boardBonusTiles.put("12,8", BonusTiles.DOUBLE_LETTER);
        boardBonusTiles.put("11,0", BonusTiles.DOUBLE_LETTER);
        boardBonusTiles.put("11,7", BonusTiles.DOUBLE_LETTER);
        boardBonusTiles.put("11,14", BonusTiles.DOUBLE_LETTER);
        boardBonusTiles.put("8,2", BonusTiles.DOUBLE_LETTER);
        boardBonusTiles.put("8,6", BonusTiles.DOUBLE_LETTER);
        boardBonusTiles.put("8,8", BonusTiles.DOUBLE_LETTER);
        boardBonusTiles.put("8,12", BonusTiles.DOUBLE_LETTER);

    }

    private static void addTripleLetters(HashMap<String, BonusTiles> boardBonusTiles){
        boardBonusTiles.put("1,5", BonusTiles.TRIPLE_LETTER);
        boardBonusTiles.put("1,9", BonusTiles.TRIPLE_LETTER);
        boardBonusTiles.put("5,1", BonusTiles.TRIPLE_LETTER);
        boardBonusTiles.put("5,5", BonusTiles.TRIPLE_LETTER);
        boardBonusTiles.put("5,9", BonusTiles.TRIPLE_LETTER);
        boardBonusTiles.put("5,13", BonusTiles.TRIPLE_LETTER);

        boardBonusTiles.put("13,5", BonusTiles.TRIPLE_LETTER);
        boardBonusTiles.put("13,9", BonusTiles.TRIPLE_LETTER);
        boardBonusTiles.put("9,1", BonusTiles.TRIPLE_LETTER);
        boardBonusTiles.put("9,5", BonusTiles.TRIPLE_LETTER);
        boardBonusTiles.put("9,9", BonusTiles.TRIPLE_LETTER);
        boardBonusTiles.put("9,13", BonusTiles.TRIPLE_LETTER);
    }
    
    private static void addDoubleWords(HashMap<String, BonusTiles> boardBonusTiles){
        boardBonusTiles.put("1,1", BonusTiles.DOUBLE_WORD);
        boardBonusTiles.put("1,13", BonusTiles.DOUBLE_WORD);
        boardBonusTiles.put("2,2", BonusTiles.DOUBLE_WORD);
        boardBonusTiles.put("2,12", BonusTiles.DOUBLE_WORD);
        boardBonusTiles.put("3,3", BonusTiles.DOUBLE_WORD);
        boardBonusTiles.put("3,11", BonusTiles.DOUBLE_WORD);
        boardBonusTiles.put("4,4", BonusTiles.DOUBLE_WORD);
        boardBonusTiles.put("4,10", BonusTiles.DOUBLE_WORD);

        boardBonusTiles.put("13,1", BonusTiles.DOUBLE_WORD);
        boardBonusTiles.put("13,13", BonusTiles.DOUBLE_WORD);
        boardBonusTiles.put("12,2", BonusTiles.DOUBLE_WORD);
        boardBonusTiles.put("12,12", BonusTiles.DOUBLE_WORD);
        boardBonusTiles.put("11,3", BonusTiles.DOUBLE_WORD);
        boardBonusTiles.put("11,11", BonusTiles.DOUBLE_WORD);
        boardBonusTiles.put("10,4", BonusTiles.DOUBLE_WORD);
        boardBonusTiles.put("10,10", BonusTiles.DOUBLE_WORD);
    }
    
    private static void addTripleWords(HashMap<String, BonusTiles> boardBonusTiles){
        boardBonusTiles.put("0,0", BonusTiles.TRIPLE_WORD);
        boardBonusTiles.put("0,7", BonusTiles.TRIPLE_WORD);
        boardBonusTiles.put("0,14", BonusTiles.TRIPLE_WORD);

        boardBonusTiles.put("7,0", BonusTiles.TRIPLE_WORD);
        boardBonusTiles.put("7,14", BonusTiles.TRIPLE_WORD);

        boardBonusTiles.put("14,0", BonusTiles.TRIPLE_WORD);
        boardBonusTiles.put("14,7", BonusTiles.TRIPLE_WORD);
        boardBonusTiles.put("14,14", BonusTiles.TRIPLE_WORD);
    }

    private static HashMap<String, BonusTiles> createBoardBonusTiles(){
        HashMap<String, BonusTiles> boardBonusTiles = new HashMap<String, BonusTiles>();
        addDoubleLetters(boardBonusTiles);
        addTripleLetters(boardBonusTiles);
        addDoubleWords(boardBonusTiles);
        addTripleWords(boardBonusTiles);
        return boardBonusTiles;
    }

    private Board(){
        this.boardTiles = new Tile[boardSize][boardSize];
        this.boardBonusTiles = createBoardBonusTiles();
        this.words = new HashSet<Word>();
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
            if(this.boardTiles[row_index][col_index] != null){
                if (word.getTiles()[i] == this.boardTiles[row_index][col_index])
                    flag = CrossWord.LEGAL_CROSSING;
                else
                    return CrossWord.ILLEGAL_CROSSING;
            }
            if(word.isVertical())
                row_index ++;
            if(word.isHorizontal())
                col_index ++;
        }
        return flag;
    }

    public boolean boardLegal(Word word){
        if (!this.isInBoard(word))
            return false;
        if (this.boardTiles[7][7] == null)
            return this.isOnTile(word, 7, 7);
        CrossWord isCrossing = this.isLegalCrossing(word);
        if(isCrossing == CrossWord.NOT_CROSSING)
            return this.isNextToWord(word);
        if (isCrossing == CrossWord.LEGAL_CROSSING)
            return true;
        else
            return false;
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
        Word newWord = null;
        if(word.isVertical())
            newWord = this.extractWordVertical(word);
        if(word.isHorizontal())
            newWord = this.extractWordHorizontal(word);
        if(!this.words.contains(newWord)){
            words.add(newWord);
            this.words.add(newWord);
        }
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
            if (newWord != null && !this.words.contains(newWord)){
                words.add(newWord);
                this.words.add(newWord);
            }
            
        }

        return words;
    }

    public int getScore(Word word){
        int score = 0;
        int wordMultiplier = 1;
        int row_index = word.getRow();
        int col_index = word.getCol();
        for (Tile letter : word.getTiles()) {
            String index = Integer.toString(row_index) + "," + Integer.toString(col_index);
            BonusTiles bonus = this.boardBonusTiles.get(index);
            if (bonus == BonusTiles.DOUBLE_LETTER)
                score += letter.score * 2;
            else if (bonus == BonusTiles.TRIPLE_LETTER)
                score += letter.score * 3;
            else if (bonus == BonusTiles.DOUBLE_WORD){
                score += letter.score;
                wordMultiplier = wordMultiplier * 2;
            }
            else if (bonus == BonusTiles.TRIPLE_WORD){
                score += letter.score;
                wordMultiplier = wordMultiplier * 3;
            }
            else
                score += letter.score;
            if (word.isVertical())
                row_index++;
            else
                col_index++;    
        }
        if(this.getTiles()[7][7] == null)
            wordMultiplier = wordMultiplier * 2;
        return score * wordMultiplier;
    }

    private boolean replaceNulls(Word word){
        int row_index = word.getRow();
        int col_index = word.getCol();
        for (int i = 0; i < word.getTiles().length; i++){
            if (word.getTiles()[i] == null){
                if(this.boardTiles[row_index][col_index] == null)
                    return false;
                else
                    word.tiles[i] = this.boardTiles[row_index][col_index];
            }
            if(word.isVertical())
                row_index ++;
            if(word.isHorizontal())
                col_index ++;
        }
        return true;

    }

    private void putWord(Word word){
        int row_index = word.getRow();
        int col_index = word.getCol();
        for (Tile tile : word.getTiles()) {
            this.boardTiles[row_index][col_index] = tile;
            if (word.isVertical())
                row_index++;
            else
                col_index++;
        }
    }

    public int tryPlaceWord(Word word){
        if (!this.replaceNulls(word))
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
        this.putWord(word);
        return score;
        }
        
    }

