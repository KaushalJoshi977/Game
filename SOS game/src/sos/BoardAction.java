package sos;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class BoardAction extends AbstractAction {

    private final SOS sos;
    private final int i;

    BoardAction(SOS sos, int i){
        this.sos = sos;
        this.i = i;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        sos.buttonClicked(e, i);
    }
}
