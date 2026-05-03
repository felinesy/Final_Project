package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed;
    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (gp.qm.isTextInputActive) {
            char c = e.getKeyChar();

            if (Character.isLetterOrDigit(c) || c == ' ') {
                gp.qm.userInput += c;
            } else if (c == '\b' && gp.qm.userInput.length() > 0) {
                gp.qm.userInput = gp.qm.userInput.substring(0, gp.qm.userInput.length() - 1);
            } else if (c == '\n') {
                checkTextAnswer();
            }
        }
    }

    public void checkTextAnswer() {
        if (gp.qm.currentGuardianQuestion == null) {
            System.out.println("⚠️ ERROR: No active question.");
            return;
        }

        String userAnswer = gp.qm.userInput.trim();

        if (gp.qm.currentGuardianQuestion.isCorrect(userAnswer)) {
            gp.ui.addMessage("Correct Answer!");
            gp.qm.guardianCorrectAnswers++;
            gp.qm.guardianQuestionIndex++;

            if (gp.qm.guardianCorrectAnswers == 3) {
                gp.qm.giveGuardianKeyToPlayer();
                gp.qm.isGuardianQuizActive = false;
                gp.qm.isQuestionActive = false;
                gp.qm.endGuardianQuiz();

                gp.ui.currentDialogue = "You have proven your wisdom. \n The Guardian grants you a special key. \n You may now return home, little soul.";
                gp.gameState = gp.dialogueState;
                gp.repaint();
            } else {
                gp.qm.askNextGuardianQuestion();
            }
        } else {
            gp.qm.guardianAttempts--;
            gp.ui.addMessage("Incorrect! Attempts remaining: " + gp.qm.guardianAttempts);

            if (gp.qm.guardianAttempts == 0) {
                gp.qm.showResetDialogue();
            } else {
                gp.ui.addMessage("Try again.");
            }
        }
        gp.qm.userInput = "";
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (gp.gameState == gp.titleState) {
            titleState(code);
        } else if (gp.gameState == gp.playState) {
            playState(e);
        } else if (gp.gameState == gp.pauseState) {
            pauseState(code);
        } else if (gp.gameState == gp.dialogueState) {
            dialogueState(code);
        } else if (gp.gameState == gp.characterState) {
            characterState(code);
        } else if (gp.gameState == gp.creditsState) {
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.titleState;
                gp.ui.titleScreenState = 0;
            }
        }
        else if (gp.gameState == gp.winState) {
            if (code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.titleState;
            }
        }
        else if (gp.gameState == gp.gameOverState) {
            gp.qm.restartGame();
        }
    }

    public void titleState(int code) {
        if (gp.ui.tutorialState == 1) {
            if (code == KeyEvent.VK_ENTER) {
                gp.ui.tutorialState = 0;
                gp.gameState = gp.playState;
            } else if (code == KeyEvent.VK_ESCAPE) {
                gp.ui.tutorialState = 0; // back to character selection
                gp.ui.titleScreenState = 1;
            }
        } else if (gp.ui.titleScreenState == 0) {
            if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 3;
                }
            }
            if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 3) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.ui.titleScreenState = 1;
                }
                if (gp.ui.commandNum == 1) {
                    gp.saveLoad.load();
                    gp.gameState = gp.playState;
                }
                if (gp.ui.commandNum == 2) {
                    gp.gameState = gp.creditsState; // Go to Credits
                }
                if (gp.ui.commandNum == 3) {
                    System.exit(0); // Quit
                }
            }
        } else if (gp.ui.titleScreenState == 1) { // Select character
            if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 3;
                }
            }
            if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 3) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    System.out.println("Character selected: Elden");
                    gp.chosenCharacter = 0;
                } else if (gp.ui.commandNum == 1) {
                    System.out.println("Character selected: Brianna");
                    gp.chosenCharacter = 1;
                    gp.player.getPlayerImage();
                } else if (gp.ui.commandNum == 2) {
                    System.out.println("Character selected: Orion");
                    gp.chosenCharacter = 2;
                    gp.player.getPlayerImage();
                } else if (gp.ui.commandNum == 3) {
                    gp.ui.titleScreenState = 0;
                }
                if (gp.ui.commandNum != 3) {
                    gp.ui.tutorialState = 1;
                }
            }
        }
    }


    public void playState(KeyEvent e) {
        int code = e.getKeyCode();

        if (gp.player.life <= 0) {
            gp.gameState = gp.gameOverState;
        } if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
            upPressed = true;
        } if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            downPressed = true;
        } if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            leftPressed = true;
        } if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            rightPressed = true;
        } if (code == KeyEvent.VK_SPACE) {
            spacePressed = true;
        } if (code == KeyEvent.VK_P) {
            gp.gameState = gp.pauseState;
            gp.ui.commandNum = 0;
        } if (code == KeyEvent.VK_C) {
            gp.gameState = gp.characterState;
        } if (code == KeyEvent.VK_V) {
            gp.gameState = gp.playState;
        } if (code == KeyEvent.VK_F5) {
            gp.saveLoad.save();
        } if (code == KeyEvent.VK_F9) {
            gp.saveLoad.load();
        } if (code == KeyEvent.VK_1) {
            gp.player.useSkill(0);
        } else if (code == KeyEvent.VK_2) {
            gp.player.useSkill(1);
        } else if (code == KeyEvent.VK_3) {
            gp.player.useSkill(2);
        }
    }

    public void pauseState(int code) {
        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 2;
            }
        }
        if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 2) {
                gp.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            switch (gp.ui.commandNum) {
                case 0:
                    gp.gameState = gp.playState;
                    break;
                case 1:
                    gp.saveLoad.save();
                    gp.gameState = gp.playState;
                    break;
                case 2:
                    gp.gameState = gp.titleState;
                    gp.ui.titleScreenState = 0;
                    break;
            }
        }
    }

    public void dialogueState(int code) {
        if (gp.qm.isQuestionActive) {
            if (gp.qm.isMultipleChoice) {
                if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = gp.qm.choices.length - 1;
                    }
                }
                if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum >= gp.qm.choices.length) {
                        gp.ui.commandNum = 0;
                    }
                }
                if (code == KeyEvent.VK_ENTER) {
                    if (gp.qm.selectedAnswerIndex != -1) {
                        gp.qm.processAnswer(gp.qm.choices[gp.qm.selectedAnswerIndex]);
                    }
                }
            }
        } else {
            gp.gameState = gp.playState;
        }
    }

    public void characterState(int code) {

        if(code == KeyEvent.VK_C) {
            gp.gameState = gp.playState;
        } if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            if(gp.ui.slotRow != 0) {
                gp.ui.slotRow--;
            }
        } if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            if(gp.ui.slotCol != 0) {
                gp.ui.slotCol--;
            }
        } if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            if(gp.ui.slotRow != 3) {
                gp.ui.slotRow++;
            }
        } if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            if(gp.ui.slotCol != 4) {
                gp.ui.slotCol++;
            }
        } if(code == KeyEvent.VK_ENTER) {
            int slotIndex = gp.ui.slotCol + (gp.ui.slotRow * 5);
            if(slotIndex < gp.player.inventory.size()) {
                gp.player.useItem(slotIndex);
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
            upPressed = false;
        } if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            downPressed = false;
        } if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            leftPressed = false;
        } if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            rightPressed = false;
        } if (code == KeyEvent.VK_SPACE) {
            spacePressed = false;
        } else if (gp.gameState == gp.creditsState) {
            if (code == KeyEvent.VK_UP || code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum = 3;
            } if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 3) {
                    gp.gameState = gp.titleState;
                    gp.ui.titleScreenState = 0;
                }
            } if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.titleState;
                gp.ui.titleScreenState = 0;
            }
        }
    }
}