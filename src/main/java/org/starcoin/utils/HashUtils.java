/*
 * Copyright 2002-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.starcoin.utils;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;

public class HashUtils {
    
    public static String hashStarcoinSignedUserTransaction(byte[] signedMessage) {
        byte[] bytesForHash = com.google.common.primitives.Bytes
                .concat(hashWithStarcoinPrefix("SignedUserTransaction"), signedMessage);
        return Hex.toHexString(new SHA3.Digest256().digest(bytesForHash));
    }

    public static byte[] hashWithStarcoinPrefix(String name) {
        return hash("STARCOIN::".getBytes(), name.getBytes());
    }

    public static byte[] hash(byte[] prefix, byte[] bytes) {
        SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest256();

        digestSHA3.update(prefix);
        digestSHA3.update(bytes);

        return digestSHA3.digest();
    }


    public static byte[] sha3Hash(byte[] data) {
        SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest256();
        return digestSHA3.digest(data);
    }

    public static String sha3HashStr(byte[] data) {
        SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest256();
        return Hex.toHexString(digestSHA3.digest(data));
    }
}
