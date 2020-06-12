package blog;

/*
 * @program: 2020608
 * @description
 * @author: mrs.yang
 * @create: 2020 -06 -08 18 :59
 */


import java.sql.*;
import java.util.Scanner;

public class UserRegisterAction implements Action{
    @Override
    public void run()  {
        System.out.println("开始用户注册...");
        //使用jdbc执行sql
        System.out.println();
        Scanner scan=new Scanner(System.in);
        System.out.println("请输入用户名称>");
        String username=scan.nextLine();
        System.out.println("请输入用户昵称>");
        String nickname=scan.nextLine();
        System.out.println("请输入密码>");
        String password=scan.nextLine();
        String sql="insert into users (userName,nickName,password) values (?,?,?)";
        try(Connection connection=DBUtil.getConnection()){
            try(PreparedStatement statement=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                statement.setString(1,username);
                statement.setString(2,nickname);
                statement.setString(3,password);
                statement.executeUpdate();
                int id;
                try(ResultSet r=statement.getGeneratedKeys()){
                    r.next();
                    id=r.getInt(1);
                }
                System.out.println("注册成功，欢迎您的加入， "+nickname);
                //注册成功后让其自动登录
                User user=new User();
                user.id=id;
                user.nickname=username;
                User.login(user);
            }
        }catch(SQLException e){
            System.out.println("错误："+e.getMessage());
        }
    }
}
