package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPageHelper extends PageBase {
    @FindBy(xpath = "//*[contains(@text,'Search Wikipedia')]" )
    WebElement searchField;

    @FindBy(id = "org.wikipedia:id/search_src_text")
    WebElement searchInput;

    @FindBy(id = "org.wikipedia:id/page_list_item_title")
    List<WebElement> articlesNamesList;

    public SearchPageHelper(WebDriver driver) {
        super(driver);
    }

    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(searchField,15);
    }

    public String getSearchFieldText(){
        return searchField.getText();
    }

    public void enterSearchText(String text) {
        searchField.click();
        waitUntilElementIsClickable(searchInput,10);
        searchInput.sendKeys(text);
        waitUntilAllElementsAreVisible(articlesNamesList,15);
        System.out.println("Articles quantity: "+ articlesNamesList.size());
    }

    public boolean existArticleInSearchResult(String article) {
        Boolean flag = false;
        for(WebElement element: articlesNamesList){
            if(element.getText().equals(article)) flag=true;
        }
        return flag;
    }

    public void openArticle(String article) {
        driver.findElement(By.xpath(xPathArticleName(article))).click();
    }

    private String xPathArticleName(String article){
        return "//*[@text='" + article +"']";
    }
}
