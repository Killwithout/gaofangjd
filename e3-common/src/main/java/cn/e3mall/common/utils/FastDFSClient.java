package cn.e3mall.common.utils;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

/**
* @author song:
* @version ：2017年6月27日 下午2:47:34
*/
public class FastDFSClient {
	private TrackerClient trackerclient=null;
	private TrackerServer trackerServer=null;
	private StorageServer storageServer=null;
	private StorageClient1 storageClient=null;
	public  FastDFSClient(String conf)throws Exception{
		
		if(conf.contains("classpath:")){
			conf=conf.replace("classpath:", this.getClass().getResource("/").getPath());
		}
		ClientGlobal.init(conf);
		trackerclient  = new TrackerClient();
		trackerServer  = trackerclient.getConnection();
		storageClient=null;
		storageClient = new StorageClient1(trackerServer,storageServer);
		
		
	}
    /*
     * 上传文件方法
     * */
	
	public String uploadFile(String fileName,String extName,NameValuePair[] metas)throws Exception {
		String result= storageClient.upload_file1(fileName,extName,metas);
	   
		return result;
	}
	
	public String uploadFile(String fileName)throws Exception{
		return uploadFile(fileName,null,null);
	}
	public String uploadFile(String filename,String extName)throws Exception{
		return  uploadFile(filename, extName,null);
	}
	

	public String uploadFile(byte[] filecontent,String extName,NameValuePair[] metas)throws Exception{
	
		String result=storageClient.upload_file1(filecontent,extName,metas);
		return result;
		
	}
	public String uploadFile(byte[] fileContent)throws Exception{
		
		return uploadFile(fileContent, null, null);
		
	}
		
	public String uploadFile(byte[] fileContent,String extName)throws Exception{
		return uploadFile(fileContent,extName,null);
	}
	
	
	

}
