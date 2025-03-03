package tsarionovtimofey.tests.info.rxjava

import android.annotation.SuppressLint
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.time.Duration
import java.time.LocalTime
import java.util.concurrent.TimeUnit

@SuppressLint("CheckResult")
fun main() {
    logThread("main")
    Observable.just("Hey")
        .subscribeOn(Schedulers.io())
        .map {
            logThread("2") // io
            it.length
        }
        .observeOn(Schedulers.newThread())
        .doOnSubscribe {
            logThread("1") // computation
        }
        .subscribeOn(Schedulers.computation())
        .flatMap {
            logThread("3") // new thread
            Observable.timer(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.single())
                .doOnSubscribe {
                    logThread("4") // new thread
                }
        }
        .subscribe {
            logThread("5") // computation
        }

    Thread.sleep(2000)
}

fun logThread(message: String = "") {
    println("Message: [$message]" + " " + threadInfo())
}

private fun threadInfo(): String {
    val thread = Thread.currentThread()

    val string = "Thread name: " + thread.name +
            " TimeOffset: " + Duration.between(programStartTime, LocalTime.now()).toMillis()

    return string
}

val programStartTime = LocalTime.now().also {
    println(it)
}