package org.bidding.device;

public class DeviceTarget {
  String deviceExtBrowser;
  String deviceLanguage;
  String deviceExtType;
  String bannerExtSize;

  public String getDeviceExtBrowserKey() {
    return "deviceExtBrowser=" + deviceExtBrowser;
  }

  public String getDeviceExtBrowser() {
    return deviceExtBrowser;
  }

  public void setDeviceExtBrowser(String deviceExtBrowser) {
    this.deviceExtBrowser = deviceExtBrowser;
  }

  public String getDeviceLanguageKey() {
    return "deviceLanguage=" + deviceLanguage;
  }

  public String getDeviceLanguage() {
    return deviceLanguage;
  }

  public void setDeviceLanguage(String deviceLanguage) {
    this.deviceLanguage = deviceLanguage;
  }

  public String getDeviceExtTypeKey() {
    return "deviceExtType=" + deviceExtType;
  }

  public String getDeviceExtType() {
    return deviceExtType;
  }

  public void setDeviceExtType(String deviceExtType) {
    this.deviceExtType = deviceExtType;
  }

  public String getBannerExtSizeKey() {
    return "bannerExtSize=" + bannerExtSize;
  }

  public String getBannerExtSize() {
    return bannerExtSize;
  }

  public void setBannerExtSize(String bannerExtSize) {
    this.bannerExtSize = bannerExtSize;
  }

}
