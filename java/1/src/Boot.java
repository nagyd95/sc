
import gui.GameFrame;
import logic.GameLogic;

public class Boot {

    public static void main(String[] args){

        java.awt.EventQueue.invokeLater(
                () -> new GameFrame(new GameLogic()).setVisible(true)
        );

    }

}
