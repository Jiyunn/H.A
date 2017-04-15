package kr.happy.myarmy.Recyclerview;



public class ItemResume  {
    String title;
    String content;

    public ItemResume(String title, String content) {
        this.content = content;
        this.title = title;
    }

    public ItemResume(String content) {
        this.content = content;
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
