package me.happy.win3win.model;



public class Keyword {
    private String title;
    private String content;

    public Keyword(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Keyword(String title) {
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
