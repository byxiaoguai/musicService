package org.system.vip.controller;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.system.vip.common.MiGuTypeEnum;
import org.system.vip.common.NoLog;
import org.system.vip.common.ResponseEnum;
import org.system.vip.common.ResponseMessage;
import org.system.vip.dto.PageHelp;
import org.system.vip.dto.Play;
import org.system.vip.dto.Song;
import org.system.vip.entity.MiGu.MiGuLyric;
import org.system.vip.entity.MiGu.MiGuSong;
import org.system.vip.entity.MiGu.query.MiGuQuery;
import org.system.vip.entity.MiGu.query.Singers;
import org.system.vip.entity.QQ.QQQuery;
import org.system.vip.service.MiGuService;
import org.system.vip.service.QQService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 音乐接口
 * @author lz
 * @date 2022/9/20 15:57
 */
@RestController
public class MusicController {

    @Resource
    private MiGuService miGuService;

    @Autowired
    private QQService qqService;


    /**
     * 音乐搜索
     * @param pageHelp
     * @return
     * @throws Exception
     */
    @NoLog
    @GetMapping("/api/v1/getSearch")
    public ResponseMessage getSearch(PageHelp pageHelp) throws Exception {
        ResponseMessage responseMessage = new ResponseMessage(ResponseEnum.REQUEST_SUCCESS);
        if (pageHelp.getType() != null) {

            switch (pageHelp.getType()) {
                case "1": //qq音乐
                    final QQQuery search = qqService.getSearchV2(pageHelp);
                    if (search == null  || search.getSongList() == null) {
                        responseMessage.setData(new HashMap<String, Object>() {{
                            put("total", null);
                            put("items", null);
                        }});
                        return responseMessage;
                    }

                    Map<String, Object> params = new HashMap<>();
                    params.put("total", search.getTotal());
                    params.put("items", search.getSongList());
                    responseMessage.setData(params);
                    break;
                case "2": //咪咕音乐
                    final MiGuQuery miGuQuery = miGuService.getSearch(pageHelp);
                    if (miGuQuery==null||miGuQuery.getData() == null) {
                        responseMessage.setData(new HashMap<String, Object>(){{
                            put("total",null);
                            put("items",null);
                        }});
                        return responseMessage;
                    }
                    final List<Song> songs = miGuQuery.getData().getSongsData().getItems().stream().map(items -> {
                        List<MiGuTypeEnum>miGuTypeEnums=new ArrayList<>();
                        Song song = new Song();
                        song.setId(items.getCopyrightId());
                        song.setName(items.getName());
                        song.setSinger(String.join(",", items.getSingers().stream().map(Singers::getName).collect(Collectors.toList())));
                        song.setPic(items.getLargePic() != null ? items.getLargePic() : items.getMediumPic() != null ? items.getMediumPic() : items.getSmallPic());

                        if (ObjectUtil.isNotEmpty(items.getBit24())) {
                            miGuTypeEnums.add(MiGuTypeEnum.BIT24);
                        }
                        if (ObjectUtil.isNotEmpty(items.getHq())) {
                            miGuTypeEnums.add(MiGuTypeEnum.HQ);
                        }
                        if (ObjectUtil.isNotEmpty(items.getFullSong())) {
                            miGuTypeEnums.add(MiGuTypeEnum.FULL_SONG);
                        }
                        if (ObjectUtil.isNotEmpty(items.getSq())) {
                            miGuTypeEnums.add(MiGuTypeEnum.SQ);
                        }
                        song.getKbpsList().addAll(MiGuTypeEnum.getFull(miGuTypeEnums));
                        return song;
                    }).collect(Collectors.toList());

                    Map<String, Object> objectMap = new HashMap<>();

                    objectMap.put("total", miGuQuery.getData().getSongsData().getTotal());
                    objectMap.put("items", songs);
                    responseMessage.setData(objectMap);
                    break;

            }
        }





        return responseMessage;
    }


    /**
     * 获取歌词
     *
     * @param id
     * @return
     */
    @NoLog
    @GetMapping("/api/v1/getLyric")
    public ResponseMessage getLyric(String id,String type) {
        ResponseMessage responseMessage = new ResponseMessage(ResponseEnum.REQUEST_SUCCESS);
        if (type != null) {
            switch (type) {
                case "1":
                    responseMessage.setData(qqService.getLyric(id));
                    break;
                case "2":
                    final MiGuLyric lyric = miGuService.getLyric(id);
//                    Lyric lyric1 = new Lyric();
//                    lyric1.setLyric(lyric.getData().getLyric() != null ? lyric.getData().getLyric()
//                            : lyric.getData().getSbslyric() != null ? lyric.getData().getSbslyric()
//                            : lyric.getData().getTranslatedLyric());
                    responseMessage.setData(lyric.getData().getLyric() != null ? lyric.getData().getLyric()
                            : lyric.getData().getSbslyric() != null ? lyric.getData().getSbslyric()
                            : lyric.getData().getTranslatedLyric());
                    break;
            }
        }


        return responseMessage;
    }

    /**
     * 获取歌曲播放
     *
     * @param id
     * @return
     */
    @NoLog
    @GetMapping("/api/v1/getSong")
    public ResponseMessage getSong(String type,String id,  String code) {
        ResponseMessage responseMessage = new ResponseMessage(ResponseEnum.REQUEST_SUCCESS);
        if (type != null) {
            switch (type) {
                case "1":
                    final Play play = qqService.getPlay(id, code);
                    responseMessage.setData(play);
                    break;
                case "2":
                    Play play1 = new Play();
                    final MiGuSong song = miGuService.getSong(id, code);
                    play1.setUrl(song.getData().getPlayUrl());
                    responseMessage.setData(play1);
                    break;
            }
        }

        return responseMessage;
    }
}
