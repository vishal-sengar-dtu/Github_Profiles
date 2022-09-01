package com.vishal.kotlin_github.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.vishal.kotlin_github.testutils.CoroutinesTestUtils
import com.vishal.kotlin_github.model.ContributorsItemModel
import com.vishal.kotlin_github.network.NetworkRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.any

@RunWith(JUnit4::class)
class ContributorViewModelTest {
    @get:Rule var rule: TestRule = InstantTaskExecutorRule()
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule val coroutineRule = CoroutinesTestUtils.MainCoroutineRule()

    private lateinit var validUsername: String
    private lateinit var repository: String
    private lateinit var mockContributorResponse: List<ContributorsItemModel>
    private lateinit var viewModel: ContributorViewModel
    private lateinit var mockNetworkRepositoryImpl: NetworkRepositoryImpl

    @Before
    fun setUp() {
        validUsername = "vipinhelloindia"
        repository = "fakeRepository"
        mockContributorResponse = mockk(relaxed = true)
        mockNetworkRepositoryImpl = mockk()
        viewModel = ContributorViewModel(mockNetworkRepositoryImpl)

        coEvery {
            mockNetworkRepositoryImpl.getContributorList(validUsername, repository)
        } returns mockContributorResponse

    }

    @Test
    fun  `show contributors list on API call`() {
        viewModel.username = validUsername
        viewModel.repository = repository
        runBlocking { viewModel.contributorApiCall() }
        assertEquals(mockContributorResponse, viewModel.response.value)
        assertEquals(false, viewModel.failure.value)
    }

}