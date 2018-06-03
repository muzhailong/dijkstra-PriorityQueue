package com;
//优先队列
public class PriorityQueue {

    private int size;//元素个数
    private int capacity;//容量
    private Entry[]arr;//保存元素
    private int[]pos;//同步根据index和位置的对应关系
    
    public PriorityQueue(int capacity) {
        this.capacity=capacity;
        arr=new Entry[capacity+1];
        pos=new int[capacity+1];
    }
    
	//添加一个节点
    public void offer(int index,int dis) {
        if(size==0) {
            arr[++size]=new Entry(index,dis);
            pos[index]=size;
        }else {
            arr[++size]=new Entry(index,dis);
            pos[index]=size;
            int j=size;
            for(int i=j/2;i>0;j=i,i/=2) {//上滤
                if(arr[j].dis<arr[i].dis) {
                    Entry p=arr[i];
                    arr[i]=arr[j];
                    arr[j]=p;
                    pos[arr[i].index]=i;
                    pos[arr[j].index]=j;
                }
            }
        }
    }
    
    public int peek() {//获取头部元素
        return arr[1].index;
    }
    
	//删除头部元素
    public int poll() {
        Entry temp=arr[size];
        int res=arr[1].index;
        --size;
        int j=1;
        int i=j*2;
        while(i<=size) {//下滤
            if(i+1<=size&&arr[i+1].dis<arr[i].dis) {
                ++i;
            }
            if(arr[i].dis<temp.dis) {
                arr[j]=arr[i];
                pos[arr[j].index]=j;
                j=i;
                i*=2;
            }else {
                break;
            }
        }
        arr[j]=temp;
        pos[arr[j].index]=j;
        return res;
    }
    //更新操作
    public void increase(int index,int inc) {
        Entry temp=null;
        int i;
//        for(i=1;i<=size;++i) {
//            if(index==arr[i].index) {
//                temp=arr[i];
//                break;
//            }
//        }
        i=pos[index];
        temp=arr[i];
        temp.dis+=inc;
        if(inc>0) {//下滤
            int j=i;
            i*=2;
            while(i<=size) {
                if(i+1<=size&&arr[i+1].dis<arr[i].dis) {
                    ++i;
                }
                if(arr[i].dis<temp.dis) {
                    arr[j]=arr[i];
                    pos[arr[j].index]=j;
                    j=i;
                    i*=2;
                }else {
                    break;
                }
            }
            arr[j]=temp;
            pos[arr[j].index]=j;
        }else {//上滤
            int j;
            for(j=i,i/=2;i>0;j=i,i/=2) {
                if(temp.dis<arr[i].dis) {
                    arr[j]=arr[i];
                    pos[arr[j].index]=j;
                }else {
                    break;
                }
            }
            arr[j]=temp;
            pos[arr[j].index]=j;
        }
    }
    
	//优先队列中的节点类
    public static class Entry{
        int index;
        int dis;
        public Entry(int index,int dis) {
            this.index=index;
            this.dis=dis;
        }
        
        public int getIndex() {
            return index;
        }
    }
    
    
    public static void main(String[]args) {
       PriorityQueue pq=new PriorityQueue(20); 
       pq.offer(1, 1);
       pq.offer(2, 2);
       pq.offer(3, 2);
       pq.increase(3, 1);
       pq.poll();
    }
}
