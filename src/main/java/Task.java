import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private final String task;
    private boolean done = false;
    private final int type; // 0 is todo, 1 is deadline, 2 is event
    private final LocalDate date;
    private final LocalTime time;

    public Task(String s, int i) {
        if (i == 0) {
            this.task = s;
            this.date = null;
            this.time = null;
        } else {
            String[] info = s.split("/");
            if (info.length == 1 || info[0].equals(" ")) {
                throw new IllegalArgumentException();
            } else if (i == 1) {
                this.date = LocalDate.parse(info[1].substring(3));
                this.time = null;
            } else {
                this.date = null;
                this.time = LocalTime.parse(info[1].substring(3));
            }
            this.task = info[0];
        }
        this.type = i;
    }

    public void markDone() {
        this.done = true;
    }

    public String checkDone() {
        if (this.done) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    public String checkType() {
        if (this.type == 0) {
            return "[T]";
        } else if (this.type == 1) {
            return "[D]";
        } else {
            return "[E]";
        }
    }
    
    public String toString() {
        if (this.type == 0) {
            return checkType() + checkDone() + this.task;
        } else if  (this.type == 1) {
            return checkType() + checkDone() + this.task + "(by: "
                    + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return checkType() + checkDone() + this.task + "(at: " +
                    this.time.format(DateTimeFormatter.ofPattern("hh:mm a"))+ ")";
        }
    }
}
