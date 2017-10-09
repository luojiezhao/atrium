package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.contains
import ch.tutteli.atrium.api.cc.en_UK.containsDefaultTranslationOf
import ch.tutteli.atrium.api.cc.en_UK.message
import ch.tutteli.atrium.api.cc.en_UK.toThrow
import ch.tutteli.atrium.assertions.DescriptionNumberAssertion
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe

abstract class NumberAssertionsSpec(
    verbs: IAssertionVerbFactory,
    isLessThanPair: Pair<String, IAssertionPlant<Int>.(Int) -> IAssertionPlant<Int>>,
    isLessOrEqualPair: Pair<String, IAssertionPlant<Int>.(Int) -> IAssertionPlant<Int>>,
    isGreaterThanPair: Pair<String, IAssertionPlant<Int>.(Int) -> IAssertionPlant<Int>>,
    isGreaterOrEqualPair: Pair<String, IAssertionPlant<Int>.(Int) -> IAssertionPlant<Int>>
) : Spek({

    val expect = verbs::checkException
    val (isLessThan, isLessThanFun) = isLessThanPair
    val (isLessOrEqual, isLessOrEqualFun) = isLessOrEqualPair
    val (isGreaterThan, isGreaterThanFun) = isGreaterThanPair
    val (isGreaterOrEqual, isGreaterOrEqualFun) = isGreaterOrEqualPair

    val fluent = verbs.checkImmediately(10)
    context("assert(10)") {
        describe("$isLessThan...") {
            test("... 11 does not throw") {
                fluent.isLessThanFun(11)
            }
            test("... 10 throws an AssertionError containing ${DescriptionNumberAssertion::class.simpleName}.${DescriptionNumberAssertion.IS_LESS_THAN} and `: 10`") {
                expect {
                    fluent.isLessThanFun(10)
                }.toThrow<AssertionError>().and.message {
                    containsDefaultTranslationOf(DescriptionNumberAssertion.IS_LESS_THAN)
                    contains(": 10")
                }
            }
            test("... 9 throws an AssertionError containing ${DescriptionNumberAssertion::class.simpleName}.${DescriptionNumberAssertion.IS_LESS_THAN} and `: 10`") {
                expect {
                    fluent.isLessThanFun(9)
                }.toThrow<AssertionError>().and.message {
                    containsDefaultTranslationOf(DescriptionNumberAssertion.IS_LESS_THAN)
                    contains(": 9")
                }
            }
        }

        describe("$isLessOrEqual...") {
            test("... 11 does not throw") {
                fluent.isLessOrEqualFun(11)
            }
            test("... 10 does not throw") {
                fluent.isLessOrEqualFun(10)
            }
            test("... 9 throws an AssertionError containing ${DescriptionNumberAssertion::class.simpleName}.${DescriptionNumberAssertion.IS_LESS_OR_EQUALS} and `: 10`") {
                expect {
                    fluent.isLessOrEqualFun(9)
                }.toThrow<AssertionError>().and.message {
                    containsDefaultTranslationOf(DescriptionNumberAssertion.IS_LESS_OR_EQUALS)
                    contains(": 9")
                }
            }
        }

        describe("$isGreaterThan ...") {
            test("... 11 throws an AssertionError containing ${DescriptionNumberAssertion::class.simpleName}.${DescriptionNumberAssertion.IS_GREATER_THAN} and `: 11`") {
                expect {
                    fluent.isGreaterThanFun(11)
                }.toThrow<AssertionError>().and.message {
                    containsDefaultTranslationOf(DescriptionNumberAssertion.IS_GREATER_THAN)
                    contains(": 11")
                }
            }
            test("... 10 throws an AssertionError containing ${DescriptionNumberAssertion::class.simpleName}.${DescriptionNumberAssertion.IS_GREATER_THAN} and `: 10`") {
                expect {
                    fluent.isGreaterThanFun(10)
                }.toThrow<AssertionError>().and.message {
                    containsDefaultTranslationOf(DescriptionNumberAssertion.IS_GREATER_THAN)
                    contains(": 10")
                }
            }
            test("... 9 does not throw") {
                fluent.isGreaterThanFun(9)
            }
        }

        describe("$isGreaterOrEqual ...") {
            test("... 11 throws an AssertionError containing ${DescriptionNumberAssertion::class.simpleName}.${DescriptionNumberAssertion.IS_GREATER_OR_EQUALS} and `: 11`") {
                expect {
                    fluent.isGreaterOrEqualFun(11)
                }.toThrow<AssertionError>().and.message {
                    containsDefaultTranslationOf(DescriptionNumberAssertion.IS_GREATER_OR_EQUALS)
                    contains(": 11")
                }
            }
            test("... 10 does not throw") {
                fluent.isGreaterOrEqualFun(10)
            }
            test("... 9 does not throw") {
                fluent.isGreaterOrEqualFun(9)
            }
        }
    }

})