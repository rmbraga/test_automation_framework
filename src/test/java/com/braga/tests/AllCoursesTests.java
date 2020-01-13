package com.braga.tests;

import com.braga.base.BaseTest;
import com.braga.pages.CategoryFilterPage;
import com.braga.pages.SearchBarPage;
import com.braga.utilities.Constants;
import com.braga.utilities.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AllCoursesTests extends BaseTest {

    @DataProvider(name = "verifySearchCourseData")
    public Object[][] getVerifySearchCoursesData() {
        Object[][] testData = ExcelUtility.getTestData("verify_search_course");
        return testData;
    }

    @BeforeClass
    public void setUp() {
        pNavigation = pLogin.signInWith("test@email.com", "abcabc");
        ExcelUtility.setExcelFile(Constants.EXCEL_FILE, "AllCoursesTests");
    }

    @Test(dataProvider = "verifySearchCourseData")
    public void verifySearchCourse(String courseName) {
        pSearch = new SearchBarPage(driver);

        pNavigation.allCourses();
        //pResults = pSearch.course("rest api");
        pResults = pSearch.course(courseName);
        boolean searchResult = pResults.verifySearchResult();
        Assert.assertTrue(searchResult);
    }

    @Test(enabled = false)
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
