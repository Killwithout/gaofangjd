package cn.e3mall.pojo;

import java.io.Serializable;

/**
 * Created by cjk on 2017/7/6.
 */
public class SearchItem implements Serializable {

    private String Id;
    private String title;
    private String sell_point;
    private long price;
    private String image;

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {

        return image;
    }

    private String category_name;

    public String getId() {
        return Id;
    }

    public String getTitle() {
        return title;
    }

    public String getSell_point() {
        return sell_point;
    }

    public long getPrice() {
        return price;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSell_point(String sell_point) {
        this.sell_point = sell_point;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
