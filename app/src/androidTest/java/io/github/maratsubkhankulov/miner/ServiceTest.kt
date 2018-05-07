package io.github.maratsubkhankulov.miner

import android.support.test.runner.AndroidJUnit4
import android.util.Log
import io.github.maratsubkhankulov.miner.entities.Job
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Assert

import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ServiceTest {
    @Test
    fun apiServiceFetchWork() {
        val service = ApiClientService()

        Log.d("TEST", "Test string")
        var single : Single<Job> = service.getWorkRx()
        var observer: TestObserver<Job> = single.test()
        observer.awaitTerminalEvent()

        var list : List<Job> = observer.values()
        Assert.assertNotNull(list)
        Assert.assertEquals(1, list.size)
        println(list[0].jobId)
    }
}
