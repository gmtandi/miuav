package org.codehaus.jackson.xc;

import com.fimi.soul.entity.User;
import com.tencent.mm.sdk.contact.RContact;
import java.lang.reflect.Type;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.SerializerBase;
import org.codehaus.jackson.smile.SmileConstants;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomElementJsonSerializer extends SerializerBase<Element> {
    public DomElementJsonSerializer() {
        super(Element.class);
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        JsonNode createSchemaNode = createSchemaNode("object", true);
        createSchemaNode.put(User.FN_NAME, createSchemaNode("string"));
        createSchemaNode.put("namespace", createSchemaNode("string", true));
        createSchemaNode.put("attributes", createSchemaNode("array", true));
        createSchemaNode.put("children", createSchemaNode("array", true));
        return createSchemaNode;
    }

    public void serialize(Element element, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        int i = 0;
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(User.FN_NAME, element.getTagName());
        if (element.getNamespaceURI() != null) {
            jsonGenerator.writeStringField("namespace", element.getNamespaceURI());
        }
        NamedNodeMap attributes = element.getAttributes();
        if (attributes != null && attributes.getLength() > 0) {
            jsonGenerator.writeArrayFieldStart("attributes");
            for (int i2 = 0; i2 < attributes.getLength(); i2++) {
                Attr attr = (Attr) attributes.item(i2);
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField(RContact.FAVOUR_CONTACT_SHOW_HEAD_CHAR, attr.getValue());
                jsonGenerator.writeStringField(User.FN_NAME, attr.getName());
                String namespaceURI = attr.getNamespaceURI();
                if (namespaceURI != null) {
                    jsonGenerator.writeStringField("namespace", namespaceURI);
                }
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();
        }
        NodeList childNodes = element.getChildNodes();
        if (childNodes != null && childNodes.getLength() > 0) {
            jsonGenerator.writeArrayFieldStart("children");
            while (i < childNodes.getLength()) {
                Node item = childNodes.item(i);
                switch (item.getNodeType()) {
                    case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                        serialize((Element) item, jsonGenerator, serializerProvider);
                        break;
                    case org.codehaus.jackson.org.objectweb.asm.Type.BYTE /*3*/:
                    case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                        jsonGenerator.writeStartObject();
                        jsonGenerator.writeStringField(RContact.FAVOUR_CONTACT_SHOW_HEAD_CHAR, item.getNodeValue());
                        jsonGenerator.writeEndObject();
                        break;
                    default:
                        break;
                }
                i++;
            }
            jsonGenerator.writeEndArray();
        }
        jsonGenerator.writeEndObject();
    }
}
