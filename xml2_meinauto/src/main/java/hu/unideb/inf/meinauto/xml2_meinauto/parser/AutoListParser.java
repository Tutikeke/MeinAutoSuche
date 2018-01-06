package hu.unideb.inf.meinauto.xml2_meinauto.parser;

import hu.unideb.inf.meinauto.xml2_meinauto.model.Auto;
import hu.unideb.inf.meinauto.xml2_meinauto.model.Price;
import hu.unideb.inf.meinauto.xml2_meinauto.model.SearchResultItem;
import hu.unideb.inf.meinauto.xml2_meinauto.model.SearchResults;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class AutoListParser {
    private static Logger logger = LoggerFactory.getLogger(AutoParser.class);

    public AutoListParser() {
    }

    public SearchResults parse(String url) throws IOException {
        Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
        SearchResults results = parse(doc);
        return results;
    }

    private SearchResults parse(Document doc) {
        List<SearchResultItem> items=new LinkedList<SearchResultItem>();
        
        Elements elements = doc.select("div.vehicle-active>div>div>article[itemprop=itemListElement]");
        
        int i=1;
        
        for (Element e:elements){
        	
            SearchResultItem item = new SearchResultItem();
            
            String name = e.select("div.vehicle-item-title > h3[itemprop=name] > a[itemprop=url]").text().trim();
            
            item.setName(name);
            
            String uri = e.select("div.vehicle-item-title > h3[itemprop=name] > a[itemprop=url]").get(0).attr("abs:href");
            
            item.setUri(uri);
            
            String fahrzeugtype = e.select("div.vehicle-body-types > div > span.vehicle-body-type").text().trim().replaceAll(" ","/");
            
            item.setFahrzeugtype(fahrzeugtype);
            
            Double minimalR = Double.parseDouble(e.select("div.vehicle-price > div.vehicle-price-discount-min ").text().split(" ")[3].replaceAll(",","."));
            
            item.setMinRabatt(minimalR);
            
            Double maximalR = Double.parseDouble(e.select("div.vehicle-price > div.vehicle-price-discount-max ").text().split(" ")[3].replaceAll(",","."));
            
            item.setMaxRabatt(maximalR);
            
            
            item.setPrice(new Price(Double.parseDouble(e.select("div.vehicle-price > div.vehicle-price-retail ").text().split(" ")[2].replaceAll(",",".").replaceAll("\\.","")),e.select("div.vehicle-price > div.vehicle-price-retail > s > abbr").get(0).attr("title")));
        
            String img = e.select("div.vehicle-image > a > img").get(0).attr("src");
            
            item.setImage(img);
            
            logger.info(e.text()+" "+(++i));
            
            items.add(item);
        }


        return new SearchResults(items.size(),1,50,items);
    }

}
