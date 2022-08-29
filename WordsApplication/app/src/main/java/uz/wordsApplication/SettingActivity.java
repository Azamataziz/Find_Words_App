package uz.wordsApplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class SettingActivity extends AppCompatActivity {

    private FrameLayout clear, backToMenu, root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        clear = findViewById(R.id.bntClear);
        backToMenu = findViewById(R.id.bntBack);
        root = findViewById(R.id.root);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DataBase.getDataBase().saveLastLevel(0);

                Snackbar.make(root, "Ваши данные удалены" , Snackbar.LENGTH_LONG)
                        .setTextColor(Color.WHITE)
                        .show();

            }
        });

        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

    }
}