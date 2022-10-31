/**
  * Copyright 2022 json.cn 
  */
package org.system.vip.entity.MiGu.query;



/**
 * Auto-generated: 2022-09-20 17:24:3
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class Data {

    private BestShow bestShow;
    private String songResult;
    private String tagSongResult;
    private SongsData songsData;
    private int tagPriority;
    private int total;
    public void setBestShow(BestShow bestShow) {
         this.bestShow = bestShow;
     }
     public BestShow getBestShow() {
         return bestShow;
     }

    public void setSongResult(String songResult) {
         this.songResult = songResult;
     }
     public String getSongResult() {
         return songResult;
     }

    public void setTagSongResult(String tagSongResult) {
         this.tagSongResult = tagSongResult;
     }
     public String getTagSongResult() {
         return tagSongResult;
     }

    public void setSongsData(SongsData songsData) {
         this.songsData = songsData;
     }
     public SongsData getSongsData() {
         return songsData;
     }

    public void setTagPriority(int tagPriority) {
         this.tagPriority = tagPriority;
     }
     public int getTagPriority() {
         return tagPriority;
     }

    public void setTotal(int total) {
         this.total = total;
     }
     public int getTotal() {
         return total;
     }

}