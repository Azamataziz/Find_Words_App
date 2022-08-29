package uz.wordsApplication.core;

import java.util.ArrayList;
import java.util.Collections;

import uz.wordsApplication.DataBase;

public class GameController {

    private int level;

    private int total_score;
    private int max_score = 5;
    private int mix_score = 1;
    private int delta_score = 1;

    public ArrayList<GameData> gameData;

    public GameController(ArrayList<GameData> gameData, int lastLevel, int coins) {
        this.gameData = gameData;
        level = lastLevel;
        total_score = coins;
    }

    private GameData getData() {
        return gameData.get(level);
    }

    private String getAnswer() {
        return getData().getAnswer();
    }

    public boolean checkAnswer (String userAnswer) {

        boolean isTrue = getAnswer().equalsIgnoreCase(userAnswer);

        if (isTrue) {
            total_score += max_score;
            level ++;
            max_score = 5;
        }
        else {
            if (max_score > mix_score) {
                max_score -= delta_score;
            }
        }

        return isTrue;

    }

    public char [] helper () {
        return getAnswer().toCharArray();
    }

    public char [] getCharVariants() {
        return getData().getVariants().toCharArray();
    }

    public ArrayList<Character> getVariants() {

        char [] variants = getData().getVariants().toCharArray();

        ArrayList<Character> arrayVariants = new ArrayList<>();
        for (char c : variants) {
            arrayVariants.add(c);
        }

        Collections.shuffle(arrayVariants);

        return arrayVariants;
    }

    public ArrayList<Integer> getImages() {
        Collections.shuffle(getData().getImages());
        return getData().getImages();
    }

    public boolean hasQuestion() {
        return level+1 <= gameData.size();
    }

    public int getAnswerLength() {
        return getData().getAnswer().length();
    }

    public int minusTotalScore(int score) {
        total_score = total_score-score;
        return total_score;
    }

    public int getTotalScore() {
        return total_score;
    }

    public int getMaxScore() {
        return max_score;
    }

    public int getLevel() {
        return level;
    }

}
