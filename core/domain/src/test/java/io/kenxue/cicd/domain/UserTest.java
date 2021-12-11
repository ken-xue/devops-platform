//package io.kenxue.cicd.domain;
//
//import com.alibaba.cola.exception.BizException;
//import io.kenxue.cicd.domain.metrics.appquality.AppMetric;
//import io.kenxue.cicd.domain.metrics.appquality.AppMetricItem;
//import io.kenxue.cicd.domain.metrics.appquality.AppQualityMetric;
//import io.kenxue.cicd.domain.metrics.devquality.BugMetric;
//import io.kenxue.cicd.domain.metrics.devquality.BugMetricItem;
//import io.kenxue.cicd.domain.metrics.devquality.DevQualityMetric;
//import io.kenxue.cicd.domain.metrics.techcontribution.CodeReviewMetric;
//import io.kenxue.cicd.domain.metrics.techcontribution.CodeReviewMetricItem;
//import io.kenxue.cicd.domain.metrics.techcontribution.ContributionMetric;
//import io.kenxue.cicd.domain.metrics.techinfluence.InfluenceMetric;
//import io.kenxue.cicd.domain.metrics.weight.DevWeight;
//import User;
//import org.junit.Assert;
//import org.junit.Test;
//
///**
// * UserProfileTest
// *
// * @date 2020-08-28 2:03 PM
// */
//public class UserTest {
//
//    @Test
//    public void testCalculateScore(){
//        User user = new User();
//        //user.setWeight(new DevWeight());
//
//        //App Quality Metric
//        AppMetricItem appMetricItem1 = new AppMetricItem();
//        appMetricItem1.setAppName("app1");
//        appMetricItem1.setCyclomaticComplexityCount(200);
//        appMetricItem1.setDuplicatedMethodCount(80);
//        appMetricItem1.setLongMethodCount(70);
//        appMetricItem1.setBlockedCodeConductCount(20);
//        appMetricItem1.calculateScore();
//
//        AppMetricItem appMetricItem2 = new AppMetricItem();
//        appMetricItem2.setAppName("app2");
//        appMetricItem2.setCyclomaticComplexityCount(20);
//        appMetricItem2.setDuplicatedMethodCount(30);
//        appMetricItem2.setLongMethodCount(7);
//        appMetricItem2.setBlockedCodeConductCount(5);
//        appMetricItem2.calculateScore();
//
//        AppMetric appMetric = new AppMetric();
//        appMetric.addMetricItem(appMetricItem1);
//        appMetric.addMetricItem(appMetricItem2);
//
//        AppQualityMetric appQualityMetric = new AppQualityMetric(user);
//        appMetric.setParent(appQualityMetric);
//
//        //influence Metric
//        InfluenceMetric influenceMetric = new InfluenceMetric(user);
//        InfluenceMetricTest.prepareSubMetrics(influenceMetric);
//
//        //techContribution Metric
//        CodeReviewMetric codeReviewMetric = new CodeReviewMetric();
//        CodeReviewMetricItem codeReviewMetricItem = new CodeReviewMetricItem();
//        codeReviewMetricItem.setNoteCount(4);
//        codeReviewMetricItem.setReviewId("12234455");
//        codeReviewMetric.addMetricItem(codeReviewMetricItem);
//        ContributionMetric contributionMetric = new ContributionMetric(user);
//
//        //dev quality metric
//        DevQualityMetric devQualityMetric = new DevQualityMetric(user);
//        BugMetric bugMetric = new BugMetric();
//        BugMetricItem bugMetricItem = new BugMetricItem(2, 1000);
//        bugMetric.addMetricItem(bugMetricItem);
//        devQualityMetric.setBugMetric(bugMetric);
//
//        //Execution
//        //user.calculateScore();
//
//        //Assertion
//        //Assert.assertEquals(45.8, user.getTotalScore(), 0.01);
//    }
//
////    @Test(expected = BizException.class)
////    public void testNPE(){
////        User user = new User();
////        user.setWeight(new DevWeight());
////
////        //user.calculateScore();
////    }
//}
