import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
public class TicTacToe implements ActionListener {
    Scanner sc = new Scanner(System.in);
    Random random = new Random();
    JFrame frame = new JFrame("Tic");
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JPanel reloadP = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    JButton reloadB = new JButton();
    JTextField tf = new JTextField();

    boolean player1Turn;
    TicTacToe(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        textfield.setBackground(new Color(25,25,25));
        textfield.setForeground(new Color(25,255,0));
        textfield.setFont(new Font("Ink Free",Font.BOLD,75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,800,100);

        tf.setPreferredSize(new Dimension(200,30));
        reloadP.setLayout(new BorderLayout());
        reloadP.setBackground(Color.GREEN);
        reloadP.add(reloadB);
        reloadB.setText("Reload");
        reloadB.setBackground(Color.DARK_GRAY);
        reloadB.setForeground(Color.cyan);
        reloadB.setFont(new Font("MV Boli",Font.ITALIC,50));
        reloadB.setFocusable(false);
        reloadB.addActionListener(this);
        button_panel.setLayout(new GridLayout(3,3));
        button_panel.setBackground(new Color(150,150,150));
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli",Font.BOLD,120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        title_panel.add(textfield);
        frame.add(title_panel,BorderLayout.NORTH);
        frame.add(button_panel);
        frame.add(reloadP,BorderLayout.EAST);
        frame.add(tf,BorderLayout.SOUTH);
        firstturn();


    }
    public void reload() {
        // create a for loop
        for (int i = 0; i < 9; i++) {
            // set the text of the JButton array to empty
            buttons[i].setText("");
            // set the background color of the JButton array to the default color
            buttons[i].setBackground(new Color(240, 240, 240));
            // set the text of the textfield to empty
            buttons[i].setEnabled(true);
        }
        // call the firstTurn method
        firstturn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if(player1Turn){
                    if(buttons[i].getText()==""){
                        buttons[i].setForeground(new Color(225,0,0));
                        buttons[i].setText("X");
                        player1Turn =false;
                        textfield.setText("O turn");
                        check();
                    }
                }
                else{
                    if(buttons[i].getText()==""){
                        buttons[i].setForeground(new Color(0,0,225));
                        buttons[i].setText("O");
                        player1Turn =true;
                        textfield.setText("X turn");
                        check();
                    }
                }
            }
        }
        if(e.getSource() == reloadB){
            reload();
        }

    }
    public void firstturn(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        if(random.nextInt(2)==0){
            player1Turn =true;
            textfield.setText("X turn");
        }
        else {
            player1Turn =false;
            textfield.setText("O turn");
        }
    }
    public void check(){
        if(buttons[0].getText()=="X"
                && buttons[1].getText()=="X"
                && buttons[2].getText()=="X")
        {
            xwins(0,1,2);
        }
        if(        buttons[3].getText()=="X"
                && buttons[4].getText()=="X"
                && buttons[5].getText()=="X")
        {
            xwins(3,4,5);
        }
        if(        buttons[6].getText()=="X"
                && buttons[7].getText()=="X"
                && buttons[8].getText()=="X")
        {
            xwins(6,7,8);
        }
        if(        buttons[0].getText()=="X"
                && buttons[4].getText()=="X"
                && buttons[8].getText()=="X")
        {
            xwins(0,4,8);
        }
        if(        buttons[2].getText()=="X"
                && buttons[4].getText()=="X"
                && buttons[6].getText()=="X")
        {
            xwins(2,4,6);
        }
        if(        buttons[0].getText()=="X"
                && buttons[3].getText()=="X"
                && buttons[6].getText()=="X")
        {
            xwins(0,3,6);
        }
        if(        buttons[1].getText()=="X"
                && buttons[4].getText()=="X"
                && buttons[7].getText()=="X")
        {
            xwins(1,4,7);
        }
        if(        buttons[3].getText()=="X"
                && buttons[5].getText()=="X"
                && buttons[8].getText()=="X")
        {
            xwins(3,5,8);
        }
        
//O Win conditions
        if(buttons[0].getText()=="O"
                && buttons[1].getText()=="O"
                && buttons[2].getText()=="O")
        {
            owins(0,1,2);
        }
        if(        buttons[3].getText()=="O"
                && buttons[4].getText()=="O"
                && buttons[5].getText()=="O")
        {
            owins(3,4,5);
        }
        if(        buttons[6].getText()=="O"
                && buttons[7].getText()=="O"
                && buttons[8].getText()=="O")
        {
            owins(6,7,8);
        }
        if(        buttons[0].getText()=="O"
                && buttons[4].getText()=="O"
                && buttons[8].getText()=="O")
        {
            owins(0,4,8);
        }
        if(        buttons[2].getText()=="O"
                && buttons[4].getText()=="O"
                && buttons[6].getText()=="O")
        {
            owins(2,4,6);
        }
        if(        buttons[0].getText()=="O"
                && buttons[3].getText()=="O"
                && buttons[6].getText()=="O")
        {
            owins(0,3,6);
        }
        if(        buttons[1].getText()=="O"
                && buttons[4].getText()=="O"
                && buttons[7].getText()=="O")
        {
            owins(1,4,7);
        }
        if(        buttons[3].getText()=="O"
                && buttons[5].getText()=="O"
                && buttons[8].getText()=="O")
        {
            owins(3,5,8);
        }
    }


    public void xwins(int a,int b,int c){
        buttons[a].setBackground(Color.cyan);
        buttons[b].setBackground(Color.cyan);
        buttons[c].setBackground(Color.cyan);
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textfield.setText("X wins");
    }
    public void owins(int a, int b, int c){
        buttons[a].setBackground(Color.cyan);
        buttons[b].setBackground(Color.cyan);
        buttons[c].setBackground(Color.cyan);
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textfield.setText("O wins");
    }
}

