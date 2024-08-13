package tests;

import io.restassured.common.mapper.TypeRef;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.get;

class CatTest {

    @BeforeAll
    static void beforeAll() {
        baseURI = "https://cataas.com";
    }

    @Test
    @DisplayName("Сайт доступен")
    void checkStatus200() {
        get(baseURI)
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Параметр limit работает корректно")
    void checkLimitParam() {
        List<Cat> cats = get(baseURI + "/api/cats?limit=10")
                .then()
                .extract().as(new TypeRef<>() {
                });

        Assertions.assertEquals(cats.size(), 10);
    }

    @Test
    @DisplayName("Все коты имеют айдишник")
    void checkAllFieldsPresented() {
        List<Cat> cats = get(baseURI + "/api/cats?limit=10")
                .then()
                .extract().as(new TypeRef<>() {
                });

        //TODO проверить всех котов на наличие поля id
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Cat {
        String _id;
        String mimetype;
        String size;
        List<String> tags;
    }


}