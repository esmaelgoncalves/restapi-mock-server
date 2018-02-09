package br.com.esmaelgoncalves.restapimockserver.model;

public class Status {
	private String appName;
	private String appVersion;

	public Status(String appName, String appVersion) {
		this.appName = appName;
		this.appVersion = appVersion;
	}

	public String getAppName() {
		return appName;
	}

	public String getAppVersion() {
		return appVersion;
	}

	@Override
	public String toString() {
		return "Status [appName=" + appName + ", appVersion=" + appVersion + "]";
	}
}
