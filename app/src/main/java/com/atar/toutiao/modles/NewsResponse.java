package com.atar.toutiao.modles;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Atar on 2018/4/22.
 */

public class NewsResponse {
    public int login_status;
    public int total_number;
    public boolean has_more;
    public String post_content_hint;
    public int show_et_status;
    public int feed_flag;
    public int action_to_last_stick;
    public String message;
    public boolean has_more_to_refresh;
    public TipEntity tips;
    public List<NewsData> data;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}