package com.eyelevel.project.category.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.eyelevel.project.category.entity.TeacherProfile;

public class TeacherImpl extends User {
   
   private String teacherNo;
   private String teacherName;
   private String teacherPicture;
   private String teacherAddress;
   private String teacherPhone;
   private String teacherEmail;
   private String teacherId;
   private String teacherPw;
   private String teacherEnt;
   private String teacherLevel;
   private String teacherPr;
   
   public TeacherImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
      super(username, password, authorities);
   }
   
   public void setDetails(TeacherProfile teacher) {
      this.teacherNo = teacher.getTeacherNo();
      this.teacherName = teacher.getTeacherName();
      this.teacherPicture = teacher.getTeacherPicture();
      this.teacherAddress = teacher.getTeacherAddress();
      this.teacherPhone = teacher.getTeacherPhone();
      this.teacherEmail = teacher.getTeacherEmail();
      this.teacherId = teacher.getTeacherId();
      this.teacherPw = "[PROTECTED]";
      this.teacherEnt = teacher.getTeacherEnt();
      this.teacherLevel = teacher.getTeacherLevel();
      this.teacherPr = teacher.getTeacherPr();
   }

   public String getTeacherNo() {
      return teacherNo;
   }

   public String getTeacherName() {
      return teacherName;
   }

   public String getTeacherPicture() {
      return teacherPicture;
   }

   public String getTeacherAddress() {
      return teacherAddress;
   }

   public String getTeacherPhone() {
      return teacherPhone;
   }

   public String getTeacherEmail() {
      return teacherEmail;
   }

   public String getTeacherId() {
      return teacherId;
   }

   public String getTeacherPw() {
      return teacherPw;
   }

   public String getTeacherEnt() {
      return teacherEnt;
   }

   public String getTeacherLevel() {
      return teacherLevel;
   }

   public String getTeacherPr() {
      return teacherPr;
   }

   @Override
   public String toString() {
      return "TeacherImpl [teacherNo=" + teacherNo + ", teacherName=" + teacherName + ", teacherPicture="
            + teacherPicture + ", teacherAddress=" + teacherAddress + ", teacherPhone=" + teacherPhone
            + ", teacherEmail=" + teacherEmail + ", teacherId=" + teacherId + ", teacherPw=" + teacherPw
            + ", teacherEnt=" + teacherEnt + ", teacherLevel=" + teacherLevel + ", teacherPr=" + teacherPr + "]";
   }
}