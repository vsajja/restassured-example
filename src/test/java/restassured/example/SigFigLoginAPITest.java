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

    @Test
    public void testLogin() {
        baseURI = "https://www.sigfig.com/l/v1/account/api/";
        String SIFIG_LOGIN_VALID_USERNAME = "PlatformAutomationAssignment0107";
        String SIFIG_LOGIN_VALID_PASSWORD = "Automation1234$";

        given().
                header("user-agent", "Mozilla/5.0").
                header("authority", "www.sigfig.com").
                contentType("application/x-www-form-urlencoded; charset=utf-8").
                formParam("userName", SIFIG_LOGIN_VALID_USERNAME).
                formParam("password", SIFIG_LOGIN_VALID_PASSWORD).
                formParam("verificationCode", "").
                formParam("userRemember", false).
                accept("application/json, text/plain, */*").
        when().
                post("/login").
        then().
                body("success.user.username", equalTo(SIFIG_LOGIN_VALID_USERNAME)).
                body("success.user.isAuthenticated", equalTo(true)).
                statusCode(200).log().all();
    }

}

