import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

class StudentManagementSystem extends JFrame {
    private ArrayList<Student> students;
    private JTextArea displayArea;

    public StudentManagementSystem() {
        setTitle("Student Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        students = new ArrayList<>();

        JLabel infoLabel = new JLabel("Student Management System");
        infoLabel.setBounds(120, 10, 200, 20);
        add(infoLabel);

        displayArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBounds(20, 40, 360, 150);
        add(scrollPane);

        JButton addStudentButton = new JButton("Add Student");
        addStudentButton.setBounds(20, 200, 120, 30);
        add(addStudentButton);

        JButton displayStudentsButton = new JButton("Display Students");
        displayStudentsButton.setBounds(150, 200, 150, 30);
        add(displayStudentsButton);

        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add a new student
                String name = JOptionPane.showInputDialog("Enter student's name:");
                String rollNumberStr = JOptionPane.showInputDialog("Enter student's roll number:");
                String grade = JOptionPane.showInputDialog("Enter student's grade:");

                if (name != null && rollNumberStr != null && grade != null && !name.isEmpty() && !rollNumberStr.isEmpty() && !grade.isEmpty()) {
                    try {
                        int rollNumber = Integer.parseInt(rollNumberStr);
                        Student newStudent = new Student(name, rollNumber, grade);
                        students.add(newStudent);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid roll number. Please enter a number.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                }
            }
        });

        displayStudentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayStudents();
            }
        });
    }

    private void displayStudents() {
        if (students.isEmpty()) {
            displayArea.setText("No students added yet.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Student student : students) {
                sb.append("Name: ").append(student.getName())
                        .append(", Roll Number: ").append(student.getRollNumber())
                        .append(", Grade: ").append(student.getGrade())
                        .append("\n");
            }
            displayArea.setText(sb.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                StudentManagementSystem system = new StudentManagementSystem();
                system.setVisible(true);
            }
        });
    }
}
