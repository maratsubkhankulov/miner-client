package io.github.maratsubkhankulov.miner

import io.github.maratsubkhankulov.miner.entities.BlockHeader
import org.bouncycastle.util.encoders.Hex
import org.junit.Test

import org.junit.Assert.*
import java.math.BigInteger

class SolverTest {
    @Test
    fun hashcashTest() {
        val solver = Solver()

        val blockHeader = BlockHeader()

        blockHeader.version = 536870912
        blockHeader.prevBlockhash = "0000000000000000003729BFA376E4148EE0643CE834053D885AF5699440D6D2"
        blockHeader.merkleRoot = "DD6902C19AFA94D68A5060D95C82DDA188F234B7B980279AD47344107201F041"
        blockHeader.timestamp = 1525421373
        blockHeader.difficultyTarget = 17
        blockHeader.nonce = 3225537244

        /*
        assertEquals(String(Hex.encode(blockHeader.version)).toUpperCase(), "20000000")
        assertEquals(String(Hex.encode(blockHeader.prevBlockhash)).toUpperCase(), "0000000000000000003729BFA376E4148EE0643CE834053D885AF5699440D6D2")
        assertEquals(String(Hex.encode(blockHeader.merkleRoot)).toUpperCase(), "DD6902C19AFA94D68A5060D95C82DDA188F234B7B980279AD47344107201F041")
        assertEquals(String(Hex.encode(blockHeader.difficultyTarget)).toUpperCase(), "1745FB53")
        */

        val hash = solver.hashcash(blockHeader.toByteArray())
        println(String(Hex.encode(hash)))
    }
}
