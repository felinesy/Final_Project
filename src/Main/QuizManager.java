package Main;

import object.*;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.util.*;
import java.util.List;

public class QuizManager {
    GamePanel gp;

    public boolean isQuestionActive = false;
    public String questionText = "";
    public String[] choices;
    public String correctAnswer;
    public boolean isMultipleChoice = true;

    public boolean isTextInputActive = false;
    public String userInput = "";
    public  String currentDialogue = "";
    public int maxDialogueOptions = 3;
    public int selectedAnswerIndex = -1;
    private int questionIndex = 0;
    private int correctAnswers = 0;
    private Question[] randomizedQuestions;
    public Rectangle closeButtonBounds;
    public boolean isCloseButtonHovered = false;

    public Obj_SpecialBook currentSpecialBook = null;
    private Set<Question> usedSpecialQuestions = new HashSet<>();
    public int specialKeyCount = 0;
    public int guardianAttempts = 3;
    public boolean isGuardianQuizActive = false;
    public int guardianCorrectAnswers = 0;
    public int guardianQuestionIndex = 0;
    public Question currentGuardianQuestion;


    public QuizManager(GamePanel gp) {
        this.gp = gp;
    }

    public class Question {
        String question;
        String[] choices;
        String correctAnswer;

        public Question(String question, String[] choices, String correctAnswer) {
            this.question = question;
            this.choices = choices;
            this.correctAnswer = correctAnswer.trim().toLowerCase();
        }

        public boolean isCorrect(String answer) {
            return answer.trim().toLowerCase().equals(correctAnswer);
        }

        public String getQuestionText() {
            return question;
        }
    }

    private Question[] quizQuestions = {
            new Question("What is the default value of a boolean variable in Java?",
                    new String[]{"true", "false", "null", "0"},
                    "false"),
            new Question("Which keyword is used to define a constant variable in Java?",
                    new String[]{"const", "static", "final", "immutable"},
                    "final"),
            new Question("Which of these is NOT a primitive data type in Java?",
                    new String[]{"int", "float", "boolean", "String"},
                    "String"),
            new Question("Which method is the entry point of a Java program?",
                    new String[]{"start()", "run()", "main()", "init()"},
                    "main()"),
            new Question("What symbol is used for single-line comments in Java?",
                    new String[]{"//", "/* */", "#", "--"},
                    "//"),
            new Question("Which of these access modifiers allows access within the same package?",
                    new String[]{"public", "private", "protected", "default"},
                    "default"),
            new Question("What does JVM stand for?",
                    new String[]{"Java Virtual Machine", "Java Variable Method", "Java Verified Mode", "Java Version Manager"},
                    "Java Virtual Machine"),
            new Question("What is method overloading?",
                    new String[]{"Using the same method name with different parameters", "Calling multiple methods at once", "A method that calls itself", "A method with too many lines of code"},
                    "Using the same method name with different parameters"),
            new Question("Which Java keyword is used to create an object?",
                    new String[]{"new", "create", "instance", "object"},
                    "new"),
            new Question("Which of the following is a wrapper class in Java?",
                    new String[]{"Integer", "int", "double", "boolean"},
                    "Integer")
    };

    private Question[] specialQuizQuestions = {
            new Question("Which of these is the correct way to declare a constant in Java?",
                    new String[]{"final int x = 10;", "const int x = 10;", "int final x = 10;", "final x = 10;"},
                    "final int x = 10;"),
            new Question("Which method is used to start a thread in Java?",
                    new String[]{"execute()", "start()", "run()", "begin()"},
                    "start()"),
            new Question("What is the default value of a reference variable in Java?",
                    new String[]{"null", "0", "false", "undefined"},
                    "null"),
            new Question("Which of these is not a valid access modifier in Java?",
                    new String[]{"protected", "private", "default", "internal"},
                    "internal"),
            new Question("What does the 'finally' block do in exception handling?",
                    new String[]{"Handles exceptions", "Ensures the code runs regardless of exceptions",
                            "Catches specific exceptions", "Throws an exception"},
                    "Ensures the code runs regardless of exceptions"),
            new Question("Which of these is a feature of the Java language?",
                    new String[]{"Multi-threading", "Dynamic typing", "Pointers", "Multiple inheritance with classes"},
                    "Multi-threading"),
            new Question("What is the output of the following code?\n\nint x = 5; \nSystem.out.println(x++ + ++x);",
                    new String[]{"10", "11", "12", "13"},
                    "11"),
            new Question("Which method can be used to change the size of an ArrayList in Java?",
                    new String[]{"resize()", "increase()", "add()", "set()"},
                    "add()"),
            new Question("What is the correct way to declare a 2D array in Java?",
                    new String[]{"int[] arr[];", "int arr[][];", "int arr[];", "int[][] arr;"},
                    "int[][] arr;"),
            new Question("Which of the following is a wrapper class for the int type in Java?",
                    new String[]{"Integer", "IntWrapper", "int", "Double"},
                    "Integer"),
            new Question("Which keyword is used to declare an array in Java?",
                    new String[]{"array", "new", "int", "list"},
                    "new"),
            new Question("What is method overloading in Java?",
                    new String[]{"Using the same method name with different parameters", "A method that calls itself", "A method with too many parameters", "A method with variable return types"},
                    "Using the same method name with different parameters"),
            new Question("Which of these is used to throw an exception in Java?",
                    new String[]{"throw", "catch", "try", "finally"},
                    "throw"),
            new Question("Which of the following is used for garbage collection in Java?",
                    new String[]{"finalize()", "dispose()", "gc()", "cleanup()"},
                    "finalize()"),
            new Question("What is the default value of an int in Java?",
                    new String[]{"0", "null", "1", "undefined"},
                    "0")
    };

    private Question[] guardianQuizQuestions = {
            new Question("In Java, the main method is defined as: public static _______ main(String[] args) { }",
                    new String[]{"void", "int", "String", "main"},
                    "void"),
            new Question("The keyword used to create a new object in Java is _______.",
                    new String[]{"new", "create", "instance", "object"},
                    "new"),
            new Question("A _______ is a blueprint for creating objects in Java.",
                    new String[]{"class", "method", "variable", "package"},
                    "class"),
            new Question("The _______ keyword is used to inherit properties and methods from another class.",
                    new String[]{"extends", "implements", "inherits", "super"},
                    "extends"),
            new Question("In Java, a variable that can hold a single value of a specific type is called a _______ variable.",
                    new String[]{"primitive", "reference", "static", "final"},
                    "primitive")
    };

    public void addSpecialKey() {
        specialKeyCount++;
    }

    public void startQuiz(Obj_Book book) {
        gp.currentBook = book;
        questionIndex = 0;
        correctAnswers = 0;
        randomizedQuestions = getRandomQuestions();

        if (randomizedQuestions == null || randomizedQuestions.length == 0) {
            System.out.println("Error: No questions available for the quiz!");
            return;
        }
        askNextQuestion();
    }

    public void startGuardianQuiz() {
        if (guardianQuestionIndex == 0) {
            guardianCorrectAnswers = 0;
            System.out.println("🛡 Guardian Quiz Started! Answers reset.");
        }
        askNextGuardianQuestion();
    }

    public void giveGuardianKeyToPlayer() {
        if (guardianCorrectAnswers >= 3) {
            addGuardianKey();
            gp.hasGuardianKey = true;
            guardianCorrectAnswers = 0;
            guardianQuestionIndex = 0;
            isGuardianQuizActive = false;

            Timer closeQuiz = new Timer(3000, e -> {
                gp.gameState = gp.playState;
                gp.repaint();
            });
            closeQuiz.setRepeats(false);
            closeQuiz.start();
        }
    }

    public void askNextGuardianQuestion() {
        if (guardianQuestionIndex < guardianQuizQuestions.length) {
            currentGuardianQuestion = guardianQuizQuestions[guardianQuestionIndex];

            showQuestion(
                    currentGuardianQuestion.getQuestionText(),
                    null,
                    currentGuardianQuestion.correctAnswer,
                    true // text input
            );
        } else {
            giveGuardianKeyToPlayer();
            isGuardianQuizActive = false;
            currentGuardianQuestion = null;
            gp.ui.currentDialogue = "You have completed the Guardian Quiz!";
            gp.gameState = gp.dialogueState;
            gp.repaint();
            System.out.println(" Guardian Quiz Finished.");
        }
    }

    private void addGuardianKey() {
        Obj_GuardianKey guardianKey = new Obj_GuardianKey(gp);

        guardianKey.worldX = (gp.player != null) ? gp.player.worldX + gp.tileSize : 100;
        guardianKey.worldY = (gp.player != null) ? gp.player.worldY : 100;

        for (int i = 0; i < gp.obj[gp.currentMap].length; i++) {
            if (gp.obj[gp.currentMap][i] == null) {
                gp.obj[gp.currentMap][i] = guardianKey;
                break;
            }
        }
    }

    public void processGuardianQuizAnswer(String answer) {
        if (!isGuardianQuizActive) {
            return;
        }

        String correctAnswer = currentGuardianQuestion.correctAnswer.trim().toLowerCase();
        answer = answer.trim().toLowerCase();

        if (answer.equals(correctAnswer)) {
            Sound.getCorrectSound().play();
            guardianCorrectAnswers++;
            guardianQuestionIndex++;

            if (guardianCorrectAnswers == 3) {
                giveGuardianKeyToPlayer();
                isGuardianQuizActive = false;
                return;

            } else {
                askNextGuardianQuestion();
            }
        } else {
            Sound.getBuzzerSound().play();
            guardianAttempts--;
            System.out.println("Incorrect! Attempts remaining: " + guardianAttempts);

            if (guardianAttempts == 0) {
                System.out.println("Out of attempts! The quiz is over.");
                endGuardianQuiz();
            } else {
                System.out.println("You can try again.");
            }
        }
    }

    public void resetGame() {
        gp.player.setDefaultValues();
        guardianAttempts = 3;
        guardianCorrectAnswers = 0;
        guardianQuestionIndex = 0;
        isGuardianQuizActive = false;
        questionIndex = 0;
        correctAnswers = 0;
        gp.gameState = gp.playState;
        gp.currentMap = 0;
        gp.repaint();
    }

    public void restartGame() {
        gp.player.setDefaultValues();
        gp.player.inventory.clear();
        gp.aSetter.setObject();
        gp.aSetter.setAnimals();
        gp.startMusic();
        gp.eManager.setup();

        guardianAttempts = 3;
        guardianCorrectAnswers = 0;
        guardianQuestionIndex = 0;
        isGuardianQuizActive = false;
        questionIndex = 0;
        correctAnswers = 0;

        gp.ui.addMessage("");
        gp.ui.currentDialogue = "";
        gp.ui.titleScreenState = 0;
        gp.gameState = gp.titleState;
        gp.currentMap = 0;
        gp.repaint();
    }

    public void showResetDialogue() {
        gp.qm.isQuestionActive = false;
        gp.ui.currentDialogue = "You have lost all your attempts! The game will reset.";
        gp.gameState = gp.dialogueState;
        gp.repaint();

        Timer resetTimer = new Timer(3000, e -> resetGame());
        resetTimer.setRepeats(false);
        resetTimer.start();
    }

    public void endGuardianQuiz() {
        guardianQuestionIndex = 0;
        guardianAttempts = 3;
        userInput = "";
    }

    public void processAnswer(String chosenAnswer) {
        if (isGuardianQuizActive) {
            processGuardianQuizAnswer(chosenAnswer);
            return;
        } if (!isQuestionActive || questionIndex >= randomizedQuestions.length) {
            System.out.println("⚠ Exiting: Quiz is inactive or all questions are answered.");
            return;
        }

        String correctAnswer = randomizedQuestions[questionIndex].correctAnswer.trim().toLowerCase();
        chosenAnswer = chosenAnswer.trim().toLowerCase();
        boolean isCorrect = chosenAnswer.equals(correctAnswer);

        for (int i = 0; i < choices.length; i++) {
            if (choices[i].equalsIgnoreCase(chosenAnswer)) {
                selectedAnswerIndex = i;
            }
        }

        gp.repaint();

        if (isCorrect) {
            Sound.getCorrectSound().play();
            correctAnswers++;
            gp.gameState = gp.dialogueState;
            gp.repaint();

            if (correctAnswers >= 5) {
                Timer timer = new Timer(1500, e -> {
                    if (gp.currentSpecialBook != null) {
                        gp.ui.currentDialogue = "You received a special key!";
                        giveSpecialKeyToPlayer();
                    } else {
                        gp.ui.currentDialogue = "You received a normal key!";
                        giveKeyToPlayer();
                    }
                    gp.repaint();
                    Timer switchToGameplay = new Timer(1000, e1 -> {
                        gp.gameState = gp.playState;
                        gp.repaint();
                    });
                    switchToGameplay.setRepeats(false);
                    switchToGameplay.start();
                });
                timer.setRepeats(false);
                timer.start();
            } else {
                Timer timer = new Timer(1500, e -> {
                    questionIndex++;
                    selectedAnswerIndex = -1;
                    askNextQuestion();
                    gp.repaint();
                });
                timer.setRepeats(false);
                timer.start();
            }
        } else {
            Sound.getBuzzerSound().play();
            gp.player.takeDamage(1);

            Timer resetTimer = new Timer(500, e -> {
                selectedAnswerIndex = -1;
                gp.repaint();
            });
            resetTimer.setRepeats(false);
            resetTimer.start();

            gp.gameState = gp.dialogueState;
            gp.repaint();
        }
    }

    public void startSpecialQuiz(Obj_SpecialBook specialBook) {
        gp.currentSpecialBook = specialBook;
        randomizedQuestions = getSpecialBookQuestions();
        questionIndex = 0;
        correctAnswers = 0;
        isQuestionActive = true;
        askNextQuestion();
    }

    private Question[] getSpecialBookQuestions() {
        List<Question> specialQuestionList = new ArrayList<>(Arrays.asList(specialQuizQuestions));
        specialQuestionList.removeIf(usedSpecialQuestions::contains);
        Collections.shuffle(specialQuestionList);
        Question[] selectedQuestions = specialQuestionList.subList(0, Math.min(5, specialQuestionList.size()))
                .toArray(new Question[0]);
        usedSpecialQuestions.addAll(Arrays.asList(selectedQuestions));

        return selectedQuestions;
    }

    private Question[] getRandomQuestions() {
        java.util.List<Question> questionList = new ArrayList<>(Arrays.asList(quizQuestions));
        Collections.shuffle(questionList);

        return questionList.subList(0, Math.min(5, questionList.size())).toArray(new Question[0]);
    }

    public void giveSpecialKeyToPlayer() {
        if (correctAnswers == 5 && gp.currentSpecialBook != null) {
            addSpecialKey();
            gp.hasSpecialKey = true;

            System.out.println("Total special keys: " + specialKeyCount);

            Timer delayBookRemoval = new Timer(2000, e -> {
                if (gp.currentSpecialBook != null) {
                    Obj_SpecialKey specialKey = gp.specialBookKeyMap.get(gp.currentSpecialBook.getType());

                    if (specialKey != null) {
                        specialKeyCount++;
                        specialKey.worldX = gp.currentSpecialBook.worldX;
                        specialKey.worldY = gp.currentSpecialBook.worldY;

                        for (int i = 0; i < gp.obj[gp.currentMap].length; i++) {
                            if (gp.obj[gp.currentMap][i] == null) {
                                gp.obj[gp.currentMap][i] = specialKey; // Place the special key
                                break;
                            }
                        }
                        for (int i = 0; i < gp.obj[gp.currentMap].length; i++) {
                            if (gp.obj[gp.currentMap][i] == gp.currentSpecialBook) {
                                gp.obj[gp.currentMap][i] = null;  // Remove the special book
                                break;
                            }
                        }
                        gp.currentSpecialBook = null;
                    }

                    gp.repaint();

                    Timer switchToGameplay = new Timer(2000, e1 -> {
                        gp.gameState = gp.playState;
                        currentDialogue = "";
                        gp.repaint();
                    });
                    switchToGameplay.setRepeats(false);
                    switchToGameplay.start();

                    gp.repaint();
                }
            });
            delayBookRemoval.setRepeats(false);
            delayBookRemoval.start();

            correctAnswers = 0;
            questionIndex = 0;
            isQuestionActive = false;
        } else {
            System.out.println("⚠️ No special book to replace!");
        }
    }

    private boolean isFillInTheBlankQuestion() {
        String currentQuestion = randomizedQuestions[questionIndex].question;
        return currentQuestion.contains("_______");
    }

    public void askNextQuestion() {
        if (questionIndex < randomizedQuestions.length) {
            Question currentQuestion = randomizedQuestions[questionIndex];

            boolean isTextInput = isFillInTheBlankQuestion();
            showQuestion(
                    currentQuestion.question,
                    currentQuestion.choices,
                    currentQuestion.correctAnswer,
                    isTextInput
            );

            selectedAnswerIndex = -1;
            gp.repaint();
        } else {
            giveSpecialKeyToPlayer();
        }
    }

    public void giveKeyToPlayer() {

        if (correctAnswers == 5 && gp.currentBook != null) {
            Timer delayBookRemoval = new Timer(2000, e -> {
                if (gp.currentBook != null) {
                    Obj_Key key = new Obj_Key(gp);
                    key.worldX = gp.currentBook.worldX;
                    key.worldY = gp.currentBook.worldY;

                    for (int i = 0; i < gp.obj[gp.currentMap].length; i++) {
                        if (gp.obj[gp.currentMap][i] == null) {
                            gp.obj[gp.currentMap][i] = key;
                            break;
                        }
                    }

                    for (int i = 0; i < gp.obj[gp.currentMap].length; i++) {
                        if (gp.obj[gp.currentMap][i] == gp.currentBook) {
                            gp.obj[gp.currentMap][i] = null;
                            break;
                        }
                    }

                    gp.currentBook = null;
                }

                Timer switchToGameplay = new Timer(2000, e1 -> {
                    gp.gameState = gp.playState;
                    gp.repaint();
                });
                switchToGameplay.setRepeats(false);
                switchToGameplay.start();

                gp.repaint();
            });
            delayBookRemoval.setRepeats(false);
            delayBookRemoval.start();

            isQuestionActive = false;
            correctAnswers = 0;
            questionIndex = 0; // Reset for next time

            gp.gameState = gp.dialogueState;
            gp.repaint();

            Timer closeQuiz = new Timer(5000, e -> {
                gp.gameState = gp.playState;
                gp.repaint();
            });
            closeQuiz.setRepeats(false);
            closeQuiz.start();

        } else {
            System.out.println("⚠️ No book to replace!");
        }
    }

    public void showQuestion(String question, String[] choices, String correctAnswer, boolean isTextInput) {
        this.questionText = question;
        this.choices = choices;
        this.correctAnswer = correctAnswer;
        this.isQuestionActive = true;
        this.isMultipleChoice = !isTextInput;
        this.isTextInputActive = isTextInput;
        this.maxDialogueOptions = (choices != null) ? choices.length : 0;

        if (isTextInputActive) {
            userInput = "";
        }

        selectedAnswerIndex = -1;
        gp.gameState = gp.dialogueState;
        gp.repaint();
    }

    public void drawQuestionScreen() {
        int x = gp.tileSize * 3;
        int y = gp.tileSize * 2;
        int width = gp.screenWidth - (gp.tileSize * 6);
        int height = gp.tileSize * 6;

        gp.ui.drawSubWindow(x, y, width, height);

        gp.ui.g2.setFont(gp.ui.g2.getFont().deriveFont(Font.BOLD, 18F));
        gp.ui.g2.setColor(Color.WHITE);

        String questionCounter;
        if (isMultipleChoice) {
            questionCounter = (questionIndex + 1) + ". ";
        } else {
            questionCounter =  (guardianQuestionIndex + 1) + ". ";
        }
        String fullQuestion = questionCounter + questionText;
        java.util.List<String> wrappedText = wrapText(fullQuestion, width - 40, gp.ui.g2);

        int textY = y + 40;
        for (String line : wrappedText) {
            gp.ui.g2.drawString(line, x + 20, textY);
            textY += 25;
        }

        if (isMultipleChoice) {
            for (int i = 0; i < choices.length; i++) {
                int choiceY = textY + (i * 55);

                gp.ui.g2.setColor(selectedAnswerIndex == i ? Color.YELLOW : Color.WHITE);
                gp.ui.g2.drawString(choices[i], x + 70, choiceY + 5);

                gp.ui.g2.setColor(selectedAnswerIndex == i ? Color.YELLOW : Color.WHITE);
                gp.ui.g2.fillOval(x + 20, choiceY - 15, 40, 35);
                gp.ui.g2.setColor(Color.BLACK);
                gp.ui.g2.drawOval(x + 20, choiceY - 15, 40, 35);
            }
        } else {
            gp.ui.g2.setColor(Color.WHITE);
            gp.ui.g2.drawRect(x + 50, textY + 50, width - 100, 40);
            gp.ui.g2.drawString(userInput, x + 60, textY + 75);
        }

        int closeX = x + width - 40;
        int closeY = y + 10;
        int closeSize = 30;

        gp.ui.g2.setColor(Color.RED);
        gp.ui.g2.fillRect(closeX, closeY, closeSize, closeSize);
        gp.ui.g2.setColor(Color.WHITE);
        gp.ui.g2.drawString("X", closeX + 10, closeY + 23);

        closeButtonBounds = new Rectangle(closeX, closeY, closeSize, closeSize);
    }

    public void drawMessageQuestion(Graphics2D g2) {
        int messageX = gp.tileSize * 4;
        int messageY = gp.tileSize * 10;
        g2.setFont(g2.getFont().deriveFont(Font.TRUETYPE_FONT, 18f));

        for (int i = 0; i < gp.ui.message.size(); i++) {
            if (gp.ui.message.get(i) != null) {
                g2.setColor(Color.white);
                g2.drawString(gp.ui.message.get(i), messageX, messageY);

                int counter = gp.ui.messageCounter.get(i) + 1;
                gp.ui.messageCounter.set(i, counter);
                messageY += 50;

                if (gp.ui.messageCounter.get(i) > 180) {
                    gp.ui.message.remove(i);
                    gp.ui.messageCounter.remove(i);
                }
            }
        }
    }

    private java.util.List<String> wrapText(String text, int maxWidth, Graphics2D g2) {
        java.util.List<String> lines = new ArrayList<>();
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();

        for (String word : words) {
            String testLine = line + (line.length() == 0 ? "" : " ") + word;
            int textWidth = g2.getFontMetrics().stringWidth(testLine);

            if (textWidth > maxWidth) {
                lines.add(line.toString());
                line = new StringBuilder(word);
            } else {
                line.append(line.length() == 0 ? "" : " ").append(word);
            }
        }
        if (!line.isEmpty()) {
            lines.add(line.toString());
        }

        return lines;
    }
}

