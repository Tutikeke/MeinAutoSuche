package hu.unideb.inf.meinauto.xml2_meinauto.model;

import javax.xml.bind.annotation.XmlRegistry;


@XmlRegistry
public class ObjectFactory {
	
	public Auto createAuto() {
		return new Auto();
	}
	public SearchResults createSearchResults() { return new SearchResults();}
	public SearchResultItem createSearchResultItem(){ return new SearchResultItem();}

}
