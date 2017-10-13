package edu.db.driver.queries

import org.scalatest.FunSuite

class QueryParserTest extends FunSuite {
  private val queryParser = new QueryParser

  test("parsing Sql select query should pass") {
    val selectQuery = queryParser.parseAll(queryParser.select, "Select * from myTable")
    assert(selectQuery != null)
  }

  test("parsing Sql delete query should pass") {
    val deleteQuery = queryParser.parseAll(queryParser.delete, "Delete myTable")
    assert(deleteQuery != null)
  }
}
