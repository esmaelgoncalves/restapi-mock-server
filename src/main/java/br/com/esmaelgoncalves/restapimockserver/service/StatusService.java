package br.com.esmaelgoncalves.restapimockserver.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.esmaelgoncalves.restapimockserver.model.Status;

@Service
public class StatusService {
	
	@Value("${app.appName}")
	private String appName;
	@Value("${app.appVersion}")
	private String appVersion;
	
	public Status getStatus() {
		return new Status(appName, appVersion);
	}
	
}
