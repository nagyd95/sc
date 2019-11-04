package gui;

import java.awt.event.ActionEvent;
import java.time.Duration;
import java.time.Instant;
import javax.swing.AbstractAction;
import javax.swing.JLabel;


public class TimerAction extends AbstractAction{

    private final JLabel timerLabel;
    private final Instant startTime;

    public TimerAction(final JLabel timerLabel) {
        this.timerLabel = timerLabel;
        this.startTime = Instant.now();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        timerLabel.setText(formatDuration(Duration.between(startTime, Instant.now())));
    }

    private String formatDuration(final Duration duration){
        final long seconds = duration.getSeconds();
        return String.format("%02d:%02d:%02d", seconds / 3600,
                (seconds % 3600) /60, seconds % 60);
    }
}

