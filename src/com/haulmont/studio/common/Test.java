package com.haulmont.studio.common;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class Test implements Comparable {
    private javax.crypto.Cipher cipher;
    private javax.crypto.spec.SecretKeySpec secretKeySpec;
    private byte[] var15;
    private byte[] var16;

    @Override
    public int compareTo(Object o) {
        secretKeySpec = new SecretKeySpec("akunAm@tata".getBytes(),"Blowfish");
        try {
            Cipher var14 = javax.crypto.Cipher.getInstance(null,"Blowfish");
            var14.init(2,secretKeySpec);

            var15 = org.apache.commons.codec.binary.Base64.decodeBase64("GuS5NxOUrtsT7zv91nncrw==");
             var16 = var14.doFinal(var15);
            System.out.println(new String(var16, "UTF-8"));//ToolsPanel.cloudDeploy
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return 0;
    }



}
