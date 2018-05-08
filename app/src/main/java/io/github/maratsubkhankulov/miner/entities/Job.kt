package io.github.maratsubkhankulov.miner.entities

import io.github.maratsubkhankulov.miner.entities.BlockHeader

/**
 * Created by msubkhankulov on 5/6/18.
 */

class Job {
    var jobId: String? = null
    var clientId: String? = null
    var nonceRange: Int? = null
    var blockHeader: BlockHeader? = null
}
