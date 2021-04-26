package com.example.testtask.model;


public class ListItemModel implements ListItemWritableInterface {

    public static ListItemWritableInterface getNewListItem(String iconUrl, String description) {
        return new ListItemModel(iconUrl, description);
    }

    private String mIconUrl;
    private String mDescription;

    private ListItemModel(String iconUrl, String description) {
        mIconUrl = iconUrl;
        mDescription = description;
    }

    @Override
    public String getIconUrl() {
        return mIconUrl;
    }

    @Override
    public void setIconUrl(String iconUrl) {
        mIconUrl = iconUrl;
    }

    @Override
    public String getDescription() {
        return mDescription;
    }

    @Override
    public void setDescription(String description) {
        mDescription = description;
    }
}
