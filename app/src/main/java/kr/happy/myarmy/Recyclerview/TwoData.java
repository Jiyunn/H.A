package kr.happy.myarmy.Recyclerview;



public class TwoData {
    private String title;
    private String content;

    public TwoData(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public TwoData(String title) {
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
