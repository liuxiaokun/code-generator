package com.cloudoer.bigtour.core.controller;

import com.cloudoer.bigtour.core.model.entity.BaseEntity;
import com.cloudoer.framework.context.AppContext;
import com.cloudoer.framework.domain.PagingContext;
import com.cloudoer.framework.domain.SortingContext;
import com.cloudoer.framework.util.StringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author code gen
 * @version 0.0.1
 * @since 2018/11/21
 */
public class BaseController<T extends BaseEntity> {

    private static final Pattern SC_EXTRACTION = Pattern.compile("(?<field>[0-9a-zA-z]+)\\((?<sort>[0-9a-zA-z]+)\\)", 2);
    private static final int MAX_PAGE_SIZE = 1000;

    protected T packAddBaseProps(T base, HttpServletRequest request) {
        long now = System.currentTimeMillis();
        base.setId(AppContext.IdGen.nextId());
        base.setCreatedBy(getUserId(request));
        base.setCreatedDate(now);
        base.setStatus(1);

        return base;
    }

    protected T packModifyBaseProps(T base, HttpServletRequest request) {
        base.setModifiedBy(getUserId(request));
        base.setModifiedDate(System.currentTimeMillis());
        return base;
    }

    public Long getUserId(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization");

        Claims claims = Jwts.parser()
                .setSigningKey("my-secret".getBytes())
                .parseClaimsJws(jwt.replace("Bearer ", ""))
                .getBody();
        return Long.parseLong(claims.getSubject());
    }

    /**
     * 获取排序字段
     * <p>
     * 采用固定格式：sc=n(asc)&sc=p(desc)表示先按name升序排，再按priority降序排
     *
     * @param req
     * @return
     */
    public Vector<SortingContext> getSortingContext(HttpServletRequest req) {
        Vector<SortingContext> scs = new Vector<>();
        String[] sortArray = req.getParameterValues("sc");
        if (sortArray != null) {
            String[] sortArrayNew = sortArray;
            int sortArrayLength = sortArray.length;

            for (int i = 0; i < sortArrayLength; ++i) {
                String sc_str = sortArrayNew[i];
                Matcher matcher = SC_EXTRACTION.matcher(sc_str);
                if (matcher.matches()) {
                    String f = matcher.group("field");
                    String o = matcher.group("sort");
                    String field = this.getFieldByAlias(f);
                    String order = "asc".equalsIgnoreCase(o) ? "ASC" : "DESC";
                    if (StringUtils.isNotEmpty(field) && StringUtils.isNotEmpty(order)) {
                        SortingContext sc = new SortingContext(field, order);
                        scs.add(sc);
                    }
                }
            }
        }

        return scs;
    }

    private String getFieldByAlias(String alias) {
        if ("cd".equals(alias)) {
            return "createdDate";
        } else if ("md".equals(alias)) {
            return "modifiedDate";
        } else {
            return "p".equalsIgnoreCase(alias) ? "priority" : alias;
        }
    }

    /**
     * p = pageindex s = pagesize
     */
    public PagingContext getPagingContext(HttpServletRequest req, Integer total) {

        PagingContext pc = new PagingContext();
        pc.setTotal(total);
        String s = req.getParameter("s");
        if (StringUtil.isNumeric(s)) {
            int size = Integer.parseInt(s);
            if (size > 0 && size < MAX_PAGE_SIZE) {
                pc.setPageSize(size);
            } else {
                pc.setPageSize(total <= MAX_PAGE_SIZE ? total : MAX_PAGE_SIZE);
            }
        } else {
            pc.setPageSize(20);
        }
        String p = req.getParameter("p");
        if (StringUtil.isNumeric(p)) {
            pc.setPageIndex(Integer.parseInt(p));
        } else {
            pc.setPageIndex(1);
        }
        return pc;
    }

    public Map<String, Object> getConditionsMap(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(15);

        Enumeration enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement();
            String parameterValue = request.getParameter(paraName);

            if (StringUtils.isNotEmpty(paraName) && StringUtils.isNotEmpty(parameterValue)) {
                map.put(paraName, parameterValue);
            }
        }
        return map;
    }
}
