package blog;

/*
 * @program: 2020608
 * @description
 * 发表文章-幽囚先登录
 * @author: mrs.yang
 * @create: 2020 -06 -11 15 :54
 */

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ArticlePublishAction implements Action{
    @Override
    public void run() {
        //判断用户是否已经登录
        if(!User.isLogined()){
            System.out.println("**要求先登录，才能操作该功能");
            return;
        }
        //获取用户输入信息（标题。正文）
        System.out.println("发表文章...");
        Scanner scan=new Scanner(System.in);
        System.out.println("请输入文章标题:>");
        String title=scan.nextLine();
        System.out.println("请输入文章正文:>");
        String content=scan.nextLine();
        int authorId=User.getCurrentUser().id;
        Date publishedAt=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String publishedAtStr=format.format(publishedAt);
        try(Connection c=DBUtil.getConnection()){
            String sql="insert into articles (author_id,title,published_at,content) values (?,?,?,?)";
            try(PreparedStatement s=c.prepareStatement(sql) ){
                s.setInt(1,authorId);
                s.setString(2,title);
                s.setString(3,publishedAtStr);
                s.setString(4,content);
                s.executeUpdate();
                System.out.println("《"+title+ "》 文章发表成功!");
            }
        }catch(SQLException e){
            System.out.println("错误： "+e.getMessage());
        }
    }


}
