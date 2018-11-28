package com.cloudoer.bigtour.core.service.impl;

import com.cloudoer.bigtour.core.dao.BaseDAO;
import com.cloudoer.bigtour.core.exception.BizException;
import com.cloudoer.bigtour.core.service.BaseService;
import com.cloudoer.framework.domain.PagingContext;
import com.cloudoer.framework.domain.SortingContext;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * @author code gen
 * @version 0.0.1
 * @since 2018/11/20
 */
@Slf4j
public class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    public BaseDAO<T> dao;

    @Override
    public T findById(@NonNull Object id) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        return dao.selectOne(params);
    }

    @Override
    public T findOne(@NonNull Map<String, Object> params) {
        if (params.size() > 0) {
            params = params.entrySet().stream().filter(entry ->
                    (StringUtils.isNotEmpty(entry.getKey()) && null != entry.getValue()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }
        return dao.selectOne(params);
    }

    @Override
    public List<T> find(@NonNull Map<String, Object> params, Vector<SortingContext> scs, PagingContext pc) {
        if (params.size() > 0) {
            params = params.entrySet().stream().filter(entry ->
                    (StringUtils.isNotEmpty(entry.getKey()) && null != entry.getValue()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }
        params.put("pc", pc);
        params.put("scs", scs);
        return dao.select(params);
    }

    @Override
    public List<Map> findMap(@NonNull Map<String, Object> params, Vector<SortingContext> scs,
                             PagingContext pc, @NonNull String... columns) throws BizException {
        if (columns.length == 0) {
            throw new BizException("columns长度不能为0");
        }
        if (params.size() > 0) {
            params = params.entrySet().stream().filter(entry ->
                    (StringUtils.isNotEmpty(entry.getKey()) && null != entry.getValue()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }
        params.put("pc", pc);
        params.put("scs", scs);
        params.put("columns", columns);
        return dao.selectMap(params);
    }

    @Override
    public int count(@NonNull Map<String, Object> params) {
        if (params.size() > 0) {
            params = params.entrySet().stream().filter(entry ->
                    (StringUtils.isNotEmpty(entry.getKey()) && null != entry.getValue()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }
        return dao.count(params);
    }

    @Override
    public void save(@NonNull T t) throws BizException {

        if (dao.insert(t) != 1) {
            log.error("insert error, data:{}", t);
            throw new BizException("insert error");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(@NonNull List<T> list) throws BizException {

        if (list.size() == 0) {
            throw new BizException("参数长度不能为0");
        }
        int rows = dao.insertList(list);

        if (rows != list.size()) {
            log.error("数据库插入条数({})与给定的({})不一致", rows, list.size());
            throw new BizException("数据库插入条数与给定的不一致");
        }
    }

    @Override
    public void update(@NonNull T t) {
        dao.update(t);
    }

    @Override
    public void updatex(@NonNull Map<String, Object> dataMap, Map<String, Object> conditionMap) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("datas", dataMap);
        params.put("conditions", conditionMap);
        dao.updatex(params);
    }

    @Override
    public void delete(@NonNull Object id, @NonNull Object userID) throws BizException {
        Map<String, Object> params = new HashMap<>(3);
        params.put("id", id);
        params.put("modifiedBy", userID);
        params.put("modifiedDate", System.currentTimeMillis());
        int rows = dao.delete(params);

        if (rows != 1) {
            log.error("删除异常, ID:{}", rows);
            throw new BizException("删除异常.");
        }
    }

    @Override
    public void pdelete(@NonNull Object id) throws BizException {
        Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        int rows = dao.pdelete(params);

        if (rows != 1) {
            log.error("删除了{}条数据, ID:{}", rows, id);
            throw new BizException("删除失败");
        }
    }
}
