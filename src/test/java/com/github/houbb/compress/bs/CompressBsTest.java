//package com.github.houbb.compress.bs;
//
//import com.github.houbb.compress.constant.enums.CompressTypeEnum;
//import org.junit.Ignore;
//import org.junit.Test;
//
///**
// * 引导类测试
// * @author binbin.hou
// * @since 0.0.3
// */
//@Ignore
//public class CompressBsTest {
//
//    /**
//     * 压缩测试
//     * @since 0.0.3
//     */
//    @Test
//    public void compressTest() {
//        String source = "C:\\Users\\binbin.hou\\Desktop\\1.txt";
//        String target = "C:\\Users\\binbin.hou\\Desktop\\1.zip";
//
//        CompressBs.newInstance(CompressTypeEnum.ZIP)
//                .compressSources(source)
//                .target(target)
//                .compress();
//    }
//
//    /**
//     * 解压缩测试
//     * @since 0.0.3
//     */
//    @Test
//    public void uncompressTest() {
//        String source = "C:\\Users\\binbin.hou\\Desktop\\1.zip";
//        String target = "C:\\Users\\binbin.hou\\Desktop\\";
//
//        CompressBs.newInstance(CompressTypeEnum.ZIP)
//                .compressSources(source)
//                .target(target)
//                .uncompress();
//    }
//
//    /**
//     * 配置测试
//     * @since 0.0.3
//     */
//    @Test
//    public void configTest() {
//        // 指定加密策略
//        CompressBs.newInstance(CompressTypeEnum.ZIP)
//                // 指定源文件
//                .compressSources("")
//                // 指定目标文件（夹）
//                .target("")
//                // 指定密码，默认无。
//                .password("")
//                // 是否使用相对路径进行处理
//                .relativePath(false)
//                // 指定解压或者压缩
//                .compress();
//    }
//
//}
