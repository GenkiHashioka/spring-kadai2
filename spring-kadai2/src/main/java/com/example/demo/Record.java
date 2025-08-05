package com.example.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Record {
 private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

 private LocalDateTime datetime;
 private String name;
 private String contents;
// 気分を追加する。
 private String mood;

 // レコードの引数に気分を追加。
 public Record(String name, String contents, String mood) {
  super();
  this.name = name;
  this.contents = contents;
  this.mood = mood;
  this.datetime = LocalDateTime.now();
 }

 public String getDatetime() {
  return datetime.format(fmt);
 }

 public String getName() {
  return name;
 }

 public String getContents() {
  String text = contents.replaceAll("\n", "<br>");
  // URLのリンク化
  // 正規表現でURLっぽい表現を探してaタグで囲んで処理をする。
  String url = "(https?://[\\w\\-._~:/?#\\[\\]@!$&'()*+,;=]+)";
  text = text.replaceAll(url, "<a href=\"$1\" target=\"_blank\">$1</a>");
  return text;
 }
 
// 気分のgetterを追加
 public String getMood() {
	 return mood;
 }
}
