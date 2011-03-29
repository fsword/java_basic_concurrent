package concurrency.homework02;

import java.io.IOException;

/**
 * User: john
 * Date: 11-3-20
 * Time: 下午10:56
 */
public class DefaultSpider extends SpiderTemplate{

    public static void main(String[] args) throws IOException, InterruptedException {
        SpiderTemplate so = new DefaultSpider();
        so.init();
        so.retrieveUrls();
        so.doGrap();
    }

    @Override
    public String transformUrl(String line) {
        return "http://jakarta.apache.org/site/news/news-" + line.split("site/news/news-")[1].split("[#\"]")[0];
    }
}
