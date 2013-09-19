package com.syn.pos.inventory;
/**
 * 
 * @author j1tth4
 *
 */
public interface DocumentCreation {
	/**
	 * 
	 * @param shopId
	 * @param docTypeId
	 * @return
	 */
	int getMaxDocument(int shopId);

	/**
	 * 
	 * @param documentId
	 * @param shopId
	 * @param documentMonth
	 * @param documentYear
	 * @param documentTypeId
	 * @return
	 */
	int getMaxDocumentNo(int documentId, int shopId, int documentMonth,
			int documentYear, int documentTypeId);

	/**
	 * 
	 * @param shopId
	 * @param documentTypeId
	 * @param staffId
	 * @return
	 */
	int createDocument(int shopId, int documentTypeId, int staffId);

	/**
	 * 
	 * @param shopId
	 * @param refDocumentId
	 * @param refShopId
	 * @param documentTypeId
	 * @param staffId
	 * @return
	 */
	int createDocument(int shopId, int refDocumentId, int refShopId,
			int documentTypeId, int staffId);

	/**
	 * 
	 * @param documentId
	 * @param shopId
	 * @param staffId
	 * @param remark
	 * @return
	 */
	boolean saveDocument(int documentId, int shopId, int staffId, String remark);

	/**
	 * 
	 * @param documentId
	 * @param shopId
	 * @param staffId
	 * @param remark
	 * @return
	 */
	boolean approveDocument(int documentId, int shopId, int staffId,
			String remark);

	/**
	 * 
	 * @param documentId
	 * @param shopId
	 * @param staffId
	 * @param remark
	 * @return
	 */
	boolean cancelDocument(int documentId, int shopId, int staffId,
			String remark);
}
