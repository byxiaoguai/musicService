/**
  * Copyright 2022 json.cn 
  */
package org.system.vip.entity.QQ;
import java.util.List;

/**
 * Auto-generated: 2022-02-28 21:0:7
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class Param {

    private String guid;
    private List<String> songmid;
    private List<String> filename;
    private List<Integer> songtype;
    private String uin;
    private int loginflag;
    private String platform;
    public void setGuid(String guid) {
         this.guid = guid;
     }
     public String getGuid() {
         return guid;
     }

    public void setSongmid(List<String> songmid) {
         this.songmid = songmid;
     }
     public List<String> getSongmid() {
         return songmid;
     }

    public void setFilename(List<String> filename) {
         this.filename = filename;
     }
     public List<String> getFilename() {
         return filename;
     }

    public void setSongtype(List<Integer> songtype) {
         this.songtype = songtype;
     }
     public List<Integer> getSongtype() {
         return songtype;
     }

    public void setUin(String uin) {
         this.uin = uin;
     }
     public String getUin() {
         return uin;
     }

    public void setLoginflag(int loginflag) {
         this.loginflag = loginflag;
     }
     public int getLoginflag() {
         return loginflag;
     }

    public void setPlatform(String platform) {
         this.platform = platform;
     }
     public String getPlatform() {
         return platform;
     }

}