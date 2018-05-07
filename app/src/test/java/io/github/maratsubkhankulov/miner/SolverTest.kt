package io.github.maratsubkhankulov.miner

import org.bouncycastle.util.encoders.Hex
import org.junit.Test

import org.junit.Assert.*
import java.math.BigInteger

class SolverTest {
    @Test
    fun hashcashTest() {
        val solver = Solver()

        val blockVersionNumber = BigInteger.valueOf(536870912).toByteArray()
        val hashPrevBlock = Hex.decode("0000000000000000003729BFA376E4148EE0643CE834053D885AF5699440D6D2")
        val hashMerkleRoot = Hex.decode("DD6902C19AFA94D68A5060D95C82DDA188F234B7B980279AD47344107201F041")
        val timestamp = BigInteger.valueOf(1525421373).toByteArray()
        val bits = Hex.decode("1745FB53")
        val nonce = BigInteger.valueOf(3225537244).toByteArray()

        assertEquals(String(Hex.encode(blockVersionNumber)).toUpperCase(), "20000000")
        assertEquals(String(Hex.encode(hashPrevBlock)).toUpperCase(), "0000000000000000003729BFA376E4148EE0643CE834053D885AF5699440D6D2")
        assertEquals(String(Hex.encode(hashMerkleRoot)).toUpperCase(), "DD6902C19AFA94D68A5060D95C82DDA188F234B7B980279AD47344107201F041")
        assertEquals(String(Hex.encode(bits)).toUpperCase(), "1745FB53")

        val hash = solver.hashcash(blockVersionNumber, hashPrevBlock, hashMerkleRoot, timestamp, bits, nonce)
        println(String(Hex.encode(hash)))
    }
}
