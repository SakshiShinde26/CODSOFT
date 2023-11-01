import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame extends JFrame {
    private int generatedNumber;
    private int attempts;
    private int score;

    private JTextField guessField;
    private JButton guessButton;
    private JLabel resultLabel;
    private JLabel attemptsLabel;
    private JLabel scoreLabel;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel guessTextLabel = new JLabel("Enter your guess (1-100):");
        guessTextLabel.setBounds(20, 20, 200, 20);
        add(guessTextLabel);

        guessField = new JTextField();
        guessField.setBounds(20, 40, 100, 25);
        add(guessField);

        guessButton = new JButton("Guess");
        guessButton.setBounds(130, 40, 80, 25);
        add(guessButton);

        resultLabel = new JLabel("");
        resultLabel.setBounds(20, 70, 200, 20);
        add(resultLabel);

        attemptsLabel = new JLabel("Attempts: 0");
        attemptsLabel.setBounds(20, 90, 100, 20);
        add(attemptsLabel);

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setBounds(130, 90, 100, 20);
        add(scoreLabel);

        // Generate a random number when the game starts
        generatedNumber = generateRandomNumber();

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });
    }

    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(100) + 1;
    }

    private void checkGuess() {
        String guessText = guessField.getText();
        int guess;
        try {
            guess = Integer.parseInt(guessText);
        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter a valid number!");
            return;
        }

        if (guess == generatedNumber) {
            resultLabel.setText("Congratulations! You've guessed the number.");
            score++;
            scoreLabel.setText("Score: " + score);
            generatedNumber = generateRandomNumber();
            attempts = 0;
        } else if (guess < generatedNumber) {
            resultLabel.setText("Too low, try a higher number.");
            attempts++;
        } else {
            resultLabel.setText("Too high, try a lower number.");
            attempts++;
        }

        attemptsLabel.setText("Attempts: " + attempts);

        if (attempts >= 5) {
            resultLabel.setText("Sorry, you've used all attempts. The number was " + generatedNumber);
            generatedNumber = generateRandomNumber();
            attempts = 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                NumberGuessingGame game = new NumberGuessingGame();
                game.setVisible(true);
            }
        });
    }
}
