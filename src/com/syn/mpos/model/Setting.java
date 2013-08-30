package com.syn.mpos.model;

public class Setting {
	public static class Sync{
		private long syncTime;
		private int syncStatus;
		
		public long getSyncTime() {
			return syncTime;
		}
		public void setSyncTime(long syncTime) {
			this.syncTime = syncTime;
		}
		public int getSyncStatus() {
			return syncStatus;
		}
		public void setSyncStatus(int syncStatus) {
			this.syncStatus = syncStatus;
		}
	}
	
	public static class ConnectionSetting{
		private String ipAddress;
		private String virtualDir;
		private String serviceName;
		private String fullUrl;
		
		public String getIpAddress() {
			return ipAddress;
		}
		public void setIpAddress(String ipAddress) {
			this.ipAddress = ipAddress;
		}
		public String getVirtualDir() {
			return virtualDir;
		}
		public void setVirtualDir(String virtualDir) {
			this.virtualDir = virtualDir;
		}
		public String getServiceName() {
			return serviceName;
		}
		public void setServiceName(String serviceName) {
			this.serviceName = serviceName;
		}
		public String getFullUrl() {
			return fullUrl;
		}
		public void setFullUrl(String fullUrl) {
			this.fullUrl = fullUrl;
		}
	}
}
