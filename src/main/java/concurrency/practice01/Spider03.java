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
public class Spider03 {
    public static void main(String[] args) throws IOException, InterruptedException {
        final List<String> newsUrls = retrieveUrls();
        final String folder = "pages";
        new File(folder).mkdirs();

        Thread[] ts = new Thread[newsUrls.size()];
        int i=0;

        System.out.println("开始下载");
        long start = System.currentTimeMillis();
        for (final String url : newsUrls) {
            ts[i] = new Thread() {
                @Override
                public void run() {
                    getPage(url, folder + "/news_" + new Date() + ".html");
                }
            };
            i++;
        }
        for(Thread t : ts){
            t.start();
        }
        for(Thread t : ts){
            t.join();
        }
        System.out.println("下载结束： " + (System.currentTimeMillis() - start));

    }

    private static void getPage(String url, String fileName) {
        try {
            FileUtils.writeStringToFile(
                    new File(fileName),
                    IOUtils.toString(httpget(url))
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("download - " + url);
    }

    private static List<String> retrieveUrls() throws IOException {
        Set<String> urlParts = new HashSet<String>();
        InputStream in = httpget("http://jakarta.apache.org/");
        for (Object line : IOUtils.readLines(in)) {
            if (line.toString().indexOf("href=\"site/news/news-") != -1) {
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
