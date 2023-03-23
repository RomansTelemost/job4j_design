package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.List;

@XmlRootElement(name = "document")
//@XmlSeeAlso({Person.class})
@XmlAccessorType(XmlAccessType.FIELD)
public class Document {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private boolean signed;

    @XmlElement
    private Person owner;

    @XmlElementWrapper(name = "signers")
    @XmlElement(name = "signer")
    private List<Person> signers;

    public Document() {
    }

    public Document(String name, boolean signed, Person owner, List<Person> signers) {
        this.name = name;
        this.signed = signed;
        this.owner = owner;
        this.signers = signers;
    }

    public static void main(String[] args) throws JAXBException {
        final Person owner = new Person(true, 30, new Contact("11-110"), "Owner", "Married");
        final Person signer1 = new Person(true, 31, new Contact("11-111"), "Signer", "Married");
        final Person signer2 = new Person(false, 32, new Contact("11-112"), "Signer", "Idle");
        final Document document = new Document("Cert", true, owner, List.of(signer1, signer2));

        JAXBContext context = JAXBContext.newInstance(Document.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(document, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
