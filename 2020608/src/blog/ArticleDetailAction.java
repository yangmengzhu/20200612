package blog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @program: 2020608
 * @description
 * @author: mrs.yang
 * @create: 2020 -06 -11 17 :36
 */

public class ArticleDetailAction implements Action{
    @Override
    public void run() {
        Scanner scan=new Scanner(System.in);
        String id=scan.nextLine();
        String author_id=null;
        String title=null;
        String content=null;
        String published_at=null;
        int likeCount=0;
        try(Connection c=DBUtil.getConnection()){
          String queryArticleSql="select id,author_id,title,content,published_at from articles " +
                  "where author_id=?";
          try(PreparedStatement s=c.prepareStatement(queryArticleSql) ){
                s.setString(1,id);
                try(ResultSet r=s.executeQuery()){
                    if(!r.next()){
                        System.out.println("没有这篇文章！");
                        return;
                    }else{
                        author_id =r.getString("author_id");
                        title=r.getString("title");
                        content=r.getString("content");
                        published_at=r.getString("published_at");
                    }
                }

                //查询点赞数量
                String likeCountSql="select count(*) from like_relations where article_id=?";
                try(PreparedStatement s1=c.prepareStatement(likeCountSql)){
                    s1.setString(1,id);
                    try(ResultSet r=s.executeQuery()){
                        r.next();
                        likeCount=r.getInt(1);
                    }
                }
                //查询评论信息
                List<String[]> commentList=new ArrayList<>();
                String queryCommentSql="select user_id,content,published_at from comments where article_id=? order by published_at desc";
                try(PreparedStatement s2=c.prepareStatement(queryCommentSql)){
                    s2.setString(1,id);
                    try(ResultSet r=s.executeQuery()){
                        while(r.next()){
                            String[] comment=new String[3];
                            comment[0]=r.getString("user_id");
                            comment[1]=r.getString("content");
                            comment[2]=r.getString("published_at");
                            commentList.add(comment);
                        }
                    }
                }
                //根据用户id查询用户昵称
                //用户id来自作者id+评论者id
                Set<String> set=new HashSet<>();
                for (String[] comment:commentList) {
                    set.add(comment[0]);
                }
                StringBuilder queryNick=new StringBuilder("select id,nickname from users where id in(");
              for (int i = 1; i <set.size() ; i++) {
                  queryNick.append("?, ");
              }
                queryNick.append("?)");
              Map<String,String> map=new HashMap<>();
              try(PreparedStatement s3=c.prepareStatement(queryNick.toString())){
                  int i=1;
                  for (String userId:set) {
                      s3.setString(i++,userId);
                  }
                  try(ResultSet r=s.executeQuery()){
                      while(r.next()){
                          map.put(r.getString("id"),r.getString("nickname") );
                      }
                  }
              }
            }
          //打印显示内容
            System.out.println(title);

        }catch(SQLException e){
            System.out.println("错误："+e.getMessage());
        }
    }
 }
