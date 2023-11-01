import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class WordCounterGUI extends JFrame {
    private JTextArea textArea;
    private JButton countButton;

    public WordCounterGUI() {
        setTitle("Word Counter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel infoLabel = new JLabel("Enter text or select a file to count words:");
        infoLabel.setBounds(20, 20, 300, 20);
        add(infoLabel);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(20, 50, 350, 150);
        add(scrollPane);

        JButton fileButton = new JButton("Choose File");
        fileButton.setBounds(20, 210, 120, 30);
        add(fileButton);

        countButton = new JButton("Count Words");
        countButton.setBounds(160, 210, 120, 30);
        add(countButton);

        JLabel resultLabel = new JLabel("Word Count: ");
        resultLabel.setBounds(20, 250, 200, 20);
        add(resultLabel);

        fileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        String fileContent = new String(Files.readAllBytes(selectedFile.toPath()));
                        textArea.setText(fileContent);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error reading the file.");
                    }
                }
            }
        });

        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText();
                int wordCount = countWords(text);
                resultLabel.setText("Word Count: " + wordCount);
            }
        });
    }

    private int countWords(String text) {
        if (text.isEmpty()) {
            return 0;
        }

        String[] words = text.split("[\\s.,!?]+"); // Split by space, comma, period, exclamation, or question marks
        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));

        // Implementing word counting excluding common words (stop words)
        List<String> stopWords = Arrays.asList("a", "an", "the", "and", "or", "is", "are"); // You can extend this list

        int count = 0;
        for (String word : words) {
            if (!stopWords.contains(word.toLowerCase())) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                WordCounterGUI wordCounter = new WordCounterGUI();
                wordCounter.setVisible(true);
            }
        });
    }
}
