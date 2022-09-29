import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Utils {
    static Calculator calc;
    public static void InitiateNumButton(JButton button, String value) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calc.addToNumber(value);
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
}
