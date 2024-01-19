import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeCalculatorGUI extends JFrame {
    private JTextField[] subjectFields;
    private JButton calculateButton;
    private JLabel totalMarksLabel, averagePercentageLabel, gradeLabel;

    public GradeCalculatorGUI() {
        setTitle("Grade Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new GridLayout(5, 2));

        subjectFields = new JTextField[5];

        for (int i = 0; i < subjectFields.length; i++) {
            mainPanel.add(new JLabel("Subject " + (i + 1) + " Marks: "));
            subjectFields[i] = new JTextField();
            mainPanel.add(subjectFields[i]);
        }

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateResults();
            }
        });

        totalMarksLabel = new JLabel("Total Marks: ");
        averagePercentageLabel = new JLabel("Average Percentage: ");
        gradeLabel = new JLabel("Grade: ");

        mainPanel.add(new JLabel()); // Empty label for spacing
        mainPanel.add(calculateButton);
        mainPanel.add(totalMarksLabel);
        mainPanel.add(new JLabel()); // Empty label for spacing
        mainPanel.add(averagePercentageLabel);
        mainPanel.add(new JLabel()); // Empty label for spacing
        mainPanel.add(gradeLabel);

        add(mainPanel);
    }

    private void calculateResults() {
        int totalMarks = 0;
        int totalSubjects = subjectFields.length;

        for (int i = 0; i < totalSubjects; i++) {
            try {
                int marks = Integer.parseInt(subjectFields[i].getText());
                totalMarks += marks;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid marks for all subjects.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        double averagePercentage = (double) totalMarks / totalSubjects;

        totalMarksLabel.setText("Total Marks: " + totalMarks);
        averagePercentageLabel.setText("Average Percentage: " + String.format("%.2f", averagePercentage) + "%");
        gradeLabel.setText("Grade: " + calculateGrade(averagePercentage));
    }

    private String calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return "A";
        } else if (averagePercentage >= 80) {
            return "B";
        } else if (averagePercentage >= 70) {
            return "C";
        } else if (averagePercentage >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GradeCalculatorGUI().setVisible(true);
            }
        });
    }
}
