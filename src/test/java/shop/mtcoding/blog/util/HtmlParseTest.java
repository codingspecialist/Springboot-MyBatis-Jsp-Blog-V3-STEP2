package shop.mtcoding.blog.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

public class HtmlParseTest {

    @Test
    public void jsoup_test1() throws Exception{
        System.out.println("================================");
        Document doc = Jsoup.connect("https://en.wikipedia.org/").get();
        System.out.println(doc);
        System.out.println("================================");
        Elements newsHeadlines = doc.select("#mp-itn b a");
        for (Element headline : newsHeadlines) {
            System.out.println(headline.attr("title"));
            System.out.println(headline.absUrl("href"));
        }
    }

    @Test
    public void jsoup_test2(){
        String html = "<p>1</p><p><img src=\"data:image/png;base64,iVBORw0KG\"></p>";
        Document doc = Jsoup.parse(html);
        //System.out.println(doc);
        Elements els = doc.select("img");
        //System.out.println(els);
        if(els.size() == 0){
            // 임시 사진 제공해주기
            // 디비 thumnail -> /images/profile.jfif
        }else{
            Element el = els.get(0);
            String img = el.attr("src");
            System.out.println(img);
            // 디비 thumnail -> img
        }
    }

    @Test
    public void parse_test1(){
        String html = "<p>1</p><p><img src=\"data:image/png;base64,iVBORw0KG\"></p>";
        String tag = parseEL(html, "img");
        System.out.println(tag);
        String attr = parseAttr(tag, "src");
        System.out.println(attr);
    }

    private String parseEL(String html, String tag){
        String s1 = html.substring(html.indexOf(tag)-1);
        return s1.substring(0, s1.indexOf(">")+1);
    }
    private String parseAttr(String el, String attr){
        String s1 = el.substring(el.indexOf(attr));

        int begin = s1.indexOf("\"");
        int end = s1.indexOf("\"", 2);

        return s1.substring(begin+1, end);
    }


}
