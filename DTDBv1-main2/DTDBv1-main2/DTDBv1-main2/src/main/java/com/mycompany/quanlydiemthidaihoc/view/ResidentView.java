/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.quanlydiemthidaihoc.view;
import com.mycompany.quanlydiemthidaihoc.entity.Student;
import com.mycompany.quanlydiemthidaihoc.entity.Subject;
import com.raven.chart.Chart;
import com.raven.chart.ModelChart;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.border.Border;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author PC
 */
public class ResidentView extends javax.swing.JFrame {

    /**
     * Creates new form ResidentView
     */
    private String[] allSubjects = {
    "-- Chọn môn --","Toán", "Văn", "Anh", "Vật lý", "Hóa học", "Sinh học", "Lịch sử", "Địa lý", "GDCD"
    };

    private String [] columnNames2 = new String [] {
    };

    JComboBox<String>[] boxes;
    JTextField[] fields; 

    private String [] columnNames = new String [] {
        "SBD", "Họ và tên", "tổng điểm", "Ngày sinh", "Địa chỉ", "Số đt"};
    private SimpleDateFormat fDate=new SimpleDateFormat("dd/MM/yyyy");
    FlowLayout flowLayout = new FlowLayout();
    public ResidentView() {
        initComponents();
        boxes = new JComboBox[] {BoxSubject1, BoxSubject2, BoxSubject3, BoxSubject4, BoxSubject5, BoxSubject6};
        fields = new JTextField[] {FieldSubject1, FieldSubject2, FieldSubject3, FieldSubject4, FieldSubject5, FieldSubject6};
        for (JComboBox<String> box : boxes) {
            box.setModel(new DefaultComboBoxModel<>(allSubjects));
        }
        chart1.addLegend("Số lượng", new Color(0, 178, 238));
        setupComboBoxListeners();
        btnAdd.setEnabled(true);
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        btnSearch.setEnabled(true);
        tableResident.setDefaultRenderer(Object.class, new ResidentView.MyRenderer());
    }
    
    private static Image getCircleImage(Image image) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        BufferedImage circleImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = circleImage.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, width, height);
        graphics.setClip(circle);
        graphics.drawImage(image, 0, 0, null);
        graphics.setColor(Color.WHITE);
        graphics.setStroke(new BasicStroke(2));
        graphics.draw(circle);
        return circleImage;
    }
    
    private ImageIcon ImageIconSize(JLabel label, String filename)
    {
        Image image = new ImageIcon(filename).getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imageIcon=new ImageIcon(image);
        //jLabel14.setIcon(new ImageIcon(getCircleImage(imageIcon.getImage())));
        return imageIcon;
    }
    
    public class MyRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
            TableColumnModel columnModel=table.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(5);
            columnModel.getColumn(1).setPreferredWidth(150);
            columnModel.getColumn(2).setPreferredWidth(60);
            columnModel.getColumn(3).setPreferredWidth(50);
            columnModel.getColumn(4).setPreferredWidth(50);
            columnModel.getColumn(5).setPreferredWidth(60);
            // columnModel.getColumn(5).setPreferredWidth(50);
            //columnModel.getColumn(0).setPreferredWidth(5);
            JTableHeader header = table.getTableHeader();
            header.setBackground(new Color(0, 0, 139));
            header.setForeground(Color.WHITE);
            header.setFont(new java.awt.Font("Times New Roman", 0, 18));
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
            if (!isSelected) {
                if (row % 2 == 0) {
                    c.setBackground(new Color(191, 239, 255));
                } else {
                    c.setBackground(new Color(135, 206, 250));
                }
            } else {
                c.setBackground(new Color(193, 255, 193));
            }

            return c;
        }
    }
    
    public class MyRenderer2 extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
            TableColumnModel columnModel=table.getColumnModel();
            //columnModel.getColumn(0).setPreferredWidth(5);
            JTableHeader header = table.getTableHeader();
            header.setBackground(new Color(0, 0, 139));
            header.setForeground(Color.WHITE);
            header.setFont(new java.awt.Font("Times New Roman", 0, 18));
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
            if (!isSelected) {
                if (row % 2 == 0) {
                    c.setBackground(new Color(191, 239, 255));
                } else {
                    c.setBackground(new Color(135, 206, 250));
                }
            } else {
                c.setBackground(new Color(193, 255, 193));
            }

            return c;
        }
    }
    
    public static String capitalizeWords(String str) {
        str = str.toLowerCase();
        String[] words = str.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                if (word.equals("tt") || word.equals("tp") || word.equals("tx")) {
                    sb.append(word.toUpperCase());
                } else {
                    sb.append(Character.toUpperCase(word.charAt(0)));
                    sb.append(word.substring(1));
                }
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupSex = new javax.swing.ButtonGroup();
        btnGroupSort = new javax.swing.ButtonGroup();
        btnGroupSearch = new javax.swing.ButtonGroup();
        SearchDialog = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        CheckBoxName = new javax.swing.JCheckBox();
        jLabel20 = new javax.swing.JLabel();
        CheckBoxScore = new javax.swing.JCheckBox();
        CheckBoxYear = new javax.swing.JCheckBox();
        CheckBoxSBD = new javax.swing.JCheckBox();
        jLabel21 = new javax.swing.JLabel();
        FieldSearch = new javax.swing.JTextField();
        btnCancelDialog = new javax.swing.JButton();
        btnSearchDialog = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        StatisticView = new javax.swing.JFrame();
        panelChart = new javax.swing.JPanel();
        ScrollPaneStatistic = new javax.swing.JScrollPane();
        tableStatistic = new javax.swing.JTable();
        lblTable = new javax.swing.JLabel();
        chart1 = new com.raven.chart.Chart();
        lblChart = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnStatisticUnder = new javax.swing.JButton();
        btnStatisticType = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        btnCancelSearch = new javax.swing.JButton();
        btnSort = new javax.swing.JButton();
        btnResidentUndo = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        TotalStudentPass = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        TotalStudentFail = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        ToTalStudent = new javax.swing.JTextField();
        CheckBoxSortID = new javax.swing.JCheckBox();
        CheckBoxSortName = new javax.swing.JCheckBox();
        CheckBoxSortIDFamily = new javax.swing.JCheckBox();
        btnStatistic = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableResident = new javax.swing.JTable();
        FieldName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TextAreaAddress = new javax.swing.JTextArea();
        CheckBoxFemale = new javax.swing.JCheckBox();
        CheckBoxMale = new javax.swing.JCheckBox();
        BirthdayChooser = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        FieldPhone = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        FieldScore = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        BoxSubject1 = new javax.swing.JComboBox<>();
        BoxSubject2 = new javax.swing.JComboBox<>();
        FieldSubject1 = new javax.swing.JTextField();
        BoxSubject3 = new javax.swing.JComboBox<>();
        FieldSubject3 = new javax.swing.JTextField();
        BoxSubject4 = new javax.swing.JComboBox<>();
        FieldSubject4 = new javax.swing.JTextField();
        BoxSubject5 = new javax.swing.JComboBox<>();
        FieldSubject5 = new javax.swing.JTextField();
        BoxSubject6 = new javax.swing.JComboBox<>();
        FieldSubject6 = new javax.swing.JTextField();
        FieldSBD = new javax.swing.JTextField();
        FieldSubject2 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        SearchDialog.setResizable(false);
        SearchDialog.setSize(new java.awt.Dimension(511, 390));

        jPanel3.setLayout(null);

        jLabel18.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel18.setText("Tìm kiếm");
        jPanel3.add(jLabel18);
        jLabel18.setBounds(210, 40, 110, 29);

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel19.setText("Chọn tiêu chí tìm kiếm:");
        jPanel3.add(jLabel19);
        jLabel19.setBounds(40, 190, 290, 29);

        btnGroupSearch.add(CheckBoxName);
        CheckBoxName.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        CheckBoxName.setText("Tên");
        CheckBoxName.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CheckBoxName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckBoxNameActionPerformed(evt);
            }
        });
        jPanel3.add(CheckBoxName);
        CheckBoxName.setBounds(170, 230, 85, 20);
        CheckBoxName.setOpaque(false);

        jLabel20.setIcon(new ImageIcon("src/main/java/com/mycompany/quanlydoituongdacbiet/view/search.png"));
        jPanel3.add(jLabel20);
        jLabel20.setBounds(30, 130, 30, 30);

        btnGroupSearch.add(CheckBoxScore);
        CheckBoxScore.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        CheckBoxScore.setText("Điểm");
        CheckBoxScore.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CheckBoxScore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckBoxScoreActionPerformed(evt);
            }
        });
        jPanel3.add(CheckBoxScore);
        CheckBoxScore.setBounds(390, 230, 100, 20);
        CheckBoxScore.setOpaque(false);

        btnGroupSearch.add(CheckBoxYear);
        CheckBoxYear.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        CheckBoxYear.setText("Năm sinh");
        CheckBoxYear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CheckBoxYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckBoxYearActionPerformed(evt);
            }
        });
        jPanel3.add(CheckBoxYear);
        CheckBoxYear.setBounds(260, 230, 100, 20);
        CheckBoxYear.setOpaque(false);

        btnGroupSearch.add(CheckBoxSBD);
        CheckBoxSBD.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        CheckBoxSBD.setText("SBD");
        CheckBoxSBD.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel3.add(CheckBoxSBD);
        CheckBoxSBD.setBounds(20, 230, 120, 25);
        CheckBoxSBD.setOpaque(false);

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel21.setText("Nhập nội dung tìm kiếm:");
        jPanel3.add(jLabel21);
        jLabel21.setBounds(40, 90, 290, 29);

        FieldSearch.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        FieldSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 102)));
        jPanel3.add(FieldSearch);
        FieldSearch.setBounds(60, 130, 400, 40);
        FieldSearch.setOpaque(false);

        btnCancelDialog.setBackground(new java.awt.Color(255, 255, 255, 0));
        btnCancelDialog.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnCancelDialog.setText("Hủy");
        btnCancelDialog.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel3.add(btnCancelDialog);
        btnCancelDialog.setBounds(290, 270, 150, 50);
        btnCancelDialog.setBorder(new RoundedBorder(20));
        btnCancelDialog.setOpaque(false);

        btnSearchDialog.setBackground(new java.awt.Color(255, 255, 255, 0));
        btnSearchDialog.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnSearchDialog.setText("Tìm kiếm");
        btnSearchDialog.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel3.add(btnSearchDialog);
        btnSearchDialog.setBounds(70, 270, 140, 50);
        btnSearchDialog.setBorder(new RoundedBorder(20));
        btnSearchDialog.setOpaque(false);

        jLabel22.setIcon(new ImageIcon("src/main/java/com/mycompany/quanlydoituongdacbiet/view/viewSearchView.png"));
        jLabel22.setText("=");
        jPanel3.add(jLabel22);
        jLabel22.setBounds(-10, 0, 520, 360);

        javax.swing.GroupLayout SearchDialogLayout = new javax.swing.GroupLayout(SearchDialog.getContentPane());
        SearchDialog.getContentPane().setLayout(SearchDialogLayout);
        SearchDialogLayout.setHorizontalGroup(
            SearchDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
        );
        SearchDialogLayout.setVerticalGroup(
            SearchDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
        );

        StatisticView.setTitle("Thống kê");
        StatisticView.setResizable(false);
        StatisticView.setSize(new java.awt.Dimension(1250, 600));
        StatisticView.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                StatisticViewWindowOpened(evt);
            }
        });

        panelChart.setBackground(new java.awt.Color(102, 204, 255));

        tableStatistic.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tableStatistic.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            columnNames2
        ));
        tableStatistic.setRowHeight(30);
        ScrollPaneStatistic.setViewportView(tableStatistic);

        lblTable.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblTable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTable.setText("Thống kê số lượng theo mục");

        lblChart.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblChart.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblChart.setText("Biểu đồ");

        jPanel4.setBackground(new java.awt.Color(0, 102, 255));

        btnStatisticUnder.setBackground(new java.awt.Color(0, 0, 102));
        btnStatisticUnder.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnStatisticUnder.setForeground(new java.awt.Color(255, 255, 255));
        btnStatisticUnder.setIcon(new ImageIcon("src/main/java/com/mycompany/quanlydoituongdacbiet/view/Undo.png"));
        btnStatisticUnder.setText("Quay lại");
        btnStatisticUnder.setBorder(null);
        btnStatisticUnder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnStatisticUnder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatisticUnderActionPerformed(evt);
            }
        });

        btnStatisticType.setBackground(new java.awt.Color(0, 0, 102));
        btnStatisticType.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnStatisticType.setForeground(new java.awt.Color(255, 255, 255));
        btnStatisticType.setIcon(new ImageIcon("src/main/java/com/mycompany/quanlydoituongdacbiet/view/People.png"));
        btnStatisticType.setText("Tốt nghiệp");
        btnStatisticType.setBorder(null);
        btnStatisticType.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnStatisticType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatisticTypeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnStatisticUnder, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnStatisticType, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(316, 316, 316)
                .addComponent(btnStatisticType, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addComponent(btnStatisticUnder, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );

        javax.swing.GroupLayout panelChartLayout = new javax.swing.GroupLayout(panelChart);
        panelChart.setLayout(panelChartLayout);
        panelChartLayout.setHorizontalGroup(
            panelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChartLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(panelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTable, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                    .addComponent(ScrollPaneStatistic, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chart1, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                    .addComponent(lblChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 20, Short.MAX_VALUE))
        );
        panelChartLayout.setVerticalGroup(
            panelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChartLayout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addGroup(panelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTable, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblChart, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ScrollPaneStatistic, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chart1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout StatisticViewLayout = new javax.swing.GroupLayout(StatisticView.getContentPane());
        StatisticView.getContentPane().setLayout(StatisticViewLayout);
        StatisticViewLayout.setHorizontalGroup(
            StatisticViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelChart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        StatisticViewLayout.setVerticalGroup(
            StatisticViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản Lý Điểm Thi Đại Học");
        setName("Quản lý dân cư trong khu vực"); // NOI18N
        setSize(new java.awt.Dimension(1207, 665));

        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel16.setText("Chọn tiêu chí sắp xếp:");

        btnCancelSearch.setBackground(new java.awt.Color(0, 0, 102));
        btnCancelSearch.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnCancelSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelSearch.setIcon(new ImageIcon("src/main/java/com/mycompany/quanlydoituongdacbiet/view/cancel.png"));
        btnCancelSearch.setText("Hủy tìm kiếm");
        btnCancelSearch.setToolTipText("");
        btnCancelSearch.setBorder(null);
        btnCancelSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelSearchActionPerformed(evt);
            }
        });
        btnCancelSearch.setEnabled(false);

        btnSort.setBackground(new java.awt.Color(0, 0, 102));
        btnSort.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnSort.setForeground(new java.awt.Color(255, 255, 255));
        ImageIcon imageIcon2 = new ImageIcon("src/main/java/com/mycompany/quanlydoituongdacbiet/view/sorting.png");
        Image image2 = imageIcon2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        btnSort.setIcon(new ImageIcon(image2));
        btnSort.setText("Sắp xếp");
        btnSort.setToolTipText("");
        btnSort.setBorder(null);
        btnSort.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortActionPerformed(evt);
            }
        });

        btnResidentUndo.setBackground(new java.awt.Color(0, 0, 102));
        btnResidentUndo.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnResidentUndo.setForeground(new java.awt.Color(255, 255, 255));
        btnResidentUndo.setIcon(new ImageIcon("src/main/java/com/mycompany/quanlydoituongdacbiet/view/LogOut.png"));
        btnResidentUndo.setText("Thoát");
        btnResidentUndo.setToolTipText("");
        btnResidentUndo.setBorder(null);
        btnResidentUndo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnResidentUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResidentUndoActionPerformed(evt);
            }
        });

        btnSearch.setBackground(new java.awt.Color(0, 0, 102));
        btnSearch.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setIcon(new ImageIcon("src/main/java/com/mycompany/quanlydoituongdacbiet/view/search.png"));
        btnSearch.setText("Tìm kiếm");
        btnSearch.setBorder(null);
        btnSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSearch.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        TotalStudentPass.setEditable(false);
        TotalStudentPass.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        TotalStudentPass.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        TotalStudentPass.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 51, 102)));
        TotalStudentPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalStudentPassActionPerformed(evt);
            }
        });

        ImageIcon imageIcon = new ImageIcon("src/main/java/com/mycompany/quanlydoituongdacbiet/view/logo.png");
        Image image = imageIcon.getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH);
        imageIcon=new ImageIcon(image);
        jLabel14.setOpaque(false);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel3.setText("Số học sinh đậu");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel12.setText("Số học sinh trượt");

        TotalStudentFail.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        TotalStudentFail.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        TotalStudentFail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 51, 102)));
        TotalStudentFail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalStudentFailActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel17.setText("Tổng học sinh");

        ToTalStudent.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        ToTalStudent.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 51, 102)));
        ToTalStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ToTalStudentActionPerformed(evt);
            }
        });

        CheckBoxSortID.setBackground(new java.awt.Color(0, 102, 204, 175));
        btnGroupSort.add(CheckBoxSortID);
        CheckBoxSortID.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        CheckBoxSortID.setText("Sắp xếp theo STT");
        CheckBoxSortID.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CheckBoxSortID.setOpaque(false);

        btnGroupSort.add(CheckBoxSortName);
        CheckBoxSortName.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        CheckBoxSortName.setText("Sắp xếp theo tên");
        CheckBoxSortName.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CheckBoxSortName.setOpaque(false);

        btnGroupSort.add(CheckBoxSortIDFamily);
        CheckBoxSortIDFamily.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        CheckBoxSortIDFamily.setText("Sắp xếp theo điểm số");
        CheckBoxSortIDFamily.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CheckBoxSortIDFamily.setOpaque(false);

        btnStatistic.setBackground(new java.awt.Color(0, 0, 102));
        btnStatistic.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnStatistic.setForeground(new java.awt.Color(255, 255, 255));
        btnStatistic.setIcon(new ImageIcon("src/main/java/com/mycompany/quanlydoituongdacbiet/view/LogOut.png"));
        btnStatistic.setText("Thống kê");
        btnStatistic.setToolTipText("");
        btnStatistic.setBorder(null);
        btnStatistic.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnStatistic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatisticActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel17)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(TotalStudentFail, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(52, 52, 52)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(CheckBoxSortID, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(CheckBoxSortName, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(CheckBoxSortIDFamily, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnSort, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnResidentUndo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCancelSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(btnStatistic, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ToTalStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(TotalStudentPass, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(CheckBoxSortID)
                .addGap(3, 3, 3)
                .addComponent(CheckBoxSortName)
                .addGap(6, 6, 6)
                .addComponent(CheckBoxSortIDFamily)
                .addGap(6, 6, 6)
                .addComponent(btnSort, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancelSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(ToTalStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TotalStudentPass, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TotalStudentFail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(btnStatistic, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnResidentUndo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        TotalStudentPass.setOpaque(false);
        TotalStudentPass.setEditable(false);
        TotalStudentFail.setOpaque(false);
        TotalStudentFail.setEditable(false);
        ToTalStudent.setOpaque(false);
        ToTalStudent.setEditable(false);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 230, 780);
        jPanel2.setOpaque(true);

        jScrollPane1.setBackground(new java.awt.Color(0, 51, 153, 125));

        tableResident.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tableResident.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            }, columnNames
        ));
        tableResident.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        tableResident.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableResident.setRowHeight(30);
        jScrollPane1.setViewportView(tableResident);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(240, 420, 1030, 260);

        FieldName.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        FieldName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 51, 102)));
        FieldName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FieldNameActionPerformed(evt);
            }
        });
        jPanel1.add(FieldName);
        FieldName.setBounds(390, 100, 250, 40);
        FieldName.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Địa chỉ:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(670, 300, 70, 40);

        TextAreaAddress.setBackground(new java.awt.Color(255, 255, 255, 0));
        TextAreaAddress.setColumns(20);
        TextAreaAddress.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        TextAreaAddress.setRows(5);
        TextAreaAddress.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 51, 153)));
        jScrollPane2.setViewportView(TextAreaAddress);
        TextAreaAddress.setOpaque(false);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(780, 290, 260, 60);
        jScrollPane2.setOpaque(false);

        btnGroupSex.add(CheckBoxFemale);
        CheckBoxFemale.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        CheckBoxFemale.setText("Nữ");
        CheckBoxFemale.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CheckBoxFemale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckBoxFemaleActionPerformed(evt);
            }
        });
        jPanel1.add(CheckBoxFemale);
        CheckBoxFemale.setBounds(490, 170, 85, 20);
        CheckBoxFemale.setOpaque(false);

        btnGroupSex.add(CheckBoxMale);
        CheckBoxMale.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        CheckBoxMale.setText("Nam");
        CheckBoxMale.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CheckBoxMale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckBoxMaleActionPerformed(evt);
            }
        });
        jPanel1.add(CheckBoxMale);
        CheckBoxMale.setBounds(390, 170, 85, 20);
        CheckBoxMale.setOpaque(false);

        BirthdayChooser.setBackground(new java.awt.Color(0, 204, 255));
        BirthdayChooser.setForeground(new java.awt.Color(102, 255, 255));
        BirthdayChooser.setDateFormatString("dd/MM/yyyy");
        BirthdayChooser.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jPanel1.add(BirthdayChooser);
        BirthdayChooser.setBounds(390, 220, 160, 40);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("Giới tính:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(270, 160, 80, 40);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Ngày sinh");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(270, 220, 90, 40);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setText("Tổng điểm");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(790, 230, 90, 40);

        FieldPhone.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        FieldPhone.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 51, 102)));
        FieldPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FieldPhoneActionPerformed(evt);
            }
        });
        jPanel1.add(FieldPhone);
        FieldPhone.setBounds(390, 300, 200, 40);
        FieldPhone.setOpaque(false);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("Số điện thoại:");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(260, 300, 120, 40);

        FieldScore.setEditable(false);
        FieldScore.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        FieldScore.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FieldScore.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 51, 102)));
        FieldScore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FieldScoreActionPerformed(evt);
            }
        });
        jPanel1.add(FieldScore);
        FieldScore.setBounds(900, 230, 70, 40);
        FieldScore.setOpaque(false);
        FieldScore.setVisible(false);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("<html>Quản lý điểm thi đại học<br> ");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(580, 10, 390, 80);

        btnAdd.setBackground(new java.awt.Color(0, 0, 102));
        btnAdd.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setIcon(new ImageIcon("src/main/java/com/mycompany/quanlydoituongdacbiet/view/add.png"));
        btnAdd.setText("Thêm");
        btnAdd.setBorder(null);
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel1.add(btnAdd);
        btnAdd.setBounds(330, 370, 170, 44);

        btnEdit.setBackground(new java.awt.Color(0, 0, 102));
        btnEdit.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setIcon(new ImageIcon("src/main/java/com/mycompany/quanlydoituongdacbiet/view/Edit.png"));
        btnEdit.setText("Cập nhật");
        btnEdit.setBorder(null);
        btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jPanel1.add(btnEdit);
        btnEdit.setBounds(580, 370, 170, 44);

        btnDelete.setBackground(new java.awt.Color(0, 0, 102));
        btnDelete.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setIcon(new ImageIcon("src/main/java/com/mycompany/quanlydoituongdacbiet/view/delete.png"));
        btnDelete.setText("Xóa");
        btnDelete.setBorder(null);
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(btnDelete);
        btnDelete.setBounds(830, 370, 170, 44);

        btnClear.setBackground(new java.awt.Color(0, 0, 102));
        btnClear.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setIcon(new ImageIcon("src/main/java/com/mycompany/quanlydoituongdacbiet/view/trash.png"));
        btnClear.setText("Làm mới");
        btnClear.setToolTipText("");
        btnClear.setBorder(null);
        btnClear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        jPanel1.add(btnClear);
        btnClear.setBounds(1070, 370, 170, 44);

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setText("SBD");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(280, 40, 100, 40);

        BoxSubject1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(BoxSubject1);
        BoxSubject1.setBounds(720, 100, 120, 26);

        BoxSubject2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(BoxSubject2);
        BoxSubject2.setBounds(720, 140, 120, 26);
        jPanel1.add(FieldSubject1);
        FieldSubject1.setBounds(850, 100, 72, 26);

        BoxSubject3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(BoxSubject3);
        BoxSubject3.setBounds(720, 180, 120, 26);
        jPanel1.add(FieldSubject3);
        FieldSubject3.setBounds(850, 180, 72, 26);

        BoxSubject4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(BoxSubject4);
        BoxSubject4.setBounds(950, 100, 120, 26);
        jPanel1.add(FieldSubject4);
        FieldSubject4.setBounds(1080, 100, 72, 26);

        BoxSubject5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(BoxSubject5);
        BoxSubject5.setBounds(950, 140, 120, 26);
        jPanel1.add(FieldSubject5);
        FieldSubject5.setBounds(1080, 140, 72, 26);

        BoxSubject6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(BoxSubject6);
        BoxSubject6.setBounds(950, 180, 120, 26);
        jPanel1.add(FieldSubject6);
        FieldSubject6.setBounds(1080, 180, 72, 26);

        FieldScore.setEditable(false);
        FieldSBD.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        FieldSBD.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FieldSBD.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 51, 102)));
        FieldSBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FieldSBDActionPerformed(evt);
            }
        });
        jPanel1.add(FieldSBD);
        FieldSBD.setBounds(390, 40, 100, 40);
        FieldScore.setOpaque(false);
        FieldScore.setVisible(false);
        jPanel1.add(FieldSubject2);
        FieldSubject2.setBounds(850, 140, 72, 26);

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel23.setText("Họ và tên:");
        jPanel1.add(jLabel23);
        jLabel23.setBounds(270, 100, 90, 40);

        jLabel9.setIcon(new ImageIcon("src/main/java/com/mycompany/quanlydoituongdacbiet/view/Lovepik_com-500330964-blue-blazed-background.jpg"));
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel9);
        jLabel9.setBounds(-30, 0, 1640, 890);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1276, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSortActionPerformed

    private void btnResidentUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResidentUndoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnResidentUndoActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditActionPerformed

    private void FieldNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FieldNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldNameActionPerformed

    private void CheckBoxMaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckBoxMaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CheckBoxMaleActionPerformed

    private void CheckBoxFemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckBoxFemaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CheckBoxFemaleActionPerformed

    private void FieldPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FieldPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldPhoneActionPerformed

    private void ToTalStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ToTalStudentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ToTalStudentActionPerformed

    private void FieldScoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FieldScoreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldScoreActionPerformed

    private void btnCancelSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelSearchActionPerformed

    private void TotalStudentFailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalStudentFailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalStudentFailActionPerformed

    private void TotalStudentPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalStudentPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalStudentPassActionPerformed

    private void CheckBoxNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckBoxNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CheckBoxNameActionPerformed

    private void CheckBoxYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckBoxYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CheckBoxYearActionPerformed

    private void CheckBoxScoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckBoxScoreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CheckBoxScoreActionPerformed

    private void FieldSBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FieldSBDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldSBDActionPerformed

    private void btnStatisticUnderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatisticUnderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnStatisticUnderActionPerformed

    private void btnStatisticTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatisticTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnStatisticTypeActionPerformed

    private void StatisticViewWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_StatisticViewWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_StatisticViewWindowOpened

    private void btnStatisticActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatisticActionPerformed
        
            //FrameSearch = new ManagerView();
            StatisticView.setVisible(true);
            this.setVisible(false);
            StatisticView.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    StatisticView.dispose();
                    System.exit(0); // Optional: terminate the entire application
                }
            });
        
    }//GEN-LAST:event_btnStatisticActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ResidentView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ResidentView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ResidentView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ResidentView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ResidentView().setVisible(true);
            }
        });
    }
    public void showStatisticAgeStudents(List<Student> list) {
        java.util.Date referenceDate=new java.util.Date();
        //tableStatistic=new JTable();
        lblTable.setText("Thống kê số lượng theo tuổi");
        lblChart.setText("Biểu đồ thống kê số lượng theo tuổi");
        chart1.clear();
        int size1 = 18;
        if (tableStatistic.getRowCount()>10){
            size1 = size1 - (tableStatistic.getRowCount()-10);
        }
        chart1.setFont(new java.awt.Font("sansserif", 0, size1)); 
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int size = 5;
        columnNames2 = new String[]{"Tuổi", "Số lượng"};
        Map<Integer, Integer> countMap = new HashMap<>();
        for (Student student : list) {
            int age = calculateAge(student.getDob(), referenceDate);
            if (countMap.containsKey(age)) {
                int count = countMap.get(age);
                countMap.put(age, count + 1);
            } else {
                countMap.put(age, 1);
            }
        }
        Object [][] statistic = new Object[countMap.size()][2];
        //Object[][] data = new Object[countMap.size()][2];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            chart1.addData(new ModelChart(entry.getKey().toString(), new double[]{ConvertToDouble(entry.getValue())}));
            statistic[index][0] = entry.getKey();
            statistic[index][1] = entry.getValue();
            index++;
        }
        tableStatistic.setModel(new DefaultTableModel(statistic, columnNames2));
        chart1.start();
    }
    private int calculateAge(Date birthdate, Date referenceDate) {
    if ((birthdate != null) && (referenceDate != null)) {
        Calendar birthCalendar = Calendar.getInstance();
        Calendar referenceCalendar = Calendar.getInstance();
        birthCalendar.setTime(birthdate);
        referenceCalendar.setTime(referenceDate);

        int birthYear = birthCalendar.get(Calendar.YEAR);
        int referenceYear = referenceCalendar.get(Calendar.YEAR);

        return referenceYear - birthYear;
    } else {
        return 0; // Hoặc giá trị mặc định khác tùy vào yêu cầu của bạn
    }
    }
    private double ConvertToDouble(Object k)
    {
        return Double.valueOf(k.toString());
    }
    public void showStatisticGenderStudents(List<Student> students) {
        lblTable.setText("Thống kê số lượng theo giới tính");
        lblChart.setText("Biểu đồ thống kê số lượng theo giới tính");
        chart1.clear();
    
        int size1 = 18;
        if (tableStatistic.getRowCount() > 10) {
            size1 = size1 - (tableStatistic.getRowCount() - 10);
        }
        chart1.setFont(new java.awt.Font("sansserif", 0, size1));
    
        columnNames2 = new String[]{"Giới tính", "Số lượng"};
        Map<String, Integer> genderCountMap = new HashMap<>();
    
        for (Student student : students) {
            String gender = student.getGender(); // giả sử trả về "Nam", "Nữ", hoặc "Khác"
            if (gender != null && !gender.isEmpty()) {
                genderCountMap.put(gender, genderCountMap.getOrDefault(gender, 0) + 1);
            }
        }
    
        Object[][] statistic = new Object[genderCountMap.size()][2];
        int index = 0;
        for (Map.Entry<String, Integer> entry : genderCountMap.entrySet()) {
            String gender = entry.getKey();
            int count = entry.getValue();
    
            chart1.addData(new ModelChart(gender, new double[]{Double.valueOf(count)}));
            statistic[index][0] = gender;
            statistic[index][1] = count;
            index++;
        }
    
        tableStatistic.setModel(new DefaultTableModel(statistic, columnNames2));
        chart1.repaint();  // đảm bảo biểu đồ được cập nhật
        chart1.start();
    }
    
    public void showStatisticGraduationStudents(List<Student> students) {
        lblTable.setText("Thống kê số lượng tốt nghiệp");
        lblChart.setText("Biểu đồ thống kê số lượng tốt nghiệp");
        chart1.clear();
    
        int size1 = 18;
        if (tableStatistic.getRowCount() > 10) {
            size1 = size1 - (tableStatistic.getRowCount() - 10);
        }
        chart1.setFont(new java.awt.Font("sansserif", 0, size1));
    
        columnNames2 = new String[]{"Trạng thái", "Số lượng"};
        int passCount = 0;
        int failCount = 0;
    
        for (Student student : students) {
            if (student.isPass()) {
                passCount++;
            } else {
                failCount++;
            }
        }
    
        Object[][] statistic = {
            {"Đã tốt nghiệp", passCount},
            {"Chưa tốt nghiệp", failCount}
        };
    
        chart1.addData(new ModelChart("Đã tốt nghiệp", new double[]{Double.valueOf(passCount)}));
        chart1.addData(new ModelChart("Chưa tốt nghiệp", new double[]{Double.valueOf(failCount)}));
    
        tableStatistic.setModel(new DefaultTableModel(statistic, columnNames2));
        chart1.repaint();
        chart1.start();
    }
    
    
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    public void displayStatisticView() {
        //FrameSearch = new ManagerView();
        StatisticView.setVisible(true);
        ResidentView.this.setVisible(false);
        StatisticView.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                StatisticView.dispose();
                System.exit(0); // Optional: terminate the entire application
            }
        });
    }
    
    public Student getStudentInfo() {
        // validate residents
        if (!validateSBD() || !validateName() || !validateSex() || !validateBirthday() || !validateAddress() || !validatePhone() || !validateSubject()) {
            return null;
        }
        try {
            List<Subject> subjects = new ArrayList<>();
            for (int i=0; i<6; i++) {
                String subject = (String) boxes[i].getSelectedItem();
                Double score = Double.parseDouble(fields[i].getText().trim());
                subjects.add(new Subject(subject, score));
            }
            Student student = new Student();
            student.setSBD(FieldSBD.getText().trim());
            student.setName(capitalizeWords(FieldName.getText().trim()));
            student.setPhone(FieldPhone.getText().trim());
            student.setGender(CheckBoxMale.isSelected() ? "Nam" : "Nữ");
            student.setAddress(capitalizeWords(TextAreaAddress.getText().trim()));
            student.setDob(BirthdayChooser.getDate());
            student.setSubjects(subjects);
            return student;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }
    
    public class RoundedBorder implements Border {
        private int radius;
        RoundedBorder(int radius) {
            this.radius = radius;
        }
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }
        public boolean isBorderOpaque() {
            return true;
        }
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }
    
    private boolean validateSBD() {
        try {
            String sbdText = FieldSBD.getText().trim();
            if (sbdText.isEmpty() || !sbdText.matches("\\d+")) {
                showMessage("SBD không hợp lệ");
                return false;
            }
        } catch (Exception e) {
            FieldSBD.requestFocus();
            showMessage("SBD không được trống");
            return false;
        }
        return true;
    }
    
    private boolean validatePhone() {
        try {
            String phoneText = FieldPhone.getText().trim();
            if (phoneText.isEmpty() || !phoneText.matches("\\d+")) {
                showMessage("Số điện thoại không hợp lệ");
                return false;
            }
        } catch (Exception e) {
            FieldPhone.requestFocus();
            showMessage("Số điện thoại không được trống");
            return false;
        }
        return true;
    }
    
    private boolean validateSex() {
        if (!CheckBoxMale.isSelected() && !CheckBoxFemale.isSelected()) {
            showMessage("Bạn chưa chọn giới tính");
            return false;
        }
        return true;
    }
      
    private boolean validateName() {
        String name = FieldName.getText();
        if (name == null || "".equals(name.trim())) {
            FieldName.requestFocus();
            showMessage("Họ và tên không được trống.");
            return false;
        }
        return true;
    }
    
    public boolean validateSubject() {
    Set<String> selectedSubjects = new HashSet<>();

    for (int i = 0; i < boxes.length; i++) {
        String subject = (String) boxes[i].getSelectedItem();
        String scoreText = fields[i].getText().trim();

        // 1. Kiểm tra đã chọn môn
        if (subject == null || subject.equals("-- Chọn môn --")) {
            showMessage("chưa chọn môn");
            return false;
        }

        // 3. Kiểm tra điểm là số hợp lệ
        try {
            double score = Double.parseDouble(scoreText);
            if (score < 0 || score > 10) {
                showMessage("Điểm không hợp lệ ở dòng");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Điểm không hợp lệ ở dòng " + (i + 1));
            return false;
        }
    }

    return true; 
}

    
    private boolean validateAddress() {
        String address = TextAreaAddress.getText();
        if (address == null || "".equals(address.trim())) {
            TextAreaAddress.requestFocus();
            showMessage("Địa chỉ không được trống.");
            return false;
        }
        return true;
    }
    
    private boolean validateBirthday() {
        try {
            java.util.Date today=new java.util.Date();
            Date date=BirthdayChooser.getDate();
            if (date.compareTo(today)==1) {
                BirthdayChooser.requestFocus();
                showMessage("Ngày nhập không tồn tại hoặc lớn hơn ngày hôm nay");
                return false;
            }
        } catch (Exception e) {
            BirthdayChooser.requestFocus();
            showMessage("Bạn đã nhập ngày sai định dạng");
            return false;
        }
        return true;
    }
    
    public void showListStudent(List<Student> list) {
        int size = list.size();
        // với bảng tableStudent có 6 cột, 
        // khởi tạo mảng 2 chiều students, trong đó:
        // số hàng: là kích thước của list student
        // số cột: là 7
        Object [][] students = new Object[size][7];
        for (int i = 0; i < size; i++) {
            students[i][0] = list.get(i).getSbd();
            students[i][1] = list.get(i).getName();
            students[i][2] = list.get(i).getTotalScore();
            students[i][3] = fDate.format(list.get(i).getDob());
            students[i][4] = list.get(i).getAddress();
            students[i][5] = list.get(i).getPhone();
        }
        //jLabel1.setLayout(null);
        tableResident.getColumnModel().getColumn(0).setWidth(3);
        tableResident.setModel(new DefaultTableModel(students, columnNames));
        //tableResident.removeColumn(tableResident.getColumnModel().getColumn(6));
    }

    public void fillStudentFromSelectedRow(List<Student> students) throws ParseException {
        // lấy chỉ số của hàng được chọn
        int row = tableResident.getSelectedRow();
        if (row >= 0) {
            String sbd = tableResident.getModel().getValueAt(row, 0).toString();
            Student student = findStudentBySBD(students, sbd);
            if (student != null) {
                FieldSBD.setText(student.getSbd());
                FieldName.setText(student.getName());
                if ("Nam".equals(student.getGender())) {
                   CheckBoxMale.setSelected(true);
                   CheckBoxFemale.setSelected(false);
                } else if ("Nữ".equals(student.getGender())) {
                   CheckBoxMale.setSelected(false);
                   CheckBoxFemale.setSelected(true);
                } else {
                   CheckBoxMale.setSelected(false);
                   CheckBoxFemale.setSelected(false);
                }
                BirthdayChooser.setDate(student.getDob());
                FieldPhone.setText(student.getPhone());
                TextAreaAddress.setText(student.getAddress());
                List<Subject> subjects = student.getSubjects();
                for(int i=0; i<6; i++){
                    fields[i].setText(String.valueOf(subjects.get(i).getScore()));
                    boxes[i].setSelectedItem(subjects.get(i).getName());
                }
                FieldScore.setText(String.valueOf(student.getTotalScore()));
//                // enable Edit and Delete buttons
                FieldSBD.setEditable(false);
                btnEdit.setEnabled(true);
                btnDelete.setEnabled(true);
                FieldSBD.setFocusable(false);
                FieldName.requestFocus();
//                // disable Add button
//                btnAdd.setEnabled(false);
//                btnClear.setEnabled(true);
            }
        }
    }

    private Student findStudentBySBD(List<Student> students, String sbd) {
        for (Student student : students) {
            if (student.getSbd().equals(sbd)) {
                return student;
            }
        }
        return null; // Trả về null nếu không tìm thấy đối tượng
    }
    
    public void clearStudentInfo() {
        FieldSBD.setText("");
        FieldName.setText("");
        CheckBoxMale.setSelected(false);
        CheckBoxFemale.setSelected(false);
        BirthdayChooser.setDate(null);
        TextAreaAddress.setText("");
        FieldPhone.setText("");
        for(int i=0; i<6; i++){
            fields[i].setText("");
            boxes[i].setSelectedItem("-- Chọn môn --");
        }
        FieldScore.setText("");
        FieldSBD.setFocusable(true);
        FieldSBD.setEditable(true);
        FieldSBD.requestFocus();
        // enable Add button
        btnAdd.setEnabled(true);
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        btnClear.setEnabled(true);
    }
    
    public void SearchResidentInfo() {
        //FrameSearch = new ManagerView();
        SearchDialog.setVisible(true);
    }
    
    public int getChooseSelectSort(){
        if(CheckBoxSortID.isSelected()) return 1;
        else if(CheckBoxSortName.isSelected()) return 2;
        else if(CheckBoxSortIDFamily.isSelected()) return 3;
        return 0;
    }
    
    public void showTotal(int total, int pass, int fail) {
        ToTalStudent.setText(String.valueOf(total));
        TotalStudentPass.setText(String.valueOf(pass));
        TotalStudentFail.setText(String.valueOf(fail));
    }
    
  
    public void searchResidentInfo() {
        //FrameSearch = new ManagerView();
        SearchDialog.setVisible(true);
    }
    
    public void cancelDialogSearchResidentInfo() {
        //FrameSearch = new ManagerView();
        SearchDialog.setVisible(false);
    }
    
    public int getChooseSelectSearch(){
        if(CheckBoxSBD.isSelected()) return 1;
        else if(CheckBoxName.isSelected()) return 2;
        else if(CheckBoxYear.isSelected()) return 3;
        else if(CheckBoxScore.isSelected()) return 4;
        return 0;
    }
    
    public void cancelSearchResident()
    {
        String id=FieldScore.getText();
        btnCancelSearch.setEnabled(false);
        btnSearch.setEnabled(true);
        btnClear.setEnabled(true);
        if (id == null || "".equals(id.trim()))
        {
            
            btnAdd.setEnabled(true);
            btnEdit.setEnabled(false);
            btnDelete.setEnabled(false);
        }
        else
        {
            btnAdd.setEnabled(false);
            btnEdit.setEnabled(true);
            btnDelete.setEnabled(true);
        }
    }
    
    public String validateSearch(){
        String search = FieldSearch.getText();
        if (search == null || "".equals(search.trim())) {
            FieldSearch.requestFocus();
            showMessage("Nội dung tìm kiếm không hợp lệ!");
            return "";
        }
        btnCancelSearch.setEnabled(true);
        SearchDialog.setVisible(false);
        btnAdd.setEnabled(false);
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        btnClear.setEnabled(false);
        // btnSearch.setEnabled(false);
        return search;
    }
    
    public void addUndoListener(ActionListener listener){
        btnResidentUndo.addActionListener(listener);
    }
    
    public void addAddResidentListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }
    
    public void addListResidentSelectionListener(ListSelectionListener listener) {
        tableResident.getSelectionModel().addListSelectionListener(listener);
    }
    
    public void addEditResidentListener(ActionListener listener) {
        btnEdit.addActionListener(listener);
    }
    
    public void addClearListener(ActionListener listener) {
        btnClear.addActionListener(listener);
    }
    
    public void addDeleteSpecialPersonListener(ActionListener listener) {
        btnDelete.addActionListener(listener);
    }
    
    public void addSortSpecialPersonListener(ActionListener listener) {
        btnSort.addActionListener(listener);
    }
    
    public void addSearchListener(ActionListener listener) {
        btnSearch.addActionListener(listener);
    }
    
    public void addSearchDialogListener(ActionListener listener) {
        btnSearchDialog.addActionListener(listener);
    }
    
    public void addCancelSearchResidentListener(ActionListener listener){
        btnCancelSearch.addActionListener(listener);
    }
    
    public void addCancelDialogListener(ActionListener listener){
        btnCancelDialog.addActionListener(listener);
    }

    public void addStatisticListener(ActionListener listener) {
        btnStatistic.addActionListener(listener);
    }
//    public void addStatisticAgeListener(ActionListener listener) {
//        btnStatisticAge.addActionListener(listener);
//    }

//    public void addStatisticGenderListener(ActionListener listener) {
//        btnStatisticSex.addActionListener(listener);
//    }
    public void addStatisticGraduationListener(ActionListener listener) {
        btnStatisticType.addActionListener(listener);
    }
    public void addStatisticUnderListener(ActionListener listener) {
        btnStatisticUnder.addActionListener(listener);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser BirthdayChooser;
    private javax.swing.JComboBox<String> BoxSubject1;
    private javax.swing.JComboBox<String> BoxSubject2;
    private javax.swing.JComboBox<String> BoxSubject3;
    private javax.swing.JComboBox<String> BoxSubject4;
    private javax.swing.JComboBox<String> BoxSubject5;
    private javax.swing.JComboBox<String> BoxSubject6;
    private javax.swing.JCheckBox CheckBoxFemale;
    private javax.swing.JCheckBox CheckBoxMale;
    private javax.swing.JCheckBox CheckBoxName;
    private javax.swing.JCheckBox CheckBoxSBD;
    private javax.swing.JCheckBox CheckBoxScore;
    private javax.swing.JCheckBox CheckBoxSortID;
    private javax.swing.JCheckBox CheckBoxSortIDFamily;
    private javax.swing.JCheckBox CheckBoxSortName;
    private javax.swing.JCheckBox CheckBoxYear;
    private javax.swing.JTextField FieldName;
    private javax.swing.JTextField FieldPhone;
    private javax.swing.JTextField FieldSBD;
    private javax.swing.JTextField FieldScore;
    private javax.swing.JTextField FieldSearch;
    private javax.swing.JTextField FieldSubject1;
    private javax.swing.JTextField FieldSubject2;
    private javax.swing.JTextField FieldSubject3;
    private javax.swing.JTextField FieldSubject4;
    private javax.swing.JTextField FieldSubject5;
    private javax.swing.JTextField FieldSubject6;
    private javax.swing.JScrollPane ScrollPaneStatistic;
    private javax.swing.JDialog SearchDialog;
    private javax.swing.JFrame StatisticView;
    private javax.swing.JTextArea TextAreaAddress;
    private javax.swing.JTextField ToTalStudent;
    private javax.swing.JTextField TotalStudentFail;
    private javax.swing.JTextField TotalStudentPass;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancelDialog;
    private javax.swing.JButton btnCancelSearch;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.ButtonGroup btnGroupSearch;
    private javax.swing.ButtonGroup btnGroupSex;
    private javax.swing.ButtonGroup btnGroupSort;
    private javax.swing.JButton btnResidentUndo;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearchDialog;
    private javax.swing.JButton btnSort;
    private javax.swing.JButton btnStatistic;
    private javax.swing.JButton btnStatisticType;
    private javax.swing.JButton btnStatisticUnder;
    private com.raven.chart.Chart chart1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblChart;
    private javax.swing.JLabel lblTable;
    private javax.swing.JPanel panelChart;
    private javax.swing.JTable tableResident;
    private javax.swing.JTable tableStatistic;
    // End of variables declaration//GEN-END:variables
    
    private void setupComboBoxListeners() {
    for (JComboBox<String> box : boxes) {
        box.addActionListener(e -> updateAllComboBoxes());
    }
}
    
    private void updateAllComboBoxes() {
    Set<String> selectedSubjects = new HashSet<>();

    for (JComboBox<String> box : boxes) {
        String selected = (String) box.getSelectedItem();
        if (selected != null && !selected.equals("-- Chọn môn --")) {
            selectedSubjects.add(selected);
        }
    }

    for (JComboBox<String> box : boxes) {
        String currentSelection = (String) box.getSelectedItem();

        List<String> filtered = new ArrayList<>();
        filtered.add("-- Chọn môn --");  // giữ dòng gợi ý

        for (String subj : allSubjects) {
            if (!selectedSubjects.contains(subj) || subj.equals(currentSelection)) {
                if (!subj.equals("-- Chọn môn --")) {
                    filtered.add(subj);
                }
            }
        }

        box.setModel(new DefaultComboBoxModel<>(filtered.toArray(new String[0])));
        box.setSelectedItem(currentSelection); // giữ lại lựa chọn cũ
    }


}

}
