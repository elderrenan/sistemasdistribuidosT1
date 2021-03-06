/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasdistribuidos;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;

/**
 *
 * @author elder fonte: https://www.devmedia.com.br/como-criar-uma-assinatura-digital-em-java/31287
 */
public class AssinaturaRemetente {

    private PublicKey pubKey;

    public PublicKey getPubKey() {
        return pubKey;
    }

    public void setPubKey(PublicKey pubKey) {
        this.pubKey = pubKey;
    }


    public byte[] geraAssinatura(String mensagem) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature sig = Signature.getInstance("DSA");

        //Geração das chaves públicas e privadas
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
        SecureRandom secRan = new SecureRandom();
        kpg.initialize(512, secRan);
        KeyPair keyP = kpg.generateKeyPair();
        this.pubKey = keyP.getPublic();
        PrivateKey priKey = keyP.getPrivate();

        //Inicializando Obj Signature com a Chave Privada
        sig.initSign(priKey);

        //Gerar assinatura
        sig.update(mensagem.getBytes());
        byte[] assinatura = sig.sign();

        return assinatura;
    }

}