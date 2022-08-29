package uz.wordsApplication.core;

import java.util.ArrayList;

public class GameData {

    private String answer;
    private String variants;
    private ArrayList<Integer> images;

    public GameData (String answer, String variants) {
        this.answer = answer;
        this.variants = variants;
        images =new ArrayList<>();
    }


    public void addImage(Integer variant) {
        images.add(variant);
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getVariants() {
        return variants;
    }

    public void setVariants(String variants) {
        this.variants = variants;
    }

    public ArrayList<Integer> getImages() {
        return images;
    }

    public void setImages(ArrayList<Integer> images) {
        this.images = images;
    }
}
