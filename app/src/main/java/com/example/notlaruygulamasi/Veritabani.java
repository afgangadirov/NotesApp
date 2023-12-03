/*
package com.example.notlaruygulamasi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Veritabani extends SQLiteOpenHelper {


    public Veritabani(@Nullable Context context) {
        super(context, "notlar.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE \"notlar\" (\n" +
                "\t\"not_id\"\tINTEGER,\n" +
                "\t\"ders_adi\"\tTEXT,\n" +
                "\t\"not1\"\tINTEGER,\n" +
                "\t\"not2\"\tINTEGER,\n" +
                "\tPRIMARY KEY(\"not_id\" AUTOINCREMENT)\n" +
                ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS otlar");
        onCreate(sqLiteDatabase);

    }
}
*/
