package com.javaCore.junit._1;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream; 
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore; 
import java.security.cert.CertificateException; 
import java.security.cert.X509Certificate; 

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager; 
import javax.net.ssl.TrustManagerFactory; 
import javax.net.ssl.X509TrustManager; 
public class Test implements X509TrustManager { 
    /* 
     * The default X509TrustManager returned by SunX509.  We'll delegate 
     * decisions to it, and fall back to the logic in this class if the 
     * default X509TrustManager doesn't trust it. 
     */ 
    X509TrustManager sunJSSEX509TrustManager; 
    Test() throws Exception { 
        // create a "default" JSSE X509TrustManager. 
        KeyStore ks = KeyStore.getInstance("JKS"); 
        ks.load(new FileInputStream("e:/apache-tomcat-6.0.43/conf/keystore.jks"), "ufclub218comszljs".toCharArray()); 
        TrustManagerFactory tmf = 
        TrustManagerFactory.getInstance("SunX509", "SunJSSE"); 
        tmf.init(ks); 
        TrustManager tms [] = tmf.getTrustManagers(); 
        /* 
         * Iterate over the returned trustmanagers, look 
         * for an instance of X509TrustManager.  If found, 
         * use that as our "default" trust manager. 
         */ 
        for (int i = 0; i < tms.length; i++) { 
            if (tms[i] instanceof X509TrustManager) { 
                sunJSSEX509TrustManager = (X509TrustManager) tms[i]; 
                return; 
            } 
        } 
        /* 
         * Find some other way to initialize, or else we have to fail the 
         * constructor. 
         */ 
        throw new Exception("Couldn't initialize"); 
    } 
    /* 
     * Delegate to the default trust manager. 
     */ 
    public void checkClientTrusted(X509Certificate[] chain, String authType) 
                throws CertificateException { 
        try { 
            sunJSSEX509TrustManager.checkClientTrusted(chain, authType); 
        } catch (CertificateException excep) { 
            // do any special handling here, or rethrow exception. 
        } 
    } 
    /* 
     * Delegate to the default trust manager. 
     */ 
    public void checkServerTrusted(X509Certificate[] chain, String authType) 
                throws CertificateException { 
        try { 
            sunJSSEX509TrustManager.checkServerTrusted(chain, authType); 
        } catch (CertificateException excep) { 
            /* 
             * Possibly pop up a dialog box asking whether to trust the 
             * cert chain. 
             */ 
        } 
    } 
    /* 
     * Merely pass this through. 
     */ 
    public X509Certificate[] getAcceptedIssuers() { 
        return sunJSSEX509TrustManager.getAcceptedIssuers(); 
    }
    public static void main(String[] args) {
    	try{
	    	// 创建SSLContext对象，并使用我们指定的信任管理器初始化 
	        TrustManager[] tm = { new Test() }; 
	        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE"); 
	        sslContext.init(null, tm, new java.security.SecureRandom()); 
	        // 从上述SSLContext对象中得到SSLSocketFactory对象 
	        SSLSocketFactory ssf = sslContext.getSocketFactory(); 
	        // 创建URL对象 
	        URL myURL = new URL(null,"https://2.uf-club.com/images/wap/hook.png",new sun.net.www.protocol.https.Handler()); 
	        // 创建HttpsURLConnection对象，并设置其SSLSocketFactory对象 
	        HttpsURLConnection httpsConn = (HttpsURLConnection) myURL.openConnection(); 
	        httpsConn.setSSLSocketFactory(ssf); 
	        // 取得该连接的输入流，以读取响应内容 
	        InputStream inStream = httpsConn.getInputStream();//通过输入流获取图片数据 
	        byte[] btImg = readInputStream(inStream);//得到图片的二进制数据
	        
	        if(null != btImg && btImg.length > 0){  
	            String fileName = "213.gif";  
	            writeImageToDisk(btImg, fileName);  
	        }
	        
        } catch(Exception e){
        	e.printStackTrace();
        }
	}
    
    public static byte[] readInputStream(InputStream inStream) throws Exception{  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        byte[] buffer = new byte[1024];  
        int len = 0;  
        while( (len=inStream.read(buffer)) != -1 ){  
            outStream.write(buffer, 0, len);  
        }  
        inStream.close();  
        return outStream.toByteArray();  
    }  
    
    /** 
     * 将图片写入到磁盘 
     * @param img 图片数据流 
     * @param fileName 文件保存时的名称 
     */  
    public static void writeImageToDisk(byte[] img, String fileName){  
        try {  
            File file = new File("C:\\" + fileName);  
            FileOutputStream fops = new FileOutputStream(file);  
            fops.write(img);  
            fops.flush();  
            fops.close();  
            System.out.println("图片已经写入到C盘");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    } 
} 
