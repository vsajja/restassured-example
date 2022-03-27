package restassured.example;

import org.apache.http.entity.ContentType;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Simple test suite for the SigFig.com Login API
 * using rest-assured.
 *
 * @author vsajja
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SigFigLoginAPITest {
    public static String SIFIG_LOGIN_VALID_USERNAME = "PlatformAutomationAssignment0107";
    public static String SIFIG_LOGIN_VALID_PASSWORD = "Automation1234$";

    @BeforeClass
    public static void setUpClass() {
        baseURI = "https://www.sigfig.com/l/v1/account/api/";
    }

    @Test
    public void testLogin_InvalidUsernamePassword() {
        given().
            contentType(ContentType.APPLICATION_FORM_URLENCODED.getMimeType()).
            accept(ContentType.APPLICATION_JSON.getMimeType()).
            formParam("userName", SIFIG_LOGIN_VALID_USERNAME).
            formParam("password", "invalid_password").
        when().
            post("/login").
        then().
            statusCode(200).
            body("error._", containsString("The username and password you entered " +
                    "don't match our records.")).
        log().all();
    }

    @Test
    public void testLogin_MissingPassword() {
        given().
            contentType(ContentType.APPLICATION_FORM_URLENCODED.getMimeType()).
            accept(ContentType.APPLICATION_JSON.getMimeType()).
            formParam("userName", SIFIG_LOGIN_VALID_USERNAME).
        when().
            post("/login").
        then().
            statusCode(200).
            body("error.username", equalTo(null)).
            body("error.password", equalTo("Required")).
        log().all();
    }

    @Test
    public void testLogin_MissingUsernameOrPassword() {
        given().
            contentType(ContentType.APPLICATION_FORM_URLENCODED.getMimeType()).
            accept(ContentType.APPLICATION_JSON.getMimeType()).
        when().
            post("/login").
        then().
            statusCode(200).
            body("error.userName", equalTo("Required")).
            body("error.password", equalTo("Required")).
        log().all();
    }

    @Test
    public void testLogin_Valid() {
        given().
            contentType(ContentType.APPLICATION_FORM_URLENCODED.getMimeType()).
            accept(ContentType.APPLICATION_JSON.getMimeType()).
            accept(ContentType.APPLICATION_JSON.getMimeType()).
            formParam("userName", SIFIG_LOGIN_VALID_USERNAME).
            formParam("password", SIFIG_LOGIN_VALID_PASSWORD).
            formParam("verificationCode", "").
            formParam("userRemember", false).
        when().
            post("/login").
        then().
            statusCode(200).
            body("success.user.username", equalTo(SIFIG_LOGIN_VALID_USERNAME)).
            body("success.user.isAuthenticated", equalTo(true)).
            cookie("nv_ps_0"). // checks if the nv_ps_01 session cookie is set in the response
        log().all();
    }
}
