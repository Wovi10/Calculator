import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Main extends JFrame {
    private static final String DEFAULT_EMPTY = "";
    private static final String SANITISED_VARIABLE = "0";
    private static final String PLUS_SIGN = "+";
    private static final String MINUS_SIGN = "-";
    private static final String TIMES_SIGN = "x";
    private static final String DIVIDE_SIGN = "/";
    private static final String RESULT_PATTERN = "#.#####";
    private static final String separator = ".";
    private static String variable = DEFAULT_EMPTY;
    private static String variable2 = DEFAULT_EMPTY;
    private static String sign = DEFAULT_EMPTY;
    private static String resultStr = DEFAULT_EMPTY;
    private static State calculatorState = State.Variable1;
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
    private JButton ClearEntryBut;
    private JButton ClearBut;
    private static Main main;

    public static void main(String[] args) {
        main = new Main();
        main.setContentPane(main.CalculatorPanel);
        main.setTitle(CalculatorVariables.TITLE);
        main.setVisible(true);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setSize(CalculatorVariables.FORM_WIDTH, CalculatorVariables.FORM_HEIGHT);
    }

    private Main() {
        initiateCalculator();
    }

    private void initiateCalculator() {
        initiateNumButtons();
        initiateSignButtons();
        initiateEquals();
        initiateOtherButtons();
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

    private void addNumber(String pressedValue) {
        resultStr = CalculatorVariables.DEFAULT_EMPTY;
        if (isVarEmpty(sign)) {
            if (isVarEmpty(variable)) {
                variable = pressedValue;
            } else if (isNoDoubleSep(variable, pressedValue)) {
                variable += pressedValue;
            }
            if (!calculatorState.equals(State.Variable1)){
                changeState();
            }
        } else {
            if (isVarEmpty(variable2)) {
                variable2 = pressedValue;
            } else if (isNoDoubleSep(variable2, pressedValue)) {
                variable2 += pressedValue;
            }
            if (!calculatorState.equals(State.Variable2)){
                changeState();
            }
        }
        updateForm();
    }

    private void updateForm() {
        String outputText = String.format("%s %s %s", variable, sign, variable2);
        main.OutputLbl.setText(outputText);
    }

    private void changeState() {
        calculatorState = calculatorState.setNext();
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
                setSign(button.getText());
            }
        });
    }

    private void setSign(String signToSet) {
        sign = signToSet;
        if (!isVarEmpty(resultStr)) {
            variable = resultStr;
        }
        if (!calculatorState.equals(State.Sign)){
            changeState();
        }
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

    private void calculate() {
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
        String resultToShow = new java.text.DecimalFormat(RESULT_PATTERN).format(result);
        showResult(resultToShow);
        prepareNextCalc(resultToShow);
    }

    private void sanitiseVariables() {
        if (isVarEmpty(variable)) {
            variable = SANITISED_VARIABLE;
        }
        if (isVarEmpty(variable2)) {
            variable2 = SANITISED_VARIABLE;
        }
        if (isVarEmpty(sign)) {
            sign = PLUS_SIGN;
        }
    }

    private void showResult(String result) {
        String textToShow = String.format("%s %s %s = %s", variable, sign, variable2, result);
        main.OutputLbl.setText(textToShow);
    }

    private void prepareNextCalc(String resultToShow) {
        resultStr = resultToShow;
        variable = CalculatorVariables.DEFAULT_EMPTY;
        variable2 = CalculatorVariables.DEFAULT_EMPTY;
        sign = CalculatorVariables.DEFAULT_EMPTY;
    }

    private static boolean isVarEmpty(String varToCheck) {
        return varToCheck.isEmpty();
    }

    private static boolean isNoDoubleSep(String varToCheck, String pressedValue) {
        return !varToCheck.contains(separator) || !Objects.equals(pressedValue, separator);
    }

    private void initiateOtherButtons() {
        ClearEntryBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearEntry();
            }
        });

        ClearBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAll();
            }
        });
    }

    private void clearAll() {
        variable = CalculatorVariables.DEFAULT_EMPTY;
        sign = CalculatorVariables.DEFAULT_EMPTY;
        variable2 = CalculatorVariables.DEFAULT_EMPTY;
        resultStr = CalculatorVariables.DEFAULT_EMPTY;
        calculatorState = State.Variable1;
        updateForm();
    }

    private void clearEntry() {
        switch (calculatorState){
            case Variable1 -> variable = CalculatorVariables.DEFAULT_EMPTY;
            case Sign -> sign = CalculatorVariables.DEFAULT_EMPTY;
            case Variable2 -> variable2 = CalculatorVariables.DEFAULT_EMPTY;
        }
        updateForm();
    }
}
