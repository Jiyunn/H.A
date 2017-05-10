package kr.happy.myarmy.Recyclerview;



public class Object {
    String title;
    String content;

    public Object(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Object(String title) {
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
