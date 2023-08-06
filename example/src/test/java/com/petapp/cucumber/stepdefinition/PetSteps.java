package com.petapp.cucumber.stepdefinition;

import com.petapp.cucumber.serenity.PetSerenitySteps;
import com.petapp.utils.ReusableSpecification;
import com.petapp.utils.TestUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class PetSteps {

    @Steps
    PetSerenitySteps petSerenitySteps;
    public static Integer id = TestUtils.getRandomNumber();
    public static String name = "RandomName" + TestUtils.getRandomValue();

    @Given("user searches for a list of pets by status {string}")
    public void userSearchesForPet(String status) {
        SerenityRest.rest()
                .given()
                .when()
                .get("/pet/findByStatus?status=" + status)
                .then()
                .statusCode(200);
    }

    @Given("user creates a new pet with categoryId {string} categoryName {string} tagId {string} tagName {string} photoUrls {string} status {string}")
    public void userAddsANewPet(String categoryId, String categoryName, String tagId, String tagName, String photoUrls, String status) {
        petSerenitySteps.createPet(String.valueOf(id), categoryId, categoryName, tagId, tagName, photoUrls, name, status).
                assertThat()
                .statusCode(200)
                .spec(ReusableSpecification.getGenericResponseSpec());
    }

    @Given("user updates a new pet with categoryId {string} categoryName {string} tagId {string} tagName {string} photoUrls {string} status {string}")
    public void userUpdatesPet(String categoryId, String categoryName, String tagId, String tagName, String photoUrls, String status) {
        name = name + "_Updated";
        petSerenitySteps.updatePet(String.valueOf(id), categoryId, categoryName, tagId, tagName, name, status, photoUrls)
                .statusCode(200);

        HashMap<String, Object> value = petSerenitySteps.getPetByName(name);
        assertThat(value, hasValue(name));
    }

    @Then("user verifies the new entry is added")
    public void userVerifyPresenceOfNewPet() {
        petSerenitySteps.getPetById(id)
                .statusCode(200);
    }

    @Given("user searches for an entry")
    public void searchEntry() {
        HashMap<String, Object> value = petSerenitySteps.getPetByName(name);
        assertThat(value, hasValue(name));
        id = (Integer) value.get("id");
    }

    @Then("user deletes the entry")
    public void deleteEntry() {
        petSerenitySteps.deletePet(id);
        petSerenitySteps.getPetById(id)
                .statusCode(404);
    }

    @Given("user tries to access an invalid resource")
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

    @Given("user tries to use an invalid method to access resource")
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
}