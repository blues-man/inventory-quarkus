package com.redhat.cloudnative;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class InventoryResourceTest {

    @Test
    public void testQuarkusTshirtQty() {
        given()
          .when().get("/api/inventory/329299")
          .then()
             .statusCode(200)
             .body(is("{\"id\":329299,\"quantity\":35}"));
    }

    @Test
    public void testFedoraQty() {
        given()
          .when().get("/api/inventory/100000")
          .then()
             .statusCode(200)
             .body(is("{\"id\":100000,\"quantity\":0}"));
    }

    @Test
    public void testUpdateFedoraQty() {
        given()
          .header("Content-type", "application/json")
          .and()
          .body("{\"quantity\": \"1\"}")
          .when().put("/api/inventory/100000/")
            .then()
              .statusCode(200)
              .body(is("{\"id\":100000,\"quantity\":1}"));
    }

    @Test
    public void testUpdateFedoraQtyNoItem() {
        given()
          .header("Content-type", "application/json")
          .and()
          .body("{\"quantity\": \"1\"}")
          .when().put("/api/inventory/100001/")
            .then()
              .statusCode(404);
    }

}
