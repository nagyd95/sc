package gui;

import logic.GameLogic;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;


public class GameFrame extends JFrame {

    private final GameLogic gameLogic;
    private final GamePanel gamePanel;
    private final JMenuBar menuBar;
    private final InformationPanel infoPanel;

    public GameFrame(final GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        setFrameProperties();
        applyNimbusLookAndFeelTheme();
        gameLogic.newGame(5);

        this.infoPanel = new InformationPanel();
        getContentPane().add(infoPanel, BorderLayout.NORTH);
        this.gamePanel = new GamePanel(gameLogic, infoPanel);
        getContentPane().add(gamePanel, BorderLayout.CENTER);
        this.menuBar = new GameMenuBar();
        setJMenuBar(menuBar);

        pack();
    }


    private void setFrameProperties(){
        setTitle("Color game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(100, 100);
        setLayout(new BorderLayout());
    }


    private void applyNimbusLookAndFeelTheme(){
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {
        }
    }

    public class GameMenuBar extends JMenuBar {

        private final JMenu gameMenu;
        private final JMenuItem newGame;

        public GameMenuBar(){
            gameMenu = new JMenu("Game");
            
            newGame = new JMenuItem(newGameAction);
            newGame.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
            gameMenu.add(newGame);
            add(gameMenu);
        }

        public final Action newGameAction = new AbstractAction("New game") {

            @Override
            public void actionPerformed(ActionEvent ae) {
                final Integer[] gameSizes = new Integer[]{3, 5, 7};
                final Object resultObject =
                        JOptionPane.showInputDialog(rootPane, "Select a new game table size",
                                "New game", JOptionPane.QUESTION_MESSAGE, null, gameSizes, gameSizes[0]);
                if (resultObject != null) {
                    int gameSize = (int) resultObject;
                    gameLogic.newGame(gameSize);
                    gamePanel.newGame();
                    infoPanel.newGame();
                    pack();
                }
            }

        };
    }

}
