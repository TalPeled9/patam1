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
        private final int[] maxTilesCounter;
        public final Tile[] tilesArr;
        private int totalTiles;

        private static Bag bag =null;

        public Tile getRand(){
            if (totalTiles == 0)
                return null;
            int randomIndex = (int) (Math.random() * tilesArr.length);
            while(tilesCounter[randomIndex] == 0)
                randomIndex = (int) (Math.random() * tilesArr.length);
            tilesCounter[randomIndex]--;
            totalTiles--;
            return tilesArr[randomIndex];
            }

        public Tile getTile(char letter){
            int letterIndex = (int) (letter - 'A');
            if (letter < 'A' || letter > 'Z' || totalTiles == 0 || tilesCounter[letterIndex] == 0)
                return null;
            else{
                tilesCounter[letterIndex]--;
                totalTiles--;
                return tilesArr[letterIndex];
            }
        }

        public void put(Tile tile){
            int tileIndex = (int) (tile.letter - 'A');
            if (tilesCounter[tileIndex] < maxTilesCounter[tileIndex]){
                this.tilesCounter[tileIndex]++;
                totalTiles++;
            }

        }
        
        public int size(){
            return totalTiles;
        }

        public int[] getQuantities(){
            return this.tilesCounter.clone();
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
            maxTilesCounter = tilesCounter.clone();
            totalTiles = 98;
            
        }

        public static Bag getBag(){
            if (bag == null)
                bag = new Bag();
            return bag;
        }

    }
   	
}
