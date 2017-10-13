package edu.db.driver.queries

import edu.db.driver.data.dml.{Delete, Select}

import scala.util.parsing.combinator.RegexParsers

class QueryParser extends RegexParsers {
  private def name = "\\w+".r
  private def cols: Parser[List[String]] = name ~ opt("," ~ cols) ^^ {
    case c ~ None => List(c)
    case c ~ Some("," ~ cs) => c :: cs
  }

  def select: Parser[Select] = "Select" ~ (cols | "*") ~ "from" ~ name ^^ {
    case "Select" ~ "*" ~ "from" ~ n => new Select(n)
    case "Select" ~ (cs: List[String]) ~ "from" ~ n => new Select(n, Some(cs))
  }

  def delete: Parser[Delete] = "Delete" ~ name ^^ { case _ ~ n => new Delete(n) }
}
