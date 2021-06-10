import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener{

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;

    TicTacToe(){

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(25,25,25));
        textfield.setForeground(new Color(25,255,0));
        textfield.setFont(new Font("Ink Free",Font.BOLD, 45));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,800,100);

        button_panel.setLayout(new GridLayout(3,3));
        button_panel.setBackground(new Color(150,150,150));

        for(int i=0;i<9;i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli",Font.BOLD,45));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

		

        title_panel.add(textfield);
        frame.add(title_panel,BorderLayout.NORTH);
        frame.add(button_panel);

        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for(int i=0;i<9;i++) {
            if(e.getSource()==buttons[i]) {
                if(player1_turn) {
                    if(buttons[i].getText()=="") {
                        buttons[i].setForeground(new Color(255,0,0));
                        buttons[i].setText("X");
                        player1_turn=false;
                        textfield.setText("O turn");
                        check();
                    }
                }
                else {
                    if(buttons[i].getText()=="") {
                        buttons[i].setForeground(new Color(0,0,255));
                        buttons[i].setText("O");
                        player1_turn=true;
                        textfield.setText("X turn");
                        check();
                    }
                }
            }
        }
    }

    public void firstTurn() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if(random.nextInt(2)==0) {
            player1_turn=true;
            textfield.setText("X turn");
        }
        else {
            player1_turn=false;
            textfield.setText("O turn");
        }
    }

    public void check() {
		int[][] xWinsArray = {
			{0,1,2}, {3,4,5}, {6,7,8},
			{0,3,6}, {1,4,7}, {2,5,8},
			{2,4,6}, {0,4,8}};

		//check X win conditions
		int xWinsCounter = 0;
		for (int[] i : xWinsArray) {
			xWinsCounter = 0;
			for (int j = 0; j < 9; j++) {
				for (int k : i) {
					if (buttons[j].getText() == "X" && k == j)
						++xWinsCounter;
					if (xWinsCounter == 3) {
						xWins(i);
						break;
					}	
				}
			}
		}

		int oWinsCounter = 0;
		for (int[] i : xWinsArray) {
			oWinsCounter = 0;
			for (int j = 0; j < 9; j++) {
				for (int k : i) {
					if (buttons[j].getText() == "O" && k == j)
						++oWinsCounter;
					if (oWinsCounter == 3) {
						oWins(i);
						break;
					}	
				}
			}
		}
    }

    public void xWins(int[] winSquares) {
        buttons[winSquares[0]].setBackground(Color.GREEN);
        buttons[winSquares[1]].setBackground(Color.GREEN);
        buttons[winSquares[2]].setBackground(Color.GREEN);

        for(int i=0;i<9;i++) {
            buttons[i].setEnabled(false);
        }
        textfield.setText("X wins");
    }
    public void oWins(int[] winSquares) {
        buttons[winSquares[0]].setBackground(Color.GREEN);
        buttons[winSquares[1]].setBackground(Color.GREEN);
        buttons[winSquares[2]].setBackground(Color.GREEN);

        for(int i=0;i<9;i++) {
            buttons[i].setEnabled(false);
        }
        textfield.setText("O wins");
    }
}