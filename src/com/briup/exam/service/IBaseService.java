package com.briup.exam.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Order;

import com.briup.exam.common.util.Criteriable;
import com.briup.exam.common.util.IPageInfo;


public interface IBaseService<M extends Serializable> {/**
	 * ����id��ѯΨһ����
	 * @param id
	 * @return
	 */
	M findById(Long id);
	
	/**
	 * ��ѯ����
	 * @return
	 */
	List<M> findAll(Order...orders);
	
	/**
	 * ��ѯ���У�����ҳ������
	 * @param pageInfo
	 * @param orders
	 * @return
	 */
	List<M> findAll(IPageInfo pageInfo,Order...orders);
	
	/**
	 * ���ݶ����ѯ
	 * @param model
	 * @return
	 */
	List<M> findByExample(M model,Order...orders);
	
	/**
	 * ���ݶ����ѯ������ҳ������
	 * @param model
	 * @return
	 */
	List<M> findByExample(M model,IPageInfo pageInfo,Order...orders);
	
	/**
	 * ����Criteria��ѯ
	 * @param criteriaObj
	 * @return
	 */
	List<M> findByCriteria(Criteriable criteriaObj,Order...orders);
	
	/**
	 * ����Criteria��ѯ������ҳ������
	 * @param criteriaObj
	 * @return
	 */
	List<M> findByCriteria(Criteriable criteriaObj,IPageInfo pageInfo,Order...orders);
	
	/**
	 * ���ݶ����ѯΨһ����
	 * @param model
	 * @return
	 */
	M findUnique(M model);
	
	/**
	 * ����
	 * @param model
	 */
	void save(M model);
	
	/**
	 * ��������
	 * @param model
	 */
	void batchSave(List<M> models);

	/**
	 * ������߸���
	 * @param model
	 */
	void saveOrUpdate(M model);

	/**
	 * ����
	 * @param model
	 */
	void update(M model);
	
	/**
	 * ���ݶ���ɾ��
	 * @param model
	 */
	void deleteObject(M model);
	
	/**
	 * ����id��ѯ
	 * @param id
	 */
	void delete(Long id);
	
	/**
	 * ����ɾ��
	 * @param ids
	 */
	void batchDelete(List<Long> ids);
	
	/**
	 * ����ɾ��
	 * @param ids
	 */
	void batchDelete(Long[] ids);
	
	/**
	 * 
	 * ����ɾ��
	 * @param models
	 *
	 */
	void batchDeleteModel(List<M> models);}
