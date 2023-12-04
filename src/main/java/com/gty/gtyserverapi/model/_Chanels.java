package com.gty.gtyserverapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class _Chanels {
    private int num;
    private String name;
    private String stream_type;
    private int stream_id;
    private String stream_icon;
    private String epg_channel_id;
    private String added;
    private String category_id;
    private String custom_sid;
    private int tv_archive;
    private String direct_source;
    private int tv_archive_duration;
}
