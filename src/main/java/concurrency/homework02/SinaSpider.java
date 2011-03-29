package concurrency.homework02;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: john
 * Date: 11-3-20
 * Time: 下午10:56
 */
public class SinaSpider extends SpiderTemplate{

    public static void main(String[] args) throws IOException, InterruptedException {
        SpiderTemplate so = new SinaSpider();
        so.init();
        so.retrieveUrls();
        so.doGrap();
    }


    @Override
    public void init() {
        indexUrl = "http://news.sina.com.cn/";
        conditionStr = Pattern.compile(
                ".*http:\\/\\/.*?\\.sina\\.com\\.cn\\/(.*?)\\.shtml.*"
        );
        folder = "sina";
    }

    @Override
    public String transformUrl(String line) {
        Matcher mt = conditionStr.matcher(line);
        mt.lookingAt();
        return "http://news.sina.com.cn/" + mt.group(1) + ".shtml";
    }
}
