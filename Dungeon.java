package dungeon;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Zu
 */
public class Dungeon {

    private int length;
    private int height;
    private int vampires;
    private int moves;
    private boolean vampiresMove;
    private Figure player;
    private ArrayList<Figure> figures;
    private char[][] board;
    private Random random;
    private Scanner reader;
    private String input;

    public Dungeon(int length, int height, int vampires, int moves, boolean vampiresMove) {
        this.length = length;
        this.height = height;
        this.vampires = vampires;
        this.moves = moves;
        this.vampiresMove = vampiresMove;
        this.player = new Figure('@');
        this.figures = new ArrayList<Figure>();
        this.board = new char[this.length][this.height];
        this.random = new Random();
        this.reader = new Scanner(System.in);
        this.input = "";
    }

    public void run() {
        vampireGenerator();
        System.out.println(moves + "\n");
        kropkiNaMacie();
        setPlayer();
        setVampires();
        printPositions();
        printMap();
        PlayersInput();
        movePlayer();
        killVampire();
        for (int i = moves - 1; i > 0; i--) {
            System.out.println(i + "\n");
            kropkiNaMacie();
            setPlayer();
            vampiresMove();
            printPositions();
            printMap();
            PlayersInput();
            movePlayer();
            killVampire();
            if(figures.isEmpty()){
                System.out.println("YOU WIN");
            }
            if(i==1){
                System.out.println("YOU LOSE");
            }
        }
    }

    public void vampireGenerator() {
        for (int i = 0; i < vampires; i++) {
            figures.add(new Figure('v'));
        }
    }

    private void setPlayer() {
        int x = player.getPositionX();
        int y = player.getPositionY();
        board[x][y] = '@';
    }

    private void setVampires() {

        for (Figure v : figures) {
            int x = random.nextInt(length);
            int y = random.nextInt(height);
            if (board[x][y] == '.') {
                v.setPosition(x, y);
                board[x][y] = 'v';
            }
        }
    }

    private void vampiresMove() {
        if (vampiresMove == true) {
            setVampires();
        }
        if (vampiresMove == false) {
            for (Figure v : figures) {
                int x = v.getPositionX();
                int y = v.getPositionY();
                board[x][y] = 'v';
            }
        }
    }

    private void kropkiNaMacie() {
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < length; i++) {
                board[i][j] = '.';
            }
        }
    }

    private void printPositions() {
        System.out.println(player.toString());
        for (Figure f : figures) {
            System.out.println(f.toString());
        }
        System.out.println("");
    }

    private void printMap() {
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < length; i++) {
                System.out.print(board[i][j]);

            }
            System.out.println("");
        }
        System.out.println("");
    }

    public String PlayersInput() {
        input = reader.nextLine();
        return input;
    }

    public void movePlayer() {
        int x = player.getPositionX();
        int y = player.getPositionY();
        char[] moves = input.toCharArray();
        for (char c : moves) {
            if (c == 's') {
                y++;
            }
            if (c == 'w') {
                y--;
            }
            if (c == 'a') {
                x--;
            }
            if (c == 'd') {
                x++;
            }
        }
        if (x > length - 1) {
            x = length - 1;
        }
        if (x < 0) {
            x = 0;
        }
        if (y > height - 1) {
            y = height - 1;
        }
        if (y < 0) {
            y = 0;
        }
        player.setPosition(x, y);
    }

    public void killVampire() {
        ArrayList<Figure> toRemove = new ArrayList<Figure>();
        for (Figure v : figures) {
            if (v.equals(player)) {
                toRemove.add(v);
            }
        }
        figures.removeAll(toRemove);
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    public int getVampires() {
        return this.vampires;
    }

    public int getMoves() {
        return this.moves;
    }

    public boolean isVampiresMove() {
        return vampiresMove;
    }

}
