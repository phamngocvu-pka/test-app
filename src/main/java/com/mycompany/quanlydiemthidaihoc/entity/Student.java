package com.mycompany.quanlydiemthidaihoc.entity;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {
    private String sbd;
    private String name;
    private Date dob;
    private String gender;
    private String address;
    private String phone;

    @XmlElementWrapper(name = "subjects")
    @XmlElement(name = "subject")
    private List<Subject> subjects;

    private double totalScore;


    public Student() {
    }

    public Student( String sbd, String name, Date dob, String gender, String address, String phone, List<Subject> subjects) {
        this.sbd = sbd;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.subjects = subjects;
        this.totalScore = calculateTotalScore();
    }

    private double calculateTotalScore() {
        return subjects.stream().mapToDouble(Subject::getScore).sum();
    }

    public String getName() {
        return name;
    }

    public Date getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
    
    public String getSbd() {
        return sbd;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setSBD(String sbd) {
        this.sbd = sbd;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSbd(String sbd) {
        this.sbd = sbd;
    }
    
    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
        this.totalScore = calculateTotalScore();
    }
    public boolean isPass(){
        if (totalScore/6 <5.0) {
            return false;
        }
        for (Subject subject : subjects) {
            if (subject.getScore() < 1.0) {
                return false;
            }
        }
            
        return true;
    }
}
