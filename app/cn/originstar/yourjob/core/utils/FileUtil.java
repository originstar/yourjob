package cn.originstar.yourjob.core.utils;

public class FileUtil {

    /**
     * Converts any string into a string that is safe to use as a file name. The result will only include ascii characters and numbers, and the "-", "_", and "." characters.
     * 
     * @param fileName
     * @return
     */
    // Comment copied from http://grepcode.com/file/repo1.maven.org/maven2/com.ning/metrics.collector/1.1.0/org/apache/activemq/util/IOHelper.java#IOHelper.toFileSystemSafeName
    public static String getSafeFileName(String fileName) {
        // Regex found from http://stackoverflow.com/questions/15075890/replacing-illegal-character-in-filename
        return fileName.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
    }

}
