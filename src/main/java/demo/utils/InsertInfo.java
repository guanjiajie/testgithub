package demo.utils;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class InsertInfo {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public void insertUser(String tableName){
        for (int i=0; i<200000; i++){
            jdbcTemplate.update("insert into "+tableName+ "(id,name,password) values (?,?,?)",i+1,"jack"+i,i+1);

        }
    }

    public void updateUser(String tableName){
        for (int i=0; i<200000; i++){
            jdbcTemplate.update("update "+tableName+" set email=?,status=1,validatecode='VA',stu_no=? " +
                            "where id=?",
                    "email-"+i,i+1,i+1);
        }
    }

    public void insertStu(){
        for (int i=1; i<=200000; i++){
            jdbcTemplate.update("insert into stu_list(stu_no,name,age,score) values(?,?,?,?)",
                    i,"jack"+(i-1),20,85);
        }
    }

    public void insertStatic(){
        for (int i=1; i<200000; i++){
            jdbcTemplate.update("insert into static_list(value,name,code) values(?,?,?)",
                    i,"值"+i,"code"+i);
        }
    }

    public void insertCno(){
        for (int i=0; i<200000; i++){
            jdbcTemplate.update("insert into cno_list(name,cno,score) values(?,?,?)",
                    "jack"+i,i,80);
        }
    }
}
