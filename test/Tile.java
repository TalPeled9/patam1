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
