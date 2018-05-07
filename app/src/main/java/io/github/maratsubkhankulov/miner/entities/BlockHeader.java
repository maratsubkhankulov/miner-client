package io.github.maratsubkhankulov.miner.entities;

import java.math.BigInteger;

/**
 * Created by msubkhankulov on 5/6/18.
 */

public class BlockHeader {
    public BigInteger version;
    public String prevBlockhash;
    public String merkleRoot;
    public BigInteger timestamp;
    public Integer difficultyTarget;
    public Integer nonce;

    public byte[] toByteArray() {
        return new byte[80];
        /*
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
