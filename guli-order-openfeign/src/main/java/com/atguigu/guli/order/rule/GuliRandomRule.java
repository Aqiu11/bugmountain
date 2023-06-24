package com.atguigu.guli.order.rule;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.Random;

public class GuliRandomRule extends RoundRobinRule {

    @Override
    public Server choose(ILoadBalancer lb, Object key) {
        //1、获取要访问的目标服务的所有的ip端口号列表：
        List<Server> allServers = lb.getAllServers();
        List<Server> reachableServers = lb.getReachableServers();//获取目标服务可用的ip端口号列表
        //2、使用自己的算法从列表中获取合适的服务返回
        int size = reachableServers.size();
        if(size==0){
            return null;
        }
        //随机算法
        int choose = new Random().nextInt(size);//生成[0,size)
        return reachableServers.get(choose);
    }
}
