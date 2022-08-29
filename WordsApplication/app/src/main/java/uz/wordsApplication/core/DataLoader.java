package uz.wordsApplication.core;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Collections;

public class DataLoader {

    public static ArrayList<GameData> makeData(Context context) {

        ArrayList<GameData> data = new ArrayList<>();

        GameData data1 =new GameData("сон", "соньъбаыйпнп");
        data1.addImage(getImageIdByName("a1", context));
        data1.addImage(getImageIdByName("a2", context));
        data1.addImage(getImageIdByName("a3", context));
        data1.addImage(getImageIdByName("a4", context));
        data.add(data1);

        GameData data2 =new GameData("бобы", "бобызерноёъю");
        data2.addImage(getImageIdByName("a5", context));
        data2.addImage(getImageIdByName("a6", context));
        data2.addImage(getImageIdByName("a7", context));
        data2.addImage(getImageIdByName("a8", context));
        data.add(data2);

        GameData data3 =new GameData("раб", "рабёъащедеэр");
        data3.addImage(getImageIdByName("a9", context));
        data3.addImage(getImageIdByName("a10", context));
        data3.addImage(getImageIdByName("a11", context));
        data3.addImage(getImageIdByName("a12", context));
        data.add(data3);

        GameData data4 =new GameData("подарок", "оюикдпхоатыр");
        data4.addImage(getImageIdByName("a13", context));
        data4.addImage(getImageIdByName("a14", context));
        data4.addImage(getImageIdByName("a15", context));
        data4.addImage(getImageIdByName("a16", context));
        data.add(data4);

        GameData data5 =new GameData("труба", "йргхвупфбзат");
        data5.addImage(getImageIdByName("a17", context));
        data5.addImage(getImageIdByName("a18", context));
        data5.addImage(getImageIdByName("a19", context));
        data5.addImage(getImageIdByName("a20", context));
        data.add(data5);

        GameData data6 =new GameData("шляпа", "шляпафнёчзёе");
        data6.addImage(getImageIdByName("a21", context));
        data6.addImage(getImageIdByName("a22", context));
        data6.addImage(getImageIdByName("a23", context));
        data6.addImage(getImageIdByName("a24", context));
        data.add(data6);

        GameData data100 =new GameData("книга", "книгаучебаою");
        data100.addImage(getImageIdByName("book1", context));
        data100.addImage(getImageIdByName("book2", context));
        data100.addImage(getImageIdByName("book3", context));
        data100.addImage(getImageIdByName("book4", context));
        data.add(data100);

//        Collections.shuffle(data);


        return data;
    }

    public static int getImageIdByName(String name, Context context) {
        Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier(name, "drawable", context.getPackageName());
        return resourceId;
    }

}

