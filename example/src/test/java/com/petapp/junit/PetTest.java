package com.petapp.junit;

import com.petapp.cucumber.serenity.PetSerenitySteps;
import com.petapp.model.Category;
import com.petapp.model.Tag;
import com.petapp.testbase.TestBase;
import com.petapp.utils.ReusableSpecification;
import com.petapp.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasValue;
import static org.junit.Assert.assertThat;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PetTest extends TestBase {
    @Steps
    PetSerenitySteps petSerenitySteps;

    public static Integer id = TestUtils.getRandomNumber();
    public static Category category = new Category(TestUtils.getRandomNumber(), "myDogs");
    public static List<Tag> tags = Arrays.asList(new Tag(TestUtils.getRandomNumber(), "dog"));
    public static List<String> photoUrls = Arrays.asList("urls.urls");
    public static String name = "utiputi" + TestUtils.getRandomValue();
    public static String status = "available";


    @Title("Create new pet POST method")
    @Test
    public void test1() {
        petSerenitySteps.createPet(id, category, tags, name, status, photoUrls)
                .statusCode(200)
                .spec(ReusableSpecification.getGenericResponseSpec());
    }

    @Title("Retrieve created pet GET method")
    @Test
    public void test2() {
        HashMap<String, Object> value = petSerenitySteps.getPetByName(name);
        assertThat(value, hasValue(name));
        id = (Integer) value.get("id");
    }

    @Title("Update pet information PUT method")
    @Test
    public void test3() {
        name = name + "_Updated";
        petSerenitySteps.updatePet(id, category, tags, name, status, photoUrls)
                .statusCode(200);

        HashMap<String, Object> value = petSerenitySteps.getPetByName(name);
        assertThat(value, hasValue(name));
    }

    @Title("Delete pet DELETE method")
    @Test
    public void test4() {
        petSerenitySteps.deletePet(id);
        petSerenitySteps.getPetById(id)
                .statusCode(404);
    }
}
