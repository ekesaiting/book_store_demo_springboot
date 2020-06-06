package com.feng.book_store_demo.script.task;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

@Component
public class HttpUtil {
    private final  PoolingHttpClientConnectionManager  cm;
    HttpUtil(){
        cm=new PoolingHttpClientConnectionManager();
        this.cm.setMaxTotal(100);
        this.cm.setDefaultMaxPerRoute(20);
    }
    public  String getHtml(String url)  {
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        HttpGet httpGet=new HttpGet(url);
        //设置这个header，让京东认为是浏览器在访问
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:62.0) Gecko/20100101 Firefox/62.0");
        httpGet.setConfig(this.getConfig());
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode()==200) {
                if (response.getEntity() != null) {
                    return  EntityUtils.toString(response.getEntity());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }
    public String getImage(String url)  {
        //String imgPath="images";
        String imgPath="D:\\VueProjects\\book_store_demo\\public\\images";
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
       /* URL temUrl = null;
        URI uri= null;
        try {
            temUrl = new URL(url);
            uri = new URI(temUrl.getProtocol(), temUrl.getHost(), temUrl.getPath(), temUrl.getQuery(), null);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }*/

        //HttpGet httpGet = new HttpGet(uri);
        //地址中涉及了特殊字符，如‘｜’‘&’等,使用下面的方法，会出现java.net.URISyntaxException
        HttpGet httpGet=new HttpGet(url);
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:62.0) Gecko/20100101 Firefox/62.0");
        httpGet.setConfig(this.getConfig());
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode()==200) {
                if (response.getEntity() != null) {
                    String extName=url.substring(url.lastIndexOf("."));
                    String imageName= UUID.randomUUID().toString()+".jpg";
                    OutputStream os=new FileOutputStream(new File(imgPath+"\\"+imageName));
                    response.getEntity().writeTo(os);
                    return imageName;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }
    private RequestConfig getConfig(){
        return RequestConfig.custom()
                .setConnectTimeout(2000)
                .setConnectionRequestTimeout(2000)
                .setSocketTimeout(10000).build();
    }
}
