package com.syn.pos;

import java.util.List;

/**
 * 
 * @author j1tth4
 *
 */
public interface DocumentDetail {
	int getMaxDocumentDetail(int documentId, int shopId);
	
	boolean addDocumentDetail(int documentId, int shopId, float materialId, 
			float materialQty, float materialPrice, String unitName);
	
	boolean addDocumentDetail(int documentId, int shopId, float materialId, 
			float materialQty, float materialPrice, int taxType, String unitName);
	
	boolean updateDocumentDetail(int docDetailId, int documentId, int shopId, int materialId, 
			float materialQty, float materialPrice, String unitName);
	
	boolean updateDocumentDetail(int docDetailId, int documentId, int shopId, int materialId, 
			float materialQty, float materialPrice, int taxType, String unitName);
	
	boolean deleteDocumentDetail(int docDetailId, int documentId, int shopId);
	
	com.syn.pos.inventory.Document.DocDetail getDocDetail(int docDetailId, int documentId, int shopId);
	
	List<com.syn.pos.inventory.Document.DocDetail> listAllDocDetail(int documentId, int shopId);
}
