package com.github.houbb.compress.util;

import com.github.houbb.compress.constant.enums.CompressTypeEnum;
import org.junit.Ignore;
import org.junit.Test;

/**
 * <p> project: compress-CompressHelper </p>
 * <p> create on 2020/3/2 20:32 </p>
 *
 * @author binbin.hou
 * @since 0.0.4
 */
@Ignore
public final class CompressHelperTest {

    @Test
    public void compressTest() {
        final String s = "D:\\_github\\compress\\src\\test\\resources\\compress_enum_s_t.txt";
        final String t = "D:\\_github\\compress\\src\\test\\resources\\compress_enum_s_t.zip";

        CompressHelper.compress(CompressTypeEnum.ZIP, s, t);
    }

    @Test
    public void compress2Test() {
        final String s = "D:\\_github\\compress\\src\\test\\resources\\compress_s_t.txt";
        final String t = "D:\\_github\\compress\\src\\test\\resources\\compress_s_t.zip";

        CompressHelper.compress(s, t);
    }

    @Test
    public void compress3Test() {
        final String s = "D:\\_github\\compress\\src\\test\\resources\\compress_s.txt";

        CompressHelper.compress(s);
    }

    @Test
    public void uncompressTest() {
        final String s = "D:\\_github\\compress\\src\\test\\resources\\compress_enum_s_t.zip";
        final String t = "D:\\_github\\compress\\src\\test\\resources\\";

        CompressHelper.uncompress(CompressTypeEnum.ZIP, s, t);
    }

    @Test
    public void uncompress2Test() {
        final String s = "D:\\_github\\compress\\src\\test\\resources\\compress_s_t.zip";
        final String t = "D:\\_github\\compress\\src\\test\\resources\\";

        CompressHelper.uncompress(s, t);
    }

    @Test
    public void uncompress3Test() {
        final String s = "D:\\_github\\compress\\src\\test\\resources\\compress_s.zip";

        CompressHelper.uncompress(s);
    }

}
