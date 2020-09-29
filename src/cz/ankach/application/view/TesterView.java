package cz.ankach.application.view;

import java.util.List;

public class TesterView {
    public void showErrors (List<String> errors) {
        for (String e: errors) {
            System.out.println(e);
        }
    }
}
