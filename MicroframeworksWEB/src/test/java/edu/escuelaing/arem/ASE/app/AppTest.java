package edu.escuelaing.arem.ASE.app;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Map;

public class AppTest {

    @Test
    public void testStaticFilesLocation() {
        App.staticfiles("src/main/resources");
        assertEquals("src/main/resources", App.getStaticFilesLocation());
    }

    @Test
    public void testServiceRegistration() {
        App.get("/test", (req, resp) -> "Test Service");
        Map<String, Service> services = App.getServices();
        assertTrue(services.containsKey("/App/test"));
        assertEquals("Test Service", services.get("/App/test").getValue(new Request(null), new Response()));
    }

    @Test
    public void testHelloService() {
        App.get("/hello", (req, resp) -> {
            String name = req.getValues("name");
            return name != null && !name.isEmpty() ? "Hello " + name : "Hello World!";
        });

        Request requestWithoutName = new Request(null);
        Request requestWithName = new Request("name=Richi");

        assertEquals("Hello World!", App.getServices().get("/App/hello").getValue(requestWithoutName, new Response()));
        assertEquals("Hello Richi", App.getServices().get("/App/hello").getValue(requestWithName, new Response()));

    }

    @Test
    public void testPiService() {
        App.get("/pi", (req, resp) -> String.valueOf(Math.PI));
        assertEquals(String.valueOf(Math.PI), App.getServices().get("/App/pi").getValue(new Request(null), new Response()));
    }
}
