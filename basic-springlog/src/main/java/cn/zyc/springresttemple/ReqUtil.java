package cn.zyc.springresttemple;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

/**
 * description: ReqUtil <br>
 * date: 2020/3/18 15:07 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 *     <a href="https://docs.spring.io/spring/docs/4.3.9.RELEASE/spring-framework-reference/html/remoting.html#rest-client-access">spring官方文档</a>
 */
public class ReqUtil {

    public  void req(Map<String,String> head ,Map<String,Object> param,String url,String contentType,String body){
        RestTemplate template = new RestTemplate();

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAll(head);
        HttpEntity requestEntity = new HttpEntity(requestHeaders);

        ResponseEntity<Map> resultVo = template.postForEntity(url, requestEntity, Map.class, param);
        HttpStatus statusCode = resultVo.getStatusCode();
        System.out.println(statusCode);
        Map bodyvo = resultVo.getBody();








    }

    public static void main(String[] args) {
        String url="http://localhost:8880/webpro/remote/auth/apiRemote";

        Map<String,String> head = new HashMap();
        head.put("a","a");
        head.put("b","b");
        //默认 application/x-www-form-urlencoded
        head.put("Content-Type","application/json");

        Map<String,String> param = new HashMap();

        param.put("a1","a");
        param.put("b1","a");
        RestTemplate template =null; //http
        if("http".equals("http")){
            template = new RestTemplate(); //http
        }else {//https
            TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

            SSLContext sslContext = null;
            try {
                sslContext = org.apache.http.ssl.SSLContexts.custom()
                        .loadTrustMaterial(null, acceptingTrustStrategy)
                        .build();
            } catch (Exception e) {
                e.printStackTrace();
            }
            SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

            CloseableHttpClient httpClient = HttpClients.custom()
                    .setSSLSocketFactory(csf)
                    .build();

            HttpComponentsClientHttpRequestFactory requestFactory =
                    new HttpComponentsClientHttpRequestFactory();

            requestFactory.setHttpClient(httpClient);
            template = new RestTemplate(requestFactory);
        }
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAll(head);
        HttpEntity requestEntity = new HttpEntity(param,requestHeaders);

        ResponseEntity<Map> resultVo = template.postForEntity(url, requestEntity, Map.class, param);
        HttpStatus statusCode = resultVo.getStatusCode();
        System.out.println(statusCode);
        Map body = resultVo.getBody();

        System.out.println(body);
    }
}
