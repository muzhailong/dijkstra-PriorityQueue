package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//图，使用邻接表存储
public class Graph {
    
    private int n; //the number of nodes
    public static final int INF=Integer.MAX_VALUE;
    List<List<Node>> table;//邻接表
    
    public Graph(int n) {
        this.n=n;
        table=new ArrayList<List<Node>>(n+1);// the index 0 does not use;
        for(int i=0;i<n+1;++i) {
            table.add(new LinkedList<Node>());
        }
    }
    //添加边
    public void addEdge(int u,int v,int weight) {//u->v
        table.get(u).add(new Node(v,weight));
    }
    
    
    //朴素的实现
   public List<int[]>dijkstra1(int s){
       int[]dis=new int[n+1];
       int[]path=new int[n+1];
       boolean[]mark=new boolean[n+1];
       Arrays.fill(dis, INF);
       dis[s]=0;
       path[s]=0;
       for(int i=1;i<n;++i) {
           for(Node temp:table.get(s)) {
               if(dis[s]+temp.weight<dis[temp.index]) {
                   dis[temp.index]=dis[s]+temp.weight;
                   path[temp.index]=s;
               }
           }
           mark[s]=true;
           int minDis=INF,index=0;
           for(int j=1;j<=n;++j) {
               if(!mark[j]&&dis[j]<minDis) {
                   minDis=dis[j];
                   index=j;
               }
           }
           s=index;
       }
       ArrayList<int[]>res=new ArrayList<int[]>(2);
       res.add(path);
       res.add(dis);
       return res;
    }
    
   //采用优先队列优化
   public List<int[]>dijkstra2(int s){
       int[]path=new int[n+1];
       int[]dis=new int[n+1];
       boolean[]mark=new boolean[n+1];//记录访问过的节点
       
       Arrays.fill(dis, INF);
       dis[s]=0;
       path[s]=0;
       PriorityQueue pq=new PriorityQueue(n);
       for(int i=1;i<n;++i) {
           for(Node temp:table.get(s)) {
               if(!mark[temp.index]&&dis[s]+temp.weight<dis[temp.index]) {
                   if(dis[temp.index]==INF) {
                       dis[temp.index]=dis[s]+temp.weight;
                       pq.offer(temp.index, dis[temp.index]);
                   }else {
                       pq.increase(temp.index, dis[s]+temp.weight-dis[temp.index]);
                       dis[temp.index]=dis[s]+temp.weight;
                   }
                   path[temp.index]=s;
               }
           }
           mark[s]=true;
           s=pq.poll();
       }
       ArrayList<int[]>res=new ArrayList<int[]>(2);
       res.add(path);
       res.add(dis);
       return res;
   }
   
   //递归获取路径信息
   private List<Integer>getPath(int[]path,int s,int cnt) {
       if(cnt==s) {
           List<Integer>lt=new LinkedList<Integer>();
           lt.add(s);
           return lt;
       }
       List<Integer>lt=getPath(path,s,path[cnt]);
       lt.add(cnt);
       return lt;
   }
   
   //打印路径信息
   public void printPath(List<int[]>info,int s) {
       List<List<Integer>> pathInfo=new LinkedList<List<Integer>>();
       for(int i=1;i<info.get(0).length;++i) {
           List<Integer>paths=getPath(info.get(0),s,i);
           int sz=paths.size();
           System.out.print(paths.get(0));
           for(int j=1;j<sz;++j) {
               System.out.print("->"+paths.get(j));
           }
           System.out.println(" 距离："+info.get(1)[i]);
       }
   }
   //图的节点类
    private static class Node{
        int weight;
        int index;
        public Node(int index,int weight) {
            this.index=index;
            this.weight=weight;
        }
    }
}
