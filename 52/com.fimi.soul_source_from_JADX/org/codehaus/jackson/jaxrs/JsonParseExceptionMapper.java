package org.codehaus.jackson.jaxrs;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.codehaus.jackson.JsonParseException;

@Provider
public class JsonParseExceptionMapper implements ExceptionMapper<JsonParseException> {
    public Response toResponse(JsonParseException jsonParseException) {
        return Response.status(Status.BAD_REQUEST).entity(jsonParseException.getMessage()).type("text/plain").build();
    }
}
