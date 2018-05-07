package io.github.maratsubkhankulov.miner

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.github.maratsubkhankulov.miner.entities.Job
import org.bouncycastle.util.encoders.Hex
import java.math.BigInteger
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var service = ApiClientService()
        var solver = Solver()

        val scheduler = Executors.newSingleThreadScheduledExecutor()

        scheduler.scheduleAtFixedRate({
            run(service, solver)
        }, 0, 10, TimeUnit.MINUTES)
    }

    private fun run(service: ApiClientService, solver: Solver) {
        var job: Job = service.work
        var nonce = job.blockHeader.nonce
        var nonceRange = job.nonceRange
        var data = job.blockHeader.toByteArray()

        for (i in 1..nonceRange) {

            var hash = solver.hashcash(data)

            if (solver.isSolution(hash, job.blockHeader.difficultyTarget)) {
                service.postSubmit(nonce)
            }

            // Increment nonce and update data
            nonce += i
            var nonceByteArray = BigInteger.valueOf(nonce.toLong()).toByteArray()
            data.set(75, nonceByteArray[0])
            data.set(76, nonceByteArray[1])
            data.set(78, nonceByteArray[2])
            data.set(79, nonceByteArray[3])
        }
    }
}
