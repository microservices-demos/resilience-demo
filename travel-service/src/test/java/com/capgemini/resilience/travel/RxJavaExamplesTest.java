package com.capgemini.resilience.travel;

import org.junit.Assert;
import org.junit.Test;
import rx.Observable;

import java.util.Arrays;
import java.util.List;

public class RxJavaExamplesTest {

    @Test
    public void mergeIterable() {

        // given
        Observable<String> firstObservable = Observable.just("A", "B", "C");
        Observable<String> secondObservable = Observable.just("1", "2", "3");

        // when
        Observable<String> merged = firstObservable.mergeWith(secondObservable);

        // then
        merged.subscribe(System.out::println);

        List<String> list = merged.toList().toBlocking().single();
        Assert.assertEquals(Arrays.asList("A", "B", "C", "1", "2", "3"), list);
    }

    @Test
    public void mergeThread() {

        // given
        Observable<String> observable1 = createThreadObservable1();
        Observable<String> observable2 = createThreadObservable2();

        // when
        Observable<String> merged = observable1.mergeWith(observable2);

        // then
        merged.subscribe(System.out::println);

        List<String> list = merged.toList().toBlocking().single();
        Assert.assertEquals(Arrays.asList("1", "A", "B", "2", "3", "C"), list);
    }

    @Test
    public void zipWithThread() {

        // given
        Observable<String> observable1 = createThreadObservable1();
        Observable<String> observable2 = createThreadObservable2();

        // when
        Observable<String> merged = observable1.zipWith(observable2, (o1, o2) -> o1 + o2);

        // then
        merged.subscribe(System.out::println);

        List<String> list = merged.toList().toBlocking().single();
        Assert.assertEquals(Arrays.asList("1A", "2B", "3C"), list);
    }

    @Test
    public void concatThread() {

        // given
        Observable<String> observable1 = createThreadObservable1();
        Observable<String> observable2 = createThreadObservable2();

        // when
        Observable<String> merged = Observable.concat(observable1, observable2);

        // then
        merged.subscribe(System.out::println);

        List<String> list = merged.toList().toBlocking().single();
        Assert.assertEquals(Arrays.asList("1", "2", "3", "A", "B", "C"), list);
    }

    @Test
    public void complex() {
        // given
        Observable<String> observable1 = createThreadObservable1();
        Observable<String> observable2 = createThreadObservable2();

        // when
        Observable<String> concatenatedNumbersWithoutFirst = observable1
                .skip(1)
                .collect(StringBuilder::new, (sb, s) -> sb.append(s))
                .map(StringBuilder::toString);

        Observable<String> concatenatedFirst2Strings = observable2.take(2).reduce((a, b) -> a + b);

        Observable<String> firstResult = concatenatedFirst2Strings.mergeWith(concatenatedNumbersWithoutFirst).first();

        // then
        concatenatedNumbersWithoutFirst.subscribe(System.out::println);
        concatenatedFirst2Strings.subscribe(System.out::println);
        firstResult.subscribe(System.out::println);


        Assert.assertEquals("AB", firstResult.single().toBlocking().first());
    }

    private Observable<String> createThreadObservable1() {
        return Observable.create(s -> {

            new Thread(() -> {
                s.onStart();
                sleep(10);
                s.onNext("1");
                sleep(20);
                s.onNext("2");
                sleep(30);
                s.onNext("3");
                s.onCompleted();
            }).start();
        });
    }

    private Observable<String> createThreadObservable2() {
        return Observable.create(s -> {
            new Thread(() -> {
                s.onStart();
                sleep(15);
                s.onNext("A");
                sleep(5);
                s.onNext("B");
                sleep(50);
                s.onNext("C");
                s.onCompleted();
            }).start();
        });
    }

    private void sleep(long milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
