package cn.com.nong.util;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class HttpUtil {
    private  static volatile int rdd = 0;
    private static HostnameVerifier nameVerifier;
    private static SSLSocketFactory ssLfactory;
    private  static void initsslConfig() {
        try {
            SSLContext sslc = SSLContext.getInstance("SSL");
            TrustManager[] trustManagerArray = {new X509TrustManager(){
                @Override
                public void checkClientTrusted(
                        X509Certificate[] paramArrayOfX509Certificate,
                        String paramString) throws CertificateException {
                }
                @Override
                public void checkServerTrusted(
                        X509Certificate[] paramArrayOfX509Certificate,
                        String paramString) throws CertificateException {
                }
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }};
            sslc.init(null, trustManagerArray, new java.security.SecureRandom());
            ssLfactory = sslc.getSocketFactory();
            nameVerifier = new HostnameVerifier() {
                @Override
                public boolean verify(String paramString,
                                      SSLSession paramSSLSession) {
                    // TODO Auto-generated method stub
                    return true;
                }};
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (KeyManagementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("加载ssl过滤程序成功");
    }


    public static String postHttps (String url, String content){
        if (nameVerifier == null ||  ssLfactory == null){
            initsslConfig();
        }
        String result= "";
        URL addr = null;
        BufferedReader reader = null;
        BufferedReader in = null;
        PrintWriter writer = null;
        OutputStreamWriter out=null;
        HttpsURLConnection connection = null;
        boolean flag = false;
        try {
            addr = new URL(url);
            System.out.println("访问具体地址为：" + addr);
            connection = (HttpsURLConnection)addr.openConnection();
            connection.setHostnameVerifier(nameVerifier);
            connection.setSSLSocketFactory(ssLfactory);
            connection.setConnectTimeout(600000);
            connection.setReadTimeout(600000);
            connection.setRequestMethod("POST");
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0b; Windows NT 6.0)");
			connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();
            out=new OutputStreamWriter(new BufferedOutputStream(connection.getOutputStream()),"UTF-8");
            System.out.println("http接口连接完成，开始写入参数.....");
            out.write(content);
            out.flush();
            out.close();
            if (connection.getResponseCode() >= 400) {
                throw new IllegalStateException("对方返回错误码:" + connection.getResponseCode());
            } else if (connection.getResponseCode() == 200) {
                flag = true;
            }
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
            String line;
            while((line = in.readLine()) != null){
                result += line;
            }
        } catch (Exception e) {
            System.out.println("[ERRO]fail to send the document!");
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (writer != null) {
                    writer.close();
                }
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
                if (connection != null) {
                    connection.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally{

            }
        }
        return result;
    }

    private static String getUrl(String[] urls) {
        rdd++;
        if (rdd> 10000 ){
            rdd = 0;
        }
        if (urls.length < 2){
            return urls[0];
        }
        return urls[rdd % 2];
    }



    public static String httpPost(String url, String content) throws IOException {//用于向指定地址发送content数据
        if (nameVerifier == null ||  ssLfactory == null){
            initsslConfig();
        }
        URL url1 = null;
        BufferedReader reader = null;
        PrintWriter writer = null;
        OutputStreamWriter out = null;
        HttpURLConnection connection = null;
        try {
            url1 = new URL( url );
            connection = (HttpURLConnection) url1.openConnection();
            int connectionTimeOut = 10000;
            int readTimeOut = 10000;
            connection.setConnectTimeout( connectionTimeOut );
            connection.setReadTimeout( readTimeOut );
            connection.setRequestMethod( "POST" );
            connection.setInstanceFollowRedirects( true );
            connection.setRequestProperty( "User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0b; Windows NT 6.0)" );
            connection.setRequestProperty( "Content-Type", "application/xml" );
            connection.setDoInput( true );
            connection.setDoOutput( true );
            connection.connect();
            System.out.println("http接口连接完成，开始写入参数.....");
            out = new OutputStreamWriter( new BufferedOutputStream( connection.getOutputStream() ), "UTF-8" );
            out.write( content );
            out.flush();
            out.close();
            if (connection.getResponseCode() >= 400) {
                throw new IllegalStateException( "对方返回错误码:" + connection.getResponseCode() );
            }
            reader = new BufferedReader( new InputStreamReader( connection
                    .getInputStream() ) );
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append( line ).append( "\n" );
            }
            return sb.toString();
        }catch (Exception e){
            System.out.println("http接口连接异常，"+url);
            return "";
        }finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
            if (writer != null) {
                writer.close();
            }
            if (out != null) {
                out.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }


    public static String httpGet(String strUrl){

        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {
            URL realUrl = new URL(strUrl);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            connection.setConnectTimeout(60*1000);
            connection.setReadTimeout(60*1000);
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        finally {
            if (in != null){ try { in.close(); }catch(Exception e2){} }
        }
        return result.toString();
    }
}
