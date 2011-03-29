package concurrency.practice01;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * User: john
 * Date: 11-3-20
 * Time: 上午11:09
 */
public class Spider {
    public static void main(String[] args) throws IOException {
        List<String> newsUrls = retrieveUrls();
        String folder = "pages";
        new File(folder).mkdirs();
        for(String url: newsUrls){
            getPage(url,folder+"/news_"+System.currentTimeMillis()+".html");
        }
    }

    private static void getPage(String url,String fileName) {
        System.out.println("download - "+url);
        try {
            FileUtils.writeStringToFile(
                    new File(fileName),
                    IOUtils.toString(httpget(url))
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> retrieveUrls() throws IOException {
        Set<String> urlParts = new HashSet<String>();
        InputStream in = httpget("http://jakarta.apache.org/");
        for(Object line: IOUtils.readLines(in)){
            if(line.toString().indexOf("href=\"site/news/news-")!=-1){
                urlParts.add("http://jakarta.apache.org/site/news/news-" + line.toString().split("site/news/news-")[1].split("[#\"]")[0]);
            }
        }
        return new ArrayList<String>(urlParts);
    }

    private static InputStream httpget(String url) throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);
        return client.execute(get).getEntity().getContent();
    }

}
