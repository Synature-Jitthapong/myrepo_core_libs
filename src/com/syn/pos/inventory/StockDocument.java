package com.syn.pos.inventory;

public abstract class StockDocument implements DocumentCreation, DocumentDetailCreation {

	@Override
	public int getMaxDocumentDetail(int documentId, int shopId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean addDocumentDetail(int documentId, int shopId,
			float materialId, float materialQty, float materialPrice,
			String unitName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addDocumentDetail(int documentId, int shopId,
			float materialId, float materialQty, float materialPrice,
			int taxType, String unitName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateDocumentDetail(int docDetailId, int documentId,
			int shopId, int materialId, float materialQty, float materialPrice,
			String unitName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateDocumentDetail(int docDetailId, int documentId,
			int shopId, int materialId, float materialQty, float materialPrice,
			int taxType, String unitName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteDocumentDetail(int docDetailId, int documentId,
			int shopId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getMaxDocument(int shopId, int docTypeId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxDocumentNo(int documentId, int shopId, int documentMonth,
			int documentYear, int documentTypeId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public abstract boolean createDocument(int shopId, int documentTypeId, int staffId);

	@Override
	public boolean saveDocument(int documentId, int shopId, int staffId,
			String remark) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean approveDocument(int documentId, int shopId, int staffId,
			String remark) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cancelDocument(int documentId, int shopId, int staffId,
			String remark) {
		// TODO Auto-generated method stub
		return false;
	}

}
