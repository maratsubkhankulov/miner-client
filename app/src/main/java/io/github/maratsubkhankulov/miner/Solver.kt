package io.github.maratsubkhankulov.miner

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * Created by msubkhankulov on 5/3/18.
 */

class Solver @Throws(NoSuchAlgorithmException::class)
constructor() {
    internal var messageDigest: MessageDigest

    init {
        messageDigest = MessageDigest.getInstance("SHA-256")
    }

    /**
     * Hashcash algorithm: sha256(sha256(blockVersionNumber||hashPrevBlock||hashMerkleRoot||time||bits||none)
     * Source: https://en.bitcoin.it/wiki/Block_hashing_algorithm
     * @param blockVersionNumber - 4 bytes
     * @param hashPrevBlock - 32 bytes
     * @param hashMerkleRoot - 32 bytes
     * @param timestamp - current time in seconds since epoch, 4 bytes
     * @param bits - current target in compact form, 4 bytes
     * @param nonce - 4 bytes
     * @return 32 byte hash
     */
    internal fun hashcash(data: ByteArray): ByteArray {
        return messageDigest.digest(messageDigest.digest(data))
    }

    internal fun isSolution(hash: ByteArray, difficultyTarget: Int): Boolean {
        var hashString = hash.toString()
        for (i in 0..difficultyTarget) {
            if (hashString[i] != '0') {
                return false
            }
        }
        return true
    }
}
