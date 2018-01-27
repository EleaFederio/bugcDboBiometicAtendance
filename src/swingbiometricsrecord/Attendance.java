/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingbiometricsrecord;

/**
 *
 * @author Eleazar
 */
public class Attendance {
    private String firstName;
    private String lastname;
    private String course;
    private int year;
    private String block;
    private String time;
    private String date;

    public Attendance(String firstName, String lastname, String course, int year, String block, String time, String date) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.course = course;
        this.year = year;
        this.block = block;
        this.time = time;
        this.date = date;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
}
