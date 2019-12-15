package com.structure.test;

import java.util.Scanner;

//结点关键字
class DATA {
    String key;
    String name;
    int age;
}

class SLType {
    static final int MAXLEN = 100;
    DATA[] ListData = new DATA[MAXLEN + 1];     //新建一个列表
    int ListLen;                                //顺序表已有结点的数量

    //初始化列表
    void SLInit(SLType SL) {
        SL.ListLen = 0;                         //初始为空表
    }

    //返回列表元素数量
    int SLLength(SLType SL) {
        return SL.ListLen;
    }

    //insert data
    int SLInsert(SLType SL, int n, DATA data) {
        int i;
        //先判断列表是否满了，然后插入的是否再范围内，然后再移动元素，最后插入
        if (SL.ListLen >= MAXLEN) {
            System.out.print("顺序表已满，不能插入结点！\n");
            return 0;                       //返回0插入结点不成功
        }

        if (n < 1 || n > SL.ListLen) {
            System.out.println("插入结点序号错误，不能插入结点！");
            return 0;                       //返回0插入不成功
        }
        //移动其他的结点，给插入的结点腾出位置
        for (i = SL.ListLen; i >= n; i--) {
            SL.ListData[i + 1] = SL.ListData[i];
        }
        SL.ListData[n] = data;              //插入结点
        SL.ListLen++;                       //结点数量加1
        return 1;                           //插如成功
    }

    //插入结点到尾部
    int SLAdd(SLType SL, DATA data) {
        //判断列表是否满了
        if (SL.ListLen >= MAXLEN) {
            System.out.println("列表已满，不能插入结点！");
            return 0;
        }
        SL.ListData[++SL.ListLen] = data;
        return 1;
    }

    //删除结点
    int SLDelete(SLType SL, int n) {
        int i;
        if (n < 1 || n > SL.ListLen + 1) {
            System.out.println("删除的序号错误，不能删除结点!");
            return 0;
        }
        for (i = n; i < SL.ListLen; i++) {
            SL.ListData[i] = SL.ListData[i + 1];
        }
        SL.ListLen--;
        return 1;
    }

    //按序号返回元素
    DATA SLFindByNum(SLType SL, int n) {
        if (n < 1 || n > SL.ListLen + 1) {
            System.out.println("结点序号错误，不能返回结点！");
            return null;
        }
        return SL.ListData[n];
    }

    //按关键字查询
    int SLFindByCont(SLType SL, String key) {
        int i;
        for (i = 1; i <= SL.ListLen; i++) {
            if (SL.ListData[i].key.compareTo(key) == 0) {
                return i;                                      //找到所需结点
            }
        }
        return 0;                                               //搜索之后没找到
    }

    //显示列表中的所有结点
    int SLAll(SLType SL) {
        for (int i = 1; i <= SL.ListLen; i++) {
            System.out.printf("(%s,%s,%d)\n", SL.ListData[i].key,
                    SL.ListData[i].name, SL.ListData[i].age);
        }
        return 0;
    }
}

public class ListBox {
    public static void main(String[] args) {
        int i;
        SLType SL = new SLType();                    //定义顺序表变量
        DATA pdata;                                  //定义结点保存引用变量
        String key;                                 //保存关键字
        System.out.println("顺序表操作演示！");


        SL.SLInit(SL);                              //初始化列表
        System.out.println("初始化列表完成！");

        Scanner input = new Scanner(System.in);

        do {
            //循环添加数据
            System.out.print("输入添加的结点（学号 姓名 年龄）： ");
            DATA data = new DATA();
            data.key = input.next();
            data.name = input.next();
            data.age = input.nextInt();

            if (data.age != 0) {                    //若年龄不为0
                if (SL.SLAdd(SL, data) == 0) {       //若添加结点失败
                    break;
                }
            } else {                                //若年龄为0则退出死循环
                break;
            }
        } while (true);
        System.out.println("\n顺序表中的结点为：");
        SL.SLAll(SL);                               //显示所有的结点

        System.out.print("\n要取出的结点的序号：");
        i = input.nextInt();                        //输入序号
        pdata = SL.SLFindByNum(SL, i);              //按序号查找
        if (pdata != null) {
            //若结点引用不为空
            System.out.printf("第%d个结点为： （%s,%s,%d）\n", i, pdata.key,
                    pdata.name, pdata.age);
        }

        System.out.print("要查找的关键字： ");
        key = input.next();                         //输入关键字
        i = SL.SLFindByCont(SL, key);               //返回关键字结点序号
        pdata = SL.SLFindByNum(SL, i);              //根据结点序号查询
        if (pdata != null) {
            //若结点部位空
            System.out.printf("第%d个结点为： （%s,%s,%d）\n", i, pdata.key,
                    pdata.name, pdata.age);
        }
    }
}
