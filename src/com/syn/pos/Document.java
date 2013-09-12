package com.syn.pos;

/**
 * 
 * @author j1tth4
 * 
 */
public interface Document {
	int getMaxDocument(int shopId, int docTypeId);

	int getMaxDocumentNo(int documentId, int shopId, int documentMonth,
			int documentYear, int documentTypeId);

	boolean newDocument(int shopId, int documentTypeId, int staffId);
	
	boolean saveDocument(int documentId, int shopId, int staffId, String remark);
	
	boolean approveDocument(int documentId, int shopId, int staffId, String remark);
	
	boolean cancelDocument(int documentId, int shopId, int staffId, String remark);
}
