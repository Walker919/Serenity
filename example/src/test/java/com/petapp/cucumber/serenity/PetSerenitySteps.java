package com.petapp.cucumber.serenity;

import com.petapp.model.Category;
import com.petapp.model.PetClass;
import com.petapp.model.Tag;
import com.petapp.utils.ReusableSpecification;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.List;

public class PetSerenitySteps {

    @Step("Create new pet with id {0}, name {3} and status {4}")
    public ValidatableResponse createPet(Integer id, Category category, List<Tag> tag, String name, String status
            , List<String> photoUrl) {

        PetClass pet = new PetClass();
        pet.setId(id);
        pet.setCategory(category);
        pet.setTags(tag);
        pet.setName(name);
        pet.setStatus(status);
        pet.setPhotoUrls(photoUrl);

        return SerenityRest.given()
                .spec(ReusableSpecification.getGenericRequestSpec())
                .when()
                .body(pet)
                .post("/pet")
                .then();
    }

    @Step("Retrieve created pet name {0}")
    public HashMap<String, Object> getPetByName(String name) {

        return SerenityRest.rest()
                .given()
                .when()
                .get("/pet/findByStatus?status=available")
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .path("findAll{it.name=='" + name + "'}.get(0)");
    }

    @Step("Update existing pet with id {0}, name {3} and status {4}")
    public ValidatableResponse updatePet(Integer id, Category category, List<Tag> tag, String name, String status
            , List<String> photoUrl) {

        PetClass pet = new PetClass();
        pet.setId(id);
        pet.setCategory(category);
        pet.setTags(tag);
        pet.setName(name);
        pet.setStatus(status);
        pet.setPhotoUrls(photoUrl);

        return SerenityRest.given()
                .spec(ReusableSpecification.getGenericRequestSpec())
                .when()
                .body(pet)
                .post("/pet")
                .then();
    }

    @Step("Delete pet by id {0}")
    public void deletePet(Integer id) {
        SerenityRest.rest()
                .given()
                .when()
                .delete("/pet/" + id);
    }

    @Step("Get pet by id {0}")
    public ValidatableResponse getPetById(Integer id) {
        return SerenityRest.rest()
                .given()
                .when()
                .get("/pet/" + id)
                .then();
    }
}
