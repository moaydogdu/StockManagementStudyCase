package com.study.stockmanagementstudycase.auth.util;

import lombok.experimental.UtilityClass;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import java.io.IOException;
import java.io.StringReader;
import java.security.PrivateKey;
import java.security.PublicKey;

@UtilityClass
public class KeyConverter {

    public PrivateKey convertPrivateKey(
            final String privateKeyPem
    ) {
        StringReader keyReader = new StringReader(privateKeyPem);
        try {
            final PrivateKeyInfo privateKeyInfo = PrivateKeyInfo
                    .getInstance(new PEMParser(keyReader).readObject());

            return new JcaPEMKeyConverter().getPrivateKey(privateKeyInfo);
        } catch (final IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    public PublicKey convertPublicKey(
            final String publicKeyPem
    ) {
        StringReader keyReader = new StringReader(publicKeyPem);
        try {
            final SubjectPublicKeyInfo publicKeyInfo = SubjectPublicKeyInfo
                    .getInstance(new PEMParser(keyReader).readObject());

            return new JcaPEMKeyConverter().getPublicKey(publicKeyInfo);
        } catch (final IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

}
