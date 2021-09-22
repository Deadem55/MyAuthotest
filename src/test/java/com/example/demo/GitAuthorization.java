package com.example.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class GitAuthorization {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\deade\\IdeaProjects\\demo\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);    // Выставить время ожидания (количество, единица измерения)
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.manage().window().setSize(new Dimension(1000, 800));
//        driver.manage().window().maximize();
        driver.get("https://github.com/");

        WebElement menu = driver.findElement(By.xpath("//div/button[@class='js-details-target btn-link d-lg-none mt-1']"));
        WebElement signIn = driver.findElement(By.xpath("//a[@class='HeaderMenu-link flex-shrink-0 no-underline']"));

        menu.click();
        signIn.click();
        WebElement login = driver.findElement(By.xpath("//input[@id='login_field']"));
        WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
        WebElement buttonSign = driver.findElement(By.xpath("//input[@name='commit']"));

        login.sendKeys("testAddres@gmail.com");
        password.sendKeys("PaSsWoRd1234");
        System.out.println("В поле логина находился текст:" + login.getAttribute("value"));
        System.out.println("В поле пароля находился текст:" + password.getAttribute("value"));
        buttonSign.submit();
        WebElement messageBox = driver.findElement(By.xpath("//body/div/main/div/div[2]/div/div[@class='container-lg px-2']"));
        System.out.println("Статус авторизации: " + messageBox.getText());

        if (messageBox.getText().equals("Incorrect username or password")) {
            System.out.println("Autotest failed authorisation: Success!");
        } else System.out.println("Autotest failed authorisation: Failed!");
        System.out.println();

        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
        System.out.println();

        WebElement createAccount = driver.findElement(By.xpath("//html/body/div/main/div/p/a[@data-ga-click='Sign in, switch to sign up']"));
        createAccount.click();

        // TODO    // (сделать ожидание для момента когда текст пользователю появится (пробовал делать ожидание появления кнопки) Сделал!!! ^_^
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//html/body/div[4]/main/div[2]//button[contains(@class, 'js-continue-button signup-continue-button form-control')]")));
        WebElement messageForUser = driver.findElement(By.xpath("//typing-effect[@class='js-signup-typed-welcome']"));
        System.out.println("Пользователю выводится приветственное сообщение: \n" + messageForUser.getText());
        if (messageForUser.getText().equals("Welcome to GitHub!\n" +
                "Let’s begin the adventure")) {
            System.out.println("Test Success!");
        } else System.out.println("Test Failed!");

        WebElement username = driver.findElement(By.xpath("//input[@name='user[email]']"));
        username.sendKeys("yasoba6178@godpeed.com");
        WebElement emailButton = driver.findElement(By.xpath("//button[contains(@class, 'js-continue-button signup-continue-button form-control')]"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'js-continue-button signup-continue-button form-control')]")));
        emailButton.click();

        WebElement newPassword = driver.findElement(By.xpath("//input[@id='password']"));
        newPassword.sendKeys("SfKwIp24!");
        WebElement passwordButton = driver.findElement(By.xpath("//div[2]/div/button[contains(@class, 'js-continue-button signup-continue-button form-control')]"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[2]/div/button[contains(@class, 'js-continue-button signup-continue-button form-control')]")));
        passwordButton.click();

        WebElement newLogin = driver.findElement(By.xpath("//input[@id='login']"));
        newLogin.sendKeys("AuTstUser");
        WebElement userButton = driver.findElement(By.xpath("//div[3]/div/button[contains(@class, 'js-continue-button signup-continue-button form-control')]"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[3]/div/button[contains(@class, 'js-continue-button signup-continue-button form-control')]")));
        userButton.click();

        WebElement opt = driver.findElement(By.xpath("//input[@id='opt_in']"));
        opt.sendKeys("y");
        WebElement optButton = driver.findElement(By.xpath("//div[4]/div/button[contains(@class, 'js-continue-button signup-continue-button form-control')]"));
        Thread.sleep(3000);
        optButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='captcha-and-submit-container']")));
        if (driver.findElement(By.xpath("//div[@id='captcha-and-submit-container']")).isDisplayed()) {
            System.out.println("Я всего лишь автотест и не могу выполнить за тебя проверку на капчу. Автотест выполнен и закончен в виду блокирующего события ^_^");
        } else {
            System.out.println("Autotest failed: something wrong in registration new user!");
        }

        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

        driver.quit();
    }
}
