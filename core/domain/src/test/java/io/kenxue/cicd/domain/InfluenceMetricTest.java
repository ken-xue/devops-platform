//package io.kenxue.cicd.domain;
//
//import io.kenxue.cicd.domain.metrics.techinfluence.*;
//import io.kenxue.cicd.domain.metrics.weight.DevWeight;
//import io.kenxue.cicd.domain.metrics.weight.QAWeight;
//import User;
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.util.Date;
//
//public class InfluenceMetricTest {
//
//    @Test
//    public void testDevScore(){
//        User user = new User();
//        //user.setWeight(new DevWeight());
//        InfluenceMetric influenceMetric = new InfluenceMetric(user);
//        prepareSubMetrics(influenceMetric);
//
////        Assert.assertEquals(124.5, influenceMetric.calculateScore(), 0.01);
//    }
//
//    @Test
//    public void testQAScore(){
//        User user = new User();
//        //user.setWeight(new QAWeight());
//        InfluenceMetric influenceMetric = new InfluenceMetric(user);
//        prepareSubMetrics(influenceMetric);
//
////        Assert.assertEquals(124.5, influenceMetric.calculateScore(), 0.01);
//    }
//
//    public static void prepareSubMetrics(InfluenceMetric influenceMetric) {
//        ATAMetric ataMetric = new ATAMetric(influenceMetric);
//        ataMetric.addMetricItem( new ATAMetricItem("article",19,99,14,2));
//        ataMetric.addMetricItem( new ATAMetricItem("article",20,100,15,3) );
//        ataMetric.addMetricItem( new ATAMetricItem("article",100, 500, 75, 15) );
//
//        PatentMetric patentMetric = new PatentMetric(influenceMetric);
//        patentMetric.addMetricItem(new PatentMetricItem("patentName","patentDesc","patentNo","sharingLink", AuthorType.FIRST_AUTHOR));
//        patentMetric.addMetricItem(new PatentMetricItem("patentName","patentDesc","patentNo","sharingLink", AuthorType.OTHER_AUTHOR));
//
//        SharingMetric sharingMetric = new SharingMetric(influenceMetric);
//        sharingMetric.addMetricItem(new SharingMetricItem("title", SharingScope.TEAM, new Date(), "sharingLink"));
//        sharingMetric.addMetricItem(new SharingMetricItem("title", SharingScope.BU, new Date(), "sharingLink"));
//        sharingMetric.addMetricItem(new SharingMetricItem("title", SharingScope.ALIBABA, new Date(), "sharingLink"));
//        sharingMetric.addMetricItem(new SharingMetricItem("title", SharingScope.COMMUNITY, new Date(), "sharingLink"));
//    }
//}
