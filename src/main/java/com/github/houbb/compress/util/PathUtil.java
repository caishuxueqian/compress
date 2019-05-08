package com.github.houbb.compress.util;

import com.github.houbb.compress.annotation.CommonEager;
import com.github.houbb.compress.support.filter.PathCondition;
import com.github.houbb.compress.support.filter.PathFilter;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 路径工具类
 * @author binbin.hou
 * @since 0.0.1
 */
@CommonEager
public class PathUtil {

    /**
     * 根路径
     */
    private static final Path ROOT_PATH = Paths.get("/");

    /**
     * 获取满足条件的列表
     * @param pathList 路径
     * @param pathCondition 条件
     * @return 结果
     */
    public static List<Path> getConditionList(final List<Path> pathList,
                                            final PathCondition pathCondition) {
        if(CollectionUtil.isEmpty(pathList)) {
            return Collections.emptyList();
        }

        List<Path> conditionList = new ArrayList<>();
        for(Path path : pathList) {
            if(pathCondition.condition(path)) {
                conditionList.add(path);
            }
        }
        return conditionList;
    }

    /**
     * 获取 path 相对于 parentPath 剩余的路径
     * 和 {@link Path#relativize(Path)} 不同，这个结果更加直观。不过性能一般。
     * @param parentPath 父类路径
     * @param path 原始路径
     * @return 相对结果路径
     */
    public static String getRelativePath(final Path parentPath,
                                         final Path path) {
        final String pathStr = path.toString();
        if(ObjectUtil.isNull(parentPath)) {
            return pathStr;
        }

        // 认为是根路径
        if(parentPath.toString().length() <= 1) {
            return pathStr;
        }

        final String parentPathStr = parentPath.toString();
        if(pathStr.startsWith(parentPathStr)) {
            return pathStr.substring(parentPathStr.length()+1);
        }
        return pathStr;
    }

    /**
     * 获取共有的路径
     * @param pathList 路径列表
     * @return 结果
     */
    public static Path getPublicParentPath(final List<Path> pathList) {
        // 直接返回第一个元素的父类路径即可、
        if(pathList.size() == 1) {
            return getParentPath(pathList.get(0));
        }

        // 获取所有的父类文件夹
        List<List<String>> pathStrList = new ArrayList<>(pathList.size());
        for(Path path : pathList) {
            List<String> stringList = toStringList(getParentPaths(path));
            pathStrList.add(stringList);
        }

        // 获取共有的父类文件夹
        List<String> publicParentPathStrs = retainAll(pathStrList);

        // 获取最长的一个作为最大的公共路径
        String maxLengthParent = getMaxLength(publicParentPathStrs);
        return Paths.get(maxLengthParent);
    }

    /**
     * 获取最长的字符串
     * @param stringList 字符串列表
     * @return 最长的结果
     */
    private static String getMaxLength(final List<String> stringList) {
        String result = StringUtil.EMPTY;

        for(String string : stringList) {
            if(string.length() > result.length()) {
                result = string;
            }
        }
        return result;
    }



    /**
     * 获取所有的父类路径
     * 1. 不包含本身
     * 2. 递归获取父类，如果父类为 null 则停止（说明到 root 了）
     * 3. 默认 / root 的是所有逻辑的父亲路径，包括 root 文件夹本身。
     * @param path 当前路径
     * @return 所有的父类列表
     */
    public static List<Path> getParentPaths(final Path path) {
        if(ObjectUtil.isNull(path)) {
            return Collections.emptyList();
        }

        List<Path> pathList = new ArrayList<>();
        Path parentPath = path.getParent();
        while (ObjectUtil.isNotNull(parentPath)) {
            pathList.add(parentPath);

            parentPath = parentPath.getParent();
        }

        // 如果列表为空，则默认添加 /
        if(CollectionUtil.isEmpty(pathList)) {
            pathList.add(ROOT_PATH);
        }

        return pathList;
    }


    /**
     * 获取父类路径，避免返回 null
     * 1. 如果为根路径，则依然返回根路径
     * @param path 路径
     * @return 结果
     */
    public static Path getParentPath(final Path path) {
        Path parentPath = path.getParent();
        if(ObjectUtil.isNull(parentPath)) {
            return ROOT_PATH;
        }
        return parentPath;
    }

    /**
     * 对象列表转换为 toString 列表
     * 1. 会跳过所有的 null 对象。
     * 2. 建议放在 collectUtil 下。
     * @param pathList 原始对象
     * @return 结果
     */
    public static List<String> toStringList(final List<?> pathList) {
        if(CollectionUtil.isEmpty(pathList)) {
            return Collections.emptyList();
        }

        List<String> stringList = new ArrayList<>(pathList.size());
        for(Object object : pathList) {
            if(ObjectUtil.isNotNull(object)) {
                stringList.add(object.toString());
            }
        }

        return stringList;
    }

    /**
     * 获取所有集合的交集
     * 1. 如果后续参数为空，则直接返回第一个集合。
     * 2. 如果第一个列表为空，则直接返回第一个集合。
     * @param collectionList 原始对象集合
     * @return 满足条件的结合
     */
    public static List<String> retainAll(final List<List<String>> collectionList) {
        if(CollectionUtil.isEmpty(collectionList)) {
            return Collections.emptyList();
        }
        if(collectionList.size() == 1) {
            return collectionList.get(0);
        }

        List<String> result = collectionList.get(0);
        for(int i = 1; i < collectionList.size(); i++) {
            result.retainAll(collectionList.get(i));
        }

        return result;
    }



    public static void main(String[] args) {
//        System.out.println(getParentPaths(Paths.get("/user/1243/ddd")));
//        System.out.println(getPublicParentPath(Arrays.asList(Paths.get("/user/1243/ddd"),
//                Paths.get("/user/1243/ddd/123"))));

//        System.out.println(Paths.get("/user/1243/ddd"));
//        System.out.println(Paths.get("/user/1243/ddd").relativize(Paths.get("\\user\\1243")));

        System.out.println(getRelativePath(
                Paths.get("/user/1243"),
                Paths.get("/user/1243/ddd/123.txt")));

        System.out.println("/user/1243/ddd/123.txt".indexOf("/user/1243"));
    }

}
