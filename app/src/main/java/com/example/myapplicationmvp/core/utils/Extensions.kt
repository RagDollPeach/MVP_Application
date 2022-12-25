package com.example.myapplicationmvp.core.utils

import android.view.View
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.makeGone() {
    this.visibility = View.GONE
}

fun View.makeInvisible() {
    this.visibility = View.INVISIBLE
}

fun Disposable.disposeBy(bag: CompositeDisposable) {
    bag.add(this)
}

fun <T : Any> Single<T>.doCompletableIf(
    predicate: Boolean,
    completable: (data: T) -> Completable): Single<T> {
    return if (predicate) {
        this.flatMap { completable(it).andThen(Single.just(it)) }
    } else { this }
}
