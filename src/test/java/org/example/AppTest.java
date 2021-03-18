package org.example;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.junit.Test;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;

public class AppTest 
{
    @Test
    public void selenideTrendyol() throws  Exception {
        Configuration.browser = "Chrome";
        Configuration.startMaximized = true;
        Configuration.timeout = 10000;

        //pop up ı kapatma
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--disable-notifications");
        Configuration.browserCapabilities= opt;

        open("http://www.trendyol.com");
        $(".fancybox-item").click();
        $(".vn-close").click();
        $(".search-box").setValue("Bilgisayar").pressEnter();

        //$(".srch-ttl-cntnr-wrppr").click();


        Assert.assertEquals("Bilgisayar",$(By.cssSelector(".dscrptn>h1")).getText());

        //$$ ile bir listeyi çeker
        $$(By.className("p-card-wrppr")).shouldHave(size(24));
        //acılan sayfada 24 tane pc oldugunu varsayar
        $$(By.className("p-card-img")).shouldHave(CollectionCondition.sizeGreaterThan(0));

        $(By.className("p-card-wrppr"),4).click();

        int handles = getWebDriver().getWindowHandles().size();
        switchTo().window(handles-1);
        $(By.className("add-to-bs-tx")).click();
       // $(By.className("add-to-bs-tx-sc")).shouldHave(text("Sepete Eklendi"));

        Assert.assertEquals("Sepete Eklendi",$(By.className("add-to-bs-tx-sc")).getText());
        String productAmount = $(By.className("prc-slg")).getText();

        Assert.assertEquals("Giriş Yap",$(By.cssSelector(".link-text")).getText());

       // $(By.className("link-text")).click();
       /* $(By.className("account-user")).findElement();
        $(By.className("login-button")).click();*/

        $(By.cssSelector(".link.account-user")).click();
        Configuration.timeout = 10000;

        $(By.id("login-email")).setValue("cs@gmail.com");
        $(By.id("login-password-input")).setValue("*******");
        $(By.cssSelector(".q-primary.q-fluid.q-button-medium.q-button.submit")).click();
        Configuration.timeout = 10000;
        //Assert.assertEquals("Hesabım",$(By.cssSelector(".link-text")).getText());

        $(By.cssSelector(".link.account-basket")).click();
        Assert.assertTrue(productAmount.contains($(By.className("pb-basket-item-price")).getText()));
    }
}
