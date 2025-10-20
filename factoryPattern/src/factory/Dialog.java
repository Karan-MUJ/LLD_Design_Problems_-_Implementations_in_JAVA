package factory;

import buttons.Button;

public abstract class Dialog {
    abstract Button createButton();
    void render(){
        Button button = createButton();
        button.render();
        button.onClick();
    }
}
