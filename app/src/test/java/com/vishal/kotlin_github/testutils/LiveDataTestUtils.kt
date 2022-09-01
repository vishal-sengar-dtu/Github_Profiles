package com.vishal.kotlin_github.testutils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch


class LiveDataTestUtils {
    fun <T> LiveData<T>.getOrAwaitValue(): T {
        var data: T? = null
        val latch = CountDownLatch(2)

        val observer = object : Observer<T> {
            override fun onChanged(t: T) {
                data = t
                this@getOrAwaitValue.removeObserver(this)
                latch.countDown()
            }
        }
        this.observeForever(observer)
        return data as T
    }

}