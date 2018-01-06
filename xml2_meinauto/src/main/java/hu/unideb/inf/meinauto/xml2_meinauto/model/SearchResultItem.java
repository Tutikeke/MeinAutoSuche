package hu.unideb.inf.meinauto.xml2_meinauto.model;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
	propOrder = {
		"name",
		"fahrzeugtype",
		"minRabatt",
		"maxRabatt",
		"preis",
		"image"
	}
)

public class SearchResultItem {

	@XmlElement
	private String name;
	
	@XmlElement
	private String fahrzeugtype;

	@XmlElement
	private double minRabatt;

	@XmlElement
	private double maxRabatt;

	@XmlElement
	private Price preis;

	@XmlElement
	private String image;
	
	public String getFahrzeugtype() {
		return fahrzeugtype;
	}
	public void setFahrzeugtype(String fahrzeugtype) {
		this.fahrzeugtype = fahrzeugtype;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}

	public Price getPrice() {
		return preis;
	}

	public void setPrice(Price price) {
		this.preis = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@XmlAttribute(required = true)
	private String uri;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public double getMinRabatt() {
		return minRabatt;
	}

	public void setMinRabatt(double minRabatt) {
		this.minRabatt = minRabatt;
	}

	public double getMaxRabatt() {
		return maxRabatt;
	}

	public void setMaxRabatt(double maxRabatt) {
		this.maxRabatt = maxRabatt;
	}

	public SearchResultItem() {
	}

	public SearchResultItem(String uri, String name, String fahrzeugtype) {
		this.uri = uri;
		this.name = name;
		this.fahrzeugtype = fahrzeugtype;
	}
	
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public static void main(String[] args) {
		SearchResultItem item = new SearchResultItem();
		item.setUri("http://www.meinauto.de/seat/neuwagen/43-leon/angebote/leon-st");
		item.setName("Seat Leon ST");
		item.setFahrzeugtype("kombi");
		System.out.println(item);
		try {
			hu.unideb.inf.jaxb.JAXBUtil.toXML(item, System.out);
		} catch(javax.xml.bind.JAXBException e) {
			e.printStackTrace();
		}
	}
}
