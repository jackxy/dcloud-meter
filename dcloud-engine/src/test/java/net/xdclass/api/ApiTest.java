package net.xdclass.api;

import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.xdclass.util.ApiRegexpUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.Matchers.equalTo;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@SpringBootTest
public class ApiTest {



    @Test
    public void testRegexp(){

        String url = "https://xdclass.net/get/detail?name={{name}}&token={{token}}";
        Pattern pattern = Pattern.compile(ApiRegexpUtil.REGEXP);
        Matcher matcher = pattern.matcher(url);
        while (matcher.find()){
            String group = matcher.group(1);
            System.out.println(group);
            String byName = ApiRegexpUtil.byName(group);
            System.out.println(byName);
            url = url.replaceAll(byName,"xdclass");
            System.out.println(url);
        }

    }



    @Test
    public void testBase(){
        RestAssured.given()
                //.param("id",1)
                .queryParam("id",1)
                .when()
                .get("http://127.0.0.1:8082/api/v2/test/detail")
                .then()
                .log()
                .all()
                .statusCode(200);
    }


    @Test
    public void testPostForm(){

        RestAssured.given()
                .formParam("mail","xdclass")
                .formParam("pwd","123456")
                .when()
                .post("http://127.0.0.1:8082/api/v2/test/login_form")
                .then()
                .log()
                .all()
                .statusCode(200);

    }


    @Test
    public void testPostJsonHeader(){
        Map<String,String> map = new HashMap<>();
        map.put("title","小滴课堂云测大课");
        RestAssured.given()
                .header("token","3a74fbbeb3114b38bc0f5b61296e8835")
                //.header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .body(map)
                .when()
                .post("http://127.0.0.1:8082/api/v2/test/buy")
                .then()
                .log()
                .all()
                .statusCode(200);

    }


    @Test
    public void testFile(){

        RestAssured.given()
                .multiPart(new File("/Users/xdclass/Desktop/测试jmx/id.csv"))
                .when()
                .post("http://127.0.0.1:8082/api/v2/test/upload")
                .then()
                .log()
                .all()
                .statusCode(200);

    }



    @Test
    public void testAssert(){
        Map<String,String> map = new HashMap<>();
        map.put("title","小滴课堂云测大课");
        RestAssured.given()
                .header("token","3a74fbbeb3114b38bc0f5b61296e8835")
                //.header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .body(map)
                .when()
                .post("http://127.0.0.1:8082/api/v2/test/buy")
                .then()
                .log()
                .all()
                .statusCode(200)
                .body("code",equalTo(0));

    }


    @Test
    public void testResponse(){
        Map<String,String> map = new HashMap<>();
        map.put("title","小滴课堂云测大课");
        Response response = RestAssured.given()
                .header("token", "3a74fbbeb3114b38bc0f5b61296e8835")
                //.header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .body(map)
                .when()
                .post("http://127.0.0.1:8082/api/v2/test/buy")
                .then()
                .log()
                .all()
                .statusCode(200).extract().response();

        //Object data = response.jsonPath().get("data");
        Object data = response.jsonPath().get("data.title");

        System.out.println(data);

    }



    @Test
    public void testJsonPath(){
        Map<String,String> map = new HashMap<>();
        map.put("title","小滴课堂云测大课");
        Response response = RestAssured.given()
                .header("token", "55d0467289b14f04a9cd9fd086f9011b")
                //.header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .body(map)
                .when()
                .post("http://127.0.0.1:8082/api/v2/test/buy")
                .then()
                .log()
                .all()
                .statusCode(200).extract().response();
        String responseString = response.asString();
        System.out.println(responseString);
        String read = JsonPath.read(responseString, "$.data.title");
        System.out.println(read);
    }



    @Test
    public void testData(){
            String input = "{\"code\":0,\"data\":\"27c997b26c5b446098610e87d1d907cb\"}";
            String patternString = "\"data\":\"(.*?)\"";

            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(input);

            if (matcher.find()) {
                System.out.println("Data value: " + matcher.group(1));
            } else {
                System.out.println("No match found");
            }
    }

}
