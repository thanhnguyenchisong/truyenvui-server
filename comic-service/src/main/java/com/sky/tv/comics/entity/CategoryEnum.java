package com.sky.tv.comics.entity;

public enum CategoryEnum {
  ROMANTIC, ADVENTURE, LGBT, UNKNOWN;

}
 class A {

   public static void main(String[] args) {
     String a = "ROMANTIC";
     CategoryEnum c = CategoryEnum.valueOf(a);
     System.out.println(c.name());
   }
 }
