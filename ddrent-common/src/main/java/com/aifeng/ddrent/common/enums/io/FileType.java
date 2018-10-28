/**
 * Copyright © 2018 aifeng club All rights reserved.
 *
 * @Package: com.aifeng.ddrent.common.enums.io
 * @author imart·deng
 * @date 创建时间：2018/10/28 21:27
 * @version 1.0
 */
package com.aifeng.ddrent.common.enums.io;

/**
 * @ClassName: FileType
 * @Description: 文件类型
 * @author: imart·deng
 * @date: 2018/10/28 21:27 
 *
 */
public enum FileType {
    /** JPEG */
    JPEG("FFD8FF", ".jpg"),
    /** PNG */
    PNG("89504E47", ".png"),
    /** GIF */
    GIF("47494638", ".gif"),
    /** TIFF */
    TIFF("49492A00", ".tiff"),
    /** Windows bitmap */
    BMP("424D", ".bmp"),
    /** CAD */
    DWG("41433130", ".dwg"),
    /** Adobe photoshop */
    PSD("38425053", ".psd"),
    /** Rich Text Format */
    RTF("7B5C727466", ".rtf"),
    /** XML */
    XML("3C3F786D6C", ".xml"),
    /** HTML */
    HTML("68746D6C3E", ".html"),
    /** Outlook Express */
    DBX("CFAD12FEC5FD746F", ".dbx"),
    /** Outlook */
    PST("2142444E", ".pst"),
    /** doc;xls;dot;ppt;xla;ppa;pps;pot;msi;sdw;db */
    OLE2("0xD0CF11E0A1B11AE1", ".ole2"),
    /** Microsoft Word/Excel */
    XLS_DOC("D0CF11E0", ".xls"),
    /** Microsoft Access */
    MDB("5374616E64617264204A", ".mdb"),
    /** Word Perfect */
    WPB("FF575043", ".wpb"),
    /** Postscript */
    EPS_PS("252150532D41646F6265", ".eps"),
    /** Adobe Acrobat */
    PDF("255044462D312E", ".pdf"),
    /** Windows Password */
    PWL("E3828596", ".pwl"),
    /** ZIP Archive */
    ZIP("504B0304", ".zip"),
    /** ARAR Archive */
    RAR("52617221", ".rar"),
    /** WAVE */
    WAV("57415645", ".wave"),
    /** AVI */
    AVI("41564920", ".avi"),
    /** Real Audio */
    RAM("2E7261FD", ".ram"),
    /** Real Media */
    RM("2E524D46", ".rm"),
    /** Quicktime */
    MOV("6D6F6F76", ".mov"),
    /** Windows Media */
    ASF("3026B2758E66CF11", ".media"),
    /** MIDI */
    MID("4D546864", ".mid")
    ;
    // 文件头值
    private String value;
    // 文件后缀
    private String suffix;

    FileType(String value, String suffix) {
        this.value = value;
        this.suffix = suffix;
    }

    /**
     * 获取文件类型
     * @param value
     * @return
     */
    public static FileType getByValue(String value){
        for (FileType item: FileType.values()) {
            if(item.value.equals(value)) return item;
        }
        return null;
    }

    /**
     * 获取文件头值
     * @return  文件头值
     */
    public String getValue() {
        return value;
    }

    /**
     * 获取文件后缀命
     * @return
     */
    public String getSuffix() {
        return suffix;
    }
}
