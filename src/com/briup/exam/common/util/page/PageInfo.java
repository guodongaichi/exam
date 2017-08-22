package com.briup.exam.common.util.page;

import com.briup.exam.common.util.IPageInfo;



/**
 * ��ҳ��Ϣʵ����
 * */
public class PageInfo implements IPageInfo {
	public PageInfo() {
	}

	public PageInfo(int pageNo, int pageSize) {
		super();
		this.offset = pageSize * pageNo;
		this.pageSize = pageSize;
	}


	/**
	 * ÿҳ��һ�����ݵ�λ��
	 */
	private int offset;

	/**
	 * ��¼������
	 */
	private long totalCount;

	/**
	 * ÿҳ��ʾ��¼�����
	 */
	private int pageSize = DEFAULT_PAGE_SIZE;

	public int getPageCount() {
		return (int) Math.ceil((0.0 + this.totalCount) / this.pageSize);
	}

	public int getOffset() {
		return this.offset;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	/**
	 * ���ôӵڼ�����ʼ
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
