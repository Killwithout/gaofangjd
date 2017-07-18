package cn.e3mall.redis;

/**
 * Created by cjk on 2017/7/12.
 */
public class myJedisTest {

    /**
     **************************************** redis练习 start **************************************
    **/
   /** @Test
    public  void testMyJedis(){
        Jedis jedis = new Jedis("192.168.30.91",6379);
        jedis.set("test123", "hello redis");
        String str = jedis.get("test123");
        System.out.println(str);
        jedis.close();
    }

    @Test
    public void testJedisPool(){
        JedisPool jedisPool = new JedisPool("192.168.30.91", 6379);
        Jedis jedis = jedisPool.getResource();
        String str = jedis.get("test123");
        System.out.println(str);
        jedis.close();
        jedisPool.close();
    }
     /**
     **************************************** redis练习 end *******************************************
     **/

    /**
     * JedisClientTest练习
     */

/**
    @Test
    public void testMyJedisClient(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
        JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
        jedisClient.set("my", "JedisClient");
        String str = jedisClient.get("my");
        System.out.println(str);
    }
    /**
     *   JedisClientTest练习 end
     */

}
