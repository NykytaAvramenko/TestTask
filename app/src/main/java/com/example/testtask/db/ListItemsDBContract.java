package com.example.testtask.db;

import android.provider.BaseColumns;


final class ListItemsDBContract {

    private ListItemsDBContract() {
    }
    public static final class MyItemsConstants implements BaseColumns {
        public static final String TABLE_NAME = "listItems";
        public static final String COLUMN_ICON_URL = "imageUrl";
        public static final String COLUMN_DESCRIPTION = "description";
    }
}