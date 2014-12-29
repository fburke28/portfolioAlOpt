//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.10.31 at 11:06:40 PM EDT 
//


package com.po.domain.ski;

import java.math.BigInteger;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.poi.domain package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PubDate_QNAME = new QName("", "pubDate");
    private final static QName _Link_QNAME = new QName("", "link");
    private final static QName _RegionName_QNAME = new QName("http://www.onthesnow.com/ots_rss_namespace/", "region_name");
    private final static QName _Copyright_QNAME = new QName("", "copyright");
    private final static QName _ResortId_QNAME = new QName("http://www.onthesnow.com/ots_rss_namespace/", "resort_id");
    private final static QName _Url_QNAME = new QName("", "url");
    private final static QName _SurfaceCondition_QNAME = new QName("http://www.onthesnow.com/ots_rss_namespace/", "surface_condition");
    private final static QName _Snowfall48HrMetric_QNAME = new QName("http://www.onthesnow.com/ots_rss_namespace/", "snowfall_48hr_metric");
    private final static QName _ResortRssLink_QNAME = new QName("http://www.onthesnow.com/ots_rss_namespace/", "resort_rss_link");
    private final static QName _Title_QNAME = new QName("", "title");
    private final static QName _OpenStaus_QNAME = new QName("http://www.onthesnow.com/ots_rss_namespace/", "open_staus");
    private final static QName _Description_QNAME = new QName("", "description");
    private final static QName _BaseDepth_QNAME = new QName("http://www.onthesnow.com/ots_rss_namespace/", "base_depth");
    private final static QName _BaseDepthMetric_QNAME = new QName("http://www.onthesnow.com/ots_rss_namespace/", "base_depth_metric");
    private final static QName _Language_QNAME = new QName("", "language");
    private final static QName _Snowfall48Hr_QNAME = new QName("http://www.onthesnow.com/ots_rss_namespace/", "snowfall_48hr");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.poi.domain
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Item }
     * 
     */
    public Item createItem() {
        return new Item();
    }

    /**
     * Create an instance of {@link Channel }
     * 
     */
    public Channel createChannel() {
        return new Channel();
    }

    /**
     * Create an instance of {@link Rss }
     * 
     */
    public Rss createRss() {
        return new Rss();
    }

    /**
     * Create an instance of {@link Image }
     * 
     */
    public Image createImage() {
        return new Image();
    }

    /**
     * Create an instance of {@link Guid }
     * 
     */
    public Guid createGuid() {
        return new Guid();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "pubDate")
    public JAXBElement<String> createPubDate(String value) {
        return new JAXBElement<String>(_PubDate_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "link")
    public JAXBElement<String> createLink(String value) {
        return new JAXBElement<String>(_Link_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.onthesnow.com/ots_rss_namespace/", name = "region_name")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createRegionName(String value) {
        return new JAXBElement<String>(_RegionName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "copyright")
    public JAXBElement<String> createCopyright(String value) {
        return new JAXBElement<String>(_Copyright_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.onthesnow.com/ots_rss_namespace/", name = "resort_id")
    public JAXBElement<BigInteger> createResortId(BigInteger value) {
        return new JAXBElement<BigInteger>(_ResortId_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "url")
    public JAXBElement<String> createUrl(String value) {
        return new JAXBElement<String>(_Url_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.onthesnow.com/ots_rss_namespace/", name = "surface_condition")
    public JAXBElement<String> createSurfaceCondition(String value) {
        return new JAXBElement<String>(_SurfaceCondition_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.onthesnow.com/ots_rss_namespace/", name = "snowfall_48hr_metric")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSnowfall48HrMetric(String value) {
        return new JAXBElement<String>(_Snowfall48HrMetric_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.onthesnow.com/ots_rss_namespace/", name = "resort_rss_link")
    public JAXBElement<String> createResortRssLink(String value) {
        return new JAXBElement<String>(_ResortRssLink_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "title")
    public JAXBElement<String> createTitle(String value) {
        return new JAXBElement<String>(_Title_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.onthesnow.com/ots_rss_namespace/", name = "open_staus")
    public JAXBElement<String> createOpenStaus(String value) {
        return new JAXBElement<String>(_OpenStaus_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "description")
    public JAXBElement<String> createDescription(String value) {
        return new JAXBElement<String>(_Description_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.onthesnow.com/ots_rss_namespace/", name = "base_depth")
    public JAXBElement<BigInteger> createBaseDepth(BigInteger value) {
        return new JAXBElement<BigInteger>(_BaseDepth_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.onthesnow.com/ots_rss_namespace/", name = "base_depth_metric")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createBaseDepthMetric(String value) {
        return new JAXBElement<String>(_BaseDepthMetric_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "language")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createLanguage(String value) {
        return new JAXBElement<String>(_Language_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.onthesnow.com/ots_rss_namespace/", name = "snowfall_48hr")
    public JAXBElement<BigInteger> createSnowfall48Hr(BigInteger value) {
        return new JAXBElement<BigInteger>(_Snowfall48Hr_QNAME, BigInteger.class, null, value);
    }

}
