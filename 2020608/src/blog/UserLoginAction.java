package blog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @program: 2020608
 * @description
 * @author: mrs.yang
 * @create: 2020 -06 -11 15 :31
 */

public class UserLoginAction implements  Action{
    @Override
    public void run() {
        User user=new User();
        System.out.println("开始用户登录...");
        System.out.println();
        Scanner scan=new Scanner(System.in);
        System.out.println("请输入用户名>");
        String username=scan.nextLine();
        System.out.println("请输入密码>");
        String password=scan.nextLine();
        try(Connection connection=DBUtil.getConnection()){
            String sql="select id,nickname from users where username=? and password=?";
            try(PreparedStatement s=connection.prepareStatement(sql) ){
                s.setString(1,username);
                s.setString(2,password);
                try(ResultSet r=s.executeQuery()){
                    if(r.next()){
                        int id=r.getInt(1);
                        String nickname=r.getString(2);
                        user.id=id;
                        user.username=username;
                        user.nickname=nickname;
                        User.login(user);
                    }else{
                        System.out.println("用户名或者密码输入错误，请重新输入");
                    }
                }
            }
        }catch(SQLException e){
            System.out.println("错误 "+e.getMessage());
        }

    }
 }
