package com.example.aop.pojo;

import java.io.Serializable;
import java.math.BigInteger;

public class Student implements Serializable {
        private BigInteger id;
        private String studentName;
        private String gender;
        private String birthday;
        private String enterYear;
        private String graduateYear;
        private String city;
        private String workplace;
        private String job;
        private String phone;
        private String email;
        private String wechat;

        public BigInteger getId() {
                return id;
        }

        public void setId(BigInteger id) {
                this.id = id;
        }

        public String getStudentName() {
                return studentName;
        }

        public void setStudentName(String studentName) {
                this.studentName = studentName;
        }

        public String getGender() {
                return gender;
        }

        public void setGender(String gender) {
                this.gender = gender;
        }

        public String getBirthday() {
                return birthday;
        }

        public void setBirthday(String birthday) {
                this.birthday = birthday;
        }

        public String getEnterYear() {
                return enterYear;
        }

        public void setEnterYear(String enterYear) {
                this.enterYear = enterYear;
        }

        public String getGraduateYear() {
                return graduateYear;
        }

        public void setGraduateYear(String graduateYear) {
                this.graduateYear = graduateYear;
        }

        public String getCity() {
                return city;
        }

        public void setCity(String city) {
                this.city = city;
        }

        public String getWorkplace() {
                return workplace;
        }

        public void setWorkplace(String workplace) {
                this.workplace = workplace;
        }

        public String getJob() {
                return job;
        }

        public void setJob(String job) {
                this.job = job;
        }

        public String getPhone() {
                return phone;
        }

        public void setPhone(String phone) {
                this.phone = phone;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getWechat() {
                return wechat;
        }

        public void setWechat(String wechat) {
                this.wechat = wechat;
        }

        @Override
        public String toString() {
                return "studentName='" + studentName + '\'' +
                        ", gender='" + gender + '\'' +
                        ", birthday='" + birthday + '\'' +
                        ", enterYear='" + enterYear + '\'' +
                        ", graduateYear='" + graduateYear + '\'' +
                        ", city='" + city + '\'' +
                        ", workplace='" + workplace + '\'' +
                        ", job='" + job + '\'' +
                        ", phone='" + phone + '\'' +
                        ", email='" + email + '\'' +
                        ", wechat='" + wechat + '\'' +
                        '\n';
        }
}