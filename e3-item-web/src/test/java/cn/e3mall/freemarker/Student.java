package cn.e3mall.freemarker;

/**
 * Created by cjk on 2017/7/17.
 */
public class Student {
    private int id;
    private String name;
    private int age;
    private String address;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public Student(int id,String name,int age,String address){
        super();
        this.id=id;
        this.name=name;
        this.age=age;
        this.address=address;
    }
}
