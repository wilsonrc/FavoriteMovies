package com.wilsonrc.favoritemovies.base

interface BasePresenter<V : BaseView> {
    fun attach(view: V)
    fun detach()
}