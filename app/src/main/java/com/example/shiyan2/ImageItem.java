package com.example.shiyan2;

public class ImageItem {
    private final int imageResId;
    private final String title;
    private final int imageHeight;
    private boolean isFavorite; // 新增收藏状态字段

    public ImageItem(int imageResId, String title) {
        this.imageResId = imageResId;
        this.title = title;
        this.imageHeight = (int) (300 + Math.random() * 300);
        this.isFavorite = false; // 默认未收藏
    }

    // 新增收藏状态方法
    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    // 其余方法保持不变
    public int getImageResId() { return imageResId; }
    public String getTitle() { return title; }
    public int getImageHeight() { return imageHeight; }
    public String getFormattedTime() { return "2025年4月9日"; }
}