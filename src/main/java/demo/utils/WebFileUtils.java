package demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class WebFileUtils {

    /**
     * 使用自定义的httpClient的RestTemplate
     */
    @Resource
    private RestTemplateTest httpClientTemplate;

    /**
     * 下载小文件，采用字节数组方式，所有都放入内存
     */
    public void downloadLittleFileToPath(String url,String targetDir){
        downloadLittleFileToPath(url,targetDir,null);
    }

    public void downloadLittleFileToPath(String url, String targetDir, Map<String,String> params){
        Instant now = Instant.now();
        String completeUrl =addGetQueryParam(url,params);
        ResponseEntity<byte[]> rsp = httpClientTemplate.getForEntity(completeUrl,byte[].class);
        log.info("[下载文件] [状态码] code:{}",rsp.getStatusCode());
        try {
            String path = getAndCreateDownloadDir(url,targetDir);
            Files.write(Paths.get(path), Objects.requireNonNull(rsp.getBody(),"未获取到下载文件"));

        } catch (IOException e){
            log.error("[下载文件] 写入失败:",e);
        }
        log.info("[下载文件] 完成, 耗时:{}", ChronoUnit.MILLIS.between(now,Instant.now()));
    }

    /**
     * 拼接get请求参数
     */
    private String addGetQueryParam(String url,Map<String,String> params){
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(url);
        if(!CollectionUtils.isEmpty(params)){
            for (Map.Entry<String,?> varEntry : params.entrySet()){
                uriComponentsBuilder.queryParam(varEntry.getKey(),varEntry.getValue());
            }
        }
        return uriComponentsBuilder.build().encode().toString();
    }

    /**
     * 创建或获取下载的文件夹路径
     */
    public String getAndCreateDownloadDir(String url, String targetDir) throws IOException {
        String filename = url.substring(url.lastIndexOf("/")+1);
        int i = 0;
        if((i = url.indexOf("?")) != -1){
            filename = filename.substring(0,i);
        }
        if(!Files.exists(Paths.get(targetDir))){
            Files.createDirectories(Paths.get(targetDir));
        }
        return targetDir.endsWith("/") ? targetDir+filename : targetDir+"/"+filename;
    }

    /**
     * 单线程下载，使用流
     */
    public void downloadBigFileToPath(String url,String targetDir, Map<String,String> params){
        Instant now = Instant.now();
        String completeUrl = addGetQueryParam(url,params);
        try {
            String path = getAndCreateDownloadDir(url,targetDir);
            //定义请求头的接收类型
            RequestCallback requestCallback = request -> request.getHeaders()
                    .setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM,MediaType.ALL));
            //getForObject会将所有返回值放入内存，使用流来代替此操作
            ResponseExtractor<Void> responseExtractor = response -> {
                Files.copy(response.getBody(),Paths.get(path));
                return null;
            };
            httpClientTemplate.execute(completeUrl, HttpMethod.GET,requestCallback,responseExtractor);

        } catch (IOException e){
            log.error("[下载文件] 写入失败:",e);
        }
        log.info("[下载文件] 完成,耗时:{}",ChronoUnit.MILLIS.between(now,Instant.now()));
    }

}
