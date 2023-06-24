package com.example.xxl;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * ClassName: MyXXL
 * Package: com.example.xxl
 * Description:
 *
 * @Author AQiu
 * @Create 28/05/2023 20:53
 */
@Component
public class MyXXL {
    @XxlJob("myFirstJobHandler")
    public ReturnT<String> test(String param){
        
        System.out.println("test" + param);
        XxlJobLogger.log("first job handler");
        return ReturnT.SUCCESS;
    }

}
