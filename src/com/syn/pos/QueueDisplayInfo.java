package com.syn.pos;

import java.util.List;

public class QueueDisplayInfo {
	public List<QueueInfo> xListQueueInfo;
	private String szCurQueueGroupA;
	private String szCurQueueGroupB;
	private String szCurQueueGroupC;
	private String szCurQueueCustomerA;
	private String szCurQueueCustomerB;
	private String szCurQueueCustomerC;

	public String getSzCurQueueCustomerA() {
		return szCurQueueCustomerA;
	}

	public void setSzCurQueueCustomerA(String szCurQueueCustomerA) {
		this.szCurQueueCustomerA = szCurQueueCustomerA;
	}

	public String getSzCurQueueCustomerB() {
		return szCurQueueCustomerB;
	}

	public void setSzCurQueueCustomerB(String szCurQueueCustomerB) {
		this.szCurQueueCustomerB = szCurQueueCustomerB;
	}

	public String getSzCurQueueCustomerC() {
		return szCurQueueCustomerC;
	}

	public void setSzCurQueueCustomerC(String szCurQueueCustomerC) {
		this.szCurQueueCustomerC = szCurQueueCustomerC;
	}

	public String getSzCurQueueGroupA() {
		return szCurQueueGroupA;
	}

	public void setSzCurQueueGroupA(String szCurQueueGroupA) {
		this.szCurQueueGroupA = szCurQueueGroupA;
	}

	public String getSzCurQueueGroupB() {
		return szCurQueueGroupB;
	}

	public void setSzCurQueueGroupB(String szCurQueueGroupB) {
		this.szCurQueueGroupB = szCurQueueGroupB;
	}

	public String getSzCurQueueGroupC() {
		return szCurQueueGroupC;
	}

	public void setSzCurQueueGroupC(String szCurQueueGroupC) {
		this.szCurQueueGroupC = szCurQueueGroupC;
	}

	public static class QueueInfo {
		private int iQueueID;
		private int iQueueIndex;
		private int iQueueGroupID;
		private String szQueueName;
		private String szCustomerName;
		private int iCustomerQty;
		private String szStartQueueDate;
		private int iWaitQueueMinTime;
		private int iWaitQueueCurrentOfGroup;
		private int iHasPreOrderList;

		public int getiHasPreOrderList() {
			return iHasPreOrderList;
		}

		public void setiHasPreOrderList(int iHasPreOrderList) {
			this.iHasPreOrderList = iHasPreOrderList;
		}

		public int getiQueueID() {
			return iQueueID;
		}

		public void setiQueueID(int iQueueID) {
			this.iQueueID = iQueueID;
		}

		public int getiQueueIndex() {
			return iQueueIndex;
		}

		public void setiQueueIndex(int iQueueIndex) {
			this.iQueueIndex = iQueueIndex;
		}

		public int getiQueueGroupID() {
			return iQueueGroupID;
		}

		public void setiQueueGroupID(int iQueueGroupID) {
			this.iQueueGroupID = iQueueGroupID;
		}

		public String getSzQueueName() {
			return szQueueName;
		}

		public void setSzQueueName(String szQueueName) {
			this.szQueueName = szQueueName;
		}

		public String getSzCustomerName() {
			return szCustomerName;
		}

		public void setSzCustomerName(String szCustomerName) {
			this.szCustomerName = szCustomerName;
		}

		public int getiCustomerQty() {
			return iCustomerQty;
		}

		public void setiCustomerQty(int iCustomerQty) {
			this.iCustomerQty = iCustomerQty;
		}

		public String getSzStartQueueDate() {
			return szStartQueueDate;
		}

		public void setSzStartQueueDate(String szStartQueueDate) {
			this.szStartQueueDate = szStartQueueDate;
		}

		public int getiWaitQueueMinTime() {
			return iWaitQueueMinTime;
		}

		public void setiWaitQueueMinTime(int iWaitQueueMinTime) {
			this.iWaitQueueMinTime = iWaitQueueMinTime;
		}

		public int getiWaitQueueCurrentOfGroup() {
			return iWaitQueueCurrentOfGroup;
		}

		public void setiWaitQueueCurrentOfGroup(int iWaitQueueCurrentOfGroup) {
			this.iWaitQueueCurrentOfGroup = iWaitQueueCurrentOfGroup;
		}
	}
}
