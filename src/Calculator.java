import java.util.Objects;

public class Calculator {
    private String variable = "";
    private String variable2 = "";
    private String sign = "";
    private final Main main = new Main();
    public void addToNumber(String value) {
        String separator = ".";
        if (variable.isEmpty()){
            variable = value;
        } else if (variable.contains(separator) && !Objects.equals(value, separator)) {
            variable += value;
        }
        updateForm(variable);
    }

    private void updateForm(String lblText) {
        main.OutputLbl.setText(lblText);
    }

    public void setSign(String signToSet) {
        
    }
}
