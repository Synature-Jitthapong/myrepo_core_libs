package com.syn.pos;

public class Setting {
	private String menuImageUrl;
	public Sync sync = new Sync();
	public Connection conn = new Connection();
	
	public String getMenuImageUrl() {
		return menuImageUrl;
	}

	public void setMenuImageUrl(String menuImageUrl) {
		this.menuImageUrl = menuImageUrl;
	}

	public static class Sync{
		private long syncTime;
		private int syncStatus;
		private boolean syncWhenLogin;
		
		public boolean isSyncWhenLogin() {
			return syncWhenLogin;
		}
		public void setSyncWhenLogin(boolean syncWhenLogin) {
			this.syncWhenLogin = syncWhenLogin;
		}
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
	
	public static class Connection{
		private String httpProto = "http://";
		private String ipAddress;
		private String virtualDir;
		private String serviceName = "ws_mpos.asmx";
		private String fullUrl;
		
		public String getHttpProto() {
			return httpProto;
		}
		public void setHttpProto(String httpProto) {
			this.httpProto = httpProto;
		}
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
