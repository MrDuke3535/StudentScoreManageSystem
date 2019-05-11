package com.cqupt.utils;

import com.cqupt.pojo.Student;
import net.sourceforge.pinyin4j.PinyinHelper;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortUtil {

    public static List<Student> sortStudent(List<Student> studentList,String name,String type){
        if(name.trim().equals("idType")){
            if(type.trim().equals("desc")){
                Collections.sort(studentList, new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        return o2.getId().compareTo(o1.getId());
                    }
                });
            }
        }else if(name.trim().equals("nameType")){
            if(type.trim().equals("asc")){
                Collections.sort(studentList, new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        String c1 = o1.getName();
                        String c2 = o2.getName();
                        for(int i=0;i<c1.length()&&i<c2.length();i++){
                            int code1 = c1.charAt(i);
                            int code2 = c2.charAt(i);
                            if(Character.isSupplementaryCodePoint(code1)||
                                    Character.isSupplementaryCodePoint(code2)){//判断是否是增补字符集（如中文）
                                i++;
                            }
                            if(code1!=code2){
                                if (Character.isSupplementaryCodePoint(code1)||
                                        Character.isSupplementaryCodePoint(code2)){
                                    return code1-code2;
                                }
                                String pinyin1 = PinyinHelper.toHanyuPinyinStringArray((char)code1) ==null
                                        ?null :PinyinHelper.toHanyuPinyinStringArray((char)code1)[0];
                                String pinyin2 = PinyinHelper.toHanyuPinyinStringArray((char)code2) ==null
                                        ?null :PinyinHelper.toHanyuPinyinStringArray((char)code2)[0];
                                if(pinyin1!=null&&pinyin2!=null){
                                    if(!pinyin1.equals(pinyin2)){
                                        return pinyin1.compareTo(pinyin2);
                                    }
                                }else {
                                    return code1-code2;
                                }
                            }
                        }
                        return c1.length()-c2.length();
                    }
                });
            }else if(type.trim().equals("desc")){
                Collections.sort(studentList, new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        String c1 = o1.getName();
                        String c2 = o2.getName();
                        for(int i=0;i<c1.length()&&i<c2.length();i++){
                            int code1 = c1.charAt(i);
                            int code2 = c2.charAt(i);
                            if(Character.isSupplementaryCodePoint(code1)||
                                    Character.isSupplementaryCodePoint(code2)){//判断是否是增补字符集（如中文）
                                i++;
                            }
                            if(code1!=code2){
                                if (Character.isSupplementaryCodePoint(code1)||
                                        Character.isSupplementaryCodePoint(code2)){
                                    return code2-code1;
                                }
                                String pinyin1 = PinyinHelper.toHanyuPinyinStringArray((char)code1) ==null
                                        ?null :PinyinHelper.toHanyuPinyinStringArray((char)code1)[0];
                                String pinyin2 = PinyinHelper.toHanyuPinyinStringArray((char)code2) ==null
                                        ?null :PinyinHelper.toHanyuPinyinStringArray((char)code2)[0];
                                if(pinyin1!=null&&pinyin2!=null){
                                    if(!pinyin1.equals(pinyin2)){
                                        return pinyin2.compareTo(pinyin1);
                                    }
                                }else {
                                    return code2-code1;
                                }
                            }
                        }
                        return c2.length()-c1.length();
                    }
                });
            }
        }else if(name.trim().equals("dataType")){
            if(type.trim().equals("asc")){
                Collections.sort(studentList, new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        return o1.getDataStructure()-o2.getDataStructure();
                    }
                });
            }else if(type.trim().equals("desc")){
                Collections.sort(studentList, new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        return o2.getDataStructure()-o1.getDataStructure();
                    }
                });
            }
        }else if(name.trim().equals("javaType")){
            if(type.trim().equals("asc")){
                Collections.sort(studentList, new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        return o1.getJava()-o2.getJava();
                    }
                });
            }else if(type.trim().equals("desc")){
                Collections.sort(studentList, new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        return o2.getJava()-o1.getJava();
                    }
                });
            }
        }else if(name.trim().equals("sumType")||name.trim().equals("avgType")||name.trim().equals("rankType")){
            if(type.trim().equals("desc")){
                Collections.sort(studentList, new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        return o1.getJava()+o1.getDataStructure()-o2.getJava()-o2.getDataStructure();
                    }
                });
            }else if(type.trim().equals("asc")){
                Collections.sort(studentList, new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        return o2.getJava()+o2.getDataStructure()-o1.getJava()-o1.getDataStructure();
                    }
                });
            }
        }
        return studentList;
    }
}
