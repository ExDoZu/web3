package zuev.nikita.beans;


import zuev.nikita.beans.database.Database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HitHandlerBean implements Serializable {

    private final Database database = new Database();

    private boolean xMinus4;
    private boolean xMinus3;
    private boolean xMinus2;
    private boolean xMinus1;
    private boolean x0 = true;
    private boolean x1;
    private boolean x2;

    private int realX;

    private double y;

    private double r = 2.0;

    final private List<Hit> hits;
    final private List<String> hitResultsCssClasses;


    public HitHandlerBean() {

        hits = database.getHits();
        hitResultsCssClasses = getListOfCssClasses();
    }

    public void addHit() {
        long startTime = System.nanoTime();
        boolean hitResult = checkArea();
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        long currentTime = new Date().getTime();
        Hit hit = new Hit(-1, realX, y, r, hitResult, executionTime, currentTime);
        database.addHit(hit);
        addCssClassToHitResult(hit);
        hits.add(0, hit);

    }

    public String getHitResultsCssClassesAsString() {
        return hitResultsCssClasses.toString().replace("[", "").replace("]", "").replace(" ", "");

    }

    private List<String> getListOfCssClasses() {
        List<String> hrcc = new ArrayList<>();
        for (Hit hit : hits) {
            hrcc.add(hit.getHitResult() ? "hit_row" : "miss_row");
        }
        return hrcc;
    }

    private void addCssClassToHitResult(Hit hit) {
        hitResultsCssClasses.add(0, hit.getHitResult() ? "hit_row" : "miss_row");
    }


    private boolean checkArea() {
        if (realX <= r && y <= r / 2 && y >= 0 && realX >= 0) {
            return true;
        }
        if (y >= realX / 2.0 - r / 2.0 && y <= 0 && realX >= 0) {
            return true;
        }
        if (y >= 0 && realX <= 0 && Math.sqrt(realX * realX + y * y) <= r / 2.0) {
            return true;
        }
        return false;
    }

    public void clear() {
        database.clear();
        hits.clear();
        hitResultsCssClasses.clear();
    }

    public List<Hit> getHits() {
        return hits;
    }


    public boolean getxMinus4() {
        return xMinus4;
    }

    public void setxMinus4(boolean xMinus4) {
        this.xMinus4 = xMinus4;
        if (xMinus4) realX = -4;

    }

    public boolean getxMinus3() {
        return xMinus3;
    }

    public void setxMinus3(boolean xMinus3) {
        this.xMinus3 = xMinus3;
        if (xMinus3) realX = -3;
    }

    public boolean getxMinus2() {
        return xMinus2;
    }

    public void setxMinus2(boolean xMinus2) {
        this.xMinus2 = xMinus2;
        if (xMinus2) realX = -2;

    }

    public boolean getxMinus1() {
        return xMinus1;
    }

    public void setxMinus1(boolean xMinus1) {
        this.xMinus1 = xMinus1;
        if (xMinus1) realX = -1;


    }

    public boolean getx0() {
        return x0;
    }

    public void setx0(boolean x0) {
        this.x0 = x0;
        if (x0) realX = 0;

    }

    public boolean getx1() {
        return x1;
    }

    public void setx1(boolean x1) {
        this.x1 = x1;
        if (x1) realX = 1;

    }

    public boolean getx2() {
        return x2;
    }

    public void setx2(boolean x2) {
        this.x2 = x2;
        if (x2) realX = 2;

    }

    public int getRealX() {
        return realX;
    }

    public void setRealX(int realX) {
        this.realX = realX;
    }


    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r * 4;
    }

    public void setR(double r) {
        this.r = r / 4;
    }
}
