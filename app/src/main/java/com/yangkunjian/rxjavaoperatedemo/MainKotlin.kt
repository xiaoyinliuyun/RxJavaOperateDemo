package com.yangkunjian.rxjavaoperatedemo

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

object MainKotlin {
    @JvmStatic
    fun main(args: Array<String>) {
        println("main")
//        createObservable()
        justObservable()
    }

    private fun justObservable() {
        Observable.just(-1, 2, -3, 0,-4)
            .filter { it >= 0 }
            .subscribe(object : Observer<Int> {
            override fun onSubscribe(d: Disposable?) {

            }

            override fun onNext(t: Int?) {
                println("onNext -> $t")
            }

            override fun onError(e: Throwable?) {

            }

            override fun onComplete() {
                println("onComplete")
            }

        })
    }

    private fun createObservable() {
        Observable.create<String> { emitter ->
            emitter!!.onNext("1")
            emitter.onNext("2")
            emitter.onNext("1")
            emitter.onComplete()
        }.doOnNext { s -> println("doOnNext accept -> $s") }
            .doAfterNext { s -> println("doAfterNext accept -> $s") }
            .doOnComplete { println("doOnComplete run") }
            .subscribe(object : Observer<String?> {
                override fun onSubscribe(d: @NonNull Disposable?) {}
                override fun onNext(s: @NonNull String?) {
                    println("onNext -> $s")
                }

                override fun onError(e: @NonNull Throwable?) {}
                override fun onComplete() {
                    println("onComplete")
                }
            })

        Observable.create<String> { emitter ->
            // 通过create API 把一个回调风格的调用世界的事件，转移成响应世界的事件
            emitter!!.onNext("1")
            emitter.onNext("2")
            emitter.onNext("1")
            emitter.onComplete()
        }.doOnNext {

        }.doOnComplete {

        }.subscribe(object : Observer<String?> {
            override fun onSubscribe(d: @NonNull Disposable?) {}
            override fun onNext(s: @NonNull String?) {
                println("onNext -> $s")
            }

            override fun onError(e: @NonNull Throwable?) {}
            override fun onComplete() {
                println("onComplete")
            }
        })

        Observable.create<Int> {
            it.onNext(4)
            it.onNext(5)
            it.onNext(6)
            it.onComplete()
        }.subscribe(object : Observer<Int> {
            override fun onSubscribe(d: Disposable?) {}

            override fun onNext(t: Int?) {
                println("onNext -> $t")
            }

            override fun onError(e: Throwable?) {}

            override fun onComplete() {
                println("onComplete")
            }
        })
    }
}