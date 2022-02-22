package io.kenxue.cicd.infrastructure.repositoryimpl;

import java.util.Optional;

public class Tester {
    public static void main(String[] args) {
        Integer a= 3;
        Optional<Integer> a1 = Optional.of(a);
        boolean present = a1.isPresent();
        System.out.println(present);
    }
}
