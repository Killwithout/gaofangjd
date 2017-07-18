package cn.e3mall.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by cjk on 2017/7/5.
 */
public class JedisTest {

        @Test
        public void testJedis() throws Exception{
            Jedis jedis=new Jedis("192.168.30.91", 6379);
            jedis.set("test134","hello jedis");
            String str= jedis.get("rest134");
            System.out.println(str);
            jedis.close();
        }
        //连接池
        @Test
        public void testjedispool() throws Exception{

            JedisPool jedisPool = new JedisPool("192.168.30.91",6379);
            Jedis jedis= jedisPool.getResource();
            String str= jedis.get("test134");
            System.out.println(str);
            jedis.close();
            jedisPool.close();
        }
}
