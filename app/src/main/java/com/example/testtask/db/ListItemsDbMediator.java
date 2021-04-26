package com.example.testtask.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.testtask.model.ListItemModel;
import com.example.testtask.model.ListItemReadableInterface;

import java.util.LinkedList;
import java.util.List;

import static android.provider.BaseColumns._ID;
import static com.example.testtask.db.ListItemsDBContract.MyItemsConstants.COLUMN_DESCRIPTION;
import static com.example.testtask.db.ListItemsDBContract.MyItemsConstants.COLUMN_ICON_URL;
import static com.example.testtask.db.ListItemsDBContract.MyItemsConstants.TABLE_NAME;


public class ListItemsDbMediator implements ListItemsDbMediatorInterface {

    public static ListItemsDbMediatorInterface getListItemsDb(Context context) {
        return new ListItemsDbMediator(context);
    }

    private SQLiteDatabase mDatabase;
    private List<ListItemReadableInterface> mAllItems;
    private Cursor mLatestCursor;
    private boolean latestUpdate;


    private ListItemsDbMediator(Context context) {
        ListItemsDBHelper ListItemsDbHelper = new ListItemsDBHelper(context);
        mDatabase = ListItemsDbHelper.getWritableDatabase();
        latestUpdate = false;
    }




    @Override
    public List<ListItemReadableInterface> getAllListItems() {
        if (!latestUpdate) {
            updateLatestCursorAndList();
        }
        return mAllItems;
    }


    private void updateLatestCursorAndList() {
        updateOnlyCursor();
        updateListOfItems();
        latestUpdate = true;
    }

    private void updateOnlyCursor() {
        mLatestCursor = mDatabase.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                _ID + " DESC"
        );
    }


    private void updateListOfItems() {

        mAllItems = new LinkedList<>();
        for (int i = 0; i < mLatestCursor.getCount(); i++) {
            mLatestCursor.moveToPosition(i);
            String iconUrl = mLatestCursor.getString(mLatestCursor.getColumnIndex(COLUMN_ICON_URL));
            String description = mLatestCursor.getString(mLatestCursor.getColumnIndex(COLUMN_DESCRIPTION));
            mAllItems.add(ListItemModel.getNewListItem(iconUrl, description));
        }
    }




    @Override
    public void addToDB(ListItemReadableInterface listItem) {
        addItemOnlyToDB(listItem.getIconUrl(), listItem.getDescription());
        latestUpdate = false;
    }

    private void addItemOnlyToDB(String iconUrl, String description) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ICON_URL, iconUrl);
        cv.put(COLUMN_DESCRIPTION, description);
        mDatabase.insert(TABLE_NAME, null, cv);
    }


}
