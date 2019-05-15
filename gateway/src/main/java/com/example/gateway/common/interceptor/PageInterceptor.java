package com.example.gateway.common.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMap;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.Properties;

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
        ParameterMap parameterMap = mappedStatement.getParameterMap();


        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        String mappedStatementId = mappedStatement.getId();
        String countSqlName = mappedStatementId + COUNT_SQL_SUFFIX;

        Configuration configuration = mappedStatement.getConfiguration();

        MappedStatement countMappedStatement = configuration.getMappedStatement(countSqlName);

        Executor executor = (Executor) invocation.getTarget();

        //executor.query(countMappedStatement,parameter,)

        System.out.println("mybatis plugin");

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

    /**
     * @return
     */
    public boolean checkParameter(ParameterMap parameterMap) {
        boolean flag = false;

        return flag;
    }
}
