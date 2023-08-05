package com.petapp.junit;

import com.petapp.cucumber.serenity.PetSerenitySteps;
import com.petapp.model.Category;
import com.petapp.model.Tag;
import com.petapp.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.Arrays;
import java.util.List;

@Concurrent
@UseTestDataFrom("testdata/petinfo.csv")
@RunWith(SerenityParameterizedRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CreatePetDataDriven extends TestBase {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(String photoUrls) {
        this.photoUrls = photoUrls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String id;
    private String categoryId;
    private String categoryName;
    private String tagId;
    private String tagName;
    private String photoUrls;
    private String name;
    private String status;
    @Steps
    PetSerenitySteps petSerenitySteps;

    @Title("Data Driven test for adding multiple pets")
    @Test
    public void createMultiplePets() {
        Category category = new Category(Integer.valueOf(categoryId), categoryName);
        List<Tag> tags = Arrays.asList(new Tag(Integer.valueOf(tagId), tagName));
        List<String> photoUrl = Arrays.asList(photoUrls);
        petSerenitySteps.createPet(Integer.valueOf(id), category, tags, name, status, photoUrl)
                .statusCode(200);
    }

    @Title("Data Driven test for deleting multiple pets")
    @Test
    public void deleteMultiplePets() {
        petSerenitySteps.deletePet(Integer.valueOf(id));
    }
}
