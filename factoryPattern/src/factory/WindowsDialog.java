package factory;

import buttons.Button;
import buttons.WindowsButton;

public class WindowsDialog extends Dialog {

    @Override
    Button createButton() {
        return new WindowsButton();
    }
}
