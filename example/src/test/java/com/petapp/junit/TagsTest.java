package com.petapp.junit;

import com.petapp.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class TagsTest extends TestBase {

    @WithTag("feature:Negative")
    @Title("Incorrect HTTP method used")
    @Test
    public void invalidMethod() {
        SerenityRest
                .rest()
                .given()
                .when()
                .get("/pet")
                .then()
                .statusCode(405)
                .log()
                .all();
    }

    @WithTags({
            @WithTag("feature:SMOKE"),
            @WithTag("feature:Negative")
    })
    @Title("Invalid resource accessed")
    @Test
    public void invalidResource() {
        SerenityRest
                .rest()
                .given()
                .when()
                .get("/pets")
                .then()
                .statusCode(404)
                .log()
                .all();
    }

    @WithTags({
            @WithTag("feature:SMOKE"),
            @WithTag("feature:Positive")
    })
    @Title("Retrieve sold pets")
    @Test
    public void retrieveSoldPets() {
        SerenityRest
                .rest()
                .given()
                .when()
                .get("/pet/findByStatus?status=sold")
                .then()
                .statusCode(200)
                .log()
                .all();
    }
}
