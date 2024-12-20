package com.cyberslex.api.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cyberslex.api.entity.Base;

//public interface IBaseService<E> {
public interface IBaseService<E extends Base, ID extends Serializable> {
	
	public List<E> findAll() throws Exception;
	
//	public E findById(Long Id) throws Exception;
	public E findById(ID Id) throws Exception;
	
	public E save(E entity) throws Exception;
	
//	public E update(Long id, E entity) throws Exception;
	public E update(ID id, E entity) throws Exception;
	
//	public boolean delete(Long id) throws Exception;
	public boolean delete(ID id) throws Exception;
	
	
	public Page<E> findAll(Pageable pageable) throws Exception;
}
