package org.system.vip.controller;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.system.vip.common.NoLog;
import org.system.vip.common.ResponseEnum;
import org.system.vip.common.ResponseMessage;
import org.system.vip.dto.PageHelp;
import org.system.vip.dto.Play;
import org.system.vip.entity.MiGu.*;
import org.system.vip.service.MiGuService;
import javax.annotation.Resource;



/**
 * @Author lz
 * @Date 2021/12/25 21:41
 * @Version 1.0
 */

@RequestMapping("/v1")
@RestController
public class MiGuController {


    @Resource
    private MiGuService miGuService;

    /**
     * 咪咕搜索
     *
     * @param pageHelp
     * @return
     */
    @NoLog
    @GetMapping("/getMiGuSearch")
    public ResponseMessage getSearch(PageHelp pageHelp) {
        ResponseMessage responseMessage = new ResponseMessage(ResponseEnum.REQUEST_SUCCESS);
//        final MiGuQuery search = miGuService.getSearch(pageHelp);
//        if (search==null||search.getData() == null||search.getData().getItems()==null) {
//            responseMessage.setData(new HashMap<String, Object>(){{
//                 put("total",null);
//                 put("items",null);
//            }});
//            return responseMessage;
//        }
//        final List<Song> songs = search.getData().getItems().stream().map(items -> {
//            List<MiGuTypeEnum>miGuTypeEnums=new ArrayList<>();
//            Song song = new Song();
//            song.setId(items.getCopyrightId());
//            song.setName(items.getName());
//            song.setSinger(String.join(",", items.getSingers().stream().map(Singers::getName).collect(Collectors.toList())));
//            song.setPic(items.getLargePic() != null ? items.getLargePic() : items.getMediumPic() != null ? items.getMediumPic() : items.getSmallPic());
//
//            if (ObjectUtil.isNotEmpty(items.getBit24())) {
//                miGuTypeEnums.add(MiGuTypeEnum.BIT24);
//            }
//            if (ObjectUtil.isNotEmpty(items.getHq())) {
//                miGuTypeEnums.add(MiGuTypeEnum.HQ);
//            }
//            if (ObjectUtil.isNotEmpty(items.getFullSong())) {
//                miGuTypeEnums.add(MiGuTypeEnum.FULL_SONG);
//            }
//            if (ObjectUtil.isNotEmpty(items.getSq())) {
//                miGuTypeEnums.add(MiGuTypeEnum.SQ);
//            }
//            song.getKbpsList().addAll(MiGuTypeEnum.getFull(miGuTypeEnums));
//            return song;
//        }).collect(Collectors.toList());
//
//        Map<String, Object> params = new HashMap<>();
//
//        params.put("total", search.getData().getTotal());
//        params.put("items", songs);
//        responseMessage.setData(params);
        return responseMessage;
    }

    /**
     * 获取歌词
     *
     * @param id
     * @return
     */
    @NoLog
    @GetMapping("/getMiGuLyric")
    public ResponseMessage getLyric( String id) {
        ResponseMessage responseMessage = new ResponseMessage(ResponseEnum.REQUEST_SUCCESS);
        final MiGuLyric lyric = miGuService.getLyric(id);
        Lyric lyric1 = new Lyric();
        lyric1.setLyric(lyric.getData().getLyric() != null ? lyric.getData().getLyric()
                : lyric.getData().getSbslyric() != null ? lyric.getData().getSbslyric()
                : lyric.getData().getTranslatedLyric());
        responseMessage.setData(lyric1);
        return responseMessage;
    }

    /**
     * 获取歌曲播放
     *
     * @param id
     * @param type
     * @return
     */
    @NoLog
    @GetMapping("/getMiGuSong")
    public ResponseMessage getSong( String id, String type) {
        ResponseMessage responseMessage = new ResponseMessage(ResponseEnum.REQUEST_SUCCESS);
        Play play = new Play();
        final MiGuSong song = miGuService.getSong(id, type);
        play.setUrl(song.getData().getPlayUrl());
        responseMessage.setData(play);
        return responseMessage;
    }
}
