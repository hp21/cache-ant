package com.googlecode.cacheant;

public class CParams {
	public String url;
	public String cClazz;
	public String cmethod;
	public Object[] params;
	public boolean retrunStatus;

	public CParams() {
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getcClazz() {
		return cClazz;
	}

	public void setcClazz(String cClazz) {
		this.cClazz = cClazz;
	}

	public String getCmethod() {
		return cmethod;
	}

	public void setCmethod(String cmethod) {
		this.cmethod = cmethod;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	public boolean isRetrunStatus() {
		return retrunStatus;
	}

	public void setRetrunStatus(boolean retrunStatus) {
		this.retrunStatus = retrunStatus;
	}

}