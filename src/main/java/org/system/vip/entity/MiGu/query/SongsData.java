/**
  * Copyright 2022 json.cn 
  */
package org.system.vip.entity.MiGu.query;
import java.util.List;

/**
 * Auto-generated: 2022-09-20 17:24:3
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class SongsData {

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