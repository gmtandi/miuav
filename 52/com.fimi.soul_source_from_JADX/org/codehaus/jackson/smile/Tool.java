package org.codehaus.jackson.smile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.smile.SmileGenerator.Feature;

public class Tool {
    public static final String SUFFIX = ".lzf";
    public final JsonFactory jsonFactory;
    public final SmileFactory smileFactory;

    public Tool() {
        this.jsonFactory = new JsonFactory();
        this.smileFactory = new SmileFactory();
        this.smileFactory.configure(Feature.CHECK_SHARED_NAMES, true);
        this.smileFactory.configure(Feature.CHECK_SHARED_STRING_VALUES, true);
        this.smileFactory.configure(Feature.ENCODE_BINARY_AS_7BIT, true);
        this.smileFactory.configure(Feature.WRITE_HEADER, true);
        this.smileFactory.configure(Feature.WRITE_END_MARKER, false);
        this.smileFactory.configure(SmileParser.Feature.REQUIRE_HEADER, false);
    }

    private void decode(InputStream inputStream) {
        JsonParser createJsonParser = this.smileFactory.createJsonParser(inputStream);
        JsonGenerator createJsonGenerator = this.jsonFactory.createJsonGenerator(System.out, JsonEncoding.UTF8);
        while (true) {
            if (createJsonParser.nextToken() == null && createJsonParser.nextToken() == null) {
                createJsonParser.close();
                createJsonGenerator.close();
                return;
            }
            createJsonGenerator.copyCurrentEvent(createJsonParser);
        }
    }

    private void encode(InputStream inputStream) {
        JsonParser createJsonParser = this.jsonFactory.createJsonParser(inputStream);
        JsonGenerator createJsonGenerator = this.smileFactory.createJsonGenerator(System.out, JsonEncoding.UTF8);
        while (createJsonParser.nextToken() != null) {
            createJsonGenerator.copyCurrentEvent(createJsonParser);
        }
        createJsonParser.close();
        createJsonGenerator.close();
    }

    private InputStream inputStream(String str) {
        if (str == null) {
            return System.in;
        }
        File file = new File(str);
        if (!file.exists()) {
            System.err.println("File '" + str + "' does not exist.");
            System.exit(1);
        }
        return new FileInputStream(file);
    }

    public static void main(String[] strArr) {
        new Tool().process(strArr);
    }

    private void process(String[] strArr) {
        Object obj;
        String str = null;
        if (strArr.length == 2) {
            obj = strArr[0];
            str = strArr[1];
        } else if (strArr.length == 1) {
            obj = strArr[0];
        } else {
            showUsage();
            obj = null;
        }
        if ("-e".equals(obj)) {
            encode(inputStream(str));
        } else if ("-d".equals(obj)) {
            decode(inputStream(str));
        } else if ("-v".equals(obj)) {
            verify(inputStream(str), inputStream(str));
        } else {
            showUsage();
        }
    }

    private void verify(InputStream inputStream, InputStream inputStream2) {
        String text;
        String text2;
        JsonParser createJsonParser = this.jsonFactory.createJsonParser(inputStream);
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream(4000);
        JsonGenerator createJsonGenerator = this.smileFactory.createJsonGenerator(byteArrayOutputStream, JsonEncoding.UTF8);
        while (createJsonParser.nextToken() != null) {
            createJsonGenerator.copyCurrentEvent(createJsonParser);
        }
        createJsonParser.close();
        createJsonGenerator.close();
        JsonParser createJsonParser2 = this.jsonFactory.createJsonParser(inputStream2);
        byte[] toByteArray = byteArrayOutputStream.toByteArray();
        JsonParser createJsonParser3 = this.smileFactory.createJsonParser(toByteArray);
        int i = 0;
        do {
            JsonToken nextToken = createJsonParser2.nextToken();
            if (nextToken != null) {
                JsonToken nextToken2 = createJsonParser3.nextToken();
                i++;
                if (nextToken != nextToken2) {
                    throw new IOException("Input and encoded differ, token #" + i + "; expected " + nextToken + ", got " + nextToken2);
                }
                text = createJsonParser2.getText();
                text2 = createJsonParser3.getText();
            } else {
                System.out.println("OK: verified " + i + " tokens (from " + toByteArray.length + " bytes of Smile encoded data), input and encoded contents are identical");
                return;
            }
        } while (text.equals(text2));
        throw new IOException("Input and encoded differ, token #" + i + "; expected text '" + text + "', got '" + text2 + "'");
    }

    protected void showUsage() {
        System.err.println("Usage: java " + getClass().getName() + " -e/-d [file]");
        System.err.println(" (if no file given, reads from stdin -- always writes to stdout)");
        System.err.println(" -d: decode Smile encoded input as JSON");
        System.err.println(" -e: encode JSON (text) input as Smile");
        System.err.println(" -v: encode JSON (text) input as Smile; read back, verify, do not write out");
        System.exit(1);
    }
}
