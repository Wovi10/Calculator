import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Main extends JFrame{
    public static final String TITLE = "Calculator";
    public static final int FORM_WIDTH = 500;
    public static final int FORM_HEIGHT = 800;
    public static final String SANATISED_VARIABLE = "0";
    public static final String DEFAULT_EMPTY = "";
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
    private static String variable = DEFAULT_EMPTY;
    private static String variable2 = DEFAULT_EMPTY;
    private static String sign = DEFAULT_EMPTY;
    private static final String separator = ".";

    public static void main(String[] args) {
        main = new Main();
        main.setContentPane(main.CalculatorPanel);
        main.setTitle(TITLE);
        main.setVisible(true);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setSize(FORM_WIDTH, FORM_HEIGHT);
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
        initiateNumButton(NumZeroBut);
        initiateNumButton(NumOneBut);
        initiateNumButton(NumTwoBut);
        initiateNumButton(NumThreeBut);
        initiateNumButton(NumFourBut);
        initiateNumButton(NumFiveBut);
        initiateNumButton(NumSixBut);
        initiateNumButton(NumSevenBut);
        initiateNumButton(NumEightBut);
        initiateNumButton(NumNineBut);
        initiateNumButton(NumSepBut);
    }

    private void initiateNumButton(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNumber(button.getText());
            }
        });
    }

    private void addNumber(String value) {
        System.out.println(variable);
        if (sign.isEmpty()){
            if (variable.isEmpty()){
                variable = value;
            } else if (!variable.contains(separator) || !Objects.equals(value, separator)) {
                variable += value;
            }
            updateForm();
        }else {
            if (variable2.isEmpty()){
                variable2 = value;
            } else if (!variable2.contains(separator) || !Objects.equals(value, separator)) {
                variable2 += value;
            }
            updateForm();
        }
    }

    private void updateForm(){
        String outputText = String.format("%s %s %s", variable, sign, variable2);
        main.OutputLbl.setText(outputText);
    }

    private void initiateSignButtons() {
        initiateSignButton(SignPlusBut);
        initiateSignButton(SignMinBut);
        initiateSignButton(SignTimeBut);
        initiateSignButton(SignDivBut);
    }

    private void initiateSignButton(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSign(SignPlusBut.getText());
            }
        });
    }

    private void setSign(String signToSet) {
        sign = signToSet;
        updateForm();
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
        prepareNextCalc();
        sign = DEFAULT_EMPTY;
    }

    private void prepareNextCalc() {
        
    }

    private void sanitiseVariables() {
        if (variable.isEmpty()){
            variable = SANATISED_VARIABLE;
        }
        if (variable2.isEmpty()){
            variable2 = SANATISED_VARIABLE;
        }
        if (sign.isEmpty()){
            sign = PLUS_SIGN;
        }
    }

    private void showResult(float result) {
        String textToShow = String.format("%s %s %s = %s", variable, sign, variable2, result);
        main.OutputLbl.setText(textToShow);
    }
}
