
package br.inatel.dm112.client.billet.stub;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the dm112 package. 
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

    private final static QName _BilletGenData_QNAME = new QName("dm112", "billetGenData");
    private final static QName _GenerateBillet_QNAME = new QName("dm112", "generateBillet");
    private final static QName _GenerateBilletResponse_QNAME = new QName("dm112", "generateBilletResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: dm112
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BilletGenData }
     * 
     */
    public BilletGenData createBilletGenData() {
        return new BilletGenData();
    }

    /**
     * Create an instance of {@link GenerateBillet }
     * 
     */
    public GenerateBillet createGenerateBillet() {
        return new GenerateBillet();
    }

    /**
     * Create an instance of {@link GenerateBilletResponse }
     * 
     */
    public GenerateBilletResponse createGenerateBilletResponse() {
        return new GenerateBilletResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BilletGenData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "dm112", name = "billetGenData")
    public JAXBElement<BilletGenData> createBilletGenData(BilletGenData value) {
        return new JAXBElement<BilletGenData>(_BilletGenData_QNAME, BilletGenData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateBillet }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "dm112", name = "generateBillet")
    public JAXBElement<GenerateBillet> createGenerateBillet(GenerateBillet value) {
        return new JAXBElement<GenerateBillet>(_GenerateBillet_QNAME, GenerateBillet.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateBilletResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "dm112", name = "generateBilletResponse")
    public JAXBElement<GenerateBilletResponse> createGenerateBilletResponse(GenerateBilletResponse value) {
        return new JAXBElement<GenerateBilletResponse>(_GenerateBilletResponse_QNAME, GenerateBilletResponse.class, null, value);
    }

}
