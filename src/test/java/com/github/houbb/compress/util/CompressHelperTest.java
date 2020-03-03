package com.github.houbb.compress.util;

import com.github.houbb.compress.api.ICompressResult;
import com.github.houbb.compress.api.IUncompressResult;
import com.github.houbb.compress.bs.CompressBs;
import com.github.houbb.compress.constant.enums.CompressTypeEnum;
import com.github.houbb.compress.support.result.compress.impl.CompressResultHandlers;
import com.github.houbb.compress.support.result.uncompress.impl.UncompressResultHandlers;
import com.github.houbb.heaven.util.io.FileUtil;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

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
        final String s = "D:\\github\\compress\\src\\test\\resources\\compress_enum_s_t.txt";
        final String t = "D:\\github\\compress\\src\\test\\resources\\compress_enum_s_t.zip";

        CompressHelper.compress(CompressTypeEnum.ZIP, s, t);
    }

    @Test
    public void compressDeleteTest() {
        final String s = "D:\\github\\compress\\src\\test\\resources\\compress_enum_s_t_delete.txt";
        final String t = "D:\\github\\compress\\src\\test\\resources\\compress_enum_s_t_delete.zip";

        ICompressResult result = CompressBs.newInstance(CompressTypeEnum.ZIP)
                .compressSources(s)
                .target(t)
                .createFile(false)
                .compress(CompressResultHandlers.defaults());

        final String targetPath = result.fileInfo().path();
        final byte[] bytes = result.fileInfo().content();
        FileUtil.createFile(targetPath, bytes);
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
        final String s = "D:\\github\\compress\\src\\test\\resources\\compress_enum_s_t.zip";
        final String t = "D:\\github\\compress\\src\\test\\resources\\un\\";

        CompressHelper.uncompress(CompressTypeEnum.ZIP, s, t);
    }

    @Test
    public void uncompressNotCreateFileTest() throws FileNotFoundException, UnsupportedEncodingException {
        final String s = "D:\\github\\compress\\src\\test\\resources\\compress_enum_s_t.zip";
        final String t = "D:\\github\\compress\\src\\test\\resources\\un\\";

        IUncompressResult result = CompressBs.newInstance(CompressTypeEnum.ZIP)
                .target(t)
                .uncompressStream(new FileInputStream(new File(s)))
                .createFile(false)
                .uncompress(UncompressResultHandlers.defaults());

        String string = new String(result.fileInfos().get(0).content());
        System.out.println(string);
    }

    @Test
    public void uncompress2Test() {
        final String s = "D:\\_github\\compress\\src\\test\\resources\\compress_s_t.zip";
        final String t = "D:\\_github\\compress\\src\\test\\resources\\un\\";

        CompressHelper.uncompress(s, t);
    }

    @Test
    public void uncompress3Test() {
        final String s = "D:\\_github\\compress\\src\\test\\resources\\compress_s.zip";
        CompressHelper.uncompress(s);
    }

}
