/*
 * Copyright (C) 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.kotlincoroutines.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.android.kotlincoroutines.fakes.IMainNetworkServiceFake
import com.example.android.kotlincoroutines.fakes.ITitleDaoFake
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

class TitleRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Test
    fun whenRefreshTitleSuccess_insertsRows() = runBlockingTest {
        val titleDao = ITitleDaoFake("title")

        val subject = TitleRepository(
                IMainNetworkServiceFake("OK"),
                titleDao)

        subject.refreshTitle()
        Truth.assertThat(titleDao.nextInsertedOrNull()).isEqualTo("OK")

    }

    @Test(expected = TitleRefreshError::class)
    fun whenRefreshTitleTimeout_throws() {
        // TODO: Write this test
        throw TitleRefreshError("Remove this – made test pass in starter code", null)
    }
}