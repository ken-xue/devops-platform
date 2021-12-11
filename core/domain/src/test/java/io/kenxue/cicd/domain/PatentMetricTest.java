//package io.kenxue.cicd.domain;
//
//import io.kenxue.cicd.domain.metrics.techinfluence.*;
//import User;
//import org.junit.Assert;
//import org.junit.Test;
//
///**
// * PatentMetricTest
// *
// * @date 2019-02-26 4:20 PM
// */
//public class PatentMetricTest {
//
//    @Test
//    public void testPatentMetric(){
//        PatentMetric patentMetric = new PatentMetric(new InfluenceMetric(new User()));
//        patentMetric.addMetricItem(new PatentMetricItem("patentName","patentDesc","patentNo","sharingLink", AuthorType.FIRST_AUTHOR));
//        patentMetric.addMetricItem(new PatentMetricItem("patentName","patentDesc","patentNo","sharingLink", AuthorType.OTHER_AUTHOR));
//
//        Assert.assertEquals(25, patentMetric.calculateScore(), 0.01);
//
//    }
//}
