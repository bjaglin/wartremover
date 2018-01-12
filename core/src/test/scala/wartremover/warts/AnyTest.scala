package org.wartremover
package test

import org.scalatest.FunSuite

import org.wartremover.warts.Any

class AnyTest extends FunSuite with ResultAssertions {
  private def foo: Any = 42

  test("Any can't be inferred") {
    val result = WartTestTraverser(Any) {
      val x = foo
      x
    }
    assertError(result)("Inferred type containing Any: Any")
  }
  test("Any wart obeys SuppressWarnings") {
    val result = WartTestTraverser(Any) {
      @SuppressWarnings(Array("org.wartremover.warts.Any"))
      val x = foo
      x
    }
    assertEmpty(result)
  }
}
