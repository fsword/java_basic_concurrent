package concurrency.homework02;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * User: john
 * Date: 11-3-20
 * Time: 上午11:09
 */
public abstract class SpiderTemplate {
    private int fileIndex = 0;
    private static List<String> newsUrls;
    protected static String folder;
    protected String indexUrl;
    protected Pattern conditionStr;

    /**
     * 初始化，用于构造必要的参数
     */
    public void init() {
        indexUrl = "http://jakarta.apache.org/";
        conditionStr = Pattern.compile("href=\"site/news/news-");
        folder = "pages";
    }

    /**
     * 收集网址列表
     * @throws IOException
     */
    public void retrieveUrls() throws IOException {
        Set<String> urlParts = new HashSet<String>();
        InputStream in = httpget(indexUrl);
        for (Object line : IOUtils.readLines(in)) {
            if (conditionStr.matcher(line.toString()).matches()) {
                urlParts.add(transformUrl(line.toString()));
            }
        }
        newsUrls = new ArrayList<String>(urlParts);
        System.out.println("一共有"+newsUrls.size()+"个页面");
    }

    /**
     * 执行执行网页抓取
     * @throws InterruptedException
     */
    public void doGrap() throws InterruptedException {
        new File(folder).mkdirs();

        Thread[] ts = new Thread[newsUrls.size()];
        int i=0;

        System.out.println("开始下载");
        long start = System.currentTimeMillis();
        for (final String url : newsUrls) {
            ts[i] = new Thread() {
                @Override
                public void run() {
                    try{
                        getPage(url, folder + "/news_" + (fileIndex++) + ".html");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
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

    /**
     * 从符合条件的源文件行中构造需要的url
     * @param line
     * @return
     */
    public abstract String transformUrl(String line);

    private void getPage(String url, String fileName) {
        try {
            FileUtils.writeStringToFile(
                    new File(fileName),
                    IOUtils.toString(httpget(url))
            );
            System.out.println("download - " + url);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println("文件下载错误: "+url);
        } catch (Exception e) {
            System.out.println("文件下载错误: "+fileName+"-"+e.getClass());
        }
    }

    private InputStream httpget(String url) throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);
        return client.execute(get).getEntity().getContent();
    }

}
