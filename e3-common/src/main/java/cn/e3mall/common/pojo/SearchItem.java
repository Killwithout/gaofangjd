package cn.e3mall.common.pojo;

import java.io.Serializable;

/**
 * Created by cjk on 2017/7/6
 */
public class SearchItem implements Serializable{
    private  String id;
    private  String title;
    private  String sell_point;
    private  long    price;
    private  String   image;
    private  String  categroy_name;

    public void setId(String id) {
        this.id = id;
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

    public void setImage(String image) {
        this.image = image;
    }

    public void setCategroy_name(String categroy_name) {
        this.categroy_name = categroy_name;
    }

    public String getId() { return id; }

    public String getTitle() {
        return title;
    }

    public String getSell_point() {
        return sell_point;
    }

    public long getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getCategroy_name() {
        return categroy_name;
    }

}
