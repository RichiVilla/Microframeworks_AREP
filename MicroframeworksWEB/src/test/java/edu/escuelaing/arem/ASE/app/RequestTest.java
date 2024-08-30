package edu.escuelaing.arem.ASE.app;

import static org.junit.Assert.*;
import org.junit.Test;

public class RequestTest {

    @Test
    public void testParseQueryString() {
        Request req = new Request("name=Richi&age=23");
        assertEquals("Richi", req.getValues("name"));
        assertEquals("23", req.getValues("age"));
    }

    @Test
    public void testEmptyQueryString() {
        Request req = new Request("");
        assertNull(req.getValues("name"));
    }

    @Test
    public void testNullQueryString() {
        Request req = new Request(null);
        assertNull(req.getValues("name"));
    }
}
