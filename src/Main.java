import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

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
    public static final String PLUS_SIGN = "+";
    public static final String MINUS_SIGN = "-";
    public static final String TIMES_SIGN = "x";
    public static final String DIVIDE_SIGN = "/";
    private static String variable = "";
    private static String variable2 = "";
    private static String sign = "";
    private static String separator = ".";

    public static void main(String[] args) {
        main = new Main();
        main.setContentPane(main.CalculatorPanel);

        main.setTitle("Calculator");
        main.setVisible(true);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setSize(500, 800);
    }

    public Main() {
        initiateCalculator();
    }

    private void initiateCalculator() {
        initiateNumButtons();
        initiateSignButtons();
        initiateEquals();
    }

    private void initiateNumButtons() {
        initiateNumButton(NumZeroBut, "0");
        initiateNumButton(NumOneBut, "1");
        initiateNumButton(NumTwoBut, "2");
        initiateNumButton(NumThreeBut, "3");
        initiateNumButton(NumFourBut, "4");
        initiateNumButton(NumFiveBut, "5");
        initiateNumButton(NumSixBut, "6");
        initiateNumButton(NumSevenBut, "7");
        initiateNumButton(NumEightBut, "8");
        initiateNumButton(NumNineBut, "9");
        initiateNumButton(NumSepBut, ".");
    }

    private void initiateNumButton(JButton button, String value) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNumber(value);
            }
        });
    }

    private void addNumber(String value) {
        System.out.println(variable);
        if (sign.isEmpty()){
            if (variable.isEmpty()){
                variable = value;
            } else if (variable.contains(separator) && !Objects.equals(value, separator)) {
                variable += value;
            }
            updateForm(variable);
        }else {
            if (variable2.isEmpty()){
                variable2 = value;
            } else if (variable2.contains(separator) && !Objects.equals(value, separator)) {
                variable2 += value;
            }
            updateForm(variable2);
        }
    }

    private void updateForm(String textToAdd) {
        String originalText = main.OutputLbl.getText();
        main.OutputLbl.setText(originalText + textToAdd);
    }

    private void initiateSignButtons() {
        initiateSignButton(SignPlusBut, PLUS_SIGN);
        initiateSignButton(SignMinBut, MINUS_SIGN);
        initiateSignButton(SignTimeBut, TIMES_SIGN);
        initiateSignButton(SignDivBut, DIVIDE_SIGN);
    }

    private void initiateSignButton(JButton button, String sign) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSign(sign);
            }
        });
    }

    private void setSign(String signToSet) {
        sign = signToSet;
        updateForm(sign);
    }

    private void initiateEquals() {
        SignEquBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });
    }
    public void calculate() {
        sanitiseVariables();
        float variableFloat = Float.parseFloat(variable);
        float variable2Float = Float.parseFloat(variable2);
        float result = switch (sign) {
            case PLUS_SIGN -> variableFloat + variable2Float;
            case MINUS_SIGN -> variableFloat - variable2Float;
            case TIMES_SIGN -> variableFloat * variable2Float;
            case DIVIDE_SIGN -> variableFloat / variable2Float;
            default -> 0;
        };
        showResult(result);
        sign = "";
    }

    private void sanitiseVariables() {
        if (variable.isEmpty()){
            variable = "0";
        }
        if (variable2.isEmpty()){
            variable2 = "0";
        }
        if (sign.isEmpty()){
            sign = "+";
        }
    }

    private void showResult(float result) {
        String textToShow = String.format("%f", result);
        main.OutputLbl.setText(textToShow);
    }
}
