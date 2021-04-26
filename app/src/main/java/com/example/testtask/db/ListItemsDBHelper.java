package com.example.testtask.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.example.testtask.db.ListItemsDBContract.MyItemsConstants.COLUMN_DESCRIPTION;
import static com.example.testtask.db.ListItemsDBContract.MyItemsConstants.COLUMN_ICON_URL;
import static com.example.testtask.db.ListItemsDBContract.MyItemsConstants.TABLE_NAME;


class ListItemsDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "listItems.db";
    public static final int DATABASE_VERSION = 1;

    private static final String DEMO_URL_JPG = "https://upload.wikimedia.org/wikipedia/commons/d/d8/Friedrich-Johann-Justin-Bertuch_Mythical-Creature-Dragon_1806.jpg";
    private static final String DEMO_URL_PNG = "https://toppng.com/uploads/preview/hoenix-kind-logo-vector-phoenix-bird-vector-11562899990gcdoe5osxb.png";
    private static final String DEMO_URL_SVG = "https://cdn.coinranking.com/B1oPuTyfX/xrp.svg";
    private static final String LONG_LOREM_IPSUM = "habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas integer eget aliquet nibh praesent tristique magna sit amet purus gravida quis blandit turpis cursus in hac habitasse platea dictumst quisque sagittis purus sit amet volutpat consequat mauris nunc congue nisi vitae suscipit tellus mauris a diam maecenas sed enim ut sem viverra aliquet eget sit amet tellus cras adipiscing enim eu turpis egestas pretium aenean pharetra magna ac placerat vestibulum lectus mauris ultrices eros in cursus turpis massa tincidunt dui ut ornare lectus sit amet est placerat in egestas erat imperdiet sed euismod nisi porta lorem mollis aliquam ut porttitor leo a diam sollicitudin tempor id eu nisl nunc mi ipsum faucibus vitae aliquet nec ullamcorper sit amet risus nullam eget felis eget nunc lobortis mattis aliquam faucibus purus in massa tempor nec feugiat nisl pretium fusce id velit ut tortor pretium viverra suspendisse potenti nullam ac tortor vitae purus faucibus ornare suspendisse sed nisi lacus sed viverra tellus in hac habitasse platea dictumst vestibulum rhoncus est pellentesque elit ullamcorper dignissim cras tincidunt lobortis feugiat vivamus at augue eget arcu dictum varius duis at consectetur lorem donec massa sapien faucibus et molestie ac feugiat sed lectus vestibulum";


    private final static String SQL_CREATE_LISTITEMS_TABLE = "CREATE TABLE " +
            TABLE_NAME + " (" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_ICON_URL + " TEXT NOT NULL, " +
            COLUMN_DESCRIPTION + " TEXT NOT NULL " +
            ");";

    public ListItemsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_LISTITEMS_TABLE);
        addDemoData(db);
    }

    private void addDemoData(SQLiteDatabase db) {
        addToDB(DEMO_URL_JPG, "Longer line :\n" + LONG_LOREM_IPSUM, db);
        addToDB("dfdfdfd", "Broken link", db);
        addToDB(DEMO_URL_SVG, "SVG image (vector)", db);
        addToDB(DEMO_URL_PNG, "PNG image", db);
        addToDB(DEMO_URL_JPG, "JPG image", db);
    }

    private void addToDB(String imageURL, String description, SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ICON_URL, imageURL);
        cv.put(COLUMN_DESCRIPTION, description);
        db.insert(TABLE_NAME, null, cv);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}