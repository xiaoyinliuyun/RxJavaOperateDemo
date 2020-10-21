package com.yangkunjian.rxjavaoperatedemo;


import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;

public class MainJava {

    public static void main(String[] args) {
        System.out.println("main");
        createObservable();
    }

    private static void createObservable() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onNext("1");
                emitter.onComplete();
            }
        }).doOnNext(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                System.out.println("doOnNext accept -> " + s);
            }
        }).doAfterNext(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                System.out.println("doAfterNext accept -> " + s);
            }
        }).doOnComplete(new Action() {
            @Override
            public void run() throws Throwable {
                System.out.println("doOnComplete run");
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String s) {
                System.out.println("onNext -> " + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });


    }
}
