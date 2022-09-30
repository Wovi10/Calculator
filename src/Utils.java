import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Utils {
    private static Main main;
    public Utils(Main main) {
        Utils.main = main;
    }

    private static Calculator calc = new Calculator(main);
    public static void InitiateNumButton(JButton button, String value) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calc.addNumber(value);
            }
        });
    }

    public static void InitiateSignButton(JButton button, String sign) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calc.setSign(sign);
            }
        });
    }

    public static void InitiateEquals(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calc.calculate();
            }
        });
    }
}
