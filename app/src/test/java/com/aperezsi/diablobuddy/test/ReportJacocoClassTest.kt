package com.aperezsi.diablobuddy.test

import com.aperezsi.diablobuddy.ReportJacocoClass
import org.junit.Assert.assertEquals
import org.junit.Test

class ReportJacocoClassTest {
    private val reportJacocoClass = ReportJacocoClass()

    @Test
    fun `test hola`() {
        assertEquals("test",reportJacocoClass.hola())
    }
}
