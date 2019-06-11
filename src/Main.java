import hx.insist.HashMap;
import hx.insist.Stu;
import hx.insist.User;

public class Main {

    public static void main(String[] args) {
        HashMap hashMap = new HashMap();

        hashMap.put("aaa","111");//0
        hashMap.put(222,333);//5
        hashMap.put('q','w');//8

        Stu stu = new Stu(111,"学生");
        hashMap.put(stu,"111是学生");//9
        Stu stu2 = new Stu(111,"老师");
        hashMap.put(stu2,"111是老师");//8

        User u = new User(10010,"中国联通");
        hashMap.put(u,"10010是中国联通");//2
        User u2 = new User(10010,"中国移动");
        hashMap.put(u2,"10010是中国移动");
        // 重写User对象的hashCode和equals方法，id一样的认为是一个，
        // 所以添加两个10010，出现了重复的key,
        // 则 key使用老的：10010 中国联通，
        // value使用新的：10010是中国移动



        System.out.println(hashMap.get("aaa"));
        System.out.println(hashMap.get(222));
        System.out.println(hashMap.get('q'));

        System.out.println(hashMap.get(1234));//null

        //没有重写hashCode equals 两个对象不一样
        System.out.println(hashMap.get(stu));//111是学生
        System.out.println(hashMap.get(stu2));//111是老师

        //重写了hashCode equals id一致则认为此key一致，所以无论get u 或者 u2 都是能得到：10010是中国移动
        System.out.println(hashMap.get(u));//10010是中国移动
        System.out.println(hashMap.get(u2));//10010是中国移动


        System.out.println(hashMap);

        /*hashMap.put(null,null);  //不能储存null ，因为getIndex时 null.hashCode() java.lang.NullPointerException
        System.out.println(hashMap.get(null));*/

    }
}
