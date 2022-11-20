package sos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.swing.*;

import static sos.SOS.Dirn.*;

public class SOS {
    public static final Color BACKGROUND = new Color(25, 25, 25);
    public static final Color FOREGROUND = new Color(25, 255, 0);
    public static final Color BUTTON_BG = new Color(150, 150, 150);
    public static final Color FG_X = new Color(225, 0, 0);
    public static final Color FG_O = new Color(0, 0, 225);
    public static final Color PANE_BG = new Color(50, 50, 50);
    public static final int ROW = 7;
    public static final int COL = 7;
    public static final int SIZE = ROW * COL;
    Random random = new Random();
    JFrame frame = new JFrame("SOS Game");
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JPanel reloadP = new JPanel();
    JLabel textField = new JLabel();
    JButton[] buttons = new JButton[SIZE];
    JButton reloadB = new JButton();
    JTextField tf = new JTextField();

    boolean player1Turn;

    SOS() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(PANE_BG);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        textField.setBackground(BACKGROUND);
        textField.setForeground(FOREGROUND);
        textField.setFont(new Font("Ink Free", Font.BOLD, 75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("SOS");
        textField.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);

        tf.setPreferredSize(new Dimension(200, 30));
        reloadP.setLayout(new BorderLayout());
        reloadP.setBackground(Color.GREEN);
        reloadP.add(reloadB);
        reloadB.setText("Reload");
        reloadB.setBackground(Color.DARK_GRAY);
        reloadB.setForeground(Color.cyan);
        reloadB.setFont(new Font("MV Boli", Font.ITALIC, 50));
        reloadB.setFocusable(false);
        reloadB.addActionListener(new BoardAction(this, -1));
        button_panel.setLayout(new GridLayout(ROW, COL));
        button_panel.setBackground(BUTTON_BG);
        IntStream.range(0, SIZE).forEach(i -> {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 50));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(new BoardAction(this, i));
            button_panel.add(buttons[i]);
        });

        title_panel.add(textField);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);
        frame.add(reloadP, BorderLayout.WEST);
        frame.add(tf, BorderLayout.SOUTH);
        firstturn();


    }

    public void reload() {
        // create a for loop
        for (JButton button : buttons) {
            // set the text of the JButton array to empty
            button.setText("");
            // set the text of the textfield to empty
            button.setEnabled(true);
            button.setBackground(new Color(240,240,240));
        }
        // call the firstTurn method
        firstturn();
    }

    public void buttonClicked(ActionEvent e, int i) {
        if (e.getSource() instanceof JButton button) {
            if (button == reloadB) {
                reload();
            } else {
                playTurn(button, i);
            }
        }
    }

    private void playTurn(JButton button, int i) {
        if (!button.getText().isEmpty()) return;

        String c = tf.getText();
        if (player1Turn) {
            selected(button, FG_X, c);
        } else {

            selected(button, FG_O, c);
        }
        player1Turn = !player1Turn;
        textField.setText(player1Turn ? "Player 1 turn" : "Player 2 turn");
        check(button, i);
    }

    private void selected(JButton button, Color fgX, String X) {
        button.setForeground(fgX);
        button.setText(X);
    }

    public void firstturn() {
        textField.setText("SOS G");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        player1Turn = random.nextInt(2) == 0;
        textField.setText(player1Turn ? "Player 1 turn" : "Player 2 turn");
    }

    public void check(JButton button, int i) {
        String[] buttons = Stream.of(this.buttons).map(JButton::getText).toArray(String[]::new);
        if (button.getText().equals("O")) {
            for(Dirn d : Dirn.values()){
                int x = go(i,d);
                if(x<0 || x>SIZE) continue;
                if(buttons[x].equals("S")){
                    if(buttons[go(i,revd(d))].equals("S")){
                        winCol(i,x,go(i,revd(d)));
                        break;
                    }
                }
            }
        }
        if (buttons[i].equals("S")) {
            for(Dirn d : Dirn.values()){
                int x = go(i,d);
                if(x<0 || x>SIZE) continue;
                if(buttons[x].equals("O")){
                    int y = go(x,d);
                    if(y<0 || y>SIZE) continue;
                    if(buttons[go(x,d)].equals("S")){
                        winCol(i,x,go(x,d));
                        break;
                    }
                }
            }

        }
    }

    int go(int i, Dirn dirn) {
        return switch (dirn) {
            case E -> i + 1;
            case W -> i - 1;
            case N -> i - ROW;
            case S -> i + ROW;
            case NE -> i - ROW + 1;
            case NW -> i - ROW - 1;
            case SE -> i + ROW + 1;
            case SW -> i + ROW - 1;
        };
    }
    Dirn revd(Dirn d){
        return switch (d){
            case E -> W;
            case N -> S;
            case S -> N;
            case W -> E;
            case NE -> SW;
            case NW -> SE;
            case SE -> NW;
            case SW -> NE;
        };
    }

    public enum Dirn {
        E, W, N, S, NE, NW, SE, SW
    }

    public void winCol(int a, int b, int c) {
        buttons[a].setBackground(Color.cyan);
        buttons[b].setBackground(Color.cyan);
        buttons[c].setBackground(Color.cyan);
        textField.setText(player1Turn ? "Player 2 Win" : "Player 1 Win");
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }

}

