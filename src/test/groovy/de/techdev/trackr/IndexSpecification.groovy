package de.techdev.trackr

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import spock.lang.Shared
import spock.lang.Specification

import static java.util.concurrent.TimeUnit.SECONDS

class IndexSpecification extends Specification {

    static String baseUrl = 'http://moritz.schulze%40techdev.de@localhost/trackr/'

    @Shared WebDriver driver;

    def setupSpec() {
        driver = new RemoteWebDriver(new URL('http://localhost:4444'), DesiredCapabilities.chrome())
        driver.manage().timeouts().implicitlyWait 10, SECONDS
    }

    def cleanupSpec() {
        driver.quit()
    }

    def 'the page title is trackr'() {
        when:
        driver.get baseUrl

        then:
        driver.title == "trackr"
    }

    def 'clicking administration moves to the correct state'() {
        when:
        driver.get baseUrl + 'trackr'
        def button = driver.findElement By.className("btn-primary")
        button.click()

        then:
        driver.findElement By.className('breadcrumb')
        driver.currentUrl.contains("administration")
    }

}
