package io.kenxue.cicd.domain.factory.sys;

import io.kenxue.cicd.domain.domain.sys.Log;
/**
 * 系统日志
 * @author mikey
 * @date 2021-12-03 17:28:52
 */
public class LogFactory {
    public static Log getLog(){
        return new Log();
    }
}
