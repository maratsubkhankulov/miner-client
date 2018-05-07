package io.github.maratsubkhankulov.miner.entities;

import io.github.maratsubkhankulov.miner.entities.BlockHeader;

/**
 * Created by msubkhankulov on 5/6/18.
 */

public class Job {
    public String jobId;
    public String clientId;
    public Integer nonceRange;
    public BlockHeader blockHeader;
}
