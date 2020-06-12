package blog;

/*
 * @program: 2020608
 * @description
 * 查看文章列表，按照发表时间倒序给出
 * @author: mrs.yang
 * @create: 2020 -06 -11 15 :56
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArticleListAction implements Action {
    @Override
    public void run() {
       try(Connection c=DBUtil.getConnection()){
           List<String[]> articleList=new ArrayList<>();
           String sql="select id,author_id,title,published_at from articles order by published_at desc";
           try(PreparedStatement s=c.prepareStatement(sql)){
               try(ResultSet r=s.executeQuery()){
                   while(r.next()){
                       String[] article=new String[5];
                       String id=r.getString("id");
                       String authorId=r.getString("author_id");
                       String title=r.getString("title");
                       String publishedAt=r.getString("published_at");
                       article[0]=id;
                       article[1]=authorId;
                       article[2]=title;
                       article[3]=publishedAt;
                       articleList.add(article);
                   }
               }
           }
           Set<String> set=new HashSet<>();
           for(String[] article:articleList){
               String authorId=article[1];
               set.add(authorId);
           }
           String querySql="select id,nickname from users where id in(";
           for(int i=1;i<set.size();i++){
               querySql+="?,";
           }
           querySql+="?)";
           System.out.println("DEBUG: "+querySql);
           try(PreparedStatement s=c.prepareStatement(sql)){
               int i=1;
               for(String id:set){
                   s.setString(i++,id);
               }
           }
           System.out.println("#ID | 标题 | 作者 | 发表时间%n");
           for(String[] article:articleList){
               System.out.printf("%-4s | %-10s | %-20s | %s%n",article[0],article[2],article[1],article[3] );
           }
       }catch(SQLException e){
           System.out.println("错误 "+e.getMessage());
       }
    }
 }
