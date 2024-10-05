package ir.ha.meproject.samples

import ir.ha.meproject.data.repository.SampleRepositoryImpl
import ir.ha.meproject.domain.SampleUseCaseImpl
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.Mockito


/** JUnit5*/

/*---------------------------------------------------------------------------*/


@RunWith(value = Parameterized::class)
class StringHelperParameterTest0 (private val input: Int, private val expectedValue: Boolean){


    companion object {

        private lateinit var repo: SampleRepositoryImpl
        private lateinit var useCase: SampleUseCaseImpl

        @JvmStatic
        @Parameterized.Parameters()
        fun data(): List<Array<Any>> {
            repo = Mockito.spy(SampleRepositoryImpl())
            useCase = Mockito.spy(SampleUseCaseImpl(repo))
            return useCase.getNumberByAnswers()
        }
    }


    @Test
    fun testParameterized_IsPositiveNumberOrNot() {
        val result = useCase.isPositiveNumber(input)
        println("result is $result")
        assertEquals(expectedValue,result)
    }

}



/*---------------------------------------------------------------------------*/


class StringHelperParameterTest1 {

    companion object {

        private lateinit var repo : SampleRepositoryImpl
        private lateinit var useCase : SampleUseCaseImpl

        @JvmStatic
        fun provideTestData() : List<Array<Any>> {
            repo = Mockito.spy(SampleRepositoryImpl())
            useCase = Mockito.spy(SampleUseCaseImpl(repo))
            return useCase.getNumberByAnswers()
        }
    }

    @ParameterizedTest
    @MethodSource("provideTestData")
    fun testIsPositiveNumber(number: Int, expected: Boolean) {
        val result = useCase.isPositiveNumber(number)
        println("number is $number and result is $result")
        Assertions.assertEquals(expected,result)
    }

}



/*---------------------------------------------------------------------------*/




class StringHelperParameterTest2 {

    @ParameterizedTest
    @ValueSource(ints = [-1, 0, 1, 2, 3])
    fun testIsPositiveNumber(number: Int) {
        val repo = Mockito.spy(SampleRepositoryImpl())
        val useCase = Mockito.spy(SampleUseCaseImpl(repo))
        val result = useCase.isPositiveNumber(number)
        println("number is $number and result is $result")
        Assertions.assertEquals(number >= 0 , result)
    }

}


/*---------------------------------------------------------------------------*/


class StringHelperParameterTest3 {

    @ParameterizedTest
    @CsvSource(
        "-1,false",
        "0,true",
        "1,true"
    )
    fun testIsPositiveNumber(input: Int, expected: Boolean) {
        val repo = Mockito.spy(SampleRepositoryImpl())
        val useCase = Mockito.spy(SampleUseCaseImpl(repo))
        val result = useCase.isPositiveNumber(input)
        println("input is $input and result is $result")
        assertEquals(expected, result)
    }
}