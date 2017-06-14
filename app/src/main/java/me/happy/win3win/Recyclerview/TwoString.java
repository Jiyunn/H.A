package me.happy.win3win.Recyclerview;



public class TwoString {
    private String title;
    private String content;

    public TwoString(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public TwoString(String title) {
        this.title=title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
