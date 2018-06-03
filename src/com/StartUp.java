package com;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

//启动类，程序的入口
public class StartUp {


    public static void start() {
        try {
            Scanner scan=new Scanner(new FileReader("in.txt"));
            int t=scan.nextInt();//测试用例的数目
            for(int i=0;i<t;++i) {
                int n=scan.nextInt();//节点数目
                int m=scan.nextInt();//边的数目
                int s=scan.nextInt();
                Graph g=new Graph(n);
                for(int j=0;j<m;++j) {
                    int u=scan.nextInt();
                    int v=scan.nextInt();
                    int w=scan.nextInt();
                    g.addEdge(u, v, w);
                }
                List<int[]>res=g.dijkstra2(s);
                g.printPath(res, s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[]args) {
        start();
    }
}
