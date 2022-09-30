import java.util.Objects;

public class Calculator {
    public static final String PLUS_SIGN = "+";
    public static final String MINUS_SIGN = "-";
    public static final String TIMES_SIGN = "x";
    public static final String DIVIDE_SIGN = "/";
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

    private void updateForm(String textToAdd) {
        String originalText = main.OutputLbl.getText();
        main.OutputLbl.setText(originalText + textToAdd);
    }

    private void showResult(float result) {
        String textToShow = String.format("%f", result);
        main.OutputLbl.setText(textToShow);
    }
}
