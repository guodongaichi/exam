package com.briup.exam.common.util;

/**
 * ��ҳ��Ϣ�ӿ�
 */
public interface IPageInfo {
	/**
	 * Ĭ��ÿҳ��ʾ�����
	 */
	public static final int DEFAULT_PAGE_SIZE = 10;

	/**
	 * ��ȡ��¼������
	 */
	long getTotalCount();

	/**
	 * ��ȡÿҳ��ʾ��¼�����
	 */
	int getPageSize();

	/**
	 * ��ȡÿҳ��ʼ���ݵ�λ��
	 */
	int getOffset();
	
	/**
	 * ���ü�¼������
	 */
	void setTotalCount(long totalCount);

	/**
	 * ����ÿҳ��ʾ��¼�����
	 */
	void setPageSize(int pageSize);
	
	/**
	 * ��ȡÿҳ��ʼ���ݵ�λ��
	 */
	void setOffset(int offset);
}

