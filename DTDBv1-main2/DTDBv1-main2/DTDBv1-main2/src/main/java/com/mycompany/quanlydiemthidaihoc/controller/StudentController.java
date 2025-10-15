/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydiemthidaihoc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mycompany.quanlydiemthidaihoc.action.ManagerStudents;
import com.mycompany.quanlydiemthidaihoc.entity.Student;
import com.mycompany.quanlydiemthidaihoc.view.ResidentView;

public class StudentController 
{
    private ResidentView residentView;
    private ManagerStudents managerStudents;

    public StudentController(ResidentView view)
    {
        this.residentView=view;
        this.managerStudents = new ManagerStudents();
        view.addUndoListener(new UndoListener());
        view.addAddResidentListener(new AddResidentListener());
        view.addListResidentSelectionListener(new ListResidentsSelectionListener());
        view.addEditResidentListener(new EditResidentListener());
        view.addClearListener(new ClearResidentListener());
        view.addDeleteSpecialPersonListener(new DeleteSpecialPersonListener());
        view.addSortSpecialPersonListener(new SortResidentsListener());
        view.addSearchListener(new SearchResidentViewListener());
        view.addSearchDialogListener(new SearchResidentListener());
        view.addCancelSearchResidentListener(new CancelSearchResidentListener());
        view.addCancelDialogListener(new CancelDialogSearchResidentListener());
//        view.addStatisticAgeListener(new StatisticAgeListener());
//        view.addStatisticGenderListener(new StatisticGenderListener());
        view.addStatisticGraduationListener(new StatisticGraduationListener());
        view.addStatisticListener(new StatisticListener());
        view.addStatisticUnderListener(new StatisticUnderListener());
        
    }
    
    public void showManagerView() 
    {
        List<Student> students = managerStudents.readAll();
        residentView.setVisible(true);
        residentView.showListStudent(students);
        int[] total = managerStudents.getTotal();
        residentView.showTotal(total[0], total[1], total[2]);
    }

    private class StatisticListener implements ActionListener {
        public void actionPerformed(ActionEvent e) 
        {
            residentView.displayStatisticView();
        }
    }
    class StatisticUnderListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {

            StudentController mainController = new StudentController(new ResidentView());
            mainController.showManagerView();

            residentView.setVisible(false);
        }
    }
    
    class UndoListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            System.exit(0);
        }
    }
    
     private boolean checkStudent(Student student) {
        List<Student> students = managerStudents.readAll();
        for (Student s : students) {
            if (s.getSbd().equals(student.getSbd())) {
                return false;
            }
        }
        return true;
    }

    class AddResidentListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            Student student = residentView.getStudentInfo();
            if (student != null) {
                try {
                    if (!checkStudent(student)) {
                        residentView.showMessage("SBD " + student.getSbd() + " đã tồn tại!");
                        return;
                    }
                    managerStudents.add(student);
                    residentView.clearStudentInfo();
                    residentView.showListStudent(managerStudents.readAll());
                    int[] total = managerStudents.getTotal();
                    residentView.showTotal(total[0], total[1], total[2]);
                    residentView.showMessage("Thêm thành công!");
                } catch (IllegalArgumentException ex) {
                    residentView.showMessage(ex.getMessage());
                }
            }
        }
    }
    
    
    class EditResidentListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            Student student = residentView.getStudentInfo();
            if (student != null) 
            managerStudents.update(student);
            residentView.clearStudentInfo();
            residentView.showListStudent(managerStudents.readAll());
            int[] total = managerStudents.getTotal();
            residentView.showTotal(total[0], total[1], total[2]);
            residentView.showMessage("Cập nhật thành công!");
        }
    }
    
    class DeleteSpecialPersonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            Student student = residentView.getStudentInfo();
            if (student != null) 
            {
                managerStudents.delete(student.getSbd());
                residentView.clearStudentInfo();
                residentView.showListStudent(managerStudents.readAll());
                int[] total = managerStudents.getTotal();
                residentView.showTotal(total[0], total[1], total[2]);
                residentView.showMessage("Xóa thành công!");
            }
        }
    }

   
    
    class ListResidentsSelectionListener implements ListSelectionListener 
    {
        public void valueChanged(ListSelectionEvent e) 
        {
            List<Student> students = managerStudents.readAll();
            try {
                residentView.fillStudentFromSelectedRow(students);
                
            } catch (ParseException ex) {
                // Logger.getLogger(ResidentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    class ClearResidentListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            residentView.clearStudentInfo();
        }
    }
    
    class SortResidentsListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            boolean kt = false;
            int check = residentView.getChooseSelectSort();
            if(check == 1){
                managerStudents.sortStdentbySBD();;
                residentView.showListStudent(managerStudents.readAll());
            }else if(check == 2){
                managerStudents.sortStudentsByName();
                residentView.showListStudent(managerStudents.readAll());
            }else if(check == 3){
                managerStudents.sortStudentsByScore();
                residentView.showListStudent(managerStudents.readAll());
            } else
                residentView.showMessage("Bạn chưa chọn tiêu chí sắp xếp");
        }
    }
    
    class SearchResidentViewListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            residentView.searchResidentInfo();
        }
    }
    class StatisticAgeListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            residentView.displayStatisticView();
            residentView.showStatisticAgeStudents(managerStudents.readAll());
        }
    }
    class StatisticGenderListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            residentView.displayStatisticView();
            residentView.showStatisticGenderStudents(managerStudents.readAll());
        }
    }
    
    class StatisticGraduationListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            residentView.displayStatisticView();
            residentView.showStatisticGraduationStudents(managerStudents.readAll());
        }
    }

    class CancelDialogSearchResidentListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            residentView.cancelDialogSearchResidentInfo();
        }
    }
    
    class CancelSearchResidentListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            residentView.showListStudent(managerStudents.readAll());
            // residentView.showListResidents(managerResidents.getListResidents());
            residentView.cancelSearchResident();
        }
    }
    private Double[] analysis(String input) {
    input = input.trim();

    if (input.startsWith(">")) {
        double value = Double.parseDouble(input.substring(1).trim());
        return new Double[] { value, 60.0 };
    } else if (input.startsWith("<")) {
        double value = Double.parseDouble(input.substring(1).trim());
        return new Double[] { 0.0, value };
    } else if (input.startsWith("=")) {
        double value = Double.parseDouble(input.substring(1).trim());
        return new Double[] { value, value };
    } else if (input.contains("-")) {
        String[] parts = input.split("-");
        if (parts.length == 2) {
            double min = Double.parseDouble(parts[0].trim());
            double max = Double.parseDouble(parts[1].trim());
            return new Double[] { min, max };
        }
    }
    return null;
    }
    
    class SearchResidentListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            boolean kt = false;
            List<Student> temp = new ArrayList<>();
            int check = residentView.getChooseSelectSearch();
            String search = residentView.validateSearch();
            if(check == 1){
                // Tìm kiếm theo sbd
                temp = managerStudents.searchStudentBySBD(search);
            }else if(check == 2){
                // Tìm kiếm theo tên
                temp = managerStudents.searchStudentByName(search);
            }else if(check == 3){
                // Tìm kiếm theo năm sinh
                temp = managerStudents.searchStudentByYear(search);
            }else if(check == 4){
                // Tìm kiếm theo điểm
                Double[] minMax = analysis(search);
                temp = managerStudents.searchStudentByScore(minMax[0], minMax[1]);
            }
            if(!temp.isEmpty()) residentView.showListStudent(temp);
            else residentView.showMessage("Không tìm thấy kết quả!");
        }
    }
}
