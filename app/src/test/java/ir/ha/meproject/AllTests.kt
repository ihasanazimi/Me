package ir.ha.meproject

import ir.ha.meproject.usecase.UserUseCaseTest1
import ir.ha.meproject.usecase.UserUseCaseTest2
import ir.ha.meproject.validations.BankCardNumberTest
import ir.ha.meproject.validations.IsValidPostalCodeTest
import org.junit.runner.RunWith
import org.junit.runners.Suite


@RunWith(Suite::class)
@Suite.SuiteClasses(
    BankCardNumberTest::class,
    IsValidPostalCodeTest::class,
    UserUseCaseTest1::class,
    UserUseCaseTest2::class
)
class AllTests