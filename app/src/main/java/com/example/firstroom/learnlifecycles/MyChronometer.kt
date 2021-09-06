package com.example.firstroom.learnlifecycles

import android.content.Context
import android.os.Build
import android.os.SystemClock
import android.util.AttributeSet
import android.widget.Chronometer
import androidx.annotation.RequiresApi
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class MyChronometer(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : Chronometer(context, attrs, defStyleAttr,defStyleRes), LifecycleObserver {

    constructor(context: Context) : this(context,null)
    //Todo 一定要重写下面的这个两个参数的构造器，因为从xml文件里解析后反射是找的两个参数的构造器，如果反射的时候找不到这个方法会报错！！！
    //虽然你的主构造函数也可以匹配两个参数的情况，但你编译成java文件就能看到，他是反编译是一个四个参数的，是没有两参构造的
    constructor(context: Context,attributeSet: AttributeSet?) : this(context,attributeSet,0)
    constructor(context: Context,attributeSet: AttributeSet?,defStyleAttr: Int) : this(context,attributeSet,0,defStyleAttr)

    var leave: Long = 0

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private fun pauseMeter() {
        leave = SystemClock.elapsedRealtime() - base
        stop()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun resumeMeter() {
        base = SystemClock.elapsedRealtime() - leave
        start()
    }


}