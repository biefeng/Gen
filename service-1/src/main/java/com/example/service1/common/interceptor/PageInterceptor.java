package com.example.service1.common.interceptor;

import com.example.service1.common.model.PageResult;
import com.example.service1.common.model.PageVo;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMap;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
@Intercepts(@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
public class PageInterceptor implements Interceptor {

    private static final int MAPPED_STATEMENT_INDEX = 0;
    private static final int PARAMETER_INDEX = 1;
    private static final int ROW_BOUNDS_INDEX = 2;
    private static final int RESULT_HANDLER_INDEX = 3;
    private static final String COUNT_SQL_SUFFIX = "Count";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];

        Object parameter = args[1];
        RowBounds rowBounds = (RowBounds) args[2];
        ResultHandler resultHandlers = (ResultHandler) args[3];

        Executor executor = (Executor) invocation.getTarget();
        Configuration configuration = mappedStatement.getConfiguration();

        if (checkParameter(parameter)) {
            List resultList = new ArrayList();
            PageResult pageResult = new PageResult();
            String mappedStatementId = mappedStatement.getId();
            String countSqlName = mappedStatementId + COUNT_SQL_SUFFIX;
            MappedStatement countMappedStatement = configuration.getMappedStatement(countSqlName);
            List<Object> count = executor.query(countMappedStatement, parameter, rowBounds, resultHandlers);
            if (null != count && count.size() > 0) {
                int total = (int) count.get(0);
                pageResult.setTotalRows(total);
                if (total > 0) {
                    List<Object> objects = executor.query(mappedStatement, parameter, rowBounds, resultHandlers);
                    pageResult.setResult(objects);
                }
            }
            resultList.add(pageResult);
            return resultList;
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        //TODO 拦截器完成注入后的逻辑（自定义）
    }

    public boolean checkParameter(Object parameterObject) {
        AtomicBoolean flag = new AtomicBoolean(false);
        if (parameterObject instanceof MapperMethod.ParamMap) {
            MapperMethod.ParamMap paramMap = (MapperMethod.ParamMap) parameterObject;
            paramMap.values().stream().forEach(e -> {
                if (e instanceof PageVo) {
                    flag.set(true);
                }
            });
        }
        return flag.get();
    }
}
