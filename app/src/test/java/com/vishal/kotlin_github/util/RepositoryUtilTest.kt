package com.vishal.kotlin_github.util

import io.mockk.mockk
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class RepositoryUtilTest {

    @Test
    fun `return username on receiving fullName`() {
        assertEquals(
            "vipinhelloindia",
            RepositoryUtil.getUsername("vipinhelloindia/MockRepository")
        )
    }

    @Test
    fun `if description in null return empty string`() {
        assertEquals(
            "",
            RepositoryUtil.getDescription(null)
        )
    }

    @Test
    fun `if description length is less than 120 characters return description as it is`() {
        assertEquals(
            "fake description",
            RepositoryUtil.getDescription("fake description")
        )
    }

    @Test
    fun `if description length is greater than 120 characters return description with 120 characters`() {
        assertEquals(
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard  ...",
            RepositoryUtil.getDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s. It has survived not only five centuries. More recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
        )
    }

    @Test
    fun `get last updated string`() {
        assertEquals("Updated 4 year ago", RepositoryUtil.getLastUpdated("2018-04-18T00:04:10Z"))
        assertEquals("Updated 5 month ago", RepositoryUtil.getLastUpdated("2022-04-18T00:04:10Z"))
        assertEquals("Updated 4 day ago", RepositoryUtil.getLastUpdated("2022-09-01T00:04:10Z"))
        assertEquals("Updated 2 hour ago", RepositoryUtil.getLastUpdated("2022-09-05T11:04:10Z"))
        assertEquals("Updated 10 minute ago", RepositoryUtil.getLastUpdated("2022-09-05T13:08:10Z"))
    }
}
