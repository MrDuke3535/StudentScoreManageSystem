package com.cqupt.pojo;

public class Student {
    private String id;
    private String name;
    private int dataStructure;
    private int java;
    private int rank;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDataStructure() {
        return dataStructure;
    }

    public void setDataStructure(int dataStructure) {
        this.dataStructure = dataStructure;
    }

    public int getJava() {
        return java;
    }

    public void setJava(int java) {
        this.java = java;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dataStructer=" + dataStructure +
                ", java=" + java +
                ", rank=" + rank +
                '}';
    }
}
