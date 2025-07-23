import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizFrame extends JFrame implements ActionListener {
    Question[] questions = QuestionBank.getQuestions();
    int index = 0;
    int score = 0;
    JLabel questionLabel;
    JLabel timerLabel;
    JRadioButton[] options = new JRadioButton[4];
    ButtonGroup group;
    JButton nextButton;
    Timer quizTimer;
    int timeLeft = 600;
    public QuizFrame() {
        setTitle("QuizMaster");
        setSize(700, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new BorderLayout());
        questionLabel = new JLabel("Question");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        questionLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.add(questionLabel, BorderLayout.WEST);
        timerLabel = new JLabel("Time Left: 10:00");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        timerLabel.setForeground(Color.BLUE);
        timerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        timerLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));
        topPanel.add(timerLabel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 1, 10, 10));
        group = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setFont(new Font("Arial", Font.PLAIN, 14));
            group.add(options[i]);
            centerPanel.add(options[i]);
        }
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        add(centerPanel, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel();
        nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));
        nextButton.addActionListener(this);
        bottomPanel.add(nextButton);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(bottomPanel, BorderLayout.SOUTH);
        loadQuestion();
        startTimer();
        setVisible(true);
    }
    void loadQuestion() {
        if (index < questions.length) {
            Question q = questions[index];
            questionLabel.setText((index + 1) + ". " + q.question);
            group.clearSelection();
            for (int i = 0; i < 4; i++) {
                options[i].setText(q.options[i]);
            }
        } else {
            showResult();
        }
    }
    void showResult() {
        if (quizTimer != null) quizTimer.stop();
        JOptionPane.showMessageDialog(this, "Quiz Over! Your Score: " + score + "/" + questions.length);
        System.exit(0);
    }
    void startTimer() {
        quizTimer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                int minutes = timeLeft / 60;
                int seconds = timeLeft % 60;
                if (timeLeft <= 60) {
                    timerLabel.setForeground(Color.RED);
                }
                timerLabel.setText(String.format("Time Left: %02d:%02d", minutes, seconds));
                if (timeLeft <= 0) {
                    quizTimer.stop();
                    JOptionPane.showMessageDialog(QuizFrame.this, "Time's up! Auto-submitting your quiz.");
                    showResult();
                }
            }
        });
        quizTimer.start();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String selected = null;
        for (JRadioButton option : options) {
            if (option.isSelected()) {
                selected = option.getText();
                break;
            }
        }
        if (selected != null && selected.equals(questions[index].correctAnswer)) {
            score++;
        }
        index++;
        loadQuestion();
    }
}