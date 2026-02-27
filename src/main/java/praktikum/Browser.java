package praktikum;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Browser {

    public WebDriver getWebDriver(String browserName) throws IOException {
        switch (browserName.toLowerCase()) {
            case "chrome": return new ChromeDriver();
            case "yandex":
                ChromeOptions options = new ChromeOptions();
                String binaryPath = "/Applications/Yandex.app/Contents/MacOS/Yandex";
                options.setBinary(binaryPath);
                Path driverPath = Paths.get("drivers", "yandexdriver").toAbsolutePath();
                System.setProperty("webdriver.chrome.driver", driverPath.toString());
                Runtime.getRuntime().exec("chmod +x " + driverPath); // даём права

                return new ChromeDriver(options);
            default:
                throw new IllegalArgumentException("Поддерживаемые браузеры: chrome, yandex. Передано: " + browserName);
        }
    }
}