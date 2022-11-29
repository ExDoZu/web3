package zuev.nikita.beans;

import jakarta.persistence.*;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Table(name = "HitResults")
@Entity
public class Hit implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id=1;
    @Column
    private int x;
    @Column
    private double y;
    @Column
    private double r;
    @Column
    private boolean hitResult;
    @Column
    private long executionTime;
    @Column
    private long currentTime;

    public Hit() {
    }

    public Hit(int id, int x, double y, double r, boolean hit, long executionTime, long currentTime) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.r = r;
        this.hitResult = hit;
        this.executionTime = executionTime;
        this.currentTime = currentTime;
    }

    public String getHitResultAsString() {
        return hitResult ? "Попал(a)" : "Не попал(a)";
    }

    public String getCurrentTimeAsString() {
        return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date(currentTime));
    }

    public String getExecutionTimeAsString() {
        return String.format("%.3f мкс", executionTime / 1000.0).replace(',', '.');
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public boolean getHitResult() {
        return hitResult;
    }

    public void setHitResult(boolean hit) {
        this.hitResult = hit;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public Long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }
}

