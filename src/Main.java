import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame{
    /**
     * Standard declaration of form elements
     */
    public JLabel OutputLbl;
    private JButton NumZeroBut;
    private JButton NumOneBut;
    private JButton NumTwoBut;
    private JButton NumThreeBut;
    private JButton NumFourBut;
    private JButton NumFiveBut;
    private JButton NumSixBut;
    private JButton NumSevenBut;
    private JButton NumEightBut;
    private JButton NumNineBut;
    private JButton NumSepBut;
    private JButton SignPlusBut;
    private JButton SignMinBut;
    private JButton SignTimeBut;
    private JButton SignDivBut;
    private JButton SignEquBut;
    private JPanel CalculatorPanel;
    private static Main main;
    private static Utils utils;

    public Main() {
        NumZeroBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calculator.addNumber("0");
            }
        });

        InitiateCalc();
    }

    public static void main(String[] args) {
        main = new Main();
        main.setContentPane(main.CalculatorPanel);

        main.setTitle("Calculator");
        main.setVisible(true);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setSize(500, 800);
        utils = new Utils(main);
    }

    /**
     * Extra Methods to keep Main clean
     */
    private void InitiateCalc() {
        InitiateNumbers();
        InitiateSigns();
        InitiateEquals();
    }

    private void InitiateNumbers() {
//        Utils utils = new Utils(m);
        utils.InitiateNumButton(NumZeroBut, "0");
        utils.InitiateNumButton(NumOneBut, "1");
        utils.InitiateNumButton(NumTwoBut, "2");
        utils.InitiateNumButton(NumThreeBut, "3");
        utils.InitiateNumButton(NumFourBut, "4");
        utils.InitiateNumButton(NumFiveBut, "5");
        utils.InitiateNumButton(NumSixBut, "6");
        utils.InitiateNumButton(NumSevenBut, "7");
        utils.InitiateNumButton(NumEightBut, "8");
        utils.InitiateNumButton(NumNineBut, "9");
        utils.InitiateNumButton(NumSepBut, ".");
    }

    private void InitiateSigns() {
        utils.InitiateSignButton(SignPlusBut, "+");
        utils.InitiateSignButton(SignMinBut, "-");
        utils.InitiateSignButton(SignTimeBut, "x");
        utils.InitiateSignButton(SignDivBut, "/");
    }

    private void InitiateEquals() {
        utils.InitiateEquals(SignEquBut);
    }
}
