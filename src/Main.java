import javax.swing.*;

public class Main {
    /**
     * Standard declaration of form elements
     */
    public JLabel OutputLbl;
    private static JButton NumZeroBut;
    private static JButton NumOneBut;
    private static JButton NumTwoBut;
    private static JButton NumThreeBut;
    private static JButton NumFourBut;
    private static JButton NumFiveBut;
    private static JButton NumSixBut;
    private static JButton NumSevenBut;
    private static JButton NumEightBut;
    private static JButton NumNineBut;
    private static JButton NumSepBut;
    private static JButton SignPlusBut;
    private static JButton SignMinBut;
    private static JButton SignTimeBut;
    private static JButton SignDivBut;
    private static JButton SignEquBut;

    public static void main(String[] args) {
        InitiateCalc();
    }

    /**
     * Extra Methods to keep Main clean
     */
    private static void InitiateCalc() {
        InitiateNumbers();
        InitiateSigns();
        InitiateEquals();
    }

    private static void InitiateNumbers() {
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

    private static void InitiateSigns() {
        Utils.InitiateSignButton(SignPlusBut, "+");
        Utils.InitiateSignButton(SignMinBut, "-");
        Utils.InitiateSignButton(SignTimeBut, "x");
        Utils.InitiateSignButton(SignDivBut, "/");
    }

    private static void InitiateEquals() {
        Utils.InitiateEquals(SignEquBut);
    }
}
