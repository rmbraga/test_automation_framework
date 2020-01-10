package com.braga.pages;

import com.braga.base.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CategoryFilterPage extends BasePage {

    public CategoryFilterPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        js = (JavascriptExecutor) driver;
    }

    /***
     * Variables
     * URL
     * Title
     */
    public WebDriver driver;
    private JavascriptExecutor js;
    private String CATEGORY_DROPDOWN = "xpath=>(//div[contains(@class,'course-filter')])[1]//button";
    private String CATEGORY_OPTION = "xpath=>//a[@href='/courses/category/%s']";

    /***
     * Methods
     */

    public void clickCategoryDropdown() {
        // Find category dropdown
        elementClick(CATEGORY_DROPDOWN, "Category Dropdown");
    }

    public WebElement findCategory(String categoryName) {
        clickCategoryDropdown();
        // Wait for the element to be clickable
        WebElement categoryOption = waitForElementToBeClickable(
                String.format(CATEGORY_OPTION, categoryName), 15);
        return categoryOption;
    }

    public ResultsPage select(String categoryName) {
        WebElement categoryOption = findCategory(categoryName);
        // Used JavaScript click because this element was having issues with usual click method
        javascriptClick(categoryOption, "Category Option");
        return new ResultsPage(driver);
    }

    public int findCoursesCount(String categoryName) {
        WebElement categoryOption = findCategory(categoryName);
        // Get category text
        String categoryText = getText(categoryOption, "Category Option");
        // Split on ( symbol
        // Example: Software IT (2)
        // Value of arrayTemp[0] ->Software IT
        // Value of arrayTemp[1] ->2)
        String[] arrayTemp = categoryText.split("\\(");
        // Split arrayTemp[1] on ) symbol
        // Value of [0] ->2
        String courseCountString = arrayTemp[1].split("\\)")[0];
        int courseCount = Integer.parseInt(courseCountString);
        // Click the dropdown again to close the menu
        clickCategoryDropdown();
        return courseCount;
    }
}
