package restassured.example;

import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

/**
 * Example test suite for the Login API on SigFig.com
 * using restassured.
 *
 * @author vsajja
 */
public class SigFigLoginAPITest {
    @Test
    public void testGetAccountInfo() {
        baseURI = "https://www.sigfig.com/l/account/api";

        get("/info").
                then().
                    statusCode(200).
                    body("info.username", equalTo(null)).
                    body("info.isAuthenticated", equalTo(false)).
                log().all();
    }
}

