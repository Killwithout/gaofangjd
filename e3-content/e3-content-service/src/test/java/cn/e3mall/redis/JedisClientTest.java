package cn.e3mall.redis;

import cn.e3mall.common.jedis.JedisClient;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * Created by cjk on 2017/7/5.
 */
public class JedisClientTest {
    @Test
    public void testJedisClient() throws Exception{
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
        JedisClient jedisClient  =  applicationContext.getBean(JedisClient.class);
        jedisClient.set("my","jedisclient");
        String str =jedisClient.get("my");
        System.out.println(str);
    }
}
