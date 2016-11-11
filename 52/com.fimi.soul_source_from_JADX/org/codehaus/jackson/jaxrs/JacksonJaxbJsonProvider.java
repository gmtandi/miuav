package org.codehaus.jackson.jaxrs;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.ext.Provider;
import org.codehaus.jackson.map.ObjectMapper;

@Consumes({"application/json", "text/json"})
@Produces({"application/json", "text/json"})
@Provider
public class JacksonJaxbJsonProvider extends JacksonJsonProvider {
    public static final Annotations[] DEFAULT_ANNOTATIONS;

    static {
        DEFAULT_ANNOTATIONS = new Annotations[]{Annotations.JACKSON, Annotations.JAXB};
    }

    public JacksonJaxbJsonProvider() {
        this(null, DEFAULT_ANNOTATIONS);
    }

    public JacksonJaxbJsonProvider(ObjectMapper objectMapper, Annotations[] annotationsArr) {
        super(objectMapper, annotationsArr);
    }

    public JacksonJaxbJsonProvider(Annotations... annotationsArr) {
        this(null, annotationsArr);
    }
}
