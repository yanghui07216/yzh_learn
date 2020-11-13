package com.yzh.learn.test;

import io.netty.util.internal.StringUtil;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;


public class LombokTest {

    public static void main(String[] args) {

        System.out.println(System.currentTimeMillis());
        System.out.println(new Date().getTime());
//        Person p = Person.builder().build();
//        p.setAge(1);
//        p.setName("p1");
//
//        p = Person.builder().build();
//        p.setName("p2");
//        p.setAge(2);
//
//        System.out.println(p);

//        boolean appVersionNewerOrEqualThan = isAppVersionNewerOrEqualThan("4.50.0");
//        System.out.println(appVersionNewerOrEqualThan);

//        Integer addressType = null;
//        String cutOffTime = null;
//
//        if (1 == addressType) {
//            System.out.println("============");
//        } else {
//            System.out.println("预计" + cutOffTime + "送达");
//        }


//        //筛选mall已同意退货的退款单
//        if (reverseStatus.contains(ReverseStageEnum.Mall_GoodsRefund_Agree.getStage())) {
//            tempReverseAggregateList = reverseAggregateList.stream()
//                    .filter(cur -> cur.getReverse().getReverseStatus() == ReverseStatusEnum.AGREE_FETCH.getStatus())
//                    .filter(cur -> cur.getFundsReverses().get(0).getFundsReverseStatus() == ReverseStatusEnum.AUDIT_AGREE.getStatus())
//                    .filter(cur -> cur.getGoodsReverses().size() < 1).collect(Collectors.toList());
//            canOperateList.addAll(tempReverseAggregateList);
//        }
//
//        //筛选mall已发起预约退货的退款单
//        if (reverseStatus.contains(ReverseStageEnum.Mall_GoodsRefund_Create.getStage())) {
//            tempReverseAggregateList = reverseAggregateList.stream()
//                    .filter(cur -> cur.getReverse().getReverseStatus() == ReverseStatusEnum.AGREE_FETCH.getStatus())
//                    .filter(cur -> cur.getFundsReverses().get(0).getFundsReverseStatus() == ReverseStatusEnum.AUDIT_AGREE.getStatus())
//                    .filter(cur -> cur.getGoodsReverses().size() >= 1)
//                    .filter(cur -> cur.getGoodsReverses().get(0).getGoodsReverseStatus() == ReverseStatusEnum.AUDIT_AGREE.getStatus())
//                    .collect(Collectors.toList());
//            canOperateList.addAll(tempReverseAggregateList);
//        }
//        //筛选mall退货单已退货到仓的退款单
//        if (reverseStatus.contains(ReverseStageEnum.Mall_GoodsRefund_Audit.getStage())) {
//            tempReverseAggregateList = reverseAggregateList.stream()
//                    .filter(cur -> cur.getReverse().getReverseStatus() == ReverseStatusEnum.AGREE_FETCH.getStatus())
//                    .filter(cur -> cur.getFundsReverses().get(0).getFundsReverseStatus() == ReverseStatusEnum.AUDIT_AGREE.getStatus())
//                    .filter(cur -> cur.getGoodsReverses().size() >= 1)
//                    .filter(cur -> cur.getGoodsReverses().get(0).getGoodsReverseStatus() == ReverseStatusEnum.SUCCESS.getStatus())
//                    .collect(Collectors.toList());
//            canOperateList.addAll(tempReverseAggregateList);
//        }

    }

    /**
     * 判断当前app版本是否大于某个版本号
     * @param requestVersion
     * @return
     */
    public static boolean isAppVersionNewerOrEqualThan(String requestVersion){
        String appVersion = "4.50.0.12345";
        if (StringUtils.isEmpty(appVersion)) {
            return true;
        }
        return compareVersion(appVersion, requestVersion) >= 0;
    }

    /**
     * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
     * @param requestVersion
     * @param matchVersion
     * @return
     */
    public static int compareVersion(String requestVersion, String matchVersion){
        if (StringUtils.isEmpty(requestVersion) || StringUtils.isEmpty(matchVersion)) {
            return 0;
        }
        String[] requestVersionArray = requestVersion.split("\\.");//注意此处为正则匹配，不能用"."；
        String[] matchVersionArray = matchVersion.split("\\.");
        int minLength = Math.min(requestVersionArray.length, matchVersionArray.length);//取最小长度值

        int diff = 0;
        int idx = 0;
        while (idx < minLength && (diff = requestVersionArray[idx].length() - matchVersionArray[idx].length()) == 0//先比较长度
                && (diff = requestVersionArray[idx].compareTo(matchVersionArray[idx])) == 0) {//再比较字符
            ++idx;
        }
        //如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : requestVersionArray.length - matchVersionArray.length;
        return diff;
    }
}

@Data
@Builder
class Person {
    private String name;

    private Integer age;
}
