import java.util.Objects;

public class Calculator {
    private String variable = "";
    private String variable2 = "";
    private String sign = "";
    private String separator = ".";
    private final Main main = new Main();
    public void addNumber(String value) {
        if (sign.isEmpty()){
            if (variable.isEmpty()){
                variable = value;
            } else if (variable.contains(separator) && !Objects.equals(value, separator)) {
                variable += value;
            }
        }else {
            if (variable2.isEmpty()){
                variable2 = value;
            } else if (variable2.contains(separator) && !Objects.equals(value, separator)) {
                variable2 += value;
            }
        }
        updateForm(variable);
    }

    public void setSign(String signToSet) {
        sign = signToSet;
        updateForm(sign);
    }

    private void updateForm(String textToAdd) {
        String originalText = main.OutputLbl.getText();
        main.OutputLbl.setText(originalText + textToAdd);
    }
}
