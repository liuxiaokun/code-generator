package com.cloudoer.bigtour.core.service;

import com.cloudoer.bigtour.core.exception.BizException;
import com.cloudoer.framework.domain.PagingContext;
import com.cloudoer.framework.domain.SortingContext;

import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * @author code gen
 * @version 0.0.1
 * @since 2018/11/20
 */
public interface BaseService<T> {

    T findById(Object id);

    T findOne(Map<String, Object> params);

    List<T> find(Map<String, Object> params, Vector<SortingContext> scs, PagingContext pc);

    List<Map> findMap(Map<String, Object> params, Vector<SortingContext> scs,
                      PagingContext pc, String... columns) throws BizException;

    int count(Map<String, Object> params);

    void save(T t) throws BizException;

    void save(List<T> list) throws BizException;

    void update(T t);

    void updatex(Map<String, Object> dataMap, Map<String, Object> conditionMap);

    void delete(Object id, Object userID) throws BizException;

    void pdelete(Object id) throws BizException;
}
