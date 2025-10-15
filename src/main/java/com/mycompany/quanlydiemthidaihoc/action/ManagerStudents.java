package com.mycompany.quanlydiemthidaihoc.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mycompany.quanlydiemthidaihoc.entity.Student;
import com.mycompany.quanlydiemthidaihoc.entity.StudentXML;
import com.mycompany.quanlydiemthidaihoc.entity.Subject;
import com.mycompany.quanlydiemthidaihoc.utils.FileUtils;

public class ManagerStudents {
    private static final String STUDENT_FILE = "students.xml";
    private List<Student> students;

    public ManagerStudents() {
        this.students = readAll();
        if (students == null) students = new ArrayList<>();
    }

    public List<Student> readAll() {
        if (students != null) return students;
        StudentXML data = (StudentXML) FileUtils.readXMLFile(STUDENT_FILE, StudentXML.class);
        return (data != null) ? data.getStudents() : new ArrayList<>();
    }

    public void writeAll() {
        StudentXML data = new StudentXML();
        data.setStudents(students);
        FileUtils.writeXMLtoFile(STUDENT_FILE, data);
    }

    public void add(Student s) {
        students.add(s);
        writeAll();
    }

    public void update(Student updated) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getSbd().equals(updated.getSbd())) {
                students.set(i, updated);
                break;
            }
        }
        writeAll();
        
    }

    public boolean delete(String sbd) {
        boolean removed = students.removeIf(s -> s.getSbd().equals(sbd));
        if (removed) writeAll();
        return removed;
    }
    public List<Student> searchStudentBySBD(String search) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getSbd().equals(search)) {
                result.add(student);
            }
        }
        return result;
    }
    public List<Student> searchStudentByName(String keyword) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(student);
            }
        }
        return result;
    }

    public List<Student> searchStudentByYear(String year) {
        List<Student> temp = new ArrayList<>();
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

        for (Student student : students) {
            // Chuyển đổi ngày sinh thành chuỗi năm
            String personYearStr = yearFormat.format(student.getDob());

            // So sánh chuỗi năm với năm tìm kiếm
            if (personYearStr.equals(year)) {
                temp.add(student);
            }
        }

        return temp;
    }
    public int[] getTotal(){
        int[] result = new int[3];
        result[0] = students.size();
        int pass = 0;
        for (Student student : students) {
            if (student.isPass()){
                pass++;
            }
        }
        result[1] = pass;
        result[2] = result[0] - pass;
        return result;
    }

    public List<Student> searchStudentByScore(double min, double max) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getTotalScore() >= min && s.getTotalScore() <= max) {
                result.add(s);
            }
        }
        return result;
    }

    public List<Student> getAll() {
        return students;
    }

    public void sortStudentsByName() {
        students.sort((s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));
    }
    public void sortStudentsByScore() {
        students.sort((s1, s2) -> Double.compare(s2.getTotalScore(), s1.getTotalScore()));
    }
    public void sortStdentbySBD() {
        students.sort((s1, s2) -> s1.getSbd().compareToIgnoreCase(s2.getSbd()));
    }
    public static void main(String[] args) {
        ManagerStudents manager = new ManagerStudents();
        
        List<Subject> subjects = Arrays.asList(
            new Subject("Math", 8.5),
            new Subject("English", 9.0),
            new Subject("History", 7.5)
        );
        
    }

    
}
