package tp1.swing;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import tp1.Calculatrice_Core;

public class WindowSwing extends JFrame {
    
    private final static Font SCREEN_FONT = new Font("sans-serif", 0, 35);
    private final static Font DIGIT_FONT = new Font("serif", Font.BOLD, 25);
    private final static Font OPERATION_FONT = new Font("serif", Font.BOLD, 40);
    
    private final JLabel screen;
    private final JButton[] digits;
    private final JButton addButton;
    private final JButton minusButton;
    private final JButton productButton;
    private final JButton divideButton;
    private final JButton equalButton;
    
    private final Calculatrice_Core core;
    
    public WindowSwing(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Calculatrice Swing");
        resize(720, 350);
        
        core = new Calculatrice_Core();
        
        screen = new JLabel("0");
        screen.setFont(SCREEN_FONT);
        screen.setHorizontalAlignment(SwingConstants.RIGHT);
        
        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        JPanel digitPanel = new JPanel(new GridLayout(3, 3));
        JPanel operationPanel = new JPanel(new GridLayout(2, 2));
        
        digits = new JButton[10];
        
        digits[0] = new JButton("0");
        digits[0].setFont(DIGIT_FONT);
        digits[0].addMouseListener(new DigitListener(0));
        
        for(int i=1; i<=9; ++i){
            digits[i] = new JButton(""+(i));
            digits[i].setFont(DIGIT_FONT);
            digits[i].addMouseListener(new DigitListener(i));
            digitPanel.add(digits[i]);
        }
        
        addButton = new JButton("+");
        addButton.setFont(OPERATION_FONT);
        addButton.addMouseListener(new OperationListener('+'));
        operationPanel.add(addButton);
        
        minusButton = new JButton("-");
        minusButton.setFont(OPERATION_FONT);
        minusButton.addMouseListener(new OperationListener('-'));
        operationPanel.add(minusButton);
        
        productButton = new JButton("*");
        productButton.setFont(OPERATION_FONT);
        productButton.addMouseListener(new OperationListener('*'));
        operationPanel.add(productButton);
        
        divideButton = new JButton("/");
        divideButton.setFont(OPERATION_FONT);
        divideButton.addMouseListener(new OperationListener('/'));
        operationPanel.add(divideButton);
        
        equalButton = new JButton("=");
        equalButton.setFont(OPERATION_FONT);
        equalButton.addMouseListener(new OperationListener('='));
        
        centerPanel.add(digitPanel);
        centerPanel.add(operationPanel);
        
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
        bottomPanel.add(digits[0]);
        bottomPanel.add(equalButton);
        
        getContentPane().add(screen, BorderLayout.NORTH);
        getContentPane().add(centerPanel, BorderLayout.CENTER);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);
    }
    
    class DigitListener implements MouseListener {
        private final char digit;
        
        public DigitListener(int digit){
            this.digit = (""+digit).charAt(0);
        }
        
        @Override
        public void mouseClicked(MouseEvent e){
            core.add_number(digit);
            screen.setText(""+core.getCurrentValue());
        }
        
        @Override
        public void mousePressed(MouseEvent e){ }
        @Override
        public void mouseReleased(MouseEvent e){ }
        @Override
        public void mouseEntered(MouseEvent e){ }
        @Override
        public void mouseExited(MouseEvent e){ }
    }
    
    class OperationListener implements MouseListener {
        private final char operation;
        
        public OperationListener(char operation){
            this.operation = operation;
        }
        
        @Override
        public void mouseClicked(MouseEvent e){
            core.add_symbol(operation);
            screen.setText(""+core.getCurrentValue());
        }
        
        @Override
        public void mousePressed(MouseEvent e){ }
        @Override
        public void mouseReleased(MouseEvent e){ }
        @Override
        public void mouseEntered(MouseEvent e){ }
        @Override
        public void mouseExited(MouseEvent e){ }
    }
}
