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

        private boolean isEmpty(){
            for (int tileIndex : tilesCounter) {
                if (tileIndex != 0)
                    return false;
            }
            return true;              
        }

        private boolean reachedTileLImit(Tile tile){
            switch (tile.letter) {
                case 'A':
                    if (tilesCounter[0] == 9)
                        return true;
                    break;
                case 'B':
                    if (tilesCounter[0] == 2)
                        return true;
                    break;
                case 'C':
                    if (tilesCounter[0] == 2)
                        return true;
                    break;
                case 'D':
                    if (tilesCounter[0] == 4)
                        return true;
                    break;
                case 'E':
                    if (tilesCounter[0] == 12)
                        return true;
                    break;
                case 'F':
                    if (tilesCounter[0] == 2)
                        return true;
                    break;
                case 'G':
                    if (tilesCounter[0] == 3)
                        return true;
                    break;
                case 'H':
                    if (tilesCounter[0] == 2)
                        return true;
                    break;
                case 'I':
                    if (tilesCounter[0] == 9)
                        return true;
                    break;
                case 'J':
                    if (tilesCounter[0] == 1)
                        return true;
                    break;
                case 'K':
                    if (tilesCounter[0] == 1)
                        return true;
                    break;
                case 'L':
                    if (tilesCounter[0] == 4)
                        return true;
                    break;
                case 'M':
                    if (tilesCounter[0] == 2)
                        return true;
                    break;
                case 'N':
                    if (tilesCounter[0] == 6)
                        return true;
                    break;
                case 'O':
                    if (tilesCounter[0] == 8)
                        return true;
                    break;
                case 'P':
                    if (tilesCounter[0] == 2)
                        return true;
                    break;
                case 'Q':
                    if (tilesCounter[0] == 1)
                        return true;
                    break;
                case 'R':
                    if (tilesCounter[0] == 6)
                        return true;
                    break;
                case 'S':
                    if (tilesCounter[0] == 4)
                        return true;
                    break;
                case 'T':
                    if (tilesCounter[0] == 6)
                        return true;
                    break;
                case 'U':
                    if (tilesCounter[0] == 4)
                        return true;
                    break;
                case 'V':
                    if (tilesCounter[0] == 2)
                        return true;
                    break;
                case 'W':
                    if (tilesCounter[0] == 2)
                        return true;
                    break;
                case 'X':
                    if (tilesCounter[0] == 1)
                        return true;
                    break;
                case 'Y':
                    if (tilesCounter[0] == 2)
                        return true;
                    break;
                case 'Z':
                    if (tilesCounter[0] == 1)
                        return true;
                    break;
            
                default:
                    break;
            }
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

        public int size(){
            int counter = 0;
            for (int tileCount : tilesCounter)
                counter += tileCount;
            return counter;
        }

        public void put(Tile tile){
            int tileIndex = (int)(tile.letter - 'A');

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

        

    }

    
    

	
}
