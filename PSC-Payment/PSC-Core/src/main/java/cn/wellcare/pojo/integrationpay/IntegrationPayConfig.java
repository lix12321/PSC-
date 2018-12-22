package cn.wellcare.pojo.integrationpay;

/**
 * 聚合支付参数工具类
 *
 */
public class IntegrationPayConfig {
    public static final String MERCHANTID = "105000060120010"; //商户代码
    //支付公钥
    public static String PUBLICKEY = "30819d300d06092a864886f70d010101050003818b0030818702818100c6667db209fc63485e4019ab7d14699225f4c2b2b3ff68b46aa3c1ffc74d120e4617ce1844cfe3681b71c1e0e8da9184b32741352688cb876df0942885bbc7dcfe15c4aafc02e50b7b689778e5ca9706cd96e7c504c51168135bcf3d8d2edd5007c891ee22c05762ce32e7cf8cf28b2f6c3b26333803c2e14454db1d588fd29b020111";
    public static final String POSID = "017052253";	   //商户柜台代码 --
    public static final String BRANCHID = "610000000";   //分行代码
    public static final String CURCODE = "01";	   //币种
    public static final String TXCODE = "530550";	   //交易码
    public static final String RETURNTYPE = "3"; //返回类型
    public static final String PUB32TR2 = "3803c2e14454db1d588fd29b020111";   //
    public static final String BANKURL = "https://ibsbjstar.ccb.com.cn/CCBIS/ccbMain"; //网银网关地址
    public static final String BEGORDERTIME="00:00:00";//订单开始时间
    public static final String ENDORDERTIME="23:59:59";//订单结束时间
    //退款
    public static final String TXCODES="410408";//交易码
    public static String CUSTID = "105000060120010";//商户号 --
    public static String USER_ID = "105000060120010-002";//操作员号 --
    public static String PASSWORD = "ccb003";//操作员密码 --



    public static final String JUHEPAY_ORDER_NAME = "聚合支付HIS订单";




}
