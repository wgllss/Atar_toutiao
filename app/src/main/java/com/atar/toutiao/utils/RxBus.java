package com.atar.toutiao.utils;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

/**
 * 描述：事件总线
 * Created by Lee
 * on 2016/9/19.
 */
public class RxBus {
    private ConcurrentMap<Object,List<Subject>> SubjectMap = new ConcurrentHashMap<Object, List<Subject>>();
    private static RxBus sInstance;
    public static RxBus getInstance(){
        if(sInstance == null){
            sInstance = new RxBus();
        }
        return sInstance;
    }

    /**
     * 订阅事件
     * @param tag
     * @param <T>
     * @return
     */
    public <T> Observable<T> register(@NonNull Object tag){
        List<Subject> SubjectList = SubjectMap.get(tag);
        if(SubjectList == null){
            SubjectList = new ArrayList<Subject>();
            SubjectMap.put(tag,SubjectList);
        }
        Subject<T,T> subject;
        SubjectList.add(subject =  PublishSubject.create());
        return subject;
    }

    /**
     * 取消订阅
     * @param tag
     */
    public void unRegister(@NonNull Object tag){
        List<Subject> SubjectList = SubjectMap.get(tag);
        if(SubjectList!=null){
            SubjectMap.remove(tag);
        }
    }

    /**
     * 发送消息
     * @param tag
     */
    public void post(@NonNull Object tag){
        post(tag.getClass().getSimpleName(),tag);
    }

    private void post(@NonNull Object tag, @NonNull Object o){
        List<Subject> SubjectList = SubjectMap.get(tag);
        if(SubjectList!=null && !isEmpty(SubjectList)){
            for(Subject s : SubjectList){
                s.onNext(o);
            }
        }
    }

    public static boolean isEmpty(Collection<Subject> collection) {
        return null == collection || collection.isEmpty();
    }
}
