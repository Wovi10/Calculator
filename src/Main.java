import javax.swing.*;

public class Main {
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

    public Main() {
        InitiateCalc();
    }

    /**
     * Extra Methods to keep Main clean
     */
    private void InitiateCalc() {
        InitiateNumbers();
        InitiateSigns();
    }

    private void InitiateNumbers() {
        Utils.InitiateNumButton(NumZeroBut, "0");
        Utils.InitiateNumButton(NumOneBut, "1");
        Utils.InitiateNumButton(NumTwoBut, "2");
        Utils.InitiateNumButton(NumThreeBut, "3");
        Utils.InitiateNumButton(NumFourBut, "4");
        Utils.InitiateNumButton(NumFiveBut, "5");
        Utils.InitiateNumButton(NumSixBut, "6");
        Utils.InitiateNumButton(NumSevenBut, "7");
        Utils.InitiateNumButton(NumEightBut, "8");
        Utils.InitiateNumButton(NumNineBut, "9");
        Utils.InitiateNumButton(NumSepBut, ".");
    }

    private void InitiateSigns() {
        Utils.InitiateSignButton(SignPlusBut, "+");
        Utils.InitiateSignButton(SignMinBut, "-");
        Utils.InitiateSignButton(SignTimeBut, "x");
        Utils.InitiateSignButton(SignDivBut, "/");
    }
}
