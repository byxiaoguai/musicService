/**
  * Copyright 2021 json.cn 
  */
package org.system.vip.entity.MiGu;
import java.util.List;


/**
 * 咪咕搜索结果
 */
public class Search {

    private int total;
    private List<Items> items;
    public void setTotal(int total) {
         this.total = total;
     }
     public int getTotal() {
         return total;
     }

    public void setItems(List<Items> items) {
         this.items = items;
     }
     public List<Items> getItems() {
         return items;
     }

}