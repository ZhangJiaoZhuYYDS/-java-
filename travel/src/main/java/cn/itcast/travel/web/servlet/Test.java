package cn.itcast.travel.web.servlet;

class Fu {
//    int num = 10;
    int fu_var = 30;

    public void method() {
        System.out.println("父类方法");
    }
}

class Zi extends Fu {
    int num = 20;
    int zi_var = 40;

    @Override
    public void method() {
        System.out.println("子类方法");
    }
}

public class Test {
    public static void main(String[] args) {
        Fu var = new Zi(); //左侧父类的引用，指向右侧子类的对象
        var.method();    //子类方法
        //System.out.println(num);// 10  父类中和子类中都有同名变量num，优先访问父类中的同名num
        System.out.println(var.fu_var); // 30
        //System.out.println(var.zi_var);  //报错 多态能访问子类独有属性
        Zi zi=(Zi) var;//向下转型 访问子类独有属性
        System.out.println(zi.zi_var); //报错  Cannot resolve symbol 'zi_var'
    }
}

