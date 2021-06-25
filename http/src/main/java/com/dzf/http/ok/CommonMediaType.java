package com.dzf.http.ok;

import java.io.File;

import okhttp3.MediaType;


/**
 * Created by zzbmi on 2016/2/20.
 */

public class CommonMediaType {
    /**
     * 根据文件的后缀识别MediaType
     * @param file
     * @return
     */
    public static MediaType getMediaType(File file){
        MediaType mediaType = null;
        String name = file.getName();
        String subffix = name.substring(name.lastIndexOf("."));
        subffix = subffix.toLowerCase();
        switch (subffix){
            case "png":
                mediaType = MediaType.parse("image/png");
                break;
            case "jpg":
            case "jpeg":
                mediaType = MediaType.parse("image/jpeg");
                break;
            case "webp":
                mediaType = MediaType.parse("image/webp");
                break;
            case "xml":
                mediaType = MediaType.parse("application/xml");
                break;
            case "md":
                mediaType = MediaType.parse("text/x-markdown");
                break;
            case "3gp":
                mediaType = MediaType.parse("video/3gp");
                break;
            case "mp4":
                mediaType = MediaType.parse("video/mp4");
                break;
            case "mp3":
                mediaType = MediaType.parse("audio/mpeg");
                break;
            case "avi":
                mediaType = MediaType.parse("video/x-msvideo");
                break;
            case "rmvb":
                mediaType = MediaType.parse("application/vnd.rn-realmedia-vbr");
                break;
            case "apk":
                mediaType = MediaType.parse("application/vnd.android.package-archive");
                break;
            case "exe":
            case "zip":
                mediaType = MediaType.parse("application/octet-stream");
                break;
            case "js":
                mediaType = MediaType.parse("application/javascript");
                break;
            case "html":
            case "htm":
                mediaType = MediaType.parse("text/html");
                break;
            case "text":
            case "txt":
                mediaType = MediaType.parse("text/plain");
                break;
            case "json":
                mediaType = MediaType.parse("application/json");
                break;
            default:
                mediaType = MediaType.parse("application/octet-stream");
                break;

        }
        return mediaType;
    }
}
