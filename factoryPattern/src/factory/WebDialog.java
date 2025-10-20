package factory;

import buttons.Button;
import buttons.HTMLButton;

public class WebDialog extends Dialog {
    @Override
    Button createButton() {
        return new HTMLButton();
    }
}
