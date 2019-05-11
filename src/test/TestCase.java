import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cqupt.dao.StudentDao;
import com.cqupt.dao.UserDao;
import com.cqupt.pojo.Student;
import com.cqupt.service.ScoreService;
import com.cqupt.utils.JdbcUtil;
import org.junit.Test;

import javax.sql.rowset.serial.SerialStruct;
import java.sql.Connection;
import java.util.List;

public class TestCase {
    @Test
    public void testGetConnection(){
        Connection conn = JdbcUtil.getConnection();
        if(conn==null){
            System.out.println("空");
        }
    }
    @Test
    public void testGetPassword(){
        UserDao userDao = new UserDao();
        String password = userDao.getPasswordByUserMame("admin");
        System.out.println("admin:"+password);
    }
    @Test
    public void testGetScoreList(){
        StudentDao studentDao = new StudentDao();
        List<Student> studentList = studentDao.getScoreList();
        for(Student student:studentList){
            System.out.println(student.toString());
        }
        String json = JSON.toJSONString(studentList);
        System.out.println(json);
    }
    @Test
    public void testAdd(){
        Student stu = new Student();
        stu.setId("2017214713");
        stu.setName("张三");
        stu.setDataStructure(100);
        stu.setJava(99);
        StudentDao studentDao = new StudentDao();
        int result = studentDao.addScore(stu);
        System.out.println(result);
    }

    @Test
    public void testGetStudentById(){
        StudentDao studentDao = new StudentDao();
        Student student = studentDao.getStudentById("2017214100");
        if(student!=null)
        System.out.println(student.toString());
        else System.out.println("null");
    }

    @Test
    public void testDeleteScore(){
        StudentDao studentDao = new StudentDao();
        int result = studentDao.deleteScore("2017214713");
        System.out.println(result);
    }

    @Test
    public void testQueryScoreByKeyWord(){
        StudentDao studentDao = new StudentDao();
        List<Student> studentList = studentDao.queryScoreByKeyWord("赵");
        for(Student student:studentList){
            System.out.println(student);
        }
    }

    @Test
    public void testSortScore(){
        ScoreService scoreService = new ScoreService();
        List<Student> studentList = scoreService.getSortedScore("nameType","asc");
        for(Student student:studentList){
            System.out.println(student.toString());
        }
    }
}
