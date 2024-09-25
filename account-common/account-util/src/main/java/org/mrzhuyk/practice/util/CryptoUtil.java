package org.mrzhuyk.practice.util;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.DES;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.mrzhuyk.practice.exception.BizException;
import org.mrzhuyk.practice.exception.ErrorEnum;

import java.util.Objects;

/**
 * 加解密工具类
 */
@UtilityClass
public class CryptoUtil {
    /**
     * 对称加密
     * <p>
     * 加密算法
     * DES:
     * AES:
     * <p>
     * 加密模式
     * ECB: 电子密码本，需要加密的消息按照块密码的块大小被分为数个块，并对每个块进行独立加密
     * 优点：可以进行处理数据
     * 缺点：同样的原文生成同样的密文，不能很好的保护数据
     * CBC: 密码块链接，每个明文块先于前一个密文块进行异或后，再进行加密，每个密文块都依赖于它前面的所有明文块
     * 优点：同样的原文生成的密文不一样
     * 缺点：串行处理数据
     * <p>
     * 填充模式
     * NoPadding不填充
     * 1.在DES加密算法下, 要求原文长度必须是8byte的整数倍
     * 2.在AES加密算法下, 要求原文长度必须是16byte的整数倍
     * PKCS5Padding
     * 数据块的大小为8位, 不够就补足
     */
    @UtilityClass
    public static class Symmetric {
        private static final int[] AES_KEY_SIZES = new int[]{16, 24, 32};
        
        /**
         * 加密
         * DES加密算法，ECB模式
         *
         * @param content 明文
         * @param key     加密key 长度不能小于8，超过8，也会只取8位长度
         * @return 密文
         */
        public static String encryptDES(String content, String key) {
            if (StringUtils.isBlank(content)) {
                return content;
            }
            if (Objects.isNull(key) || key.getBytes().length < 8) {
                throw new BizException(ErrorEnum.INTERNAL_SERVER_ERROR, "DEC加密Key长度不能小于8位");
            }
            DES des = new DES(Mode.ECB, Padding.PKCS5Padding, key.getBytes());
            return des.encryptBase64(content);
        }
        
        
        /**
         * 解密
         * DES加密算法，ECB模式
         *
         * @param content 明文
         * @param key     加密key 长度不能小于8，超过8，也会只取8位长度
         * @return 密文
         */
        public static String decryptDES(String content, String key) {
            if (StringUtils.isBlank(content)) {
                return content;
            }
            if (Objects.isNull(key) || key.getBytes().length < 8) {
                throw new BizException(ErrorEnum.INTERNAL_SERVER_ERROR, "DEC解密Key长度不能小于8位");
            }
            DES des = new DES(Mode.ECB, Padding.PKCS5Padding, key.getBytes());
            return des.decryptStr(content);
        }
        
        /**
         * 加密
         * DES加密算法，CBC模式
         *
         * @param content 明文
         * @param key     加密key 长度不能小于8，超过8，也会只取8位长度
         * @param iv      偏移量 长度不能小于8，超过8，也会只取8位长度
         * @return 密文
         */
        public static String encryptDES(String content, String key, String iv) {
            if (StringUtils.isBlank(content)) {
                return content;
            }
            if (Objects.isNull(key) || key.getBytes().length < 8) {
                throw new BizException(ErrorEnum.INTERNAL_SERVER_ERROR, "DEC加密Key长度不能小于8位");
            }
            if (Objects.isNull(iv) || iv.getBytes().length != 8) {
                throw new BizException(ErrorEnum.INTERNAL_SERVER_ERROR, "DEC加密IV长度必须等于8位");
            }
            DES des = new DES(Mode.CBC, Padding.PKCS5Padding, key.getBytes(), iv.getBytes());
            return des.encryptBase64(content);
        }
        
        /**
         * 解密
         * DES加密算法，CBC模式
         *
         * @param content 明文
         * @param key     加密key 长度不能小于8，超过8，也会只取8位长度
         * @param iv      偏移量 长度不能小于8，超过8，也会只取8位长度
         * @return 密文
         */
        public static String decryptDES(String content, String key, String iv) {
            if (StringUtils.isBlank(content)) {
                return content;
            }
            if (Objects.isNull(key) || key.getBytes().length < 8) {
                throw new BizException(ErrorEnum.INTERNAL_SERVER_ERROR, "DEC加密Key长度不能小于8位");
            }
            if (Objects.isNull(iv) || iv.getBytes().length != 8) {
                throw new BizException(ErrorEnum.INTERNAL_SERVER_ERROR, "DEC加密IV长度必须等于8位");
            }
            DES des = new DES(Mode.CBC, Padding.PKCS5Padding, key.getBytes(), iv.getBytes());
            return des.decryptStr(content);
        }
        
        /**
         * 加密
         * AES加密算法，ECB模式
         *
         * @param content 明文
         * @param key     加密key 长度只能为16,24,32
         * @return 密文
         */
        public static String encryptAES(String content, String key) {
            if (StringUtils.isBlank(content)) {
                return content;
            }
            if (Objects.isNull(key) || keySizeValid(key.getBytes().length)) {
                throw new BizException(ErrorEnum.INTERNAL_SERVER_ERROR, "AEC加密Key长度必须为16位或24位或32位");
            }
            AES aes = new AES(Mode.ECB, Padding.PKCS5Padding, key.getBytes());
            return aes.encryptBase64(content);
        }
        
        /**
         * 解密
         * AES加密算法，ECB模式
         *
         * @param content 明文
         * @param key     加密key 长度只能为16,24,32
         * @return 密文
         */
        public static String decryptAES(String content, String key) {
            if (StringUtils.isBlank(content)) {
                return content;
            }
            if (Objects.isNull(key) || keySizeValid(key.getBytes().length)) {
                throw new BizException(ErrorEnum.INTERNAL_SERVER_ERROR, "AEC加密Key长度必须为16位或24位或32位");
            }
            AES aes = new AES(Mode.ECB, Padding.PKCS5Padding, key.getBytes());
            return aes.decryptStr(content);
        }
        
        /**
         * 对称加密
         * 加密
         * AES加密算法，CBC模式
         *
         * @param content 明文
         * @param key     加密key 长度只能为16,24,32
         * @param iv      偏移量 长度只能为16
         * @return 密文
         */
        public static String encryptAES(String content, String key, String iv) {
            if (StringUtils.isBlank(content)) {
                return content;
            }
            if (Objects.isNull(key) || keySizeValid(key.getBytes().length)) {
                throw new BizException(ErrorEnum.INTERNAL_SERVER_ERROR, "AEC加密Key长度必须为16位或24位或32位");
            }
            if (Objects.isNull(iv) || iv.getBytes().length != 16) {
                throw new BizException(ErrorEnum.INTERNAL_SERVER_ERROR, "AEC加密IV长度必须等于16位");
            }
            AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, key.getBytes(), iv.getBytes());
            return aes.encryptBase64(content);
        }
        
        /**
         * 解密
         * AES加密算法，CBC模式
         *
         * @param content 明文
         * @param key     加密key 长度只能为16,24,32
         * @param iv      偏移量 长度只能为16
         * @return 密文
         */
        public static String decryptAES(String content, String key, String iv) {
            if (StringUtils.isBlank(content)) {
                return content;
            }
            if (Objects.isNull(key) || keySizeValid(key.getBytes().length)) {
                throw new BizException(ErrorEnum.INTERNAL_SERVER_ERROR, "AEC加密Key长度必须为16位或24位或32位");
            }
            if (Objects.isNull(iv) || iv.getBytes().length != 16) {
                throw new BizException(ErrorEnum.INTERNAL_SERVER_ERROR, "AEC加密IV长度必须等于16位");
            }
            AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, key.getBytes(), iv.getBytes());
            return aes.decryptStr(content);
        }
        
        /**
         * 验证key的长度
         *
         * @param length key的长度
         * @return 是否通过验证
         */
        private static boolean keySizeValid(int length) {
            for (int aesKeySize : AES_KEY_SIZES) {
                if (length == aesKeySize) {
                    return false;
                }
            }
            return true;
        }
    }
    
}
