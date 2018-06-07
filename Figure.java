package dungeon;

import java.util.Scanner;

/**
 *
 * @author Zu
 */
public class Figure {
    private char name;
    private int x;
    private int y;
    Dungeon dungeon;

    public Figure(char name) {
        this.name = name;
        this.x = 0;
        this.y = 0;
    }
    
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getPositionX(){ 
        return this.x;
    }
    public int getPositionY(){
        return this.y;
    }

    public void howManyMoves() {
        dungeon.getMoves();
    }

    public String toString() {
        return name+" "+x+" "+y;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Figure other = (Figure) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }
    
}
