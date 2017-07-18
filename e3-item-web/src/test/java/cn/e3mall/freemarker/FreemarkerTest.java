package cn.e3mall.freemarker;


import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.*;

/**
 * Created by cjk on 2017/7/17.
 */
public class FreemarkerTest {
    @Test
    public void testFreemarker()throws Exception{
        Configuration configuration = new Configuration(freemarker.template.Configuration.getVersion());
        configuration.setDirectoryForTemplateLoading(new File("F:\\ZhuoMian\\ShoppingDemo\\work\\e3-item-web\\src\\main\\webapp\\WEB-INF\\ftl"));
        configuration.setDefaultEncoding("utf-8");
        Template template = configuration.getTemplate("student.ftl");
        Map data = new HashMap<>();
        data.put("hello","helloFreemarker");
        Student student = new Student(1,"小电风扇",1,"西安");
        data.put("student",student);
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1,"校长",11,"西安"));
        studentList.add(new Student(2,"学生",22,"兰州"));
        studentList.add(new Student(3,"老师",33,"广州"));
        studentList.add(new Student(4,"牛大",44,"上海"));
        studentList.add(new Student(5,"牛二",55,"北京"));
        studentList.add(new Student(6,"牛三",66,"天津"));
        studentList.add(new Student(7,"张三",77,"济南"));
        studentList.add(new Student(8,"李四",88,"南京"));
        studentList.add(new Student(9,"王五",99,"银川"));
        studentList.add(new Student(10,"赵六",10,"呼和浩特"));
        data.put("studentList",studentList);
        data.put("data",new Date());
        data.put("val",123);
        Writer out = new FileWriter(new File("F://ZhuoMian//test.html"));
        template.process(data,out);
        out.close();
    }
}
