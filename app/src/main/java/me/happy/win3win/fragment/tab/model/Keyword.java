package me.happy.win3win.fragment.tab.model;


import lombok.Data;

@Data
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

}
