/*@author Kai Waldner
 *This game is like the game called Sokoban with a few differences:
 * You win if you remove all "u" out of the game. To remove these you have to push "$" with you character "@" to this places.
 */

package de.uulm.waldner.kai.sokoban;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Sokoban {
    //Main gaiming Window
    protected static JFrame frame;
    protected static JPanel panel;
    protected static JButton button;
    public static char[][] sokoban;
    public static ArrayList<JButton> buttons;

    //Restart Window
    protected static JFrame restartWindow;
    protected static JPanel restartPanel;
    protected static JButton quit;
    protected static JButton restart;

    public static void main(String[] args) {

        //init main window
        sokoban = getStartPosition();
        frame = new JFrame();
        frame.setTitle("SOKOBAN");
        frame.setSize(400,500);
        panel = new JPanel();
        panel.setLayout(new GridLayout(sokoban.length,sokoban[0].length));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        printGui(sokoban);
        frame.add(panel);

        //adding a KeyListener to main window frame
        Command cmd = new Command();
        frame.addKeyListener(cmd);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //Restart Window erzeugen
        restartWindow = new JFrame();
        restartWindow.setTitle("ONE MORE GAME?");
        restartWindow.setSize(250,150);
        restartPanel = new JPanel();

        restartWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        restartWindow.add(restartPanel);

        restartWindow.setLocationRelativeTo(null);
        quit = new JButton();
        restart = new JButton();
        restartPanel.setLayout(new GridLayout(1,2));
        restartPanel.add(quit);
        quit.setText("QUIT");
        quit.setActionCommand("QUIT");
        restartPanel.add(restart);
        restart.setText("RESTART");
        restart.setActionCommand("RESTART");
        Command2 cmd2 = new Command2();
        quit.addActionListener(cmd2);
        restart.addActionListener(cmd2);


    }

    private static char[][] getStartPosition(){
        char[][] startPosition;
        startPosition= new char[10][];
        startPosition[0] = "############".toCharArray();
        startPosition[1] = "#..x.......#".toCharArray();
        startPosition[2] = "#ux..$.....#".toCharArray();
        startPosition[3] = "#...$@$....#".toCharArray();
        startPosition[4] = "#....$...u.#".toCharArray();
        startPosition[5] = "#..u.x..xxx#".toCharArray();
        startPosition[6] = "#..u.......#".toCharArray();
        startPosition[7] = "#..uu....x.#".toCharArray();
        startPosition[8] = "#..x.......#".toCharArray();
        startPosition[9] = "############".toCharArray();
        return startPosition;
    }

    protected static void checkIfFinished(char[][] array){
        int counter = 0;
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[i].length; j++){
                if(array[i][j] == 'u'){
                    counter++;
                }
            }
        }
        if(counter == 0){
            System.out.println("GEWONNEN!!!!!!");
            for(int k = 0; k < buttons.size(); k++){
                buttons.get(k).setText("WIN");
                sokoban = getStartPosition();
                frame.setTitle("GEWONNEN!!!!!");
                restartWindow();
            }
        }
        counter = 0;
    }

    protected static void restartWindow(){
        restartWindow.setVisible(true);
    }

    protected static void reDrawGui(char[][] array){
        int index = 0;
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[i].length; j++){
                buttons.get(index).setText(String.valueOf(array[i][j]));
                index++;
            }
        }
    }

    protected static void printGui(char[][] array) {
        buttons = new ArrayList<>();
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[i].length; j++){
                buttons.add(new JButton());
            }
        }

        int index = 0;
        for(int x = 0; x < array.length; x++){
            for(int y = 0; y < array[x].length; y++){
                buttons.get(index).setText(String.valueOf(array[x][y]));
                buttons.get(index).setFocusable(false);
                index++;
            }
        }

        for(int k = 0; k < buttons.size(); k++){
            panel.add(buttons.get(k));
        }
    }

    public static void printArray(char[][] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[0].length; j++){
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

    public static Point findPlayer(char[][] array){
        Point point = new Point();
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[i].length; j++){
                if(array[i][j] == '@'){
                    point.setLocation(j,i);
                }
            }
        }
        return point;
    }

    public static boolean moveNorth(char[][]array){
        return moveVertikal(array, 1);
    }

    public static boolean moveSouth(char[][] array){
        return moveVertikal(array, -1);
    }

    public static boolean moveEast(char[][] array){
        return moveHorizontal(array, 1);
    }

    public static boolean moveWest(char[][] array){
        return moveHorizontal(array, -1);
    }

    public static boolean moveVertikal(char[][] array, int sign){
        Point p = findPlayer(array);
        int x = (int) p.getX();
        int y = (int) p.getY();

        if(array[y-1*sign][x] == '#'){
            return false;
        } else if(array[y-1*sign][x] == '.'){
            array[y][x] = '.';
            array[y-1*sign][x] = '@';
            return true;
        } else if(array[y-1*sign][x] == '$'){
            if(array[y-2*sign][x] == '.' || array[y-2*sign][x] == 'u'){
                array[y][x] = '.';
                array[y-1*sign][x] = '@';
                array[y-2*sign][x] = '$';
                return true;
            }else{
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean moveHorizontal(char[][] array, int sign){
        Point p = findPlayer(array);
        int x = (int) p.getX();
        int y = (int) p.getY();

        if(array[y][x+1*sign] == '#'){
            return false;
        } else if(array[y][x+1*sign] == '.'){
            array[y][x] = '.';
            array[y][x+1*sign] = '@';
            return true;
        } else if(array[y][x+1*sign] == '$'){
            if(array[y][x+2*sign] == '.' || array[y][x+2*sign] == 'u'){
                array[y][x] = '.';
                array[y][x+1*sign] = '@';
                array[y][x+2*sign] = '$';
                return true;
            }else{
                return false;
            }
        } else {
            return false;
        }
    }
}