package test;


public class Tile {
    public final int score;
    public final char letter;

    private Tile(int score, char letter) {
        this.score = score;
        this.letter = letter;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + score;
        result = prime * result + letter;
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
        Tile other = (Tile) obj;
        if (score != other.score)
            return false;
        if (letter != other.letter)
            return false;
        return true;
    }
    
    public static class Bag{
        public final int[] tilesCounter;
        public final Tile[] tilesArr;

        private static Bag bag =null;

        private boolean isEmpty(){
            for (int tileIndex : tilesCounter) {
                if (tileIndex != 0)
                    return false;
            }
            return true;              
        }

        private boolean reachedTileLImit(Tile tile){
            return ((tile.letter == 'A' && tilesCounter[0] == 9) ||
                (tile.letter == 'B' && tilesCounter[1] == 2) ||
                (tile.letter == 'C' && tilesCounter[2] == 2) ||
                (tile.letter == 'D' && tilesCounter[3] == 4) ||
                (tile.letter == 'E' && tilesCounter[4] == 12) ||
                (tile.letter == 'F' && tilesCounter[5] == 2) ||
                (tile.letter == 'G' && tilesCounter[6] == 3) ||
                (tile.letter == 'H' && tilesCounter[7] == 2) ||
                (tile.letter == 'I' && tilesCounter[8] == 9) ||
                (tile.letter == 'J' && tilesCounter[9] == 1) ||
                (tile.letter == 'K' && tilesCounter[10] == 1) ||
                (tile.letter == 'L' && tilesCounter[11] == 4) ||
                (tile.letter == 'M' && tilesCounter[12] == 2) ||
                (tile.letter == 'N' && tilesCounter[13] == 6) ||
                (tile.letter == 'O' && tilesCounter[14] == 8) ||
                (tile.letter == 'P' && tilesCounter[15] == 2) ||
                (tile.letter == 'Q' && tilesCounter[16] == 1) ||
                (tile.letter == 'R' && tilesCounter[17] == 6) ||
                (tile.letter == 'S' && tilesCounter[18] == 4) ||
                (tile.letter == 'T' && tilesCounter[19] == 6) ||
                (tile.letter == 'U' && tilesCounter[20] == 4) ||
                (tile.letter == 'V' && tilesCounter[21] == 2) ||
                (tile.letter == 'W' && tilesCounter[22] == 2) ||
                (tile.letter == 'X' && tilesCounter[23] == 1) ||
                (tile.letter == 'Y' && tilesCounter[24] == 2) ||
                (tile.letter == 'Z' && tilesCounter[25] == 1))
        }

        public Tile getRand(){
            int randomIndex = (int) (Math.random() * 26);
            if (this.isEmpty() || tilesCounter[randomIndex] == 0)
                return null;
            else{
                tilesCounter[randomIndex]--;
                return tilesArr[randomIndex];
            }

        }
       
        public Tile getTile(char letter){
            int letterIndex = (int) (letter - 'A');
            if (this.isEmpty() || tilesCounter[letterIndex] == 0)
                return null;
            else{
                tilesCounter[letterIndex]--;
                return tilesArr[letterIndex];
            }
        }

        public void put(Tile tile){
            if (this.reachedTileLImit(tile))
                System.out.println("This tile can't be added because this tile reached the amount limit");
            else
                this.tilesCounter[tile.letter - 'A']++;      

        }
        
        public int size(){
            int counter = 0;
            for (int tileCount : tilesCounter)
                counter += tileCount;
            return counter;
        }

        public int[] getQuantities(){
            return (this.tilesCounter.clone());
        }

        private Bag() {
            tilesArr = new Tile[] {
                new Tile(1,'A'),
                new Tile(3,'B'),
                new Tile(3,'C'),
                new Tile(2,'D'),
                new Tile(1,'E'),
                new Tile(4,'F'),
                new Tile(2,'G'),
                new Tile(4,'H'),
                new Tile(1,'I'),
                new Tile(8,'J'),
                new Tile(5,'K'),
                new Tile(1,'L'),
                new Tile(3,'M'),
                new Tile(1,'N'),
                new Tile(1,'O'),
                new Tile(3,'P'),
                new Tile(10,'Q'),
                new Tile(1,'R'),
                new Tile(1,'S'),
                new Tile(1,'T'),
                new Tile(1,'U'),
                new Tile(4,'V'),
                new Tile(4,'W'),
                new Tile(8,'X'),
                new Tile(4,'Y'),
                new Tile(10,'Z')
            };
            tilesCounter = new int[] {9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};
            
        }

        public static Bag getBag(){
            if (bag == null)
                bag = new Bag();
            return bag;
        }

    }

    	
}
