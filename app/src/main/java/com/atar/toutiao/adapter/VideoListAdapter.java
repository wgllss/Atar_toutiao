package com.atar.toutiao.adapter;

import android.adapter.CommonRecyclerAdapter;
import android.common.CommonHandler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atar.toutiao.R;
import com.atar.toutiao.modles.News;
import com.atar.toutiao.utils.GlideUtils;
import com.atar.toutiao.utils.TimeUtils;
import com.atar.toutiao.utils.VideoPathDecoder;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import fm.jiecao.jcvideoplayer_lib.OnVideoClickListener;
import rx.subscriptions.CompositeSubscription;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * @author ChayChan
 * @description: 视频列表的Adapter
 * @date 2018/3/22  17:13
 */

public class VideoListAdapter extends CommonRecyclerAdapter<News> {
    protected CompositeSubscription mCompositeSubscription;

    public void setmCompositeSubscription(CompositeSubscription mCompositeSubscription) {
        this.mCompositeSubscription = mCompositeSubscription;
    }

    public VideoListAdapter(List<?> list) {
        super(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        setContext(parent.getContext());
        return new ViewHoder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_list, null));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final News info = getList().get(position);
        if (info != null) {
            ((ViewHoder) holder).tv_title.setText(info.title);
            ((ViewHoder) holder).tv_duration.setText(TimeUtils.secToTime(info.video_duration));
            ((ViewHoder) holder).tv_author.setText(info.user_info.name);
            ((ViewHoder) holder).tv_comment_count.setText(info.comment_count + "");
            GlideUtils.loadRound(getContext(), info.user_info.avatar_url, ((ViewHoder) holder).iv_avatar);//设置缩略图
            GlideUtils.load(getContext(), info.video_detail_info.detail_video_large_image.url, ((ViewHoder) holder).video_player.thumbImageView, R.color.color_d8d8d8);//设置缩略图

            ((ViewHoder) holder).video_player.setAllControlsVisible(GONE, GONE, VISIBLE, GONE, VISIBLE, VISIBLE, GONE);
            ((ViewHoder) holder).video_player.tinyBackImageView.setVisibility(GONE);
            ((ViewHoder) holder).video_player.setPosition(((ViewHoder) holder).getAdapterPosition());//绑定Position

            ((ViewHoder) holder).video_player.setOnVideoClickListener(new OnVideoClickListener() {
                @Override
                public void onVideoClickToStart() {
                    ((ViewHoder) holder).video_player.setAllControlsVisible(GONE, GONE, GONE, VISIBLE, VISIBLE, VISIBLE, GONE);
                    //点击播放
                    ((ViewHoder) holder).ll_duration.setVisibility(View.GONE);//隐藏时长
                    ((ViewHoder) holder).ll_title.setVisibility(View.GONE);//隐藏标题栏
                    ((ViewHoder) holder).video_player.changeUiToPreparingShow();
                    VideoPathDecoder decoder = new VideoPathDecoder() {
                        @Override
                        public void onSuccess(final String url) {
                            CommonHandler.getInstatnce().getHandler().post(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            ((ViewHoder) holder).video_player.setUp(url, JCVideoPlayer.SCREEN_LAYOUT_LIST, info.title);
                                            ((ViewHoder) holder).video_player.seekToInAdvance = info.video_detail_info.progress;
                                            ((ViewHoder) holder).video_player.startVideo();
                                        }
                                    });
                        }

                        @Override
                        public void onDecodeError() {
                        }
                    };
                    decoder.decodePath(mCompositeSubscription, info.url);
                }
            });

        }
    }

    class ViewHoder extends RecyclerView.ViewHolder {
        @Bind(R.id.video_player)
        JCVideoPlayerStandard video_player;
        @Bind(R.id.ll_title)
        LinearLayout ll_title;
        @Bind(R.id.tv_title)
        TextView tv_title;
        @Bind(R.id.ll_duration)
        LinearLayout ll_duration;
        @Bind(R.id.tv_duration)
        TextView tv_duration;
        @Bind(R.id.iv_avatar)
        ImageView iv_avatar;
        @Bind(R.id.tv_author)
        TextView tv_author;
        @Bind(R.id.tv_comment_count)
        TextView tv_comment_count;


        public ViewHoder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
