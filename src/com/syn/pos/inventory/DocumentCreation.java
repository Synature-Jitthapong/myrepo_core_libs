package com.syn.pos.inventory;

public interface DocumentCreation {
	int getMaxDocument(int shopId, int docTypeId);

	int getMaxDocumentNo(int documentId, int shopId, int documentMonth,
			int documentYear, int documentTypeId);

	int createDocument(int shopId, int documentTypeId, int staffId);
	
	boolean saveDocument(int documentId, int shopId, int staffId, String remark);
	
	boolean approveDocument(int documentId, int shopId, int staffId, String remark);
	
	boolean cancelDocument(int documentId, int shopId, int staffId, String remark);
}
