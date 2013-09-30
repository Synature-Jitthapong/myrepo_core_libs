package com.syn.pos.inventory;

/**
 * 
 * @author j1tth4
 *
 */
public interface DocumentDetailCreation {
	/**
	 * 
	 * @param documentId
	 * @param shopId
	 * @return
	 */
	int getMaxDocumentDetail(int documentId, int shopId);

	/**
	 * 
	 * @param documentId
	 * @param shopId
	 * @param materialId
	 * @param materialQty
	 * @param materialBalance
	 * @param materialPrice
	 * @param unitName
	 * @return
	 */
	int addDocumentDetail(int documentId, int shopId, float materialId,
			float materialQty, float materialBalance, float materialPrice, String unitName);

	/**
	 * 
	 * @param documentId
	 * @param shopId
	 * @param materialId
	 * @param materialQty
	 * @param materialBalance
	 * @param materialPrice
	 * @param taxType
	 * @param unitName
	 * @return
	 */
	int addDocumentDetail(int documentId, int shopId, float materialId,
			float materialQty, float materialBalance, float materialPrice, int taxType, String unitName);

	/**
	 * 
	 * @param docDetailId
	 * @param documentId
	 * @param shopId
	 * @param materialId
	 * @param materialQty
	 * @param materialPrice
	 * @param unitName
	 * @return
	 */
	boolean updateDocumentDetail(int docDetailId, int documentId, int shopId,
			int materialId, float materialQty, float materialPrice,
			String unitName);

	/**
	 * 
	 * @param docDetailId
	 * @param documentId
	 * @param shopId
	 * @param materialId
	 * @param materialQty
	 * @param materialPrice
	 * @param taxType
	 * @param unitName
	 * @return
	 */
	boolean updateDocumentDetail(int docDetailId, int documentId, int shopId,
			int materialId, float materialQty, float materialPrice,
			int taxType, String unitName);

	/**
	 * 
	 * @param docDetailId
	 * @param documentId
	 * @param shopId
	 * @return
	 */
	boolean deleteDocumentDetail(int docDetailId, int documentId, int shopId);
	
	/**
	 * 
	 * @param documentId
	 * @param shopId
	 * @return
	 */
	boolean deleteDocumentDetail(int documentId, int shopId);
}
