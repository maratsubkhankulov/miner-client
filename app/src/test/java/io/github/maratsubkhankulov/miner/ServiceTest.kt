package io.github.maratsubkhankulov.miner

import org.junit.Before
import org.junit.Test

import java.io.IOException
import java.math.BigInteger

import io.github.maratsubkhankulov.miner.entities.Job
import io.github.maratsubkhankulov.miner.entities.SolutionRequest
import io.github.maratsubkhankulov.miner.entities.SubmitResponse

import junit.framework.Assert.assertNotNull

/**
 * Created by msubkhankulov on 5/6/18.
 */

class ServiceTest {
    private var service: ApiClientService? = null

    @Before
    fun prepare() {
        service = ApiClientService()
    }

    @Test
    @Throws(IOException::class)
    fun getWork() {
        val job = service!!.work
        assertNotNull(job)
        println(job!!.jobId)
    }

    @Test
    @Throws(IOException::class)
    fun postSubmit() {
        val response = service!!.postSubmit(1)
        assertNotNull(response)
        println(response!!.correctSolution)
    }
}
