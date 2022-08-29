package uz.wordsApplication;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

import uz.wordsApplication.core.GameData;

public final class DataBase {

    private static DataBase dataBase = null;
    private final SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private DataBase(Context context) {
        preferences = context.getSharedPreferences("last_level", Context.MODE_PRIVATE);
    }

    public static void init (Context context) {
        if (dataBase==null) {
            dataBase = new DataBase(context);
        }
    }

    public static DataBase getDataBase() {
        return dataBase;
    }

    public void saveLastLevel(int level) {
        editor = preferences.edit();
        editor.putInt("level", level);
        editor.apply();
    }

    public int getLastLevel() {
        int level = preferences.getInt("level", 0);
        return level;
    }

    public void saveCoins(int coins) {
        editor = preferences.edit();
        editor.putInt("coins", coins);
        editor.apply();
    }

    public int getCoins() {
        int coins = preferences.getInt("coins", 150);
        return coins;
    }




}
