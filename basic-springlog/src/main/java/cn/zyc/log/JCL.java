package cn.zyc.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * JCL --JUL
 * --LOG4J
 */

public class JCL {
    public static void main(String[] args) {
        Log jcl = LogFactory.getLog("JCL");
        jcl.info("JCL");
    }
}
