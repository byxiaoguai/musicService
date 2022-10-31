package org.system.vip.dto;

import lombok.Data;

/**
 * @Author lz
 * @Date 2021/12/25 18:42
 * @Version 1.0
 */
@Data
public class PageHelp {


 /**
  * 搜索类型
  */
 private String type;
 /**
  * 搜索关键词
  */
 private String text;
 /**
  * 当前页
  */
 private String pageNo="1";
 /**
  * 每页多少
  */
 private String pageSize="30";



 public PageHelp setText(String text) {
  this.text = text;
  return this;
 }

 public PageHelp setPageNo(String pageNo) {
  this.pageNo = pageNo;
  return this;
 }

 public PageHelp setPageSize(String pageSize) {
  this.pageSize = pageSize;
  return this;
 }
}
