package kr.happy.myarmy.Recyclerview;



public class ItemResumenInfo {
    String title;
    String content;

    public ItemResumenInfo(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public ItemResumenInfo(String content) {
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
