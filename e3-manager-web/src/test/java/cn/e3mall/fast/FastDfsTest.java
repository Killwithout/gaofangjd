package cn.e3mall.fast;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import cn.e3mall.common.utils.FastDFSClient;

/**
* @author cjk
* 
*/

public class FastDfsTest {
	@Test
    public void testUpload() throws Exception{
        //创建一个配置文件，文件名任意
         ClientGlobal.init("F:/ZhuoMian/ShoppingDemo/work/e3-manager-web/src/main/resources/conf/client.conf");
    	//创建一个TrackerLient对象
         TrackerClient trackerClient = new TrackerClient();
         TrackerServer trackerServer = trackerClient.getConnection();
         StorageServer storageServer=null;
         StorageClient storageClient = new StorageClient(trackerServer, storageServer);
         String[] strings=storageClient.upload_file("C:/Users/cjk/Desktop/cjk.jpg", "jpg", null);
         for (String string : strings) {
			System.out.println(string);
		  }
         
    }
	/*@Test
	public void testFastdfsClient()throws Exception{
		
		FastDFSClient fastDFSClient = new FastDFSClient("F:/ZhuoMian/ShoppingDemo/work/e3-manager-web/src/main/resources/conf/client.conf");
		String string = fastDFSClient.uploadFile("F:/cjk.jpg");
		System.out.println(string);
	}*/
}
