package hu.unideb.inf.meinauto.xml2_meinauto.main;

import hu.unideb.inf.meinauto.xml2_meinauto.model.Auto;
import hu.unideb.inf.meinauto.xml2_meinauto.model.SearchResults;
import hu.unideb.inf.meinauto.xml2_meinauto.parser.AutoListParser;
import hu.unideb.inf.meinauto.xml2_meinauto.parser.AutoParser;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutoListResource extends ServerResource {
    private static Logger logger = LoggerFactory.getLogger(AutoResource.class);

    @Get("xml|json")
    public SearchResults represent(){
        logger.info("get marka");
        String marka = getAttribute("marka");
        logger.info("marka got");
        SearchResults results=null;

        try {
            AutoListParser aParser = new AutoListParser();
            //AutoParser aParser = new AutoParser();
            String uri = "http://www.meinauto.de/"+ marka;
            System.out.println("URI!!!!!!!!!: "+uri);

            results = aParser.parse(uri);
        } catch (Exception e) {
            logger.error("{} cause: {}",e.getMessage(),e.getCause());
            throw new ResourceException(404);
        }

        return results;
    }
}
