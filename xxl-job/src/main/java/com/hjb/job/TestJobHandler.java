package com.hjb.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class TestJobHandler {

    @XxlJob("testJobHandler")
    public ReturnT<String> demoJobHandler(String param) throws Exception {
        XxlJobLogger.log("XXL-JOB, testJobHandler");
        log.info("testJobHandler is start ,time={}",LocalDateTime.now());
        //业务处理
        return ReturnT.SUCCESS;
    }


    @XxlJob("logJobHandler")
    public ReturnT<String> logJobHandler(String param) throws Exception {
        XxlJobLogger.log("XXL-JOB, logJobHandler");
        log.info("logJobHandler is start ,time={}",LocalDateTime.now());
        //业务处理
        return ReturnT.SUCCESS;
    }
}
