import java.awt.*;
import java.awt.event.*;

public class GuessTheNumberGame extends Frame implements ActionListener {
    private TextField inputField;
    private Label messageLabel;
    private Button guessButton;
    private Button newGameButton;
    private Button exitButton;
    private int randomNumber;
    private int attempts;

    public GuessTheNumberGame() {
        // Set up the frame
        setLayout(new FlowLayout());
        setTitle("Guess the Number Game");
        setSize(300, 150);

        // Initialize components
        inputField = new TextField(10);
        messageLabel = new Label("Guess a number between 1 and 100. Attempts left: "+(5-attempts));
        guessButton = new Button("Guess");
        newGameButton = new Button("New Game");
        exitButton = new Button("Exit");

        // Add components to the frame
        add(messageLabel);
        add(inputField);
        add(guessButton);
        add(newGameButton);
        add(exitButton);

        // Add action listeners to the buttons
        guessButton.addActionListener(this);
        newGameButton.addActionListener(this);
        exitButton.addActionListener(this);

        // Generate a random number between 1 and 100
        randomNumber = (int) (Math.random() * 100) + 1;
        attempts = 0;

        // Set frame visibility
        setVisible(true);
        newGameButton.setEnabled(false);
        exitButton.setEnabled(false);

        // Add window listener to handle window closing
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guessButton) {
            int guess = Integer.parseInt(inputField.getText());
            attempts++;

            if (guess > randomNumber) {
                messageLabel.setText("Number is high. Attempts left: " + (5 - attempts));
            } else if (guess < randomNumber) {
                messageLabel.setText("Number is low. Attempts left: " + (5 - attempts));
            } else {
                messageLabel.setText("Congratulations! You guessed the number.");
                guessButton.setEnabled(false);
                newGameButton.setEnabled(true);
                exitButton.setEnabled(true);
            }

            if (attempts >= 5 && guess != randomNumber) {
                messageLabel.setText("Game Over! The number was: " + randomNumber);
                guessButton.setEnabled(false);
                newGameButton.setEnabled(true);
                exitButton.setEnabled(true);
            }

            inputField.setText("");
        } else if (e.getSource() == newGameButton) {
            // Start a new game
            randomNumber = (int) (Math.random() * 100) + 1;
            attempts = 0;
            messageLabel.setText("Guess a number between 1 and 100. Attempts left: "+(5-attempts));
            guessButton.setEnabled(true);
            newGameButton.setEnabled(false);
            exitButton.setEnabled(false);
        } else if (e.getSource() == exitButton) {
            // Close the game window
            System.exit(0);
        }
    }

    public void paint(Graphics g) {
        g.drawString("Guess the Number Game", 50, 50);
    }

    public static void main(String[] args) {
        new GuessTheNumberGame();
    }
}
