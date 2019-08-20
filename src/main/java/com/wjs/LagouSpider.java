package com.wjs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class LagouSpider {
    public static void main(String[] args) {
        System.getProperty("webdriver.chrome.driver", "D:/chromedriver/chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.lagou.com/zhaopin/Java/?labelWords=label");

        clickOptions(webDriver, "工作经验", "应届毕业生");
        clickOptions(webDriver, "学历要求", "本科");
        clickOptions(webDriver, "融资阶段", "不限");
        clickOptions(webDriver, "公司规模", "不限");
        clickOptions(webDriver, "行业领域", "移动互联网");

        jobElementByPage(webDriver);

    }

    private static void jobElementByPage(WebDriver webDriver) {
        List<WebElement> jobElements = webDriver.findElements(By.className("list_item_top"));
        for (WebElement jobElement : jobElements) {
            WebElement moneyElement = jobElement.findElement(By.className("money"));
            WebElement companyElement = jobElement.findElement(By.className("company_name"));
            System.out.println(companyElement.getText() + "----" + moneyElement.getText());
        }
        WebElement nextPage = webDriver.findElement(By.className("pager_next"));
        if (!nextPage.getAttribute("class").contains("pager_next_disabled")) {
            nextPage.click();
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            jobElementByPage(webDriver);
        }
    }

    private static void clickOptions(WebDriver webDriver, String chosenTitle, String optionTitle) {
        WebElement chosenElement = webDriver.findElement(By.xpath("//li[@class='multi-chosen']//span[contains(text(),'" + chosenTitle + "')]"));
        WebElement optionElement = chosenElement.findElement(By.xpath("../a[contains(text(),'" + optionTitle + "')]"));
        optionElement.click();
    }
}
