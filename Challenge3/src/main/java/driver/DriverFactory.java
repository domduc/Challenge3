package driver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverFactory {

    private String strBrowserType;
    private int intScreenWidth;
    private int intScreenHigh;
    private String strPlatform;
    private DesiredCapabilities desiredCapabilities;
    private ChromeOptions chromeOptions;
    private boolean boolMaximized;
    private boolean boolRemoteMode;

    public int getIntScreenWidth() {
        return intScreenWidth;
    }

    public void setIntScreenWidth(int intScreenWidth) {
        this.intScreenWidth = intScreenWidth;
    }

    public DriverFactory(Builder _builder) {
        this.intScreenHigh = _builder.intScreenHigh;
        this.intScreenWidth = _builder.intScreenWidth;
        this.strBrowserType = _builder.strBrowserType;
        this.strPlatform = _builder.strPlatform;
        this.desiredCapabilities = _builder.desiredCapabilities;
        this.chromeOptions = _builder.chromeOptions;
    }

    /**
     * Initial the web driver as the capabilities
     *
     * @return
     */
    public WebDriver getWebDriver() {
        WebDriver _driver = null;

        if (boolRemoteMode) {
            //to do: handle for execution on the grid
        } else { //build the driver for local run
            switch (strBrowserType) {
                case "chrome":
                    _driver = new ChromeDriver(chromeOptions);
                    break;
                default:
                    _driver = new ChromeDriver();
                    break;
            }
        }
        return _driver;
    }

    public static class Builder {

        private String strBrowserType = "chrome";
        private int intScreenWidth = 1920;
        private int intScreenHigh = 1080;
        private String strPlatform = "WINDOWS";
        private boolean boolMaximized = true;
        private DesiredCapabilities desiredCapabilities;
        private ChromeOptions chromeOptions;

        public boolean getMaximized() {
            return boolMaximized;
        }

        public Builder setMaximized(boolean boolValue) {
            this.boolMaximized = boolValue;
            return this;
        }

        public String getStrBrowserType() {
            return strBrowserType;
        }

        public int getIntScreenWidth() {
            return intScreenWidth;
        }

        public Builder setIntScreenWidth(int intScreenWidth) {
            this.intScreenWidth = intScreenWidth;
            return this;
        }

        public int getIntScreenHigh() {
            return intScreenHigh;
        }

        public Builder setIntScreenHigh(int intScreenHigh) {
            this.intScreenHigh = intScreenHigh;
            return this;
        }

        public String getStrPlatform() {
            return strPlatform;
        }

        public Builder setStrPlatform(String srtPlatform) {
            this.strPlatform = srtPlatform;
            return this;
        }


        public Builder setStrBrowserType(String strBrowserType) {
            this.strBrowserType = strBrowserType;
            return this;
        }

        /**
         * Build the driver capacity
         * @return
         */
        public Builder buildCapabilities() {
            DesiredCapabilities _desiredCapabilities = new DesiredCapabilities();
            ChromeOptions _chromeOptions = new ChromeOptions();


            if (strBrowserType.equals("")) {
                _desiredCapabilities.setBrowserName("chrome");
            } else
                _desiredCapabilities.setBrowserName(strBrowserType);

            if (strPlatform.equals("")) {
                _desiredCapabilities.setPlatform(Platform.WINDOWS);
            } else {
                _desiredCapabilities.setCapability("platformName", strPlatform);
                _chromeOptions.setPlatformName(strPlatform);
            }

            //set maximized browser for chrome option
            if (boolMaximized == true && strBrowserType.equals("chrome")) {
                _chromeOptions.addArguments("start-maximized");
            } else {
                //set dimension of browser
            }

            _desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, _chromeOptions);

            this.desiredCapabilities = _desiredCapabilities;
            this.chromeOptions = _chromeOptions;
            return this;
        }

        public DriverFactory build() {
            return new DriverFactory(this);
        }
    }
}
