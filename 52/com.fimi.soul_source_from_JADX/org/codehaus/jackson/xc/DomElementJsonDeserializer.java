package org.codehaus.jackson.xc;

import com.fimi.soul.entity.User;
import com.tencent.mm.sdk.contact.RContact;
import java.util.Iterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.deser.StdDeserializer;
import org.codehaus.jackson.node.ArrayNode;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DomElementJsonDeserializer extends StdDeserializer<Element> {
    private final DocumentBuilder builder;

    public DomElementJsonDeserializer() {
        super(Element.class);
        try {
            DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
            newInstance.setNamespaceAware(true);
            this.builder = newInstance.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException();
        }
    }

    public DomElementJsonDeserializer(DocumentBuilder documentBuilder) {
        super(Element.class);
        this.builder = documentBuilder;
    }

    public Element deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        return fromNode(this.builder.newDocument(), jsonParser.readValueAsTree());
    }

    protected Element fromNode(Document document, JsonNode jsonNode) {
        String valueAsText = jsonNode.get("namespace") != null ? jsonNode.get("namespace").getValueAsText() : null;
        String valueAsText2 = jsonNode.get(User.FN_NAME) != null ? jsonNode.get(User.FN_NAME).getValueAsText() : null;
        if (valueAsText2 == null) {
            throw new JsonMappingException("No name for DOM element was provided in the JSON object.");
        }
        Iterator elements;
        String valueAsText3;
        Element createElementNS = document.createElementNS(valueAsText, valueAsText2);
        JsonNode jsonNode2 = jsonNode.get("attributes");
        if (jsonNode2 != null && (jsonNode2 instanceof ArrayNode)) {
            elements = jsonNode2.getElements();
            while (elements.hasNext()) {
                jsonNode2 = (JsonNode) elements.next();
                valueAsText2 = jsonNode2.get("namespace") != null ? jsonNode2.get("namespace").getValueAsText() : null;
                valueAsText3 = jsonNode2.get(User.FN_NAME) != null ? jsonNode2.get(User.FN_NAME).getValueAsText() : null;
                valueAsText = jsonNode2.get(RContact.FAVOUR_CONTACT_SHOW_HEAD_CHAR) != null ? jsonNode2.get(RContact.FAVOUR_CONTACT_SHOW_HEAD_CHAR).getValueAsText() : null;
                if (valueAsText3 != null) {
                    createElementNS.setAttributeNS(valueAsText2, valueAsText3, valueAsText);
                }
            }
        }
        jsonNode2 = jsonNode.get("children");
        if (jsonNode2 != null && (jsonNode2 instanceof ArrayNode)) {
            elements = jsonNode2.getElements();
            while (elements.hasNext()) {
                jsonNode2 = (JsonNode) elements.next();
                valueAsText2 = jsonNode2.get(User.FN_NAME) != null ? jsonNode2.get(User.FN_NAME).getValueAsText() : null;
                valueAsText3 = jsonNode2.get(RContact.FAVOUR_CONTACT_SHOW_HEAD_CHAR) != null ? jsonNode2.get(RContact.FAVOUR_CONTACT_SHOW_HEAD_CHAR).getValueAsText() : null;
                if (valueAsText3 != null) {
                    createElementNS.appendChild(document.createTextNode(valueAsText3));
                } else if (valueAsText2 != null) {
                    createElementNS.appendChild(fromNode(document, jsonNode2));
                }
            }
        }
        return createElementNS;
    }
}
