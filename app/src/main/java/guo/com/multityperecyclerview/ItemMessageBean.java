package guo.com.multityperecyclerview;

/**
 * Created by ${GuoZhaoHui} on 2017/3/30.
 * email:guozhaohui628@gmail.com
 */

public class ItemMessageBean {

    private String title;
    private String time;
    private String content;
    private String imgUrl;

    public ItemMessageBean() {

    }

    public ItemMessageBean(String title, String time, String content, String imgUrl) {
        this.title = title;
        this.time = time;
        this.content = content;
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
