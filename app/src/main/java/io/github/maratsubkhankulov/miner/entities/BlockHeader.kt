package io.github.maratsubkhankulov.miner.entities

import java.math.BigInteger

/**
 * Created by msubkhankulov on 5/6/18.
 */

class BlockHeader {
    var version: Long? = null
    var prevBlockhash: String? = null
    var merkleRoot: String? = null
    var timestamp: Long? = null
    var difficultyTarget: Long? = null
    var nonce: Long? = null

    fun toByteArray(): ByteArray {
        return ByteArray(80)
        /*

        blockHeader.version = BigInteger.valueOf(536870912).toByteArray()
        blockHeader.prevBlockhash = Hex.decode("0000000000000000003729BFA376E4148EE0643CE834053D885AF5699440D6D2")
        blockHeader.merkleRoot = Hex.decode("DD6902C19AFA94D68A5060D95C82DDA188F234B7B980279AD47344107201F041")
        blockHeader.timestamp = BigInteger.valueOf(1525421373).toByteArray()
        blockHeader.difficultyTarget = Hex.decode("1745FB53")
        blockHeader.nonce = BigInteger.valueOf(3225537244).toByteArray()

        val data = ByteArray(blockVersionNumber.size + hashPrevBlock.size + hashMerkleRoot.size + timestamp.size + bits.size + nonce.size)
        var offset = 0

        System.arraycopy(blockVersionNumber, 0, data, offset, blockVersionNumber.size)
        offset += blockVersionNumber.size

        System.arraycopy(hashPrevBlock, 0, data, offset, hashPrevBlock.size)
        offset += hashPrevBlock.size

        System.arraycopy(hashMerkleRoot, 0, data, offset, hashMerkleRoot.size)
        offset += hashMerkleRoot.size

        System.arraycopy(timestamp, 0, data, offset, timestamp.size)
        offset += timestamp.size

        System.arraycopy(bits, 0, data, offset, bits.size)
        offset += bits.size

        System.arraycopy(nonce, 0, data, offset, nonce.size)
        */
    }
}
