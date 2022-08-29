package uz.wordsApplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button start, settings, btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        int level = DataBase.getDataBase().getLastLevel();
        int coin = DataBase.getDataBase().getCoins();

        start = findViewById(R.id.btnStart);
        settings = findViewById(R.id.btnSettings);
        btnContinue = findViewById(R.id.btnContinue);

        if (level==0)
            btnContinue.setVisibility(View.GONE);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuActivity.this, MainActivity.class);

                Bundle bundle = new Bundle();

                bundle.putInt("level", 0);

                bundle.putInt("coin", 150);

                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuActivity.this, MainActivity.class);

                Bundle bundle = new Bundle();

                bundle.putInt("level", level);

                bundle.putInt("coin", coin);

                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
    }
}