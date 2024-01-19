
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGame extends JFrame {
    private int generatedNumber;
    private int maxAttempts;
    private int attemptsLeft;
    private int roundsWon;

    private JTextField guessTextField;
    private JTextArea resultTextArea;

    public NumberGame(int maxAttempts) {
        this.maxAttempts = maxAttempts;
        this.attemptsLeft = maxAttempts;
        this.roundsWon = 0;

        setTitle("Guess the Number Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        generateRandomNumber();
    }

    private void initComponents() {
        guessTextField = new JTextField(10);
        JButton guessButton = new JButton("Guess");
        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        setLayout(new GridLayout(3, 1));

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter your guess: "));
        inputPanel.add(guessTextField);
        inputPanel.add(guessButton);

        add(inputPanel);
        add(resultTextArea);

        JButton restartButton = new JButton("Play Again");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });

        add(restartButton);
    }

    private void generateRandomNumber() {
        Random random = new Random();
        generatedNumber = random.nextInt(100) + 1; // Generates a random number between 1 and 100
    }

    private void checkGuess() {
        int userGuess;
        try {
            userGuess = Integer.parseInt(guessTextField.getText());
        } catch (NumberFormatException ex) {
            resultTextArea.setText("Invalid input. Please enter a number.");
            return;
        }

        attemptsLeft--;

        if (userGuess == generatedNumber) {
            resultTextArea.setText("Congratulations! You guessed the number!");
            roundsWon++;
            restartGame();
        } else if (attemptsLeft == 0) {
            resultTextArea.setText("Sorry, you've run out of attempts. The correct number was: " + generatedNumber);
            restartGame();
        } else if (userGuess < generatedNumber) {
            resultTextArea.setText("Too low! Attempts left: " + attemptsLeft);
        } else {
            resultTextArea.setText("Too high! Attempts left: " + attemptsLeft);
        }
    }

    private void restartGame() {
        attemptsLeft = maxAttempts;
        generateRandomNumber();
        resultTextArea.append("\n\nRounds won: " + roundsWon);
        resultTextArea.append("\nNew round started. Good luck!\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGame(8).setVisible(true); // Set the maximum attempts here
            }
        });
    }
}

