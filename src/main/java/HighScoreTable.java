import java.util.ArrayList;

public class HighScoreTable {
    private String name;
    private long time;

    private ArrayList<HighScoreListener> listeners;

    public HighScoreTable() {
        this.name = "Anonymous";
        this.time = 9999999;
        listeners = new ArrayList<>();
    }

    public void addHighScoreListener(HighScoreListener listener) {
        listeners.add(listener);
    }

    public void removeHighScoreListener(HighScoreListener listener) {
        listeners.remove(listener);
    }

    private void notifyHighScoreListeners() {
        for (HighScoreListener list : listeners) {
            list.highScoresUpdated(this);
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
