package com.braga.tests;

import com.braga.base.BaseTest;
import com.braga.pages.CategoryFilterPage;
import com.braga.pages.SearchBarPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AllCoursesTests extends BaseTest {

    @BeforeClass
    public void setUp() {
        pNavigation = pLogin.signInWith("test@email.com", "abcabc");
    }

    @Test
    public void verifySearchCourse() {
        pSearch = new SearchBarPage(driver);

        pNavigation.allCourses();
        pResults = pSearch.course("rest api");
        boolean searchResult = pResults.verifySearchResult();
        Assert.assertTrue(searchResult);
    }

    @Test
    public void filterByCategory() {
        pCategory = new CategoryFilterPage(driver);

        pNavigation.allCourses();
        pResults = pCategory.select("Software IT");
        int count = pCategory.findCoursesCount("Software IT");
        boolean filterResult = pResults.verifyFilterCourseCount(count);
        Assert.assertTrue(filterResult);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
