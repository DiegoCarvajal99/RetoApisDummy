package com.restapiexample.dummy.utils;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Esperas {

    public static void esperarPor(int segundos) throws InterruptedException {
        Thread.sleep(segundos*1000);
    }

    public static void esperaImplicita (int time, WebDriver driver){
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS) ;
    }

}
