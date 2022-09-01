package com.vishal.kotlin_github.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.vishal.kotlin_github.testutils.CoroutinesTestUtils
import com.vishal.kotlin_github.model.RepositoryItemModel
import com.vishal.kotlin_github.model.UserModel
import com.vishal.kotlin_github.network.NetworkRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProfileViewModelTest {
    @get:Rule var rule: TestRule = InstantTaskExecutorRule()
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule val coroutineRule = CoroutinesTestUtils.MainCoroutineRule()

    private lateinit var validUsername: String
    private lateinit var inValidUsername: String
    private lateinit var mockUserResponse: UserModel
    private lateinit var mockRepoResponse: List<RepositoryItemModel>
    private lateinit var viewModel: ProfileViewModel
    private lateinit var mockNetworkRepositoryImpl: NetworkRepositoryImpl

    @Before
    fun setUp() {
        validUsername = "vipinhelloindia"
        inValidUsername = "invalid username"

        mockUserResponse = mockk()
        mockRepoResponse = mockk(relaxed = true)

        mockNetworkRepositoryImpl = mockk()
        viewModel = ProfileViewModel(mockNetworkRepositoryImpl)


        coEvery{ mockNetworkRepositoryImpl.getUserObject(validUsername) } returns mockUserResponse

        coEvery{ mockNetworkRepositoryImpl.getUserObject(inValidUsername) } returns null

        coEvery{ mockNetworkRepositoryImpl.getRepositoryList(validUsername) } returns mockRepoResponse

        coEvery{ mockNetworkRepositoryImpl.getRepositoryList(inValidUsername) } returns emptyList()

    }

    @Test
    fun `for invalid username show page not found screen`() {
        viewModel.username = inValidUsername
        runBlocking { viewModel.userApiCall() }
        assertNull("user response should be null", viewModel.userResponse.value)
        assertEquals(true, viewModel.userFailure.value)
    }

    @Test
    fun `for valid username show user profile page`() {
        viewModel.username = validUsername
        runBlocking { viewModel.userApiCall() }
        assertEquals(mockUserResponse, viewModel.userResponse.value)
        assertEquals(false, viewModel.userFailure.value)
    }

    @Test
    fun `for invalid username no repositories found should be displayed`() {
        viewModel.username = inValidUsername
        runBlocking { viewModel.repoApiCall() }
        assertEquals(emptyList<RepositoryItemModel>(), viewModel.repoResponse.value)
        assertEquals(true, viewModel.repoFailure.value)
    }

    @Test
    fun `for valid username display repositories`() {
        viewModel.username = validUsername
        runBlocking { viewModel.repoApiCall() }
        assertEquals(mockRepoResponse, viewModel.repoResponse.value)
        assertEquals(false, viewModel.repoFailure.value)
    }

}