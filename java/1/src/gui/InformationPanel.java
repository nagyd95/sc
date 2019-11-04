package gui;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


public class InformationPanel extends JPanel {

    private final JLabel elapsedTimeTextLabel;
    private final JLabel elapsedTimeLabel;
    private final JLabel score_1CounterTextLabel;
    private final JLabel score_1CounterLabel;
    private final JLabel score_2CounterTextLabel;
    private final JLabel score_2CounterLabel;

    private final Font textFont;

    private Timer elapsedTime;

    public InformationPanel() {

        setPreferredSize(new Dimension(100, 50));
        textFont = new Font("Garamond", Font.ITALIC, 16);

        elapsedTimeTextLabel = createLabel("Elapsed time: ", textFont);
        elapsedTimeLabel = createLabel("", textFont);
        score_1CounterTextLabel = createLabel("Score_1: ", textFont);
        score_1CounterLabel = createLabel("", textFont);
        score_2CounterTextLabel = createLabel("Score_2: ", textFont);
        score_2CounterLabel = createLabel("", textFont);


        add(elapsedTimeTextLabel);
        add(elapsedTimeLabel);
        add(new JLabel("    "));
        add(score_1CounterTextLabel);
        add(score_1CounterLabel);
        add(score_2CounterTextLabel);
        add(score_2CounterLabel);

        newGame();
    }

    private JLabel createLabel(String text, Font font) {
        final JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
    }

    public final void newGame() {
        if (elapsedTime != null) {
            elapsedTime.stop();
        }
        elapsedTimeLabel.setText("00:00:00");
        setScore_1(0);
        setScore_2(0);
        elapsedTime = new Timer(1000, new TimerAction(elapsedTimeLabel));
        elapsedTime.start();
    }

    public void setScore_1(int score_1) {
        score_1CounterLabel.setText(String.valueOf(score_1));
    }
    public void setScore_2(int score_2) {
        score_2CounterLabel.setText(String.valueOf(score_2));
    }
}
