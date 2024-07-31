package test;

import java.util.Arrays;

public class Word {
    public Tile[] tiles;
    public int row;
	public int col;
    public boolean vertical;

    public Word(Tile[] tiles, int row, int col, boolean vertical) {
        this.tiles = tiles;
        this.row = row;
        this.col = col;
        this.vertical = vertical;
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isVertical() {
        return vertical;
    }

    public boolean isHorizontal() {
        return !vertical;
    }

    public int[] lastLetterIndex(){
        if (this.vertical)
            return new int[] {this.row + this.tiles.length -1, this.col};
        return new int[] {this.row, this.col + this.tiles.length -1};
    }
    
    @Override
    public String toString() {
        return "Word [tiles=" + Arrays.toString(tiles) + ", row=" + row + ", col=" + col + ", vertical=" + vertical
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(tiles);
        result = prime * result + row;
        result = prime * result + col;
        result = prime * result + (vertical ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Word other = (Word) obj;
        if (!Arrays.equals(tiles, other.tiles))
            return false;
        if (row != other.row)
            return false;
        if (col != other.col)
            return false;
        if (vertical != other.vertical)
            return false;
        return true;
    }

    
    
}
