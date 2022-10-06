import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Main extends JFrame {
    private static String variable = CalculatorConstants.DEFAULT_EMPTY;
    private static String variable2 = CalculatorConstants.DEFAULT_EMPTY;
    private static String sign = CalculatorConstants.DEFAULT_EMPTY;
    private static String resultStr = CalculatorConstants.DEFAULT_EMPTY;
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
    private JButton BackspaceBut;
    private static Main main;

    public static void main(String[] args) {
        main = new Main();
        main.setContentPane(main.CalculatorPanel);
        main.setTitle(CalculatorConstants.TITLE);
        main.setVisible(true);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setSize(CalculatorConstants.FORM_WIDTH, CalculatorConstants.FORM_HEIGHT);
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
        button.addActionListener(e -> addNumber(button.getText()));
    }

    private void addNumber(String pressedValue) {
        resultStr = CalculatorConstants.DEFAULT_EMPTY;
        if (isVarEmpty(sign)) {
            if (isVarEmpty(variable)) {
                variable = pressedValue;
            } else if (isNoDoubleSep(variable, pressedValue)) {
                variable += pressedValue;
            }
            if (!calculatorState.equals(State.Variable1)){
                changeState(State.Variable1);
            }
        } else {
            if (isVarEmpty(variable2)) {
                variable2 = pressedValue;
            } else if (isNoDoubleSep(variable2, pressedValue)) {
                variable2 += pressedValue;
            }
            if (!calculatorState.equals(State.Variable2)){
                changeState(State.Variable2);
            }
        }
        updateForm();
    }

    private void updateForm() {
        String outputText = String.format("%s %s %s", variable, sign, variable2);
        main.OutputLbl.setText(outputText);
    }

    private void changeState(State stateToSet) {
        calculatorState = stateToSet;
        System.out.println(calculatorState);
    }

    private void initiateSignButtons() {
        initiateSignButton(SignPlusBut);
        initiateSignButton(SignMinBut);
        initiateSignButton(SignTimeBut);
        initiateSignButton(SignDivBut);
    }

    private void initiateSignButton(JButton button) {
        button.addActionListener(e -> setSign(button.getText()));
    }

    private void setSign(String signToSet) {
        sign = signToSet;
        if (!isVarEmpty(resultStr)) {
            variable = resultStr;
        }
        if (!calculatorState.equals(State.Sign)){
            changeState(State.Sign);
        }
        updateForm();
    }

    private void initiateEquals() {
        SignEquBut.addActionListener(e -> calculate());
    }

    private void calculate() {
        sanitiseVariables();
        float variableFloat = Float.parseFloat(variable);
        float variable2Float = Float.parseFloat(variable2);
        float result = switch (sign) {
            case CalculatorConstants.PLUS_SIGN -> variableFloat + variable2Float;
            case CalculatorConstants.MINUS_SIGN -> variableFloat - variable2Float;
            case CalculatorConstants.TIMES_SIGN -> variableFloat * variable2Float;
            case CalculatorConstants.DIVIDE_SIGN -> variableFloat / variable2Float;
            default -> 0;
        };
        String resultToShow = new java.text.DecimalFormat(CalculatorConstants.RESULT_PATTERN).format(result);
        showResult(resultToShow);
        prepareNextCalc(resultToShow);
    }

    private void sanitiseVariables() {
        if (isVarEmpty(variable)) {
            variable = CalculatorConstants.SANITISED_VARIABLE;
        }
        if (isVarEmpty(variable2)) {
            variable2 = CalculatorConstants.SANITISED_VARIABLE;
        }
        if (isVarEmpty(sign)) {
            sign = CalculatorConstants.PLUS_SIGN;
        }
    }

    private void showResult(String result) {
        String textToShow = String.format("%s %s %s = %s", variable, sign, variable2, result);
        main.OutputLbl.setText(textToShow);
    }

    private void prepareNextCalc(String resultToShow) {
        resultStr = resultToShow;
        variable = CalculatorConstants.DEFAULT_EMPTY;
        variable2 = CalculatorConstants.DEFAULT_EMPTY;
        sign = CalculatorConstants.DEFAULT_EMPTY;
    }

    private static boolean isVarEmpty(String varToCheck) {
        return varToCheck.isEmpty();
    }

    private static boolean isNoDoubleSep(String varToCheck, String pressedValue) {
        return !varToCheck.contains(CalculatorConstants.SEPARATOR) || !Objects.equals(pressedValue, CalculatorConstants.SEPARATOR);
    }

    private void initiateOtherButtons() {
        ClearEntryBut.addActionListener(e -> clearEntry());

        ClearBut.addActionListener(e -> clearAll());

        BackspaceBut.addActionListener(e -> deleteLastTyped());
    }

    private void clearAll() {
        variable = CalculatorConstants.DEFAULT_EMPTY;
        sign = CalculatorConstants.DEFAULT_EMPTY;
        variable2 = CalculatorConstants.DEFAULT_EMPTY;
        resultStr = CalculatorConstants.DEFAULT_EMPTY;
        calculatorState = State.Variable1;
        updateForm();
    }

    private void clearEntry() {
        switch (calculatorState){
            case Variable1 -> variable = CalculatorConstants.DEFAULT_EMPTY;
            case Sign -> sign = CalculatorConstants.DEFAULT_EMPTY;
            case Variable2 -> variable2 = CalculatorConstants.DEFAULT_EMPTY;
        }
        updateForm();
    }

    private void deleteLastTyped() {
        switch (calculatorState){
            case Variable1 -> variable = variable.substring(0, variable.length()-1);
            case Sign -> sign = CalculatorConstants.DEFAULT_EMPTY;
            case Variable2 -> variable2 = variable2.substring(0, variable2.length()-1);
        }
        switch (calculatorState){
            case Variable1:
                if (!variable.isEmpty()){
                    System.out.println(variable);
                    variable = variable.substring(0, variable.length()-1);
                    System.out.println(variable);
                }
                break;
            case Sign:
                sign = CalculatorConstants.DEFAULT_EMPTY;
                calculatorState = State.Variable1;
                break;
            case Variable2:
                if (!variable2.isEmpty()){
                    variable2 = variable2.substring(0, variable2.length()-1);
                }else{
                    calculatorState = State.Sign;
                    deleteLastTyped();
                }
                break;
        }
        updateForm();
    }
}
