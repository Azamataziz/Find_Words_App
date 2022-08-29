package uz.wordsApplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import uz.wordsApplication.core.DataLoader;
import uz.wordsApplication.core.GameController;

public class    MainActivity extends AppCompatActivity {

    GameController gameController;

    private RelativeLayout variantsGroup;
    private LinearLayout textGroup;
    private TextView coinsView, levelView;
    private ImageView imageView1, imageView2, imageView3, imageView4, btnClear, btnDelete, btnHelp ;
    private String b = "";
    private int length = 0;
    private int indexOfDelete = 0;
    int l, c, lastLevel, coins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //  --> SharePreference

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        l = bundle.getInt("level");
        c = bundle.getInt("coin");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameController = new GameController(DataLoader.makeData(this), l, c);

        loadView();
        loadDataView();
        loadAction();


    }

    private void loadAction() {

        for (int j = 0; j < 12; j++) {
            Button variantButton = (Button) variantsGroup.getChildAt(j);
            variantButton.setOnClickListener(this::onClickVariant);
        }

        for (int i = 0; i < gameController.getAnswerLength(); i++) {
            TextView answerButton = (TextView) textGroup.getChildAt(i);
            answerButton.setOnClickListener(this::onClickAnswer);
        }

        btnClear.setOnClickListener(this::autoClick);

        btnHelp.setOnClickListener(this::helpClick);

        btnDelete.setOnClickListener(this::deleteClick);

    }

    private void deleteClick(View view) {

        ArrayList<String> deleteArray = new ArrayList<>();
        char [] a = gameController.helper();
        char [] variants = gameController.getCharVariants();

        for (int i = 0; i < variants.length; i++) {
            deleteArray.add(String.valueOf(variants[i]));
        }
        System.out.println(deleteArray+"\t"+String.valueOf(a));
        for (int i = 0; i < a.length; i++) {
            if (deleteArray.contains(String.valueOf(a[i]))){
                deleteArray.remove((String.valueOf(a[i])));
            }
        }

        for (int j = 0; j < 12; j++) {
            Button variantButton = (Button) variantsGroup.getChildAt(j);
            if (gameController.getTotalScore()>=5 && indexOfDelete<deleteArray.size()){
                if (variantButton.getText().toString().equalsIgnoreCase(deleteArray.get(indexOfDelete))) {
                    variantButton.setText("");
                    indexOfDelete = indexOfDelete+1;
                    gameController.minusTotalScore(5);
                    coinsView.setText(String.valueOf(gameController.getTotalScore()));
                    break;
                }
            }
        }

    }

    private void helpClick(View view) {

        char [] help = gameController.helper();
        int totalCoins = gameController.getTotalScore();
        String text = "";
        String tag = "";
        String textAnswer = "";
        String tagAnswer = "";

        for (int i = 0; i < gameController.getAnswerLength(); i++) {
            TextView answerButton = (TextView) textGroup.getChildAt(i);
            if (totalCoins>15 && !answerButton.getText().toString().equalsIgnoreCase(String.valueOf(help[i])) && length<=gameController.getAnswerLength()){
                length++;

                if (answerButton.getText().toString().equalsIgnoreCase("")) {

                    for (int j = 0; j < 12; j++) {
                        Button variantButton = (Button) variantsGroup.getChildAt(j);
                        if (variantButton.getText().toString().equalsIgnoreCase(String.valueOf(help[i]))) {
                            text = variantButton.getText().toString();
                            tag = variantButton.getTag().toString();
                            variantButton.setText("");
                            break;
                        }
                    }
                }

                else {

                    textAnswer = answerButton.getText().toString();
                    tagAnswer = answerButton.getTag().toString();

                    for (int j = 0; j < 12; j++) {
                        Button variantButton = (Button) variantsGroup.getChildAt(j);
                        if (variantButton.getTag().toString().equalsIgnoreCase(tagAnswer)){
                            variantButton.setText(textAnswer);
                            length--;
                            break;
                        }
                    }

                    for (int j = 0; j < 12; j++) {
                        Button variantButton = (Button) variantsGroup.getChildAt(j);
                        if (variantButton.getText().toString().equalsIgnoreCase(String.valueOf(help[i]))) {
                            text = variantButton.getText().toString();
                            tag = variantButton.getTag().toString();
                            variantButton.setText("");
                            break;
                        }
                    }

                }
                answerButton.setText(text);
                answerButton.setTag(tag);
                gameController.minusTotalScore(15);
                coinsView.setText(String.valueOf(gameController.getTotalScore()));
                if (length==gameController.getAnswerLength()){
                    checkWin();
                }
                return;
            }
        }
    }

    private void autoClick(View view) {
        length = 0;

        for (int i = 0; i < gameController.getAnswerLength(); i++) {
            TextView answerButton = (TextView) textGroup.getChildAt(i);

            if (!answerButton.getText().toString().equalsIgnoreCase("")) {
                String answerText = answerButton.getText().toString();
                String answerTag = answerButton.getTag().toString();

                answerButton.setText("");

                for (int j = 0; j < 12; j++) {
                    Button variantButton = (Button) variantsGroup.getChildAt(j);
                    if (variantButton.getTag().toString().equals(answerTag)) {
                        variantButton.setText(answerText);
                        break;
                    }
                }
            }
        }
    }

    private void onClickAnswer(View view) {

        TextView pressText = (TextView) view;

        if(!pressText.getText().toString().equals("")) {
            length--;

            String answerText = pressText.getText().toString();
            String answerTag = pressText.getTag().toString();
            pressText.setText("");
            for (int j = 0; j < 12; j++) {
                Button variantButton = (Button) variantsGroup.getChildAt(j);
                if (variantButton.getTag().toString().equals(answerTag)){
                    variantButton.setText(answerText);
                    break;
                }
            }

            System.out.println("length:\t"+length+"\t"+gameController.getAnswerLength());

        }
    }

    private void onClickVariant(View view) {

        if (length<gameController.getAnswerLength()){

            Button pressButton = (Button) view;

            if (!pressButton.getText().toString().equals("")){

                String text = pressButton.getText().toString();

                String tag = pressButton.getTag().toString();

                pressButton.setText("");

                for (int i = 0; i < gameController.getAnswerLength(); i++) {
                    TextView answerView = (TextView) textGroup.getChildAt(i);

                    if (answerView.getText().toString().equals("")) {
                        answerView.setText(text);
                        answerView.setTag(tag);
                        break;
                    }
                }
                length++;
                System.out.println("length:\t"+length+"\t"+gameController.getAnswerLength());
            }
        }

        if (length==gameController.getAnswerLength()){
            checkWin();
        }

    }

    private void checkWin() {

        char [] a = new char[gameController.getAnswerLength()];

        for (int i = 0; i < gameController.getAnswerLength(); i++) {

            TextView answerButton = (TextView) textGroup.getChildAt(i);
            a[i]=answerButton.getText().charAt(0);
        }

        String userAnswer = String.valueOf(a);

        boolean isTrue = gameController.checkAnswer(userAnswer);

        if (isTrue && gameController.hasQuestion()){

            Toast.makeText(MainActivity.this, "You find", Toast.LENGTH_LONG).show();

            CountDownTimer countDownTimer = new CountDownTimer(1000, 1000) {
                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {

                    loadDataView();
                    loadAction();
                    indexOfDelete=0;

                }
            };
            countDownTimer.start();

        }

        else if (isTrue && !gameController.hasQuestion()) {
            Intent intent = new Intent(MainActivity.this, WinActivity.class);
            startActivity(intent);
            finish();
        }

        else
            Toast.makeText(this, "You can't find", Toast.LENGTH_LONG).show();




    }

    private void loadDataView() {

        lastLevel = gameController.getLevel();
        coins = gameController.getTotalScore();

        DataBase.getDataBase().saveLastLevel(lastLevel);
        DataBase.getDataBase().saveCoins(coins);

        for (int i = 0; i < 9; i++) {
            TextView answerView = (TextView) textGroup.getChildAt(i);
            answerView.setVisibility(View.GONE);
        }

        length = 0;

        for (int i = 0; i < gameController.getAnswerLength(); i++) {

            TextView answerView = (TextView) textGroup.getChildAt(i);
            answerView.setVisibility(View.VISIBLE);
            answerView.setText("");
            answerView.setTag("");

        }

        ArrayList<Character> variants = gameController.getVariants();
        ArrayList<Integer> imageVariants = gameController.getImages();

        for (int i = 0; i < 12; i++) {

            Button variantButton = (Button) variantsGroup.getChildAt(i);

            variantButton.setText(variants.get(i).toString());

        }

        imageView1.setImageResource(imageVariants.get(0));
        imageView2.setImageResource(imageVariants.get(1));
        imageView3.setImageResource(imageVariants.get(2));
        imageView4.setImageResource(imageVariants.get(3));

        levelView.setText(Integer.toString(gameController.getLevel()+1));
        coinsView.setText(String.valueOf(gameController.getTotalScore()));


    }

    private void loadView() {

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);

        coinsView = findViewById(R.id.coinView);
        levelView = findViewById(R.id.levelView);

        textGroup = findViewById(R.id.linear_answer);
        variantsGroup = findViewById(R.id.variantsView);

        btnClear = findViewById(R.id.btnClear);
        btnDelete = findViewById(R.id.btnDelete);
        btnHelp = findViewById(R.id.btnHelper);

    }

}