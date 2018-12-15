package com.udacity.gradle.builditbigger;


import android.content.Context;
import android.support.v4.util.Pair;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AsyncTaskTest {

    @Test
    public void directTaskAccess() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        final String []s=new String[1];
        new MyAsyncTask(new MyAsyncTaskCallback() {
            @Override
            public void OnDone(String result) {
                s[0] = result;

            }
        }).execute() ;
        latch.await(5000, TimeUnit.MILLISECONDS);
        assertNotNull(s[0]);
        assertEquals("This is totally a funny joke", s[0]);
        latch.countDown();
    }
}