import java.io.Serializable;
import java.util.ArrayList;

public class HighScoreTable implements Serializable {
    private String name;
    private long time;

    // transient because it is not serializable, meaning it cannot be saved to a file and loaded back in again later on in the program
    private transient ArrayList<HighScoreListener> listeners;

    public HighScoreTable() {
        this.name = "Anonymous";
        this.time = 9999999;
        listeners = new ArrayList<>();
    }

    public void addHighScoreListener(HighScoreListener listener) {
        if (listeners == null) listeners = new ArrayList<>();
        listeners.add(listener);
    }

    public void removeHighScoreListener(HighScoreListener listener) {
        if(listeners != null)
            listeners.remove(listener);
    }

    private void notifyHighScoreListeners() {
        if (listeners != null) {
            for (HighScoreListener list : listeners) {
                list.highScoresUpdated(this);
            }
        }
    }

    public String getName() {
        return name;
    }

    public long getTime() {
        return time;
    }

    public void setRecord(String name, long time) {
        if(time < this.time) {
            this.name = name;
            this.time = time;
            notifyHighScoreListeners();
        }
    }
}
