package com.cqupt.utils;

import com.cqupt.pojo.Student;

import java.util.List;

public class RankingUtil {
    public static List<Student> rankStudent(List<Student> students){
        for(int i=0;i<students.size();i++){
            int max=Integer.MIN_VALUE;
            Student maxStu = new Student();
            for(int j=0;j<students.size();j++){
                if(students.get(j).getRank()==0&&students.get(j).getDataStructure()+students.get(j).getJava()>max){
                    max = students.get(j).getDataStructure()+students.get(j).getJava();
                    maxStu=students.get(j);
                }
            }
            maxStu.setRank(i+1);
        }
        return students;
    }
}
