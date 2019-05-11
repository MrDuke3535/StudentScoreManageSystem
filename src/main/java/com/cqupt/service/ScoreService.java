package com.cqupt.service;

import com.cqupt.dao.StudentDao;
import com.cqupt.pojo.Student;
import com.cqupt.utils.RankingUtil;
import com.cqupt.utils.SortUtil;
import net.sourceforge.pinyin4j.PinyinHelper;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class ScoreService {

    public List<Student> getScoreList(){
        StudentDao studentDao = new StudentDao();
        List<Student> studentList = studentDao.getScoreList();
        return RankingUtil.rankStudent(studentList);
    }

    public String addScoere(String stuId,String name,String dataStructure,String java){
        String result = "";
        StudentDao studentDao = new StudentDao();
        int da=0,ja=0;
        boolean flag = false;
        try {
            da = Integer.parseInt(dataStructure);
            ja = Integer.parseInt(java);
        }catch (Exception e){
            result = "成绩不能包含非数字";
            flag = true;
        }
        if(flag) return result;
        if(da<0||da>100||ja<0||ja>100){
            return "成绩只能在0-100之间.";
        }
        Student stu = studentDao.getStudentById(stuId);
        if(stu!=null){
            return "该学生已存在";
        }
        Student student = new Student();
        student.setId(stuId);
        student.setName(name);
        student.setDataStructure(da);
        student.setJava(ja);
        int changeNum = studentDao.addScore(student);
        if(changeNum>0){
            return "success";
        }else {
            return "error";
        }
    }

    public String updateScore(String stuId,String name,String dataStructure,String java){
        String result = null;
        if(name==null||name.trim().equals("")){
            result = "学生姓名不能为空！";
        }else{
            int da=0,ja=0;
            boolean flag = false;
            try{
                da = Integer.parseInt(dataStructure);
                ja = Integer.parseInt(java);
            }catch (Exception e){
                flag = true;
                result = "成绩不能包含非数字";
            }
            if(flag){
                return result;
            }
            if(da>100||da<0||ja>100||ja<0){
                result = "成绩只能在0-100之间！";
            }else{
                Student stu = new Student();
                stu.setId(stuId);
                stu.setDataStructure(da);
                stu.setJava(ja);
                stu.setName(name);
                StudentDao studentDao = new StudentDao();
                if(studentDao.updateScore(stu)>0){
                    result = "success";
                }else {
                    result = "fail";
                }
            }
        }
        return result;
    }

    public String deleteScore(String stuId){
        StudentDao studentDao = new StudentDao();
        int result = studentDao.deleteScore(stuId);
        if(result>0){
            return "success";
        }else {
            return "fail";
        }
    }

    public List<Student> queryScoreByKeyWord(String keyWord){
        StudentDao studentDao = new StudentDao();
        List<Student> studentList = studentDao.queryScoreByKeyWord(keyWord);
        List<Student> students = RankingUtil.rankStudent(studentDao.getScoreList());
        for(Student stu:students){
            for(Student s:studentList){
                if(s.getId().equals(stu.getId())){
                    s.setRank(stu.getRank());
                }
            }
        }
        return studentList;
    }

    public List<Student> getSortedScore(String name,String type){
        List<Student> studentList = getScoreList();
        return SortUtil.sortStudent(studentList,name,type);
    }

}
