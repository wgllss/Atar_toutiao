package com.atar.toutiao.net;


import com.atar.toutiao.modles.NewsResponse;
import com.atar.toutiao.modles.VideoPathResponse;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Sunflower on 2015/11/4.
 */
public interface APIService {
    String GET_ARTICLE_LIST = "api/news/feed/v62/?refer=1&count=20&loc_mode=4&device_id=34960436458&iid=13136511752";
    String GET_COMMENT_LIST = "article/v2/tab_comments/";


//    /**
//     * 获取帖子分类列表
//     *
//     * @return
//     */
//    @POST("api/gravida/article/categories.json")
//    Observable<Response<List<ArticleCategory>>> getArticleCategory();
//
//    /**
//     * 根据分类获取帖子列表
//     *
//     * @param id         分类id
//     * @param pageNumber
//     * @param pageSize
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("api/gravida/article/list.json")
//    Observable<Response<List<ArticleListDTO>>> getArticleList(@Field("id") long id,
//                                                              @Field("pageNumber") int pageNumber,
//                                                              @Field("pageSize") int pageSize);
//
//    /**
//     * �?��版本
//     *
//     * @param version
//     * @param type
//     * @param device
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("api/common/version.json")
//    Observable<Response<VersionDto>> checkVersion(@Field("version") String version,
//                                                  @Field("type") String type,
//                                                  @Field("device") String device);
//
//    /**
//     * 获取个人信息
//     *
//     * @param id
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("api/gravida/personal/info.json")
//    Observable<Response<PersonalInfo>> getPersonalInfo(@Field("id") String id);
//
//    /**
//     * 获取个人配置信息
//     *
//     * @param id
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("api/gravida/personal/configs.json")
//    Observable<Response<PersonalConfigs>> getPersonalConfigs(@Field("id") String id);
//
//    @Multipart
//    @POST("api/gravida/personal/update.json")
//    Observable<Response<PersonalInfo>> updatePersonalInfo(@Part("avatar") RequestBody avatar,
//                                                          @Part("id") String id);
//
//    @Multipart
//    @POST("api/gravida/personal/update.json")
//    Observable<Response<PersonalInfo>> updatePersonalInfo(@PartMap Map<String, RequestBody> params);
//
//    /**
//     * 测试用对象作为参数，失败
//     *
//     * @param info
//     * @return
//     */
//    @POST("api/gravida/personal/update.json")
//    Observable<Response<PersonalInfo>> updatePersonalInfo(@Body PersonalInfo info);
//
//
//    @Multipart
//    @POST("api/gravida/product/comment.json")
//    Observable<Response<Object>> commentProduct(@PartMap Map<String, RequestBody> params);
//
//
//    @FormUrlEncoded
//    @POST("api/gravida/remind/flow.json")
//    Observable<Response<List<RemindDTO>>> getNotificationList(@Field("id") String id);
//
//
//    /**
//     * 取消收藏帖子
//     * 与{@link #cancelFavorite(String, List)}效果�?��
//     *
//     * @param id
//     * @param articleId 传�?的为数组
//     * @return
//     */
//    @POST("api/gravida/article/unfavourite.json")
//    Observable<Response<Object>> cancelFavoriteWithQuery(@Query("id") String id, @Query("articleId") List<Long> articleId);
//
//
//    @FormUrlEncoded
//    @POST("api/gravida/article/unfavourite.json")
//    Observable<Response<Object>> cancelFavorite(@Field("id") String id, @Field("articleId") List<Long> articleId);

    @GET(GET_ARTICLE_LIST)
    Observable<NewsResponse> getNewsList(@Query("category") String category, @Query("min_behot_time") long lastTime, @Query("last_refresh_sub_entrance_interval") long currentTime);

    @Headers({
            "Content-Type:application/x-www-form-urlencoded; charset=UTF-8",
            "Cookie:PHPSESSIID=334267171504; _ga=GA1.2.646236375.1499951727; _gid=GA1.2.951962968.1507171739; Hm_lvt_e0a6a4397bcb500e807c5228d70253c8=1507174305;Hm_lpvt_e0a6a4397bcb500e807c5228d70253c8=1507174305; _gat=1",
            "Origin:http://toutiao.iiilab.com"

    })
    @POST("http://service.iiilab.com/video/toutiao")
    Observable<VideoPathResponse> getVideoPath(@Query("link") String link, @Query("r") String r, @Query("s") String s);
}
