package io.github.maratsubkhankulov.miner;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;

import io.github.maratsubkhankulov.miner.entities.Job;
import io.github.maratsubkhankulov.miner.entities.SolutionRequest;
import io.github.maratsubkhankulov.miner.entities.SubmitResponse;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by msubkhankulov on 5/6/18.
 */

public class ServiceTest {
    private ApiClientService service;

    @Before
    public void prepare() {
        service = new ApiClientService();
    }

    @Test
    public void getWork() throws IOException {
        Job job = service.getWork();
        assertNotNull(job);
        System.out.println(job.jobId);
    }

    @Test
    public void postSubmit() throws IOException {
        SubmitResponse response = service.postSubmit(BigInteger.valueOf(1));
        assertNotNull(response);
        System.out.println(response.correctSolution);
    }
}
