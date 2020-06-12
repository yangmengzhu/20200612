import sun.misc.PostVMInitHook;

import java.util.*;

/**
 * @program: 2020608
 * @description
 * @author: mrs.yang
 * @create: 2020 -06 -09 22 :12
 */
class   Person{
    String name="no name";
    public Person(String nm){
        name=nm;
    }
}
class Employee extends Person{
    String empID="0000";
    public Employee(String id){
        super(id);
    }
}

public class TestDemo {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        int x=scan.nextInt();
        int y=scan.nextInt();
        countWays(x,y);
    }
    //机器人走棋盘
    private static int  countWays(int x, int y) {
        if(x==1||y==1){
            return 1;
        }
        return countWays(x-1,y)+countWays(x,y-1);
    }

    //逆序输出正整数
    public static void main9(String[] args) {
        Scanner scan=new Scanner(System.in);
        int n=scan.nextInt();
        reverse(n);
    }

    private static void reverse(int n) {
        if(n>0) {
            System.out.print(n%10+" ");
            reverse(n / 10);
        }
    }
//一个正整数的阶乘末尾有几个0
    public static void main7(String[] args){
        Scanner scan=new Scanner(System.in);
        int n=scan.nextInt();
        int count=0;
        if(n<0){
            System.out.println(-1);
        }
        for(int i=0;i<n;i++){
            n=n/5;
            count=count+n;
        }
        System.out.println(count);
    }
    public static void main8(String[] args) {
        Scanner scan=new Scanner(System.in);
        int n=scan.nextInt();
        int ret=fab(n);
        System.out.println(ret);
    }

    private static int fab(int n) {
        if(n==1){
            return 1;
        }
        return n*fab(n-1);
    }

    public static void main6(String[] args) {
        int i=0;
        Integer j=new Integer(0);
        System.out.println(i==j);
        System.out.println(j.equals(i));
    }
    public static void main5(String[] args) {
       String x="fmn";
       x.toUpperCase();
       String y=x.replace('f','F');
       y=y+"wxy";
        System.out.println(y);
    }
    public static void main4(String[] args) {
        Scanner scan=new Scanner(System.in);
        int n=0;
        while(scan.hasNext() ){
            n=scan.nextInt();
            if(n>1000){
                n=999;
            }
        }
        Queue<Integer> queue=new LinkedList<>();
        for (int i = 0; i <n; i++) {
            queue.offer(n);
        }
        int count=0;
        while(queue.size()!=1){
           if(count!=2){
               int x=queue.peek();
               queue.poll();
               queue.offer(x);
               count++;
           }else{
               queue.poll();
               count=0;
           }
        }
        int y=queue.peek();
        queue.poll();
        System.out.println(y);
    }
    public static void main2(String[] args) {
        Scanner scan=new Scanner(System.in);
        List<Integer> list=new ArrayList<>();
        while(scan.hasNext()){
            list.add(scan.nextInt());
        }
        int k=list.get(list.size()-1);
        list.remove(list.size()-1);
        int[] arr=new int[list.size()];
        for (int i = 0; i <list.size() ; i++) {
            arr[i]=list.get(i);
        }
        Arrays.sort(arr);
        for (int i = 0; i < k-1; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println(k-1);
    }
    public static void main1(String[] args) {
        Scanner scan=new Scanner(System.in);
       while(scan.hasNext()){
           String str=scan.nextLine();
           String[] arr=str.split(" ");
           int[] array=new int[arr.length-1];
           int k=0;
           for (int i = 0; i < arr.length; i++) {
               if(i<arr.length-1){
                   array[i]=Integer.valueOf(arr[i]) ;
               }else{
                   k=Integer.valueOf(arr[i]);
               }
           }
           Arrays.sort(array);
           for (int i = 0; i <k-1; i++) {
               System.out.print(array[i]+" ");
           }
           System.out.println(array[k-1]);
       }
    }

}
