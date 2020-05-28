package support;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.Map;

public class RestWrapper {
    private String baseUrl = "https://skryabin.com/recruit/api/v1/";
    private static String loginToken;

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String JSON = "application/json";
    public static final String AUTH = "Authorization";

    public void login(Map<String,String> user){
        //preparation
        RequestSpecification request = RestAssured
                .given()
                .log().all()
                .baseUri(baseUrl)
                .basePath("login")
                .header(CONTENT_TYPE, JSON)
                .body(user);
        //exequte
        Response response = request
                .when()
                .post();
        //verify and extract
        Map<String,Object> result = response
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getMap("");
        loginToken = "Bearer " +  result.get("token");
        System.out.println(loginToken);
    }

    public Map<String, Object> createPosition(Map<String,String> position){
        //prepare
        RequestSpecification request = RestAssured
                .given()
                .log().all()
                .baseUri(baseUrl)
                .basePath("positions")
                .header(CONTENT_TYPE, JSON)
                .header(AUTH, loginToken )
                .body(position);
        //execute
        Response response = request
                .when()
                .post();
        //verify and extract
        Map<String, Object> result = response
                .then()
                .log().all()
                .statusCode(201)
                .extract()
                .jsonPath()
                .getMap("");
        return result;
    }
    public List<Map<String, Object>> getPositions(){
        //prepare
        RequestSpecification request = RestAssured
                .given()
                .log().all()
                .baseUri(baseUrl)
                .basePath("positions");
        //execute
        Response response = request
                .when()
                .get();
        List<Map<String, Object>> result = response
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("");
        return result;

    }

}
