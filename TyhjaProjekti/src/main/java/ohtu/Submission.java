package ohtu;

import java.util.HashMap;
import java.util.Map;

public class Submission {

    private String student_number;
    private int hours;
    private int week;
    private boolean[] a;

    public Submission() {
        a = new boolean[21];
    }
    

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getStudent_number() {
        return student_number;
    }

    public int getHours() {
        return hours;
    }
    
    public void setA1() {
        a[1] = true;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }
    
    public void setAssigment(int i, String value) {
        a[i] = Boolean.parseBoolean(value);
    }
    
    public boolean getAssigment(int i) {
        return a[i];
    }
    
    public String toStringAssigments() {
        String ret = "";
        for (int i = 0; i < a.length; i++) {
            if (a[i]) {
                ret += (i+1) + " ";
            }
        }
        return ret;
    }
    
    public int numberOfAssigmentsDone() {
        int ret = 0;
        for (boolean b : a) {
            if (b) ret++;
        }
        return ret;
    }

    @Override
    public String toString() {
        return "viikko " + week + ": tehtyjä yhteensä: " + numberOfAssigmentsDone()
                + ", aikaa kului " + hours + " tuntia, tehdyt tehtävät: " + toStringAssigments();
    }

}
